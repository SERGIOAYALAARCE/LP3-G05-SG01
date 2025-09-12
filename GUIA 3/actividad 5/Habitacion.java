package reservas;

// Habitación estándar - solo limpieza básica
public class Habitacion extends HabitacionBase implements ServicioLimpieza {
    
    public Habitacion(int id, String tipo, double precioBase) {
        super(id, tipo, precioBase);
    }

    @Override
    public double calcularPrecio(String temporada) {
        if (temporada.equalsIgnoreCase("alta")) {
            return getPrecioBase() * 1.5;
        }
        return getPrecioBase();
    }

    // Implementación de ServicioLimpieza
    @Override
    public void solicitarLimpieza() {
        System.out.println("Limpieza solicitada para habitación " + getId());
    }

    @Override
    public void programarLimpiezaRegular(String frecuencia) {
        System.out.println("Limpieza programada " + frecuencia + " para habitación " + getId());
    }

    @Override
    public double calcularCostoLimpieza() {
        return 15.0; // Costo base de limpieza
    }
}