package controlador;

import modelo.Empleado;
import vista.EmpleadoView;

import java.io.*;
import java.util.*;

public class EmpleadoController {
    private List<Empleado> empleados;
    private final String archivo = "empleados.txt";
    private EmpleadoView vista;

    public EmpleadoController(EmpleadoView vista) {
        this.vista = vista;
        empleados = new ArrayList<>();
        leerEmpleados();
    }

    // --- LEE EMPLEADOS DESDE EL ARCHIVO ---
    public void leerEmpleados() {
        empleados.clear();
        File file = new File(archivo);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    int numero = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    double sueldo = Double.parseDouble(datos[2]);
                    empleados.add(new Empleado(numero, nombre, sueldo));
                }
            }
            vista.mostrarMensaje("Empleados cargados correctamente.");
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
        }
    }

    // --- GUARDA TODOS LOS EMPLEADOS EN EL ARCHIVO ---
    private void guardarEmpleados() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Empleado e : empleados) {
                pw.println(e.getNumero() + "," + e.getNombre() + "," + e.getSueldo());
            }
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // --- LISTAR ---
    public void listarEmpleados() {
        vista.mostrarEmpleados(empleados);
    }

    // --- AGREGAR ---
    public void agregarEmpleado(Empleado empleado) {
        for (Empleado e : empleados) {
            if (e.getNumero() == empleado.getNumero()) {
                vista.mostrarMensaje("⚠️ Ya existe un empleado con ese número.");
                return;
            }
        }
        empleados.add(empleado);
        guardarEmpleados();
        vista.mostrarMensaje("Empleado agregado correctamente.");
    }

    // --- BUSCAR ---
    public Empleado buscarEmpleado(int numero) {
        for (Empleado e : empleados) {
            if (e.getNumero() == numero) {
                return e;
            }
        }
        return null;
    }

    // --- ELIMINAR ---
    public void eliminarEmpleado(int numero) {
        Empleado encontrado = buscarEmpleado(numero);
        if (encontrado != null) {
            empleados.remove(encontrado);
            guardarEmpleados();
            vista.mostrarMensaje("Empleado eliminado correctamente.");
        } else {
            vista.mostrarMensaje("⚠️ Empleado no encontrado.");
        }
    }
}
