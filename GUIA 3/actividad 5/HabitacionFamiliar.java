package reservas;

// Habitación familiar con capacidad para más personas
public class HabitacionFamiliar extends HabitacionBase {
    private int capacidad;
    private boolean tieneCuna;

    public HabitacionFamiliar(int id, double precioBase, int capacidad, boolean tieneCuna) {
        super(id, "Familiar", precioBase);
        this.capacidad = capacidad;
        this.tieneCuna = tieneCuna;
    }

    @Override
    public double calcularPrecio(String temporada) {
        double precio = getPrecioBase();
        
        if (temporada.equalsIgnoreCase("alta")) {
            precio *= 1.5;
        }
        
        // Recargo por capacidad adicional
        if (capacidad > 4) {
            precio += (capacidad - 4) * 20.0;
        }
        
        return precio;
    }

    // Métodos específicos que no afectan el comportamiento base
    public int getCapacidad() { return capacidad; }
    public boolean tieneCuna() { return tieneCuna; }
}