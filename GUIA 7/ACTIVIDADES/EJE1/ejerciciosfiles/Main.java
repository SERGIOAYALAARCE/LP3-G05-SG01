package ejerciciosfiles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor("personajes.txt");
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n--- GESTOR DE PERSONAJES ---");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Agregar personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Borrar personaje");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            // Leer opción de manera segura
            while (true) {
                try {
                    opcion = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Ingresa un número válido:");
                }
            }

            switch (opcion) {
                case 1:
                    gestor.mostrarPersonajes();
                    break;

                case 2:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    int vida = leerEntero(sc, "Vida: ");
                    int ataque = leerEntero(sc, "Ataque: ");
                    int defensa = leerEntero(sc, "Defensa: ");
                    int alcance = leerEntero(sc, "Alcance: ");

                    gestor.agregarPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance));
                    break;

                case 3:
                    System.out.print("Nombre del personaje a modificar: ");
                    String n = sc.nextLine();

                    int v = leerEntero(sc, "Nueva vida: ");
                    int a = leerEntero(sc, "Nuevo ataque: ");
                    int d = leerEntero(sc, "Nueva defensa: ");
                    int al = leerEntero(sc, "Nuevo alcance: ");

                    gestor.modificarPersonaje(n, v, a, d, al);
                    break;

                case 4:
                    System.out.print("Nombre del personaje a borrar: ");
                    String nb = sc.nextLine();
                    gestor.borrarPersonaje(nb);
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción no válida, intenta nuevamente.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // Método auxiliar para leer enteros de forma segura
    private static int leerEntero(Scanner sc, String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(sc.nextLine());
                if (numero <= 0) {
                    System.out.println("⚠️ Debe ser un número mayor que 0.");
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingresa un número válido.");
            }
        }
    }
}
