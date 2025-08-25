package EJE5;

import java.util.Scanner;

public class AppBanco {
    public static void main(String[] args) {
        // Crear un array de cuentas bancarias
        Cuenta[] cuentas = new Cuenta[10];
        
        // Inicializar 5 cuentas de ahorro
        for (int i = 0; i < 5; i++) {
            CuentaAhorro ca = new CuentaAhorro(i);
            ca.setTasaInteres(1.5); // 1.5% de interés mensual
            cuentas[i] = ca;
        }
        
        // Inicializar 5 cuentas corrientes
        for (int i = 5; i < 10; i++) {
            cuentas[i] = new CuentaCorriente(i);
        }
        
        Scanner in = new Scanner(System.in);
        boolean done = false;
        
        System.out.println("=== SISTEMA BANCARIO ===");
        
        while (!done) {
            System.out.print("\nD)epositar R)etirar C)onsultar S)alir: ");
            String op = in.next().toUpperCase();
            
            if (op.equals("D") || op.equals("R")) {
                System.out.print("Ingrese un numero de cuenta (0-9) y un monto: ");
                int num = in.nextInt();
                double monto = in.nextDouble();
                
                if (num < 0 || num >= cuentas.length) {
                    System.out.println("Error: Número de cuenta inválido");
                    continue;
                }
                
                if (op.equals("D")) {
                    cuentas[num].depositar(monto);
                    System.out.println("Depósito realizado. Nuevo saldo: $" + cuentas[num].getSaldo());
                } else {
                    cuentas[num].retirar(monto);
                    System.out.println("Retiro realizado. Nuevo saldo: $" + cuentas[num].getSaldo());
                }
            } 
            else if (op.equals("C")) {
                System.out.println("\n=== CONSULTA DE CUENTAS ===");
                for (int n = 0; n < cuentas.length; n++) {
                    cuentas[n].consultar();
                    System.out.println(n + ": " + cuentas[n].toString());
                }
            } 
            else if (op.equals("S")) {
                done = true;
                System.out.println("Gracias por usar el sistema bancario.");
            } 
            else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        
        in.close();
    }
}