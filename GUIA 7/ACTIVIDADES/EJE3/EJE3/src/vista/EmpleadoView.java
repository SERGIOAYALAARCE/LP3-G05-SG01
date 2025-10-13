package vista;


import modelo.Empleado;
import java.util.List;

public class EmpleadoView {

    public void mostrarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("\n--- LISTA DE EMPLEADOS ---");
            for (Empleado e : empleados) {
                System.out.println(e);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarEmpleado(Empleado empleado) {
        if (empleado != null) {
            System.out.println("\nEmpleado encontrado:");
            System.out.println(empleado);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }
}
