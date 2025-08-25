/**
 * Subclase de Persona (HERENCIA).
 */
public class Profesor extends Persona {
    // ===== Atributos de clase =====
    private static int totalProfesores = 0;

    // ===== Atributos de instancia =====
    private final String codigoProfesor;
    private String especialidad;

    public Profesor(String nombre, String apellido, String dni, String email, String codigoProfesor, String especialidad) {
        super(nombre, apellido, dni, email);
        this.codigoProfesor = codigoProfesor;
        this.especialidad = especialidad;
        totalProfesores++;
    }

    // ===== Métodos de instancia =====
    public String getCodigoProfesor() { return codigoProfesor; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @Override
    public void mostrarInfo() { // POLIMORFISMO
        System.out.println("Profesor: " + nombre + " " + apellido +
                " | Código: " + codigoProfesor +
                " | Especialidad: " + especialidad);
    }

    // ===== Métodos de clase =====
    public static int getTotalProfesores() {
        return totalProfesores;
    }
}
