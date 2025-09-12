package reservas;

// Habitación para negocios con servicios empresariales
public class HabitacionNegocios extends HabitacionBase {
    private boolean tieneAccesoSalonReuniones;
    private boolean tieneEscritorioEjecutivo;

    public HabitacionNegocios(int id, double precioBase, 
                             boolean tieneAccesoSalonReuniones, 
                             boolean tieneEscritorioEjecutivo) {
        super(id, "Negocios", precioBase);
        this.tieneAccesoSalonReuniones = tieneAccesoSalonReuniones;
        this.tieneEscritorioEjecutivo = tieneEscritorioEjecutivo;
    }

    @Override
    public double calcularPrecio(String temporada) {
        double precio = getPrecioBase();
        
        // Las habitaciones de negocios tienen precio fijo, no varían por temporada
        if (tieneAccesoSalonReuniones) {
            precio += 100.0;
        }
        
        if (tieneEscritorioEjecutivo) {
            precio += 50.0;
        }
        
        return precio;
    }

    // Métodos específicos
    public boolean tieneAccesoSalonReuniones() { return tieneAccesoSalonReuniones; }
    public boolean tieneEscritorioEjecutivo() { return tieneEscritorioEjecutivo; }
}