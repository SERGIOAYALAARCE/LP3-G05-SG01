package vista;

import java.util.List;
import java.util.Scanner;

public class CarritoVista {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== MENÚ CARRITO ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Eliminar producto");
        System.out.println("3. Mostrar carrito");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public String pedirProducto(String accion) {
        scanner.nextLine(); // limpiar buffer
        System.out.print("Ingrese el producto a " + accion + ": ");
        return scanner.nextLine();
    }

    public void mostrarCarrito(List<String> productos) {
        System.out.println("\n--- Carrito ---");
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            for (String producto : productos) {
                System.out.println("- " + producto);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
