package mvc3;

import java.util.ArrayList;
import java.util.List;

public class JugadorModel {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private List<InventarioModel.Item> inventario;
    private InventarioModel.Item itemEquipado;

    public JugadorModel(String nombre, int salud, int nivel) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = salud;
        this.nivel = nivel;
        this.inventario = new ArrayList<>();
        this.itemEquipado = null;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }
    public int getNivel() { return nivel; }
    public List<InventarioModel.Item> getInventario() { return inventario; }
    public InventarioModel.Item getItemEquipado() { return itemEquipado; }

    public void equiparItem(InventarioModel.Item item) {
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
        return 10 + (nivel * 2); // Daño base sin arma
    }

    public void recibirDaño(int daño) {
        this.salud = Math.max(0, this.salud - daño);
    }

    public void usarObjeto(String nombreItem) {
        for (InventarioModel.Item item : inventario) {
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

    public void agregarAlInventario(InventarioModel.Item item) {
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