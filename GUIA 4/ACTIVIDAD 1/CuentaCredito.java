package exceptions;

public class CuentaCredito extends CuentaBancaria {
    private double limiteCredito;

    public CuentaCredito(String numeroCuenta, String titular, double saldoInicial, double limiteCredito) {
        super(numeroCuenta, titular, saldoInicial);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El retiro debe ser positivo.");
        }
        if (monto > saldo + limiteCredito) {
            throw new SaldoInsuficienteException("No tienes saldo ni crédito suficiente.");
        }
        saldo -= monto;
    }

    @Override
    public String toString() {
        return super.toString() + " [Cuenta de crédito, límite=" + limiteCredito + "]";
    }
}
