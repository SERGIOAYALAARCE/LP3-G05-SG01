package mvc3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;
    private Random random;
    private InventarioModel.Jugador jugador;
    private List<InventarioModel.Enemigo> enemigos;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.random = new Random();
        inicializarJugadorYEnemigos();
    }

    private void inicializarJugadorYEnemigos() {
        // Crear jugador
        this.jugador = new InventarioModel.Jugador("Héroe", 100, 1);
        
        // Agregar items al jugador
        jugador.agregarAlInventario(new InventarioModel.Item("Espada de Acero", 1, "Arma", "Una espada afilada de acero templado"));
        jugador.agregarAlInventario(new InventarioModel.Item("Poción de Vida", 3, "Poción", "Restaura 50 puntos de vida"));
        jugador.agregarAlInventario(new InventarioModel.Item("Arco Largo", 1, "Arma", "Arco de largo alcance"));
        jugador.equiparItem(jugador.getInventario().get(0));
        
        // Crear enemigos
        this.enemigos = Arrays.asList(
            new InventarioModel.Enemigo("Goblin", 50, 1, "normal"),
            new InventarioModel.Enemigo("Orco", 80, 2, "jefe"),
            new InventarioModel.Enemigo("Mago Oscuro", 60, 2, "mago")
        );
    }

    // Métodos originales del inventario
    public void agregarItem() {
        InventarioModel.Item item = vista.solicitarDatosItem();
        modelo.agregarItem(item);
        vista.mostrarMensaje("Ítem agregado exitosamente: " + item.getNombre());
    }

    public void eliminarItem() {
        List<InventarioModel.Item> items = modelo.obtenerItems();
        if (items.isEmpty()) {
            vista.mostrarMensaje("No hay ítems para eliminar");
            return;
        }
        
        vista.mostrarInventario(items);
        int indice = vista.solicitarIndiceItem();
        
        if (modelo.eliminarItem(indice)) {
            vista.mostrarMensaje("Ítem eliminado exitosamente");
        } else {
            vista.mostrarMensaje("Índice inválido");
        }
    }

    public void verInventario() {
        List<InventarioModel.Item> items = modelo.obtenerItems();
        vista.mostrarInventario(items);
    }

    public void buscarItem() {
        String nombre = vista.solicitarNombreItem();
        InventarioModel.Item item = modelo.buscarItem(nombre);
        vista.mostrarDetallesItem(item);
    }

    public void mostrarDetalles() {
        List<InventarioModel.Item> items = modelo.obtenerItems();
        if (items.isEmpty()) {
            vista.mostrarMensaje("No hay ítems en el inventario");
            return;
        }
        
        vista.mostrarInventario(items);
        int indice = vista.solicitarIndiceItem();
        
        if (indice >= 0 && indice < items.size()) {
            vista.mostrarDetallesItem(items.get(indice));
        } else {
            vista.mostrarMensaje("Índice inválido");
        }
    }

    public void usarItem() {
        List<InventarioModel.Item> items = modelo.obtenerItems();
        if (items.isEmpty()) {
            vista.mostrarMensaje("No hay ítems para usar");
            return;
        }
        
        vista.mostrarInventario(items);
        int indice = vista.solicitarIndiceItem();
        
        if (indice >= 0 && indice < items.size()) {
            InventarioModel.Item item = items.get(indice);
            item.usarItem();
        } else {
            vista.mostrarMensaje("Índice inválido");
        }
    }

    // Métodos para el sistema de combate
    public void iniciarCombate() {
        vista.mostrarMensaje("¡COMBATE INICIADO!");
        List<InventarioModel.Enemigo> enemigosCombate = new ArrayList<>(enemigos);
        
        while (jugador.estaVivo() && !enemigosCombate.isEmpty()) {
            // Turno del jugador
            vista.mostrarEstadoCombate(jugador, enemigosCombate);
            int accion = vista.mostrarMenuCombate();
            
            switch (accion) {
                case 1:
                    atacarEnemigo(enemigosCombate);
                    break;
                case 2:
                    usarObjetoCombate();
                    break;
                case 3:
                    vista.mostrarMenuObjetos(jugador.getInventario());
                    break;
                case 4:
                    equiparArmaCombate();
                    break;
                case 5:
                    if (intentarHuir()) {
                        vista.mostrarMensaje("Has huido del combate.");
                        return;
                    }
                    break;
                default:
                    vista.mostrarMensaje("Acción no válida.");
            }
            
            eliminarEnemigosMuertos(enemigosCombate);
            
            if (!enemigosCombate.isEmpty()) {
                turnoEnemigos(enemigosCombate);
            }
        }
        
        boolean victoria = jugador.estaVivo();
        int experiencia = calcularExperiencia(enemigosCombate);
        
        if (victoria) {
            jugador.subirNivel();
            vista.mostrarMensaje("¡Has subido al nivel " + jugador.getNivel() + "!");
        }
        
        vista.mostrarFinCombate(victoria, experiencia);
    }

    private void atacarEnemigo(List<InventarioModel.Enemigo> enemigosCombate) {
        int indiceEnemigo = vista.seleccionarEnemigo(enemigosCombate.size());
        if (indiceEnemigo == -1) {
            vista.mostrarMensaje("Selección de enemigo inválida.");
            return;
        }
        
        InventarioModel.Enemigo enemigo = enemigosCombate.get(indiceEnemigo);
        int daño = jugador.atacar();
        
        boolean critico = random.nextInt(100) < 20;
        if (critico) {
            daño *= 2;
        }
        
        enemigo.recibirDaño(daño);
        vista.mostrarResultadoAtaque(jugador.getNombre(), enemigo.getNombre(), daño, critico);
        
        if (!enemigo.estaVivo()) {
            vista.mostrarMensaje("¡" + enemigo.getNombre() + " ha sido derrotado!");
        }
    }

    private void usarObjetoCombate() {
        List<InventarioModel.Item> inventario = jugador.getInventario();
        int indiceObjeto = vista.mostrarMenuObjetos(inventario);
        
        if (indiceObjeto >= 0 && indiceObjeto < inventario.size()) {
            InventarioModel.Item item = inventario.get(indiceObjeto);
            if (item.getTipo().equals("Poción")) {
                jugador.usarObjeto(item.getNombre());
                vista.mostrarCuracion(jugador.getNombre(), 30);
            } else {
                vista.mostrarMensaje("No puedes usar ese objeto en combate.");
            }
        }
    }

    private void equiparArmaCombate() {
        List<InventarioModel.Item> inventario = jugador.getInventario();
        int indiceArma = vista.mostrarMenuArmas(inventario);
        if (indiceArma >= 0) {
            List<InventarioModel.Item> armas = new ArrayList<>();
            for (InventarioModel.Item item : inventario) {
                if (item.getTipo().equals("Arma")) {
                    armas.add(item);
                }
            }
            if (indiceArma < armas.size()) {
                jugador.equiparItem(armas.get(indiceArma));
                vista.mostrarMensaje("Has equipado: " + armas.get(indiceArma).getNombre());
            }
        }
    }

    private boolean intentarHuir() {
        return random.nextBoolean();
    }

    private void turnoEnemigos(List<InventarioModel.Enemigo> enemigosCombate) {
        vista.mostrarMensaje("\n=== TURNO DE LOS ENEMIGOS ===");
        
        for (InventarioModel.Enemigo enemigo : enemigosCombate) {
            if (enemigo.estaVivo()) {
                String accion = enemigo.realizarAccionAleatoria();
                
                switch (accion) {
                    case "atacar":
                        int daño = enemigo.atacar();
                        jugador.recibirDaño(daño);
                        vista.mostrarResultadoAtaque(enemigo.getNombre(), jugador.getNombre(), daño, false);
                        break;
                    case "defender":
                        vista.mostrarMensaje(enemigo.getNombre() + " se defiende.");
                        break;
                    case "cargar":
                        vista.mostrarMensaje(enemigo.getNombre() + " está cargando un ataque poderoso.");
                        break;
                }
                
                if (!jugador.estaVivo()) {
                    break;
                }
            }
        }
    }

    private void eliminarEnemigosMuertos(List<InventarioModel.Enemigo> enemigosCombate) {
        enemigosCombate.removeIf(enemigo -> !enemigo.estaVivo());
    }

    private int calcularExperiencia(List<InventarioModel.Enemigo> enemigosCombate) {
        int experiencia = 0;
        for (InventarioModel.Enemigo enemigo : enemigos) {
            if (!enemigo.estaVivo()) {
                experiencia += enemigo.getNivel() * 10;
            }
        }
        return experiencia;
    }

    public void iniciar() {
        // Agregar ítems de ejemplo al modelo principal
        modelo.agregarItem(new InventarioModel.Item("Espada de Acero", 1, "Arma", "Una espada afilada de acero templado"));
        modelo.agregarItem(new InventarioModel.Item("Poción de Vida", 3, "Poción", "Restaura 50 puntos de vida"));
        modelo.agregarItem(new InventarioModel.Item("Arco Largo", 1, "Arma", "Arco de largo alcance"));

        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarItem();
                    break;
                case 2:
                    eliminarItem();
                    break;
                case 3:
                    verInventario();
                    break;
                case 4:
                    buscarItem();
                    break;
                case 5:
                    mostrarDetalles();
                    break;
                case 6:
                    usarItem();
                    break;
                case 7:
                    iniciarCombate();
                    break;
                case 8:
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 8);
        
        vista.cerrarScanner();
    }
}