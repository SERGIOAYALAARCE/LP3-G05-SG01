package eje4;

public class TestComposicion {
    public static void main(String[] args) {
        // Crear personas (que automáticamente crean sus cuentas)
        Persona persona1 = new Persona(1, "Juan", "Pérez");
        Persona persona2 = new Persona(2, "María", "Gómez");
        Persona persona3 = new Persona(3, "Carlos", "López");
        
        // Mostrar información de las personas
        System.out.println("=== INFORMACIÓN DE PERSONAS ===");
        System.out.println(persona1.toString());
        System.out.println(persona2.toString());
        System.out.println(persona3.toString());
        
        // Realizar operaciones bancarias
        System.out.println("\n=== OPERACIONES BANCARIAS ===");
        
        // Depositar dinero
        System.out.println("\n--- Depósitos ---");
        persona1.depositar(1000.50);
        persona2.depositar(500.75);
        persona3.depositar(200.00);
        
        // Mostrar saldos actualizados
        System.out.println("\n--- Saldos después de depósitos ---");
        System.out.println(persona1.getNombre() + " " + persona1.getApellido() + 
                          ": $" + persona1.getCuenta().getSaldo());
        System.out.println(persona2.getNombre() + " " + persona2.getApellido() + 
                          ": $" + persona2.getCuenta().getSaldo());
        System.out.println(persona3.getNombre() + " " + persona3.getApellido() + 
                          ": $" + persona3.getCuenta().getSaldo());
        
        // Retirar dinero
        System.out.println("\n--- Retiros ---");
        persona1.retirar(200.25);
        persona2.retirar(600.00); // Intentar retirar más del saldo disponible
        persona3.retirar(100.00);
        
        // Mostrar información final
        System.out.println("\n=== INFORMACIÓN FINAL ===");
        System.out.println(persona1.toString());
        System.out.println(persona2.toString());
        System.out.println(persona3.toString());
        
        // Demostrar que la cuenta no puede existir sin la persona
        System.out.println("\n=== DEMOSTRACIÓN DE COMPOSICIÓN ===");
        System.out.println("La cuenta de " + persona1.getNombre() + 
                          " tiene número: " + persona1.getCuenta().getNumCuenta());
        System.out.println("La cuenta fue creada automáticamente con la persona");
    }
}