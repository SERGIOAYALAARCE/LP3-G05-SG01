package reservas;

public interface ServicioLavanderia {
    void solicitarLavadoRopa();
    void solicitarPlanchado();
    double calcularCostoLavanderia(int prendas);
}