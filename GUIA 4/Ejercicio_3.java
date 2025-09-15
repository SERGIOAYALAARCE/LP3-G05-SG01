class Numero {
    private double valor;

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("No se permiten valores negativos.");
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}

public class Ejercicio3 {
    public static void main(String[] args) {
        Numero n = new Numero();

        try {
            n.setValor(15);
            System.out.println("Valor asignado: " + n.getValor());

            n.setValor(-8); // provocará excepción
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}
