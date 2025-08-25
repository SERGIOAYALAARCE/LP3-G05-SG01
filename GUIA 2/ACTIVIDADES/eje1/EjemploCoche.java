package eje1;

public class EjemploCoche {
    public static void main(String[] args) {
        // Crear objetos coche
        Coche cocheDeportivo = new Coche("Ferrari", "F8", 2008, 200000);
        Coche cocheTodoTerreno = new Coche("Toyota", "Land Cruiser", 2015, 50000);

        // Probar descuento
        cocheDeportivo.aplicarDescuento(5000);
        cocheTodoTerreno.aplicarDescuento(5000);

        // Encender los coches
        cocheDeportivo.encender();
        cocheTodoTerreno.encender();

        // Acelerar y frenar
        cocheDeportivo.acelerar();
        cocheTodoTerreno.acelerar();
        cocheDeportivo.frenar();
        cocheTodoTerreno.frenar();

        // Apagar los coches
        cocheDeportivo.apagar();
        cocheTodoTerreno.apagar();
    }
}
