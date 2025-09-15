import java.util.ArrayList;
import java.util.NoSuchElementException;

class RegistroEstudiantes {
    private ArrayList<String> estudiantes = new ArrayList<>();

    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        estudiantes.add(nombre);
    }

    public String buscarEstudiante(String nombre) {
        for (String e : estudiantes) {
            if (e.equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        throw new NoSuchElementException("Estudiante no encontrado: " + nombre);
    }
}

public class Ejercicio4 {
    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes();

        try {
            registro.agregarEstudiante("Juan");
            registro.agregarEstudiante("María");
            registro.agregarEstudiante(""); // excepción

        } catch (IllegalArgumentException e) {
            System.out.println("Error al agregar estudiante: " + e.getMessage());
        }

        try {
            System.out.println("Buscando a Juan: " + registro.buscarEstudiante("Juan"));
            System.out.println("Buscando a Pedro: " + registro.buscarEstudiante("Pedro")); // excepción
        } catch (NoSuchElementException e) {
            System.out.println("Error en búsqueda: " + e.getMessage());
        }
    }
}
