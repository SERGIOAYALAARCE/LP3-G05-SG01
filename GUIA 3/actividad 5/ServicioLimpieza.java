package reservas;

public interface ServicioLimpieza {
    void solicitarLimpieza();
    void programarLimpiezaRegular(String frecuencia);
    double calcularCostoLimpieza();
}