package reservas;

public class Main {
    public static void main(String[] args) {
        // Crear habitaciones
        Habitacion h1 = new Habitacion(101, "Suite", 200.0);
        Habitacion h2 = new Habitacion(102, "Doble", 150.0);

        // Crear clientes
        Cliente c1 = new Cliente("Carlos Perez", "carlos@mail.com");
        Cliente c2 = new Cliente("Ana Lopez", "ana@mail.com");

        // Crear políticas de cancelación
        PoliticaCancelacion politicaFlexible = new PoliticaCancelacionFlexible();
        PoliticaCancelacion politicaModerada = new PoliticaCancelacionModerada();
        PoliticaCancelacion politicaEstricta = new PoliticaCancelacionEstricta();

        // Crear controlador
        ControladorReservas controlador = new ControladorReservas();

        // Crear reservas con diferentes políticas
        System.out.println("=== CREANDO RESERVAS ===");
        Reserva r1 = controlador.crearReserva(h1, c1, "2025-09-10", "2025-09-12", politicaFlexible);
        Reserva r2 = controlador.crearReserva(h2, c2, "2025-09-11", "2025-09-15", politicaEstricta);

        System.out.println("\n=== INTENTANDO CANCELAR RESERVAS ===");
        // Intentar cancelar reservas
        if (r1 != null) {
            controlador.cancelarReserva(r1);
        }
        
        if (r2 != null) {
            controlador.cancelarReserva(r2);
        }

        // Intentar reservar una habitación ya ocupada
        System.out.println("\n=== INTENTANDO RESERVA EN FECHAS OCUPADAS ===");
        controlador.crearReserva(h1, c2, "2025-09-10", "2025-09-12", politicaModerada);
    }
}