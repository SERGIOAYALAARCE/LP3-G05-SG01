package reservas;

import java.util.List;
import java.util.ArrayList;

public class Reserva {
    private String fechaInicio;
    private String fechaFin;
    private Cliente cliente;
    private Habitacion habitacion;
    private PoliticaCancelacion politicaCancelacion;
    private boolean cancelada;

    public Reserva(String fechaInicio, String fechaFin, Cliente cliente, Habitacion habitacion, 
                  PoliticaCancelacion politicaCancelacion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.politicaCancelacion = politicaCancelacion;
        this.cancelada = false;
    }

    public boolean cancelar() {
        if (cancelada) {
            System.out.println("La reserva ya está cancelada.");
            return false;
        }
        
        if (politicaCancelacion.puedeCancelar(this)) {
            this.cancelada = true;
            habitacion.marcarComoDisponible();
            System.out.println("Reserva cancelada exitosamente. Penalización: $" + 
                             politicaCancelacion.calcularPenalizacion(this));
            return true;
        } else {
            System.out.println("No es posible cancelar la reserva según la política aplicada.");
            System.out.println("Penalización aplicable: $" + politicaCancelacion.calcularPenalizacion(this));
            return false;
        }
    }

    // Verifica si la reserva se cruza con otra fecha
    public boolean chocaConRango(String inicio, String fin) {
        return this.fechaInicio.equals(inicio) || this.fechaFin.equals(fin);
    }

    // Getters
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public Cliente getCliente() { return cliente; }
    public Habitacion getHabitacion() { return habitacion; }
    public PoliticaCancelacion getPoliticaCancelacion() { return politicaCancelacion; }
    public boolean isCancelada() { return cancelada; }
}