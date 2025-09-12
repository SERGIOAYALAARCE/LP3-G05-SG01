package reservas;

public class Enviadorsms implements CanalNotificacion {
    
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("📱 Enviando SMS a: " + destinatario);
        System.out.println("SMS: " + (mensaje.length() > 160 ? mensaje.substring(0, 160) + "..." : mensaje));
        System.out.println("SMS enviado exitosamente!\n");
    }
    
    @Override
    public String getTipoCanal() {
        return "SMS";
    }
}