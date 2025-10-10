package mvc3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;
    private Random random;
    private InventarioModel.Jugador jugador;
    private List<InventarioModel.Enemigo> enemigos;
    private int enemigoActualIndex;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.random = new Random();
        this.enemigoActualIndex = 0;
    }
    
    private void inicializarJugadorYEnemigos() {
        // Si el jugador ya existe, no lo reiniciamos
        if (this.jugador == null) {
            this.jugador = new InventarioModel.Jugador("Héroe", 100, 1);

            // Agregar ítems iniciales solo la primera vez
            for (InventarioModel.Item item : modelo.obtenerItems()) {
                jugador.agregarAlInventario(item);
            }

            equiparPrimerArma();
        }

        // Los enemigos sí se reinician en cada combate
        this.enemigos = Arrays.asList(
            new InventarioModel.Enemigo("Enemigo Novato", 50, 1, 3),
            new InventarioModel.Enemigo("Guerro ", 51, 2, 3),
            new InventarioModel.Enemigo("Mago", 52, 3, 3),
            new InventarioModel.Enemigo("Drangon", 53, 4, 3),
            new InventarioModel.Enemigo("Dragón Ancestral", 54, 5, 3)
        );
    }

    
    private void equiparPrimerArma() {
        for (InventarioModel.Item item : jugador.getInventario()) {
            if (item.getTipo().equalsIgnoreCase("Arma")) {
                jugador.equiparItem(item);
                break;
            }
        }
    }

    public void iniciarCombate() {
        inicializarJugadorYEnemigos();
        
        if (enemigoActualIndex >= enemigos.size()) {
            vista.mostrarMensaje(" ¡FELICIDADES! Has vencido a todos los enemigos. Eres el campeón.");
            return;
        }
        
        InventarioModel.Enemigo enemigoActual = enemigos.get(enemigoActualIndex);
        vista.mostrarMensaje("¡COMBATE " + (enemigoActualIndex + 1) + " INICIADO!");
        vista.mostrarMensaje("Te enfrentas a: " + enemigoActual.getNombre() + " (Nivel " + enemigoActual.getNivel() + ")");
        
        while (jugador.estaVivo() && enemigoActual.estaVivo()) {
            vista.mostrarEstadoCombate(jugador, enemigoActual);
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
                    if (intentarHuir()) {
                        vista.mostrarMensaje("Has huido del combate.");
                        return;
                    }
                    break;
                default:
                    vista.mostrarMensaje("Acción no válida.");
            }
            
            if (enemigoActual.estaVivo() && jugador.estaVivo()) {
                turnoEnemigo(enemigoActual);
            }
        }
        
        if (jugador.estaVivo()) {
            vista.mostrarMensaje("¡VICTORIA! Has derrotado a " + enemigoActual.getNombre());
            subirNivelJugador();
            enemigoActualIndex++;
            
            if (enemigoActualIndex < enemigos.size()) {
                vista.mostrarMensaje(" Preparándote para el próximo desafío...");
                jugador.curar(50);
            }
        } else {
            vista.mostrarMensaje(" Derrota... " + enemigoActual.getNombre() + " te ha vencido.");
        }
    }

    private void subirNivelJugador() {
        jugador = new InventarioModel.Jugador(jugador.getNombre(), 100, jugador.getNivel() + 1);
        
        for (InventarioModel.Item item : jugador.getInventario()) {
            jugador.agregarAlInventario(item);
        }
        
        equiparPrimerArma();
        
        vista.mostrarMensaje(" ¡HAS SUBIDO AL NIVEL " + jugador.getNivel() + "!");
    }
    private void atacarEnemigo(InventarioModel.Enemigo enemigo) {
        int daño = jugador.atacar();
        enemigo.recibirDaño(daño);
        vista.mostrarResultadoAtaque(jugador.getNombre(), enemigo.getNombre(), daño);
        
        if (!enemigo.estaVivo()) {
            vista.mostrarMensaje("¡" + enemigo.getNombre() + " ha sido derrotado!");
        }
    }
    private void turnoEnemigo(InventarioModel.Enemigo enemigo) {
        vista.mostrarMensaje("\n=== TURNO DE " + enemigo.getNombre().toUpperCase() + " ===");
        int daño = enemigo.atacar();
        jugador.recibirDaño(daño);
        vista.mostrarResultadoAtaque(enemigo.getNombre(), jugador.getNombre(), daño);
    }

    private void usarObjetoCombate() {
        int indiceObjeto = vista.mostrarMenuObjetos(jugador.getInventario());
        
        if (indiceObjeto >= 0 && indiceObjeto < jugador.getInventario().size()) {
            InventarioModel.Item item = jugador.getInventario().get(indiceObjeto);
            String tipo = item.getTipo().toLowerCase();

            switch (tipo) {
                case "pocion":
                    int curacion = item.getValorAtaque(); // se usa valorAtaque como valor de curación
                    jugador.curar(curacion);
                    item.usarItem();
                    vista.mostrarCuracion(jugador.getNombre(), curacion);
                    if (item.getCantidad() == 0) jugador.getInventario().remove(item);
                    break;
                    
                case "arma":
                    jugador.equiparItem(item);
                    vista.mostrarMensaje("Has equipado: " + item.getNombre());
                    break;

                default:
                    vista.mostrarMensaje("No puedes usar ese tipo de objeto en combate.");
            }
        } else {
            vista.mostrarMensaje("Selección inválida.");
        }
    }
    private boolean intentarHuir() {
        return random.nextBoolean();
    }

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
        vista.mostrarInventario(modelo.obtenerItems());
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
                case 1: agregarItem(); break;
                case 2: eliminarItem(); break;
                case 3: verInventario(); break;
                case 4: buscarItem(); break;
                case 5: mostrarDetalles(); break;
                case 6: usarItem(); break;
                case 7: iniciarCombate(); break;
                case 8: vista.mostrarMensaje("Saliendo del sistema..."); break;
                default: vista.mostrarMensaje("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 8);
        
        vista.cerrarScanner();
    }
}