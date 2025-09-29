package Sesion05.EjerciciosPropuestos;

public class PruebaPar {
    public static void main(String[] args) {
        // Código para probar los pares
        Par<String, Integer> par1 = new Par<>("Edad", 30);
        Par<String, Integer> par2 = new Par<>("Edad", 30);
        Par<String, Integer> par3 = new Par<>("Altura", 180);

        System.out.println(par1.esIgual(par2));  // true
        System.out.println(par1.esIgual(par3));  // false
    }
}
