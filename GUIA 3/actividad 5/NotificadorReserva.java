package reservas;

public class NotificadorReserva {
    private CanalNotificacion canalNotificacion;
    
    // Inyección de dependencia por constructor
    public NotificadorReserva(CanalNotificacion canalNotificacion) {
        this.canalNotificacion = canalNotificacion;
    }
    
    // Método para cambiar el canal de notificación en tiempo de ejecución
    public void setCanalNotificacion(CanalNotificacion canalNotificacion) {
        this.canalNotificacion = canalNotificacion;
    }
    
    public void notificarCreacionReserva(Reserva reserva) {
        String mensaje = "✅ Reserva confirmada - Habitación: " + reserva.getHabitacion().getId() +
                       " - Fechas: " + reserva.getFechaInicio() + " al " + reserva.getFechaFin() +
                       " - Total: $" + reserva.getHabitacion().calcularPrecio("normal");
        
        enviarNotificacion(reserva.getCliente().getEmail(), mensaje);
    }
    
    public void notificarCancelacionReserva(Reserva reserva, double penalizacion) {
        String mensaje = "❌ Reserva cancelada - Habitación: " + reserva.getHabitacion().getId() +
                       " - Penalización aplicada: $" + penalizacion +
                       " - Fechas: " + reserva.getFechaInicio() + " al " + reserva.getFechaFin();
        
        enviarNotificacion(reserva.getCliente().getEmail(), mensaje);
    }
    
    public void notificarRecordatorioCheckin(Reserva reserva) {
        String mensaje = "⏰ Recordatorio - Check-in mañana para habitación: " + reserva.getHabitacion().getId() +
                       " - Fechas: " + reserva.getFechaInicio() + " al " + reserva.getFechaFin();
        
        enviarNotificacion(reserva.getCliente().getEmail(), mensaje);
    }
    
    // Cambiado de private a public para poder usarlo desde Main
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("=== Notificación vía " + canalNotificacion.getTipoCanal() + " ===");
        canalNotificacion.enviarNotificacion(destinatario, mensaje);
    }
    
    // Método público para obtener el tipo de canal (opcional)
    public String getTipoCanal() {
        return canalNotificacion.getTipoCanal();
    }
}