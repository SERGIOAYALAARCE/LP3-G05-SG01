public class Main {
    // Método genérico estático para imprimir un Par<F, S>
    public static <F, S> void imprimirPar(Par<F, S> par) {
        System.out.println(par);
    }

    public static void main(String[] args) {
        // 1) Par<String, Integer>
        Par<String, Integer> p1 = new Par<>("Hola", 123);
        imprimirPar(p1);

        // 2) Par<Double, Boolean>
        Par<Double, Boolean> p2 = new Par<>(3.14159, true);
        imprimirPar(p2);

        // 3) Par<Persona, Integer>
        Persona ana = new Persona("Ana", 21);
        Par<Persona, Integer> p3 = new Par<>(ana, 1001);
        imprimirPar(p3);

        // Demo rápida de Contenedor<F, S>
        Contenedor<String, Integer> cont = new Contenedor<>();
        cont.agregarPar("X", 10);
        cont.agregarPar("Y", 20);
        cont.agregarPar("Z", 30);
        System.out.println("\nContenido del contenedor:");
        cont.mostrarPares();
    }
}
