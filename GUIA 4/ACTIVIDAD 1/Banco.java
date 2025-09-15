package exceptions;
import java.util.*;
public class Banco {
    private Map<String, CuentaBancaria> cuentas;
    public Banco() {
        cuentas = new HashMap<>();
    }
    public void crearCuenta(CuentaBancaria cuenta) {
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }
    public CuentaBancaria buscarCuenta(String numero) throws CuentaNoEncontradaException {
        CuentaBancaria cuenta = cuentas.get(numero);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("Cuenta " + numero + " no encontrada.");
        }
        return cuenta;
    }
    public void cerrarCuenta(String numero) throws CuentaNoEncontradaException, SaldoNoCeroException {
        CuentaBancaria cuenta = buscarCuenta(numero);
        if (cuenta.getSaldo() != 0) {
            throw new SaldoNoCeroException("No se puede cerrar, el saldo no es cero.");
        }
        cuentas.remove(numero);
        System.out.println("Cuenta cerrada con éxito.");
    }
    public Collection<CuentaBancaria> listarCuentas() {
        return cuentas.values();
    }
}
