import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
class RegistroEstudiantes {
    private ArrayList<String> estudiantes;
    public RegistroEstudiantes() {
        estudiantes = new ArrayList<>();
    }
    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        estudiantes.add(nombre);
    }
    public String buscarEstudiante(String nombre) {
        for (String estudiante : estudiantes) {
            if (estudiante.equalsIgnoreCase(nombre)) {
                return estudiante;
            }
        }
        throw new NoSuchElementException("Estudiante no encontrado: " + nombre);
    }
    public void mostrarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("Lista de estudiantes:");
            for (String e : estudiantes) {
                System.out.println("- " + e);
            }
        }
    }
}
public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistroEstudiantes registro = new RegistroEstudiantes();
        int opcion;

        do {
            System.out.println("\n=== MENÚ REGISTRO DE ESTUDIANTES ===");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Buscar estudiante");
            System.out.println("3. Mostrar todos los estudiantes");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese el nombre del estudiante: ");
                        String nombre = sc.nextLine();
                        registro.agregarEstudiante(nombre);
                        System.out.println("Estudiante agregado correctamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Ingrese el nombre del estudiante a buscar: ");
                        String nombreBuscar = sc.nextLine();
                        String encontrado = registro.buscarEstudiante(nombreBuscar);
                        System.out.println("Estudiante encontrado: " + encontrado);
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    registro.mostrarEstudiantes();
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

