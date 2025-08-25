package EJE5;

class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double saldoMinimo;
    
    public CuentaAhorro(int numero) {
        super(numero);
        this.tasaInteres = 0.0;
        this.saldoMinimo = Double.MAX_VALUE;
    }
    
    public void setTasaInteres(double interes) {
        this.tasaInteres = interes;
    }
    
    @Override
    public void retirar(double monto) {
        super.retirar(monto);
        // Actualizar saldo mínimo después del retiro
        if (saldo < saldoMinimo) {
            saldoMinimo = saldo;
        }
    }
    
    @Override
    public void consultar() {
        // Calcular y depositar intereses basados en el saldo mínimo mensual
        double intereses = saldoMinimo * tasaInteres / 100;
        depositar(intereses);
        System.out.println("Intereses de $" + intereses + " depositados en cuenta #" + getNumero());
        
        // Reiniciar el saldo mínimo para el próximo mes
        saldoMinimo = saldo;
    }
    
    @Override
    public String toString() {
        return "Cuenta Ahorro #" + getNumero() + " - Saldo: $" + getSaldo() + 
               " - Tasa interés: " + tasaInteres + "%";
    }
}
