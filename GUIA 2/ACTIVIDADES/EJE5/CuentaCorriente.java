package EJE5;

class CuentaCorriente extends Cuenta {
    private int retiros;
    private static final int LIBRE_RETIROS = 3;
    private static final double TARIFA_TRANSACCION = 3.0;
    
    public CuentaCorriente(int numero) {
        super(numero);
        this.retiros = 0;
    }
    
    @Override
    public void retirar(double monto) {
        super.retirar(monto);
        retiros++;
        
        // Aplicar tarifa si se exceden los retiros gratuitos
        if (retiros > LIBRE_RETIROS) {
            super.retirar(TARIFA_TRANSACCION);
            System.out.println("Tarifa de transacción de $" + TARIFA_TRANSACCION + 
                             " aplicada a cuenta #" + getNumero());
        }
    }
    
    @Override
    public void consultar() {
        // Reiniciar contador de retiros al inicio del mes
        retiros = 0;
        System.out.println("Contador de retiros reiniciado para cuenta #" + getNumero());
    }
    
    @Override
    public String toString() {
        return "Cuenta Corriente #" + getNumero() + " - Saldo: $" + getSaldo() + 
               " - Retiros este mes: " + retiros + "/" + LIBRE_RETIROS + " gratuitos";
    }
}