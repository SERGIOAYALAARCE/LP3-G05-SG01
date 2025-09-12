package reservas;

public class NotificadorSlack implements CanalNotificacion {
    private String canalSlack;
    
    public NotificadorSlack(String canalSlack) {
        this.canalSlack = canalSlack;
    }
    
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("💬 Enviando mensaje a Slack - Canal: " + canalSlack);
        System.out.println("Para: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Mensaje de Slack enviado exitosamente!\n");
    }
    
    @Override
    public String getTipoCanal() {
        return "Slack - Canal: " + canalSlack;
    }
}