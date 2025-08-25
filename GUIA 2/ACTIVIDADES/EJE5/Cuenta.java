package EJE5;

import java.util.Scanner;

// Clase base abstracta Cuenta
abstract class Cuenta {
    private int numero;
    protected double saldo;
    
    public Cuenta(int numero) {
        this.numero = numero;
        this.saldo = 0;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        } else {
            System.out.println("Error: El monto debe ser positivo");
        }
    }
    
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
        } else if (monto > saldo) {
            System.out.println("Error: Fondos insuficientes");
        } else {
            System.out.println("Error: El monto debe ser positivo");
        }
    }
    
    public abstract void consultar();
    
    @Override
    public String toString() {
        return "Cuenta #" + numero + " - Saldo: $" + saldo;
    }
}