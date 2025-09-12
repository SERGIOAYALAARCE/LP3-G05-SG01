package reservas;
import java.util.List;
import java.util.ArrayList;
// Clase base abstracta para todas las habitaciones
public abstract class HabitacionBase {
    private int id;
    private String tipo;
    private double precioBase;
    private boolean disponible;
    private List<Reserva> reservas;
    private GestorDisponibilidadHabitacion gestorDisponibilidad;

    public HabitacionBase(int id, String tipo, double precioBase) {
        this.id = id;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
        this.reservas = new ArrayList<>();
        this.gestorDisponibilidad = new GestorDisponibilidadHabitacion();
    }

    // Métodos que todas las habitaciones deben implementar
    public abstract double calcularPrecio(String temporada);
    
    // Métodos comunes que se comportan igual para todas las habitaciones
    public boolean verificarDisponibilidad(String fechaInicio, String fechaFin) {
        return gestorDisponibilidad.verificarDisponibilidad(this, fechaInicio, fechaFin);
    }

    public void marcarComoReservada() {
        gestorDisponibilidad.marcarComoReservada(this);
        this.disponible = false;
    }

    public void marcarComoDisponible() {
        gestorDisponibilidad.marcarComoDisponible(this);
        this.disponible = true;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public double getPrecioBase() { return precioBase; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public List<Reserva> getReservas() { return reservas; }
}