package reservas;

public class ControladorReservas {

    public Reserva crearReserva(HabitacionBase habitacion, Cliente cliente, String inicio, String fin, 
                               PoliticaCancelacion politica) {
        if (habitacion.verificarDisponibilidad(inicio, fin)) {
            // Aquí se crea la Reserva con HabitacionBase
            Reserva reserva = new Reserva(inicio, fin, cliente, habitacion, politica);
            habitacion.getReservas().add(reserva);
            habitacion.marcarComoReservada();
            System.out.println("Reserva creada para " + cliente.getNombre() + 
                             " en habitación " + habitacion.getId() +
                             " (" + habitacion.getTipo() + ")");
            System.out.println("Política de cancelación: " + politica.getDescripcion());
            return reserva;
        } else {
            System.out.println("Habitación no disponible en esas fechas.");
            return null;
        }
    }

    public boolean cancelarReserva(Reserva reserva) {
        return reserva.cancelar();
    }
}