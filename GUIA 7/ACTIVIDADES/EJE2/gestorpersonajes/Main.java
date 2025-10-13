package gestorpersonajes;

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
            System.out.println("3. Modificar personaje completo");
            System.out.println("4. Actualizar un atributo");
            System.out.println("5. Borrar personaje");
            System.out.println("6. Filtrar por atributo");
            System.out.println("7. Mostrar estadísticas");
            System.out.println("8. Importar personajes desde archivo");
            System.out.println("9. Subir de nivel un personaje");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> gestor.mostrarPersonajes();
                case 2 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    int vida = leerEntero(sc, "Vida: ");
                    int ataque = leerEntero(sc, "Ataque: ");
                    int defensa = leerEntero(sc, "Defensa: ");
                    int alcance = leerEntero(sc, "Alcance: ");
                    gestor.agregarPersonaje(new Personaje(nombre, vida, ataque, defensa, alcance));
                }
                case 3 -> {
                    System.out.print("Nombre del personaje: ");
                    String n = sc.nextLine();
                    int v = leerEntero(sc, "Nueva vida: ");
                    int a = leerEntero(sc, "Nuevo ataque: ");
                    int d = leerEntero(sc, "Nueva defensa: ");
                    int al = leerEntero(sc, "Nuevo alcance: ");
                    gestor.modificarPersonaje(n, v, a, d, al);
                }
                case 4 -> {
                    System.out.print("Nombre del personaje: ");
                    String n = sc.nextLine();
                    System.out.print("Atributo a modificar (vida, ataque, defensa, alcance): ");
                    String atributo = sc.nextLine();
                    int valor = leerEntero(sc, "Nuevo valor: ");
                    gestor.actualizarAtributo(n, atributo, valor);
                }
                case 5 -> {
                    System.out.print("Nombre del personaje a borrar: ");
                    gestor.borrarPersonaje(sc.nextLine());
                }
                case 6 -> {
                    System.out.print("Atributo para ordenar (vida, ataque, defensa, alcance): ");
                    gestor.filtrarPor(sc.nextLine());
                }
                case 7 -> gestor.mostrarEstadisticas();
                case 8 -> {
                    System.out.print("Ruta del archivo a importar: ");
                    gestor.importarDesdeArchivo(sc.nextLine());
                }
                case 9 -> {
                    System.out.print("Nombre del personaje: ");
                    gestor.subirNivel(sc.nextLine());
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    private static int leerEntero(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Ingresa un número válido: ");
            }
        }
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return leerEntero(sc);
    }
}
