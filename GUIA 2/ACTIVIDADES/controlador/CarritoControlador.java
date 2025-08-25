package controlador;

import modelo.CarritoModelo;
import vista.CarritoVista;

public class CarritoControlador {
    private CarritoModelo modelo;
    private CarritoVista vista;

    public CarritoControlador(CarritoModelo modelo, CarritoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            int opcion = vista.mostrarMenu();

            switch (opcion) {
                case 1:
                    String prodAgregar = vista.pedirProducto("agregar");
                    modelo.agregarProducto(prodAgregar);
                    vista.mostrarMensaje("Producto agregado: " + prodAgregar);
                    break;

                case 2:
                    String prodEliminar = vista.pedirProducto("eliminar");
                    modelo.eliminarProducto(prodEliminar);
                    vista.mostrarMensaje("Producto eliminado: " + prodEliminar);
                    break;

                case 3:
                    vista.mostrarCarrito(modelo.getProductos());
                    break;

                case 4:
                    salir = true;
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}
