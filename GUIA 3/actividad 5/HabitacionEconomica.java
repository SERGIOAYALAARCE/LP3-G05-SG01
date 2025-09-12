package reservas;

// Habitación económica - sin servicios adicionales
public class HabitacionEconomica extends HabitacionBase {
    
    public HabitacionEconomica(int id, double precioBase) {
        super(id, "Económica", precioBase);
    }

    @Override
    public double calcularPrecio(String temporada) {
        // Las habitaciones económicas no tienen recargo por temporada
        return getPrecioBase();
    }
    // No implementa ninguna interfaz de servicios
}