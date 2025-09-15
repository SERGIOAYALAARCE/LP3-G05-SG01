import java.util.Scanner;
class Numero {
    private double valor;

    public Numero(double valor) {
        setValor(valor); // Usamos el setter para validar
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("No se permiten números negativos: " + valor);
        }
        this.valor = valor;
    }
}
public class GestionNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Numero numero = null;
        int opcion;

        do {
            System.out.println("\n=== MENÚ DE GESTIÓN DE NÚMEROS ===");
            System.out.println("1. Crear número");
            System.out.println("2. Mostrar valor");
            System.out.println("3. Modificar valor");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese un valor inicial: ");
                        double valorInicial = sc.nextDouble();
                        numero = new Numero(valorInicial);
                        System.out.println("Número creado correctamente: " + numero.getValor());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (numero != null) {
                        System.out.println("El valor actual es: " + numero.getValor());
                    } else {
                        System.out.println("Primero debes crear un número.");
                    }
                    break;

                case 3:
                    if (numero != null) {
                        try {
                            System.out.print("Ingrese el nuevo valor: ");
                            double nuevoValor = sc.nextDouble();
                            numero.setValor(nuevoValor);
                            System.out.println("Valor actualizado: " + numero.getValor());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Primero debes crear un número.");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);

        sc.close();
    }
}
