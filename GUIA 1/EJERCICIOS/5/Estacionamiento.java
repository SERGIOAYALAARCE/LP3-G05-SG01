import java.util.Scanner;

public class Estacionamiento {

    // Método para calcular el cargo en el estacionamiento
    public static double calcularCargo(int horas) {
        if (horas <= 0) {
            return 0.0;
        }

        double cargo;

        if (horas == 1) {
            cargo = 3.0; // primera hora
        } else {
            cargo = 3.0 + (horas - 1) * 0.50; // resto de horas
        }

        // Cargo máximo diario
        if (cargo > 12.0) {
            cargo = 12.0;
        }

        return cargo;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el número de horas en el estacionamiento: ");
        int horasEst = sc.nextInt();

        double cargo = calcularCargo(horasEst);
        System.out.println("El cargo es: S/" + cargo);

        sc.close();
    }
}
