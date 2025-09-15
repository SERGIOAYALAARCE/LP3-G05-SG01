import java.util.Scanner;
class DivisionPorCeroException extends Exception {
    public DivisionPorCeroException() {
        super("No se puede dividir por cero.");
    }
}
public class Calculadora {
    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) throws DivisionPorCeroException {
        if (b == 0) {
            throw new DivisionPorCeroException();
        }
        return a / b;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculadora calc = new Calculadora();
        int opcion;
        do {
            System.out.println("\n===== MENÚ CALCULADORA =====");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            try {
                if (opcion >= 1 && opcion <= 4) {
                    System.out.print("Ingrese el primer número: ");
                    double a = sc.nextDouble();
                    System.out.print("Ingrese el segundo número: ");
                    double b = sc.nextDouble();

                    switch (opcion) {
                        case 1:
                            System.out.println("Resultado: " + calc.sumar(a, b));
                            break;
                        case 2:
                            System.out.println("Resultado: " + calc.restar(a, b));
                            break;
                        case 3:
                            System.out.println("Resultado: " + calc.multiplicar(a, b));
                            break;
                        case 4:
                            System.out.println("Resultado: " + calc.dividir(a, b));
                            break;
                    }
                } else if (opcion != 5) {
                    System.out.println("⚠ Opción inválida. Intente de nuevo.");
                }
            } 
            catch (DivisionPorCeroException e) {
                System.out.println("Error personalizado: " + e.getMessage());
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Error de argumento inválido: " + e.getMessage());
            } 
            catch (ArithmeticException e) {
                System.out.println("Error aritmético: " + e.getMessage());
            }
        } while (opcion != 5);

        System.out.println("👋 Programa finalizado.");
        sc.close();
    }
}

