package reservas;

public class Main {
    public static void main(String[] args) {
        // Crear habitaciones
        Habitacion h1 = new Habitacion(101, "Suite", 200.0);
        Habitacion h2 = new Habitacion(102, "Doble", 150.0);

        // Crear clientes
        Cliente c1 = new Cliente("Carlos Perez", "carlos@mail.com");
        Cliente c2 = new Cliente("Ana Lopez", "ana@mail.com");

        // Crear controlador
        ControladorReservas controlador = new ControladorReservas();

        // Crear reservas
        controlador.crearReserva(h1, c1, "2025-09-10", "2025-09-12");
        controlador.crearReserva(h2, c2, "2025-09-11", "2025-09-15");

        // Intentar reservar una habitación ya ocupada
        controlador.crearReserva(h1, c2, "2025-09-10", "2025-09-12");
    }
}
