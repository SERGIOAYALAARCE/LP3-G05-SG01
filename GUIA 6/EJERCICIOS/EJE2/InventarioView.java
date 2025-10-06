package mvc2;

import java.util.List;
import java.util.Scanner;

public class InventarioView {
    private Scanner scanner;

    public InventarioView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarInventario(List<InventarioModel.Item> items) {
        System.out.println("\n=== INVENTARIO ===");
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
        System.out.println("==================");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDetallesItem(InventarioModel.Item item) {
        if (item != null) {
            System.out.println("\n=== DETALLES DEL ÍTEM ===");
            System.out.println("Nombre: " + item.getNombre());
            System.out.println("Cantidad: " + item.getCantidad());
            System.out.println("Tipo: " + item.getTipo());
            System.out.println("Descripción: " + item.getDescripcion());
            System.out.println("==========================");
        } else {
            System.out.println("Ítem no encontrado");
        }
    }

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIO ===");
        System.out.println("1. Agregar ítem");
        System.out.println("2. Eliminar ítem");
        System.out.println("3. Ver inventario completo");
        System.out.println("4. Buscar ítem por nombre");
        System.out.println("5. Mostrar detalles de ítem");
        System.out.println("6. Usar ítem");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
        
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public InventarioModel.Item solicitarDatosItem() {
        System.out.print("Nombre del ítem: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Tipo (Arma/Poción): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        return new InventarioModel.Item(nombre, cantidad, tipo, descripcion);
    }

    public String solicitarNombreItem() {
        System.out.print("Ingrese el nombre del ítem: ");
        return scanner.nextLine();
    }

    public int solicitarIndiceItem() {
        System.out.print("Ingrese el número del ítem: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void cerrarScanner() {
        scanner.close();
    }
}