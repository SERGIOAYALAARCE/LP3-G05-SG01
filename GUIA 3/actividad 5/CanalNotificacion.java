package reservas;

public interface CanalNotificacion {
    void enviarNotificacion(String destinatario, String mensaje);
    String getTipoCanal();
}