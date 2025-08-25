package eje4;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private Cuenta cuenta; // Relación de composición

    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        // Generar número de cuenta basado en el ID (composición)
        int numeroCuenta = generarNumeroCuenta(id);
        this.cuenta = new Cuenta(numeroCuenta);
    }
    // Método para generar número de cuenta basado en el ID
    private int generarNumeroCuenta(int id) {
        // Podría ser cualquier lógica, por ejemplo: 100000 + id
        return 100000 + id;
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Cuenta getCuenta() {
        return cuenta;
    }
    public void depositar(double monto) {
        if (monto > 0) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            System.out.println("Depósito exitoso. Nuevo saldo: $" + cuenta.getSaldo());
        } else {
            System.out.println("El monto debe ser positivo");
        }
    }
    
    public void retirar(double monto) {
        if (monto > 0 && monto <= cuenta.getSaldo()) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            System.out.println("Retiro exitoso. Nuevo saldo: $" + cuenta.getSaldo());
        } else if (monto > cuenta.getSaldo()) {
            System.out.println("Fondos insuficientes");
        } else {
            System.out.println("El monto debe ser positivo");
        }
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Persona [ID: " + id + 
               ", Nombre: " + nombre + 
               ", Apellido: " + apellido + 
               ", " + cuenta.toString() + "]";
    }
}