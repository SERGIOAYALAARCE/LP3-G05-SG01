package Sesion05.Actividades.Act02;
public class Pila1<E> {
    private final int tamanio;  // número de elementos en la pila
    private int superior;       // ubicación del elemento superior
    private E[] elementos;      // arreglo que almacena los elementos de la pila
    // Constructor sin argumentos, crea una pila del tamaño predeterminado
    public Pila1() {
        this(10);
    }
    // Constructor que crea una pila del número especificado de elementos
    public Pila1(int s) {
        tamanio = s > 0 ? s : 10;  // establece el tamaño de la pila
        superior = -1;             // al principio, la pila está vacía
        elementos = (E[]) new Object[tamanio];  // crea el arreglo
    }
    // Método para agregar un elemento a la pila
    public void push(E valorAMeter) throws ExcepcionPilaLlena {
        if (superior == tamanio - 1) {  // si la pila está llena
            throw new ExcepcionPilaLlena("La pila está llena.");
        }
        elementos[++superior] = valorAMeter;  // mete valorAMeter en la pila
    }
    // Método para eliminar el elemento en la cima de la pila
    public E pop() throws ExcepcionPilaVacia {
        if (superior == -1) {  // si la pila está vacía
            throw new ExcepcionPilaVacia("La pila está vacía.");
        }
        return elementos[superior--];  // elimina y devuelve el último elemento
    }
    // Método que comprueba si un elemento está en la pila
    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    // Método que compara dos pilas para verificar si tienen los mismos elementos en el mismo orden
    public boolean esIgual(Pila1<E> otraPila) {
        if (this.superior != otraPila.superior) {
            return false;  // Si tienen diferente número de elementos, no son iguales
        }
        // Compara elemento por elemento
        for (int i = 0; i <= this.superior; i++) {
            if (!this.elementos[i].equals(otraPila.elementos[i])) {
                return false;  // Si algún elemento es diferente, las pilas no son iguales
            }
        }
        return true;  // Si todos los elementos son iguales, las pilas son iguales
    }
    // Método main para probar la funcionalidad de las pilas y esIgual
    public static void main(String[] args) {
        try {
            Pila1<Integer> pila1 = new Pila1<>(5);
            Pila1<Integer> pila2 = new Pila1<>(5);
            // Añadiendo elementos a ambas pilas
            pila1.push(1);
            pila1.push(2);
            pila1.push(3);
            pila2.push(1);
            pila2.push(2);
            pila2.push(3);
            // Comparando pilas
            System.out.println("¿Son iguales las dos pilas?: " + pila1.esIgual(pila2));  // Debería imprimir true
            // Añadiendo un elemento diferente a una de las pilas
            pila2.push(4);
            System.out.println("¿Son iguales las dos pilas?: " + pila1.esIgual(pila2));  // Debería imprimir false
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

