package mvc;
import java.util.ArrayList;
public class Controlador {
 private Modelo.Carrito carrito;
 private ArrayList<Modelo.Producto> catalogo;
 private Vista vista;

 public Controlador(Modelo.Carrito carrito, Vista vista) {
     this.carrito = carrito;
     this.vista = vista;
     this.catalogo = new ArrayList<>();
 }
 public void iniciar() {
     int opcion;
     do {
         opcion = vista.mostrarMenu();
         switch (opcion) {
             case 1 -> agregarProductoCatalogo();
             case 2 -> listarCatalogo();
             case 3 -> agregarAlCarrito();
             case 4 -> verCarrito();
             case 5 -> eliminarDelCarrito();
             case 6 -> aplicarDescuento();
             case 7 -> calcularEnvio();
             case 8 -> verHistorial();
             case 9 -> realizarCompra();
             case 0 -> vista.mostrarMensaje("Saliendo del sistema...");
             default -> vista.mostrarMensaje("Opción inválida.");
         }
     } while (opcion != 0);
 }
 private void agregarProductoCatalogo() {
     Modelo.Producto p = vista.leerProducto();
     catalogo.add(p);
     vista.mostrarMensaje("Producto agregado al catálogo.");
 }
 private void listarCatalogo() {
     if (catalogo.isEmpty()) {
         vista.mostrarMensaje("No hay productos en el catálogo.");
         return;
     }
     vista.mostrarMensaje("=== Catálogo ===");
     catalogo.forEach(p -> vista.mostrarMensaje(p.toString()));
 }
 private void agregarAlCarrito() {
     String nombre = vista.leerNombreProducto();
     for (Modelo.Producto p : catalogo) {
         if (p.getNombre().equalsIgnoreCase(nombre)) {
             carrito.agregarProducto(p);
             vista.mostrarMensaje("Producto agregado al carrito.");
             return;
         }
     }
     vista.mostrarMensaje("Producto no encontrado.");
 }
 private void verCarrito() {
     if (carrito.getProductos().isEmpty()) {
         vista.mostrarMensaje("El carrito está vacío.");
         return;
     }
     vista.mostrarMensaje("=== Carrito ===");
     carrito.getProductos().forEach(p -> vista.mostrarMensaje(p.toString()));
     vista.mostrarMensaje("Total: S/ " + carrito.calcularTotal());
 }

 private void eliminarDelCarrito() {
     String nombre = vista.leerNombreProducto();
     carrito.eliminarProducto(nombre);
     vista.mostrarMensaje("Producto eliminado del carrito (si existía).");
 }

 private void aplicarDescuento() {
     double porcentaje = vista.leerDescuento();
     carrito.aplicarDescuento(porcentaje);
     vista.mostrarMensaje("Descuento aplicado correctamente.");
 }

 private void calcularEnvio() {
     double envio = carrito.calcularEnvio();
     vista.mostrarMensaje("Costo de envío: S/ " + envio);
 }

 private void verHistorial() {
     if (carrito.getHistorial().isEmpty()) {
         vista.mostrarMensaje("No hay historial de compras.");
         return;
     }
     vista.mostrarMensaje("=== Historial de compras ===");
     carrito.getHistorial().forEach(vista::mostrarMensaje);
 }

 private void realizarCompra() {
     if (carrito.getProductos().isEmpty()) {
         vista.mostrarMensaje("El carrito está vacío.");
         return;
     }
     carrito.registrarCompra();
     vista.mostrarMensaje("Compra realizada exitosamente.");
 }
}
