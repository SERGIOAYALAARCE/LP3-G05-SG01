/**
 * Interfaz: permite POLIMORFISMO de tipos inscribibles.
 * Un Curso es "Inscribible".
 */
public interface Inscribible {
    boolean agregarEstudiante(Estudiante e);
    boolean removerEstudiante(Estudiante e);
    boolean tieneCupos();
}
