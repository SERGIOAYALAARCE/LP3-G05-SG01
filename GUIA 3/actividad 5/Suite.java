package reservas;

// Suite con todos los servicios premium
public class Suite extends HabitacionBase implements ServicioLimpieza, ServicioComida, ServicioLavanderia, ServicioSpa {
    private boolean tieneJacuzzi;
    private boolean tieneDesayuno;

    public Suite(int id, double precioBase, boolean tieneJacuzzi, boolean tieneDesayuno) {
        super(id, "Suite", precioBase);
        this.tieneJacuzzi = tieneJacuzzi;
        this.tieneDesayuno = tieneDesayuno;
    }

    @Override
    public double calcularPrecio(String temporada) {
        double precio = getPrecioBase();
        
        if (temporada.equalsIgnoreCase("alta")) {
            precio *= 1.5;
        }
        
        if (tieneJacuzzi) {
            precio += 50.0;
        }
        
        if (tieneDesayuno) {
            precio += 30.0;
        }
        
        return precio;
    }

    // ServicioLimpieza
    @Override
    public void solicitarLimpieza() {
        System.out.println("Limpieza premium solicitada para suite " + getId());
    }

    @Override
    public void programarLimpiezaRegular(String frecuencia) {
        System.out.println("Limpieza premium programada " + frecuencia + " para suite " + getId());
    }

    @Override
    public double calcularCostoLimpieza() {
        return 25.0; // Limpieza premium
    }

    // ServicioComida
    @Override
    public void solicitarDesayuno() {
        System.out.println("Desayuno en suite solicitado para habitación " + getId());
    }

    @Override
    public void solicitarAlmuerzo() {
        System.out.println("Almuerzo en suite solicitado para habitación " + getId());
    }

    @Override
    public void solicitarCena() {
        System.out.println("Cena en suite solicitada para habitación " + getId());
    }

    @Override
    public double obtenerMenuPrecios() {
        return 45.0; // Precio promedio menú
    }

    // ServicioLavanderia
    @Override
    public void solicitarLavadoRopa() {
        System.out.println("Servicio de lavandería solicitado para suite " + getId());
    }

    @Override
    public void solicitarPlanchado() {
        System.out.println("Servicio de planchado solicitado para suite " + getId());
    }

    @Override
    public double calcularCostoLavanderia(int prendas) {
        return prendas * 5.0;
    }

    // ServicioSpa
    @Override
    public void reservarMasaje(String tipo, String hora) {
        System.out.println("Masaje " + tipo + " reservado a las " + hora + " para suite " + getId());
    }

    @Override
    public void reservarTratamientoFacial(String tratamiento) {
        System.out.println("Tratamiento facial " + tratamiento + " reservado para suite " + getId());
    }

    @Override
    public double obtenerPrecioSpa() {
        return 80.0; // Precio base spa
    }
}