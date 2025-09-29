package Sesion05.Actividades.Act02;
public class Pila<E> {
    private final int tamanio;
    private int superior;
    private E[] elementos;
    public Pila() {
        this(10);
    }
    @SuppressWarnings("unchecked")
    public Pila(int s) {
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elementos = (E[]) new Object[tamanio];
    }
    public void push(E valorAMeter) throws ExcepcionPilaLlena {
        if (superior == tamanio - 1) {
            throw new ExcepcionPilaLlena("La pila está llena.");
        }
        elementos[++superior] = valorAMeter;
    }
    public E pop() throws ExcepcionPilaVacia {
        if (superior == -1) {
            throw new ExcepcionPilaVacia("La pila está vacía.");
        }
        return elementos[superior--];
    }
    // Método contains
    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        try {
            Pila<Integer> pila = new Pila<>(5);
            pila.push(1);
            pila.push(2);
            pila.push(3);
            System.out.println(pila.contains(2)); // true
            System.out.println(pila.contains(4)); // false
        } catch (ExcepcionPilaLlena e) {
        }
    }
}



