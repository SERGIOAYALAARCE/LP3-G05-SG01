package modelo;

import java.util.ArrayList;
import java.util.List;

public class CarritoModelo {
    private List<String> productos;

    public CarritoModelo() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(String producto) {
        productos.add(producto);
    }

    public void eliminarProducto(String producto) {
        productos.remove(producto);
    }

    public List<String> getProductos() {
        return productos;
    }
}
