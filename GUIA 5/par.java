import java.util.Objects;

public class Par<F, S> {
    private F primero;
    private S segundo;

    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    // Getters y setters
    public F getPrimero() { return primero; }
    public void setPrimero(F primero) { this.primero = primero; }

    public S getSegundo() { return segundo; }
    public void setSegundo(S segundo) { this.segundo = segundo; }

    // esIgual: mismos valores en el mismo orden
    public boolean esIgual(Par<F, S> otro) {
        if (otro == null) return false;
        return Objects.equals(this.primero, otro.primero)
            && Objects.equals(this.segundo, otro.segundo);
    }

    @Override
    public String toString() {
        return "(Primero: " + primero + ", Segundo: " + segundo + ")";
    }
}

