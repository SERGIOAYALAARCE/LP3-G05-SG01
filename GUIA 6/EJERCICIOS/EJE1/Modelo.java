package mvc;
import java.util.ArrayList;
public class Modelo {
 public static class Producto {
     private String nombre;
     private double precio;
     private int stock;
     public Producto(String nombre, double precio, int stock) {
         this.nombre = nombre;
         this.precio = precio;
         this.stock = stock;
     }
     public String getNombre() { return nombre; }
     public double getPrecio() { return precio; }
     public int getStock() { return stock; }
     public void setStock(int stock) { this.stock = stock; }
     @Override
     public String toString() {
         return nombre + " - S/ " + precio + " (Stock: " + stock + ")";
     }
 }
 public static class Carrito {
     private ArrayList<Producto> productos = new ArrayList<>();
     private ArrayList<String> historial = new ArrayList<>();

     public void agregarProducto(Producto p) {
         productos.add(p);
     }
     public void eliminarProducto(String nombre) {
         productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
     }
     public ArrayList<Producto> getProductos() {
         return productos;
     }
     public double calcularTotal() {
         double total = 0;
         for (Producto p : productos) {
             total += p.getPrecio();
         }
         return total;
     }
     public void aplicarDescuento(double porcentaje) {
         for (Producto p : productos) {
             double nuevoPrecio = p.getPrecio() - (p.getPrecio() * porcentaje / 100);
             productos.set(productos.indexOf(p), new Producto(p.getNombre(), nuevoPrecio, p.getStock()));
         }
     }
     public double calcularEnvio() {
         return productos.isEmpty() ? 0 : 10.0;
     }
     public void registrarCompra() {
         if (productos.isEmpty()) return;
         StringBuilder compra = new StringBuilder("Compra realizada: ");
         for (Producto p : productos) {
             compra.append(p.getNombre()).append(", ");
         }
         historial.add(compra.toString());
         productos.clear();
     }
     public ArrayList<String> getHistorial() {
         return historial;
     }
 }
}

