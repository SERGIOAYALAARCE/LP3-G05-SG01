package mvc;

import java.util.Scanner;
public class Vista {
 private Scanner sc = new Scanner(System.in);

 public int mostrarMenu() {
     System.out.println("\n===== MENÚ PRINCIPAL =====");
     System.out.println("1. Agregar producto al catálogo");
     System.out.println("2. Listar productos del catálogo");
     System.out.println("3. Agregar producto al carrito");
     System.out.println("4. Ver carrito");
     System.out.println("5. Eliminar producto del carrito");
     System.out.println("6. Aplicar descuento");
     System.out.println("7. Calcular envío");
     System.out.println("8. Ver historial de compras");
     System.out.println("9. Realizar compra");
     System.out.println("0. Salir");
     System.out.print("Seleccione una opción: ");
     return sc.nextInt();
 }
 public Modelo.Producto leerProducto() {
     System.out.print("Nombre del producto: ");
     sc.nextLine();
     String nombre = sc.nextLine();
     System.out.print("Precio: ");
     double precio = sc.nextDouble();
     System.out.print("Stock: ");
     int stock = sc.nextInt();
     return new Modelo.Producto(nombre, precio, stock);
 }
 public String leerNombreProducto() {
     System.out.print("Ingrese el nombre del producto: ");
     sc.nextLine();
     return sc.nextLine();
 }

 public double leerDescuento() {
     System.out.print("Ingrese porcentaje de descuento: ");
     return sc.nextDouble();
 }

 public void mostrarMensaje(String mensaje) {
     System.out.println(mensaje);
 }
}
