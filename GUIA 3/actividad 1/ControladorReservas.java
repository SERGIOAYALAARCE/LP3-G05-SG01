package reservas;

public class ControladorReservas {

    public boolean crearReserva(Habitacion habitacion, Cliente cliente, String inicio, String fin) {
        if (habitacion.verificarDisponibilidad(inicio, fin)) {
            Reserva reserva = new Reserva(inicio, fin, cliente);
            habitacion.getReservas().add(reserva);
            habitacion.marcarComoReservada();
            System.out.println("Reserva creada para " + cliente.getNombre());
            return true;
        } else {
            System.out.println("Habitación no disponible en esas fechas.");
            return false;
        }
    }
}
