package reservas;

public class Reserva {
    private String fechaInicio;
    private String fechaFin;
    private Cliente cliente;

    public Reserva(String fechaInicio, String fechaFin, Cliente cliente) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
    }

    // Verifica si la reserva se cruza con otra fecha
    public boolean chocaConRango(String inicio, String fin) {
        return this.fechaInicio.equals(inicio) || this.fechaFin.equals(fin);
    }

    public Cliente getCliente() { return cliente; }
}
