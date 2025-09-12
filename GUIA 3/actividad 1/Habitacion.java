package reservas;

import java.util.List;
import java.util.ArrayList;

public class Habitacion {
    private int id;
    private String tipo;
    private double precioBase;
    private boolean disponible;
    private List<Reserva> reservas;
    private GestorDisponibilidadHabitacion gestorDisponibilidad;

    public Habitacion(int id, String tipo, double precioBase) {
        this.id = id;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
        this.reservas = new ArrayList<>(); // 🔹 Inicialización obligatoria
        this.gestorDisponibilidad = new GestorDisponibilidadHabitacion();
    }

    // Delegación al Gestor
    public boolean verificarDisponibilidad(String fechaInicio, String fechaFin) {
        return gestorDisponibilidad.verificarDisponibilidad(this, fechaInicio, fechaFin);
    }

    public void marcarComoReservada() {
        gestorDisponibilidad.marcarComoReservada(this);
    }

    public void marcarComoDisponible() {
        gestorDisponibilidad.marcarComoDisponible(this);
    }

    public double calcularPrecio(String temporada) {
        if (temporada.equalsIgnoreCase("alta")) {
            return precioBase * 1.5;
        }
        return precioBase;
    }
    // Getters
    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public double getPrecioBase() { return precioBase; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public List<Reserva> getReservas() { return reservas; }
}
