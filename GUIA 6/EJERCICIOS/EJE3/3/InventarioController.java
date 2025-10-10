package mvc3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;
    private Random random;

    // CAMBIO: ahora tenemos varios héroes y un jugador actual
    private List<InventarioModel.Jugador> heroes; 
    private InventarioModel.Jugador jugadorActual; 
    private List<InventarioModel.Enemigo> enemigos;
    private int enemigoActualIndex;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.random = new Random();
        this.enemigoActualIndex = 0;
    }

    // CAMBIO: ahora se crean varios héroes y enemigos desde el inicio
    private void inicializarJugadorYEnemigos() {
        if (this.heroes == null) {
            this.heroes = Arrays.asList(
                new InventarioModel.Jugador("Héroe Guerrero", 100, 1),
                new InventarioModel.Jugador("Héroe Arquero", 80, 1),
                new InventarioModel.Jugador("Héroe Mago", 70, 1)
            );

            // Cada héroe obtiene los mismos ítems del modelo
            for (InventarioModel.Jugador h : heroes) {
                for (InventarioModel.Item item : modelo.obtenerItems()) {
                    h.agregarAlInventario(item);
                }
                equiparPrimerArma(h);
            }

            // Héroe por defecto (si el usuario no elige)
            this.jugadorActual = heroes.get(0);
        }

        // CAMBIO: varios enemigos en el primer nivel
        this.enemigos = Arrays.asList(
            new InventarioModel.Enemigo("Goblin", 40, 1, 3),
            new InventarioModel.Enemigo("Orco", 60, 1, 4),
            new InventarioModel.Enemigo("Trol", 80, 1, 5)
        );
    }

    // CAMBIO: ahora recibe el jugador a equipar
    private void equiparPrimerArma(InventarioModel.Jugador jugador) {
        for (InventarioModel.Item item : jugador.getInventario()) {
            if (item.getTipo().equalsIgnoreCase("Arma")) {
                jugador.equiparItem(item);
                break;
            }
        }
    }

    public void iniciarCombate() {
        inicializarJugadorYEnemigos();

        // CAMBIO: el usuario puede elegir su héroe
        vista.mostrarMensaje("\n=== SELECCIONA TU HÉROE ===");
        for (int i = 0; i < heroes.size(); i++) {
            vista.mostrarMensaje((i + 1) + ". " + heroes.get(i).getNombre() +
                                 " (Salud: " + heroes.get(i).getSalud() + ")");
        }
        vista.mostrarMensaje("Selecciona número: ");
        try {
            int eleccion = Integer.parseInt(vista.obtenerEntrada()) - 1;
            if (eleccion >= 0 && eleccion < heroes.size()) {
                jugadorActual = heroes.get(eleccion);
            } else {
                vista.mostrarMensaje("Selección inválida. Usando héroe por defecto.");
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Entrada no válida. Usando héroe por defecto.");
        }

        if (enemigoActualIndex >= enemigos.size()) {
            vista.mostrarMensaje(" ¡FELICIDADES! Has vencido a todos los enemigos. Eres el campeón.");
            return;
        }

        InventarioModel.Enemigo enemigoActual = enemigos.get(enemigoActualIndex);
        vista.mostrarMensaje("¡COMBATE " + (enemigoActualIndex + 1) + " INICIADO!");
        vista.mostrarMensaje("Te enfrentas a: " + enemigoActual.getNombre() + " (Nivel " + enemigoActual.getNivel() + ")");

        while (jugadorActual.estaVivo() && enemigoActual.estaVivo()) {
            vista.mostrarEstadoCombate(jugadorActual, enemigoActual);
            int accion = vista.mostrarMenuCombate();

            switch (accion) {
                case 1:
                    atacarEnemigo(enemigoActual);
                    break;
                case 2:
                    usarObjetoCombate();
                    break;
                case 3:
                    vista.mostrarInventario(jugadorActual.getInventario());
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

            if (enemigoActual.estaVivo() && jugadorActual.estaVivo()) {
                turnoEnemigo(enemigoActual);
            }
        }

        if (jugadorActual.estaVivo()) {
            vista.mostrarMensaje("¡VICTORIA! Has derrotado a " + enemigoActual.getNombre());
            subirNivelJugador();
            enemigoActualIndex++;

            if (enemigoActualIndex < enemigos.size()) {
                vista.mostrarMensaje(" Preparándote para el próximo desafío...");
                jugadorActual.curar(50);
            }
        } else {
            vista.mostrarMensaje(" Derrota... " + enemigoActual.getNombre() + " te ha vencido.");
        }
    }

    private void subirNivelJugador() {
        jugadorActual = new InventarioModel.Jugador(
            jugadorActual.getNombre(), 100, jugadorActual.getNivel() + 1
        );

        // Reasigna sus ítems al nuevo jugador
        for (InventarioModel.Item item : modelo.obtenerItems()) {
            jugadorActual.agregarAlInventario(item);
        }

        equiparPrimerArma(jugadorActual);
        vista.mostrarMensaje(" ¡HAS SUBIDO AL NIVEL " + jugadorActual.getNivel() + "!");
    }

    private void atacarEnemigo(InventarioModel.Enemigo enemigo) {
        int daño = jugadorActual.atacar();
        enemigo.recibirDaño(daño);
        vista.mostrarResultadoAtaque(jugadorActual.getNombre(), enemigo.getNombre(), daño);

        if (!enemigo.estaVivo()) {
            vista.mostrarMensaje("¡" + enemigo.getNombre() + " ha sido derrotado!");
        }
    }

    private void turnoEnemigo(InventarioModel.Enemigo enemigo) {
        vista.mostrarMensaje("\n=== TURNO DE " + enemigo.getNombre().toUpperCase() + " ===");
        int daño = enemigo.atacar();
        jugadorActual.recibirDaño(daño);
        vista.mostrarResultadoAtaque(enemigo.getNombre(), jugadorActual.getNombre(), daño);
    }

    private void usarObjetoCombate() {
        int indiceObjeto = vista.mostrarMenuObjetos(jugadorActual.getInventario());

        if (indiceObjeto >= 0 && indiceObjeto < jugadorActual.getInventario().size()) {
            InventarioModel.Item item = jugadorActual.getInventario().get(indiceObjeto);
            String tipo = item.getTipo().toLowerCase();

            switch (tipo) {
                case "pocion":
                    int curacion = item.getValorAtaque(); // se usa valorAtaque como valor de curación
                    jugadorActual.curar(curacion);
                    item.usarItem();
                    vista.mostrarCuracion(jugadorActual.getNombre(), curacion);
                    if (item.getCantidad() == 0) jugadorActual.getInventario().remove(item);
                    break;

                case "arma":
                    jugadorActual.equiparItem(item);
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
