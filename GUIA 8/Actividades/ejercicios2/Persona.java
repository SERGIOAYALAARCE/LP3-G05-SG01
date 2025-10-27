package ejercicios2;

public class Persona {
    private int id;
    private String nombre;
    private int edad;
    private String departamento;

    public Persona(int id, String nombre, int edad, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.departamento = departamento;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getDepartamento() { return departamento; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + edad + " | " + departamento;
    }
}
