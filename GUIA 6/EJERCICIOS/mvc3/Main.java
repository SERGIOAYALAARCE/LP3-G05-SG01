package mvc3;

public class Main {
    public static void main(String[] args) {
        // Crear los componentes MVC
        InventarioModel modelo = new InventarioModel();
        InventarioView vista = new InventarioView();
        InventarioController controlador = new InventarioController(modelo, vista);
        
        // Iniciar la aplicación
        controlador.iniciar();
    }
}