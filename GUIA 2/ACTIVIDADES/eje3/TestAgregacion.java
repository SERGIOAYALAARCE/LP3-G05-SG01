package eje3;

public class TestAgregacion {
    public static void main(String[] args) {
        // Crear motores
        Motor motor1 = new Motor(12345, 2500);
        Motor motor2 = new Motor(67890, 3000);
        Motor motor3 = new Motor(11121, 2000);
        
        // Crear automóviles
        Automovil auto1 = new Automovil("ABC123", 4, "Toyota", "Corolla");
        Automovil auto2 = new Automovil("XYZ789", 2, "Honda", "Civic");
        Automovil auto3 = new Automovil("DEF456", 5, "Ford", "Focus");
        
        // Asignar motores a los automóviles (agregación)
        auto1.setMotor(motor1);
        auto2.setMotor(motor2);
        // auto3 no tiene motor asignado (para demostrar el caso)
        
        // Mostrar información de los automóviles
        System.out.println("=== INFORMACIÓN DE AUTOMÓVILES ===");
        System.out.println(auto1.toString());
        System.out.println(auto2.toString());
        System.out.println(auto3.toString());
        
        // Cambiar las revoluciones de un motor
        System.out.println("\n=== MODIFICANDO MOTOR ===");
        System.out.println("Revoluciones antes: " + auto1.getMotor().getRevoluciones());
        auto1.getMotor().setRevoluciones(2800);
        System.out.println("Revoluciones después: " + auto1.getMotor().getRevoluciones());
        System.out.println(auto1.toString());
    }
}