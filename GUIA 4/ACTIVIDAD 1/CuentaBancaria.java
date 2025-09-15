package exceptions;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    protected double saldo;  // protected para que herede CuentaCredito
    private List<String> historial;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
        historial.add("Cuenta creada con saldo inicial: " + saldoInicial);
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El depósito debe ser positivo.");
        }
        saldo += monto;
        historial.add("Depósito: +" + monto);
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El retiro debe ser positivo.");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Fondos insuficientes. Saldo: " + saldo);
        }
        saldo -= monto;
        historial.add("Retiro: -" + monto);
    }

    public void transferir(CuentaBancaria destino, double monto) throws SaldoInsuficienteException {
        this.retirar(monto);
        destino.depositar(monto);
        historial.add("Transferencia enviada: -" + monto + " a " + destino.getNumeroCuenta());
        destino.historial.add("Transferencia recibida: +" + monto + " de " + this.getNumeroCuenta());
    }

    public List<String> getHistorial() throws HistorialVacioException {
        if (historial.isEmpty()) {
            throw new HistorialVacioException("No hay movimientos en esta cuenta.");
        }
        return historial;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
