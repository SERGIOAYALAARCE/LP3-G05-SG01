class DivisionPorCeroException extends Exception {
    public DivisionPorCeroException() {
        super("Error: División por cero no permitida.");
    }
}

class Calculadora {
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
        if (b == 0) throw new DivisionPorCeroException();
        return a / b;
    }
}

public class Ejercicio2 {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        try {
            System.out.println("Suma: " + calc.sumar(10, 5));
            System.out.println("Resta: " + calc.restar(10, 5));
            System.out.println("Multiplicación: " + calc.multiplicar(10, 5));
            System.out.println("División: " + calc.dividir(10, 0)); // provocará excepción
        } catch (DivisionPorCeroException e) {
            System.out.println("Excepción personalizada -> " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción IllegalArgumentException -> " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Excepción ArithmeticException -> " + e.getMessage());
        }
    }
}
