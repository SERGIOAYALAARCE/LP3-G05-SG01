package reservas;

public interface ServicioComida {
    void solicitarDesayuno();
    void solicitarAlmuerzo();
    void solicitarCena();
    double obtenerMenuPrecios();
}