import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Subclase de Persona (HERENCIA).
 * Mantiene agregación con Curso: un Estudiante existe independiente de los Cursos.
 */
public class Estudiante extends Persona {
    // ===== Atributos de clase =====
    private static int totalEstudiantes = 0;

    // ===== Atributos de instancia =====
    private final String codigoEstudiante;
    private final List<Curso> cursosInscritos = new ArrayList<>(); // AGREGACIÓN

    public Estudiante(String nombre, String apellido, String dni, String email, String codigoEstudiante) {
        super(nombre, apellido, dni, email);
        this.codigoEstudiante = codigoEstudiante;
        totalEstudiantes++;
    }

    // ===== Métodos de instancia =====
    public boolean inscribirse(Curso curso) {
        if (curso == null) return false;
        if (curso.agregarEstudiante(this)) { // uso de interfaz Inscribible
            cursosInscritos.add(curso);
            return true;
        }
        return false;
    }

    public boolean desinscribirse(Curso curso) {
        if (curso == null) return false;
        if (curso.removerEstudiante(this)) {
            cursosInscritos.remove(curso);
            return true;
        }
        return false;
    }

    public List<Curso> getCursosInscritos() {
        return Collections.unmodifiableList(cursosInscritos);
    }

    public String getCodigoEstudiante() { return codigoEstudiante; }

    @Override
    public void mostrarInfo() { // POLIMORFISMO
        System.out.println("Estudiante: " + nombre + " " + apellido +
                " | Código: " + codigoEstudiante +
                " | DNI: " + dni +
                " | Cursos: " + cursosInscritos.size());
    }

    // ===== Métodos de clase =====
    public static int getTotalEstudiantes() {
        return totalEstudiantes;
    }
}
