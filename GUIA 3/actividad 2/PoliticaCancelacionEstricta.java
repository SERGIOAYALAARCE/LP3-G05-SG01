package reservas;

public class PoliticaCancelacionEstricta implements PoliticaCancelacion {
    
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        return false; // No permite cancelaciones
    }
    
    @Override
    public double calcularPenalizacion(Reserva reserva) {
        return reserva.getHabitacion().getPrecioBase(); // 100% de penalización
    }
    
    @Override
    public String getDescripcion() {
        return "Cancelación estricta: No permite cancelaciones después de realizar la reserva";
    }
}