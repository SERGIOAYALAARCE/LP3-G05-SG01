package reservas;

public interface PoliticaCancelacion {
    boolean puedeCancelar(Reserva reserva);
    double calcularPenalizacion(Reserva reserva);
    String getDescripcion();
}