package Sesion05.EjerciciosPropuestos;

public class Par<F, S> {
    private F primero;
    private S segundo;

    // Constructor
    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    // Métodos getters y setters
    public F getPrimero() {
        return primero;
    }

    public S getSegundo() {
        return segundo;
    }

    public void setPrimero(F primero) {
        this.primero = primero;
    }

    public void setSegundo(S segundo) {
        this.segundo = segundo;
    }

    // Método esIgual para comparar dos objetos Par
    public boolean esIgual(Par<F, S> otroPar) {
        return this.primero.equals(otroPar.getPrimero()) && this.segundo.equals(otroPar.getSegundo());
    }

    // Método toString para mostrar el par
    @Override
    public String toString() {
        return String.format("(Primero: %s, Segundo: %s)", primero, segundo);
    }
}
