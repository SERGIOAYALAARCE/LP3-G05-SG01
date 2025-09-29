package Sesion05.EjerciciosPropuestos;

public class Main {

    // Método genérico estático para imprimir un par
    public static <F, S> void imprimirPar(Par<F, S> par) {
        System.out.println(par);
    }

    public static void main(String[] args) {
        Par<String, Integer> par1 = new Par<>("Edad", 30);
        Par<Double, Boolean> par2 = new Par<>(4.5, true);
        Par<String, String> par3 = new Par<>("Nombre", "Juan");

        imprimirPar(par1);  // (Primero: Edad, Segundo: 30)
        imprimirPar(par2);  // (Primero: 4.5, Segundo: true)
        imprimirPar(par3);  // (Primero: Nombre, Segundo: Juan)
    }
}
