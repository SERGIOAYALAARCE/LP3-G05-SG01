package mvc3;

import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private List<Item> items;
    
    public static class Item {
        private String nombre;
        private int cantidad;
        private String tipo;
        private int valorAtaque;

        public Item(String nombre, int cantidad, String tipo, int valorAtaque) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.tipo = tipo;
            this.valorAtaque = valorAtaque;
        }

        public String getNombre() { return nombre; }
        public int getCantidad() { return cantidad; }
        public String getTipo() { return tipo; }
        public int getValorAtaque() { return valorAtaque; }
        
        public void usarItem() {
            if (cantidad > 0) cantidad--;
        }

        @Override
        public String toString() {
            return nombre + " | Cantidad: " + cantidad + " | Tipo: " + tipo + " | Ataque: " + valorAtaque;
        }
    }

    public static class Jugador {
        private String nombre;
        private int salud;
        private int nivel;
        private List<Item> inventario;
        private Item itemEquipado;

        public Jugador(String nombre, int salud, int nivel) {
            this.nombre = nombre;
            this.salud = salud;
            this.nivel = nivel;
            this.inventario = new ArrayList<>();
            this.itemEquipado = null;
        }

        public String getNombre() { return nombre; }
        public int getSalud() { return salud; }
        public int getNivel() { return nivel; }
        public List<Item> getInventario() { return inventario; }
        public Item getItemEquipado() { return itemEquipado; }

        public void equiparItem(Item item) {
            if (item.getTipo().equals("Arma")) {
                this.itemEquipado = item;
            }
        }

        public int atacar() {
            int ataqueBase = nivel;
            return itemEquipado != null ? ataqueBase + itemEquipado.getValorAtaque() : ataqueBase;
        }

        public void recibirDaño(int daño) {
            this.salud = Math.max(0, this.salud - daño);
        }

        public void curar(int curacion) {
            this.salud += curacion;
        }

        public void agregarAlInventario(Item item) {
            inventario.add(item);
        }

        public boolean estaVivo() {
            return salud > 0;
        }
    }

    public static class Enemigo {
        private String nombre;
        private int salud;
        private int nivel;
        private int valorAtaque;

        public Enemigo(String nombre, int salud, int nivel, int valorAtaque) {
            this.nombre = nombre;
            this.salud = salud;
            this.nivel = nivel;
            this.valorAtaque = valorAtaque;
        }

        public String getNombre() { return nombre; }
        public int getSalud() { return salud; }
        public int getNivel() { return nivel; }
        public int getValorAtaque() { return valorAtaque; }

        public int atacar() {
            return valorAtaque;
        }

        public void recibirDaño(int daño) {
            this.salud = Math.max(0, this.salud - daño);
        }

        public boolean estaVivo() {
            return salud > 0;
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