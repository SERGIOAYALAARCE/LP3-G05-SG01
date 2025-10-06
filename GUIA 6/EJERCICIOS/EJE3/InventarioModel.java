package mvc3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    // Clase Jugador
    public static class Jugador {
        private String nombre;
        private int salud;
        private int saludMaxima;
        private int nivel;
        private List<Item> inventario;
        private Item itemEquipado;

        public Jugador(String nombre, int salud, int nivel) {
            this.nombre = nombre;
            this.salud = salud;
            this.saludMaxima = salud;
            this.nivel = nivel;
            this.inventario = new ArrayList<>();
            this.itemEquipado = null;
        }

        public String getNombre() { return nombre; }
        public int getSalud() { return salud; }
        public int getSaludMaxima() { return saludMaxima; }
        public int getNivel() { return nivel; }
        public List<Item> getInventario() { return inventario; }
        public Item getItemEquipado() { return itemEquipado; }

        public void equiparItem(Item item) {
            if (item.getTipo().equals("Arma")) {
                this.itemEquipado = item;
            }
        }

        public int atacar() {
            if (itemEquipado != null) {
                int dañoBase = 10 + (nivel * 2);
                if (itemEquipado.getNombre().toLowerCase().contains("espada")) {
                    return dañoBase + 15;
                } else if (itemEquipado.getNombre().toLowerCase().contains("arco")) {
                    return dañoBase + 10;
                }
            }
            return 10 + (nivel * 2);
        }

        public void recibirDaño(int daño) {
            this.salud = Math.max(0, this.salud - daño);
        }

        public void usarObjeto(String nombreItem) {
            for (Item item : inventario) {
                if (item.getNombre().equalsIgnoreCase(nombreItem)) {
                    if (item.getTipo().equals("Poción")) {
                        int curacion = 30;
                        this.salud = Math.min(saludMaxima, this.salud + curacion);
                        item.usarItem();
                        if (item.getCantidad() == 0) {
                            inventario.remove(item);
                        }
                        return;
                    }
                }
            }
        }

        public void agregarAlInventario(Item item) {
            inventario.add(item);
        }

        public boolean estaVivo() {
            return salud > 0;
        }

        public void subirNivel() {
            nivel++;
            saludMaxima += 20;
            salud = saludMaxima;
        }
    }

    // Clase Enemigo
    public static class Enemigo {
        private String nombre;
        private int salud;
        private int nivel;
        private String tipo;
        private Random random;

        public Enemigo(String nombre, int salud, int nivel, String tipo) {
            this.nombre = nombre;
            this.salud = salud;
            this.nivel = nivel;
            this.tipo = tipo;
            this.random = new Random();
        }

        public String getNombre() { return nombre; }
        public int getSalud() { return salud; }
        public int getNivel() { return nivel; }
        public String getTipo() { return tipo; }

        public int atacar() {
            int dañoBase = 5 + (nivel * 2);
            switch (tipo.toLowerCase()) {
                case "jefe":
                    return dañoBase + 20;
                case "mago":
                    return dañoBase + 15;
                case "arquero":
                    return dañoBase + 10;
                default:
                    return dañoBase + 5;
            }
        }

        public void recibirDaño(int daño) {
            this.salud = Math.max(0, this.salud - daño);
        }

        public boolean estaVivo() {
            return salud > 0;
        }

        public String realizarAccionAleatoria() {
            int accion = random.nextInt(3);
            switch (accion) {
                case 0:
                    return "atacar";
                case 1:
                    return "defender";
                case 2:
                    return "cargar";
                default:
                    return "atacar";
            }
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