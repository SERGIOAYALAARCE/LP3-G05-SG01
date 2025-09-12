package reservas;

public class ControladorReservas {
    private NotificadorReserva notificador;
    
    // Constructor con inyección de dependencia
    public ControladorReservas(NotificadorReserva notificador) {
        this.notificador = notificador;
    }
    
    public Reserva crearReserva(HabitacionBase habitacion, Cliente cliente, String inicio, String fin, 
                               PoliticaCancelacion politica) {
        if (habitacion.verificarDisponibilidad(inicio, fin)) {
            Reserva reserva = new Reserva(inicio, fin, cliente, habitacion, politica);
            habitacion.getReservas().add(reserva);
            habitacion.marcarComoReservada();
            
            System.out.println("Reserva creada para " + cliente.getNombre() + 
                             " en habitación " + habitacion.getId() +
                             " (" + habitacion.getTipo() + ")");
            System.out.println("Política de cancelación: " + politica.getDescripcion());
            
            // Notificar creación de reserva
            notificador.notificarCreacionReserva(reserva);
            
            return reserva;
        } else {
            System.out.println("Habitación no disponible en esas fechas.");
            return null;
        }
    }
    
    public boolean cancelarReserva(Reserva reserva) {
        boolean cancelada = reserva.cancelar();
        if (cancelada) {
            // Notificar cancelación con la penalización calculada
            double penalizacion = reserva.getPoliticaCancelacion().calcularPenalizacion(reserva);
            notificador.notificarCancelacionReserva(reserva, penalizacion);
        }
        return cancelada;
    }
    
    // Método para cambiar el notificador en tiempo de ejecución
    public void setNotificador(NotificadorReserva notificador) {
        this.notificador = notificador;
    }
}