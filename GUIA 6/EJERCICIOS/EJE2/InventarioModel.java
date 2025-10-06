package mvc2;

import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private List<Item> items;
    
    public static class Item {
        private String nombre;
        private int cantidad;
        private String tipo;
        private String descripcion;

        public Item(String nombre, int cantidad, String tipo, String descripcion) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.tipo = tipo;
            this.descripcion = descripcion;
        }

        public String getNombre() { return nombre; }
        public int getCantidad() { return cantidad; }
        public String getTipo() { return tipo; }
        public String getDescripcion() { return descripcion; }
        
        public void usarItem() {
            if (cantidad > 0) {
                cantidad--;
                System.out.println("Usando " + nombre + ". Cantidad restante: " + cantidad);
            } else {
                System.out.println("No queda " + nombre + " en el inventario");
            }
        }

        @Override
        public String toString() {
            return nombre + " | Cantidad: " + cantidad + " | Tipo: " + tipo + " | " + descripcion;
        }
    }

    public InventarioModel() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        items.add(item);
    }

    public boolean eliminarItem(int indice) {
        if (indice >= 0 && indice < items.size()) {
            items.remove(indice);
            return true;
        }
        return false;
    }

    public List<Item> obtenerItems() {
        return new ArrayList<>(items);
    }

    public Item buscarItem(String nombre) {
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }
        return null;
    }
}