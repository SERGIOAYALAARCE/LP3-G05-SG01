package reservas;

public class EnviadorCorreo implements CanalNotificacion {
    
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("📧 Enviando correo a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Correo enviado exitosamente!\n");
    }
    
    @Override
    public String getTipoCanal() {
        return "Correo Electrónico";
    }
}