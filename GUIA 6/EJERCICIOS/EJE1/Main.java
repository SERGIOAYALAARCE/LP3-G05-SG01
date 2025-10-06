package mvc;

public class Main {
 public static void main(String[] args) {
     Modelo.Carrito carrito = new Modelo.Carrito();
     Vista vista = new Vista();
     Controlador controlador = new Controlador(carrito, vista);
     controlador.iniciar();
 }
}
