package exceptions;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        try (Scanner sc = new Scanner(System.in)) {
            int opcion;
            do {
                System.out.println("\n--- MENÚ BANCO ---");
                System.out.println("1. Crear cuenta");
                System.out.println("2. Crear cuenta con crédito");
                System.out.println("3. Depositar");
                System.out.println("4. Retirar");
                System.out.println("5. Transferir");
                System.out.println("6. Consultar saldo");
                System.out.println("7. Cerrar cuenta");
                System.out.println("8. Reporte de transacciones");
                System.out.println("9. Listar cuentas");
                System.out.println("0. Salir");
                System.out.print("Seleccione opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                try {
                    switch (opcion) {
                        case 1:
                            System.out.print("Número de cuenta: ");
                            String num = sc.nextLine();
                            System.out.print("Titular: ");
                            String tit = sc.nextLine();
                            System.out.print("Saldo inicial: ");
                            double saldo = sc.nextDouble();
                            banco.crearCuenta(new CuentaBancaria(num, tit, saldo));
                            break;
                        case 2:
                            System.out.print("Número de cuenta: ");
                            String numc = sc.nextLine();
                            System.out.print("Titular: ");
                            String titc = sc.nextLine();
                            System.out.print("Saldo inicial: ");
                            double saldoc = sc.nextDouble();
                            System.out.print("Límite de crédito: ");
                            double limite = sc.nextDouble();
                            banco.crearCuenta(new CuentaCredito(numc, titc, saldoc, limite));
                            break;
                        case 3:
                            System.out.print("Número de cuenta: ");
                            String ndep = sc.nextLine();
                            System.out.print("Monto: ");
                            double mdep = sc.nextDouble();
                            banco.buscarCuenta(ndep).depositar(mdep);
                            break;
                        case 4:
                            System.out.print("Número de cuenta: ");
                            String nret = sc.nextLine();
                            System.out.print("Monto: ");
                            double mret = sc.nextDouble();
                            banco.buscarCuenta(nret).retirar(mret);
                            break;
                        case 5:
                            System.out.print("Cuenta origen: ");
                            String norig = sc.nextLine();
                            System.out.print("Cuenta destino: ");
                            String ndest = sc.nextLine();
                            System.out.print("Monto: ");
                            double mtra = sc.nextDouble();
                            banco.buscarCuenta(norig).transferir(banco.buscarCuenta(ndest), mtra);
                            break;
                        case 6:
                            System.out.print("Número de cuenta: ");
                            String ncon = sc.nextLine();
                            System.out.println("Saldo: " + banco.buscarCuenta(ncon).getSaldo());
                            break;
                        case 7:
                            System.out.print("Número de cuenta: ");
                            String ncer = sc.nextLine();
                            banco.cerrarCuenta(ncer);
                            break;
                        case 8:
                            System.out.print("Número de cuenta: ");
                            String nrep = sc.nextLine();
                            for (String mov : banco.buscarCuenta(nrep).getHistorial()) {
                                System.out.println(mov);
                            }
                            break;
                        case 9:
                            for (CuentaBancaria c : banco.listarCuentas()) {
                                System.out.println(c);
                            }
                            break;
                        case 0:
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } while (opcion != 0);
        }
    }
}


