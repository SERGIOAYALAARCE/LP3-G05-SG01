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
    private int enemigoActualIndex; // ✅ NUEVO: Controla qué enemigo enfrentar

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.random = new Random();
        this.enemigoActualIndex = 0; // ✅ Empieza con el primer enemigo
        inicializarJugadorYEnemigos();
    }

    private void inicializarJugadorYEnemigos() {
        // Crear jugador
        this.jugador = new InventarioModel.Jugador("Héroe", 100, 1);
        
        // Agregar items al jugador
        jugador.agregarAlInventario(new InventarioModel.Item("Espada de Acero", 1, "Arma", 5));
        jugador.agregarAlInventario(new InventarioModel.Item("Poción de Vida", 3, "Poción", 0));
        jugador.agregarAlInventario(new InventarioModel.Item("Arco Largo", 1, "Arma", 3));
        jugador.equiparItem(jugador.getInventario().get(0));
        
        // ✅ CREAR ENEMIGOS PROGRESIVOS (más fuertes cada uno)
        this.enemigos = Arrays.asList(
            new InventarioModel.Enemigo("Goblin Novato", 30, 1, 3),    // Nivel 1
            new InventarioModel.Enemigo("Orco Guerrero", 50, 2, 6),    // Nivel 2  
            new InventarioModel.Enemigo("Mago Oscuro", 70, 3, 9),      // Nivel 3
            new InventarioModel.Enemigo("Caballero Negro", 90, 4, 12), // Nivel 4
            new InventarioModel.Enemigo("Dragón Ancestral", 120, 5, 15) // Nivel 5 (Jefe Final)
        );
    }

    // ✅ MÉTODO MODIFICADO: Solo un enemigo por combate
    public void iniciarCombate() {
        // Verificar si ya se vencieron todos los enemigos
        if (enemigoActualIndex >= enemigos.size()) {
            vista.mostrarMensaje("🎉 ¡FELICIDADES! Has vencido a todos los enemigos. Eres el campeón.");
            return;
        }
        
        InventarioModel.Enemigo enemigoActual = enemigos.get(enemigoActualIndex);
        vista.mostrarMensaje("¡COMBATE " + (enemigoActualIndex + 1) + " INICIADO!");
        vista.mostrarMensaje("Te enfrentas a: " + enemigoActual.getNombre() + " (Nivel " + enemigoActual.getNivel() + ")");
        
        while (jugador.estaVivo() && enemigoActual.estaVivo()) {
            // Turno del jugador
            vista.mostrarEstadoCombateIndividual(jugador, enemigoActual);
            int accion = vista.mostrarMenuCombate();
            
            switch (accion) {
                case 1:
                    atacarEnemigo(enemigoActual);
                    break;
                case 2:
                    usarObjetoCombate();
                    break;
                case 3:
                    vista.mostrarInventario(jugador.getInventario());
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
            
            // Turno del enemigo si sigue vivo
            if (enemigoActual.estaVivo() && jugador.estaVivo()) {
                turnoEnemigo(enemigoActual);
            }
        }
        
        if (jugador.estaVivo()) {
            // ✅ JUGADOR GANA - Sube de nivel y pasa al siguiente enemigo
            vista.mostrarMensaje("¡VICTORIA! Has derrotado a " + enemigoActual.getNombre());
            subirNivelJugador();
            enemigoActualIndex++; // Pasar al siguiente enemigo
            
            if (enemigoActualIndex < enemigos.size()) {
                vista.mostrarMensaje("🆙 Preparándote para el próximo desafío...");
                jugador.curar(50); // Cura entre combates
            }
        } else {
            vista.mostrarMensaje("💀 Derrota... " + enemigoActual.getNombre() + " te ha vencido.");
        }
    }

    // ✅ NUEVO MÉTODO: Subir nivel del jugador al vencer enemigo
    private void subirNivelJugador() {
        jugador = new InventarioModel.Jugador(jugador.getNombre(), 100, jugador.getNivel() + 1);
        
        // Mantener el inventario y arma equipada
        for (InventarioModel.Item item : jugador.getInventario()) {
            jugador.agregarAlInventario(item);
        }
        
        // Re-equipar el arma si había una
        if (!jugador.getInventario().isEmpty()) {
            for (InventarioModel.Item item : jugador.getInventario()) {
                if (item.getTipo().equals("Arma")) {
                    jugador.equiparItem(item);
                    break;
                }
            }
        }
        
        vista.mostrarMensaje("🎊 ¡HAS SUBIDO AL NIVEL " + jugador.getNivel() + "!");
        vista.mostrarMensaje("💪 Tu ataque ahora es más poderoso");
    }

    // ✅ MÉTODO MODIFICADO: Atacar un enemigo específico
    private void atacarEnemigo(InventarioModel.Enemigo enemigo) {
        int daño = jugador.atacar();
        enemigo.recibirDaño(daño);
        vista.mostrarResultadoAtaque(jugador.getNombre(), enemigo.getNombre(), daño);
        
        if (!enemigo.estaVivo()) {
            vista.mostrarMensaje("¡" + enemigo.getNombre() + " ha sido derrotado!");
        }
    }

    // ✅ NUEVO MÉTODO: Turno de un solo enemigo
    private void turnoEnemigo(InventarioModel.Enemigo enemigo) {
        vista.mostrarMensaje("\n=== TURNO DE " + enemigo.getNombre().toUpperCase() + " ===");
        int daño = enemigo.atacar();
        jugador.recibirDaño(daño);
        vista.mostrarResultadoAtaque(enemigo.getNombre(), jugador.getNombre(), daño);
    }

    // Los demás métodos se mantienen igual...
    private void usarObjetoCombate() {
        int indiceObjeto = vista.mostrarMenuObjetos(jugador.getInventario());
        
        if (indiceObjeto >= 0 && indiceObjeto < jugador.getInventario().size()) {
            InventarioModel.Item item = jugador.getInventario().get(indiceObjeto);
            if (item.getTipo().equals("Poción")) {
                jugador.curar(30);
                item.usarItem();
                vista.mostrarCuracion(jugador.getNombre(), 30);
                
                if (item.getCantidad() == 0) {
                    jugador.getInventario().remove(item);
                }
            } else {
                vista.mostrarMensaje("No puedes usar ese objeto en combate.");
            }
        }
    }

    private void equiparArmaCombate() {
        int indiceArma = vista.mostrarMenuArmas(jugador.getInventario());
        if (indiceArma >= 0) {
            List<InventarioModel.Item> armas = new ArrayList<>();
            for (InventarioModel.Item item : jugador.getInventario()) {
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

    // Resto de métodos del inventario (se mantienen igual)...
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
            vista.mostrarMensaje("Usado: " + item.getNombre() + ". Cantidad restante: " + item.getCantidad());
        } else {
            vista.mostrarMensaje("Índice inválido");
        }
    }

    public void iniciar() {
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