package vista;

import controlador.EmpleadoController;
import modelo.Empleado;
import vista.EmpleadoView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoView vista = new EmpleadoView();
        EmpleadoController controlador = new EmpleadoController(vista);
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ DE EMPLEADOS ---");
            System.out.println("1. Listar empleados");
            System.out.println("2. Agregar empleado");
            System.out.println("3. Buscar empleado por número");
            System.out.println("4. Eliminar empleado por número");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    controlador.listarEmpleados();
                    break;

                case 2:
                    System.out.print("Número: ");
                    int numero = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Sueldo: ");
                    double sueldo = sc.nextDouble();
                    sc.nextLine();

                    controlador.agregarEmpleado(new Empleado(numero, nombre, sueldo));
                    break;

                case 3:
                    System.out.print("Ingrese el número del empleado a buscar: ");
                    int numBuscar = sc.nextInt();
                    sc.nextLine();
                    vista.mostrarEmpleado(controlador.buscarEmpleado(numBuscar));
                    break;

                case 4:
                    System.out.print("Ingrese el número del empleado a eliminar: ");
                    int numEliminar = sc.nextInt();
                    sc.nextLine();
                    controlador.eliminarEmpleado(numEliminar);
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}

