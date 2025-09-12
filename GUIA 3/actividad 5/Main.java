package reservas;

public class Main {
    public static void main(String[] args) {
        // Crear diferentes canales de notificación
        CanalNotificacion correo = new EnviadorCorreo();
        CanalNotificacion sms = new Enviadorsms();
        CanalNotificacion slack = new NotificadorSlack("#reservas-hotel");
        
        // Crear notificadores con diferentes canales
        NotificadorReserva notificadorCorreo = new NotificadorReserva(correo);
        NotificadorReserva notificadorSMS = new NotificadorReserva(sms);
        NotificadorReserva notificadorSlack = new NotificadorReserva(slack);
        NotificadorReserva notificadorMultiCanal = new NotificadorReserva(correo);
        
        // Crear habitaciones
        HabitacionBase h1 = new Habitacion(101, "Suite", 200.0);
        HabitacionBase h2 = new Habitacion(102, "Doble", 150.0);
        
        // Crear clientes
        Cliente c1 = new Cliente("Carlos Perez", "carlos@mail.com");
        Cliente c2 = new Cliente("Ana Lopez", "ana@mail.com");
        Cliente c3 = new Cliente("Maria Rodriguez", "maria@mail.com");
        
        // Crear políticas de cancelación
        PoliticaCancelacion politicaFlexible = new PoliticaCancelacionFlexible();
        PoliticaCancelacion politicaEstricta = new PoliticaCancelacionEstricta();
        
        System.out.println("=== DEMOSTRACIÓN PRINCIPIO DIP ===");
        
        // Controlador con notificador por correo
        ControladorReservas controlador1 = new ControladorReservas(notificadorCorreo);
        Reserva r1 = controlador1.crearReserva(h1, c1, "2025-09-10", "2025-09-12", politicaFlexible);
        
        // Cambiar a notificador por SMS para el mismo controlador
        controlador1.setNotificador(notificadorSMS);
        Reserva r2 = controlador1.crearReserva(h2, c2, "2025-09-11", "2025-09-15", politicaEstricta);
        
        // Controlador con notificador Slack
        ControladorReservas controlador2 = new ControladorReservas(notificadorSlack);
        Reserva r3 = controlador2.crearReserva(h1, c3, "2025-09-20", "2025-09-25", politicaFlexible);
        
        System.out.println("\n=== CAMBIANDO CANALES EN TIEMPO DE EJECUCIÓN ===");
        
        // Cambiar canal de notificación para notificador multi-canal
        notificadorMultiCanal.setCanalNotificacion(sms);
        controlador2.setNotificador(notificadorMultiCanal);
        controlador2.cancelarReserva(r3);
        
        System.out.println("\n=== DEMOSTRACIÓN DE FLEXIBILIDAD ===");
        
        // Mostrar cómo se pueden usar diferentes canales fácilmente
        CanalNotificacion[] canales = {correo, sms, slack};
        String[] mensajes = {
            "Mensaje de prueba por correo",
            "Mensaje de prueba por SMS",
            "Mensaje de prueba por Slack"
        };
        
        for (int i = 0; i < canales.length; i++) {
            NotificadorReserva notificador = new NotificadorReserva(canales[i]);
            // Ahora podemos llamar al método público enviarNotificacion
            notificador.enviarNotificacion("test@mail.com", mensajes[i]);
        }
        
        // Alternativa: usar los métodos específicos de notificación
        System.out.println("\n=== USANDO MÉTODOS ESPECÍFICOS ===");
        notificadorCorreo.enviarNotificacion("cliente@ejemplo.com", "Mensaje directo de prueba");
    }
}