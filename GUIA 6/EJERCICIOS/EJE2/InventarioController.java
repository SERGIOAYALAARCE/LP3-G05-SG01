package mvc2;

import java.util.List;

public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
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

    public void iniciar() {
        // Agregar ítems de ejemplo
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
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 7);
        
        vista.cerrarScanner();
    }
}