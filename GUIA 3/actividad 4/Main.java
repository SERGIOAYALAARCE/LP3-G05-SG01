package reservas;

public class Main {
    public static void main(String[] args) {
        // Crear diferentes tipos de habitaciones con diferentes servicios
        HabitacionBase habitacionSimple = new Habitacion(101, "Doble", 150.0);
        Suite suiteLujo = new Suite(201, 300.0, true, true);
        HabitacionEconomica economica = new HabitacionEconomica(301, 100.0);

        // Crear controladores
        ControladorReservas controladorReservas = new ControladorReservas();
        ControladorServicios controladorServicios = new ControladorServicios();

        // Crear cliente
        Cliente cliente = new Cliente("Juan García", "juan@mail.com");

        System.out.println("=== DEMOSTRACIÓN PRINCIPIO ISP ===");

        // Mostrar servicios disponibles para cada habitación
        controladorServicios.mostrarServiciosDisponibles(habitacionSimple);
        controladorServicios.mostrarServiciosDisponibles(suiteLujo);
        controladorServicios.mostrarServiciosDisponibles(economica);

        System.out.println("\n=== SOLICITANDO SERVICIOS ===");

        // Solicitar servicios según disponibilidad
        controladorServicios.solicitarServicioLimpieza(habitacionSimple);
        controladorServicios.solicitarServicioComida(habitacionSimple, "desayuno");

        controladorServicios.solicitarServicioLimpieza(suiteLujo);
        controladorServicios.solicitarServicioComida(suiteLujo, "cena");
        controladorServicios.solicitarServicioLavanderia(suiteLujo, 5);
        controladorServicios.solicitarServicioSpa(suiteLujo, "masaje", "relajante");

        controladorServicios.solicitarServicioLimpieza(economica);
        controladorServicios.solicitarServicioComida(economica, "almuerzo");

        System.out.println("\n=== CREANDO RESERVAS ===");
        PoliticaCancelacion politicaFlexible = new PoliticaCancelacionFlexible();
        
        Reserva r1 = controladorReservas.crearReserva(habitacionSimple, cliente, "2025-09-10", "2025-09-12", politicaFlexible);
        Reserva r2 = controladorReservas.crearReserva(suiteLujo, cliente, "2025-09-15", "2025-09-20", politicaFlexible);
    }
}