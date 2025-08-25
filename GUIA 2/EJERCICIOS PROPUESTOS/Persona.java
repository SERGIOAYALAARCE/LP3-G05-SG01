import java.util.Objects;

/**
 * Clase abstracta (HERENCIA).
 * Define el contrato polimórfico mostrarInfo().
 */
public abstract class Persona {
    // ===== Atributos de instancia =====
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String email;

    // ===== Constructor =====
    public Persona(String nombre, String apellido, String dni, String email) {
        this.nombre = Objects.requireNonNull(nombre);
        this.apellido = Objects.requireNonNull(apellido);
        this.dni = Objects.requireNonNull(dni);
        this.email = Objects.requireNonNull(email);
    }

    // ===== Métodos de instancia (POLIMORFISMO) =====
    public abstract void mostrarInfo();

    // Getters básicos
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }
}
