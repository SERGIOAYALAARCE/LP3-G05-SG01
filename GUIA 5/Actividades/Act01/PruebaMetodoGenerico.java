package Sesion05.Actividades.Act01;

public class PruebaMetodoGenerico {
    // Método genérico para imprimir un arreglo completo
    public static <E> void imprimirArreglo(E[] arregloEntrada) {
        for (E elemento : arregloEntrada) {
            System.out.printf("%s ", elemento);
        }
        System.out.println(); // Agrega un salto de línea
    }

    // Método sobrecargado para imprimir una parte del arreglo
    public static <E> int imprimirArreglo(E[] arregloEntrada, int subindiceInferior, int subindiceSuperior) throws InvalidSubscriptException {
        if (subindiceInferior < 0 || subindiceSuperior >= arregloEntrada.length || subindiceSuperior < subindiceInferior) {
            throw new InvalidSubscriptException("Índices fuera de rango o inválidos: " + subindiceInferior + ", " + subindiceSuperior);
        }
        for (int i = subindiceInferior; i <= subindiceSuperior; i++) {
            System.out.printf("%s ", arregloEntrada[i]);
        }
        System.out.println(); // Agrega un salto de línea
        return (subindiceSuperior - subindiceInferior + 1);
    }

    public static void main(String[] args) {
        Integer[] arregloInteger = {1, 2, 3, 4, 5, 6};
        try {
            imprimirArreglo(arregloInteger); // Imprime todo el arreglo
            imprimirArreglo(arregloInteger, 1, 4); // Imprime desde el índice 1 al 4
            // Ejemplo de índice inválido
            imprimirArreglo(arregloInteger, -1, 4); // Esto lanzará una excepción
        } catch (InvalidSubscriptException e) {
            System.out.println(e.getMessage());
        }
    }
}
