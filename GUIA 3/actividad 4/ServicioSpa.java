package reservas;

public interface ServicioSpa {
    void reservarMasaje(String tipo, String hora);
    void reservarTratamientoFacial(String tratamiento);
    double obtenerPrecioSpa();
}