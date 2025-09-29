import java.util.Objects;
import java.util.Scanner;
public class MainPilasMenu {
    // ===== Clase genérica Pila<E> =====
    public static class Pila<E> {
        private final int tamanio;
        private int superior;      // -1 si está vacía
        private E[] elementos;
        @SuppressWarnings("unchecked")
        public Pila(int s) {
            this.tamanio = s > 0 ? s : 10;
            this.superior = -1;
            this.elementos = (E[]) new Object[this.tamanio];
        }
        public void push(E valor) {
            if (superior == tamanio - 1) throw new ExcepcionPilaLlena("La Pila está llena");
            elementos[++superior] = valor;
        }
        public E pop() {
            if (superior == -1) throw new ExcepcionPilaVacia("Pila vacía");
            return elementos[superior--];
        }
        public boolean estaVacia() { return superior == -1; }
        public int size() { return superior + 1; }
        /** 
         * Compara esta pila con otra: mismo tamaño y mismos elementos en el mismo orden (fondo → tope).
         * No modifica el estado de ninguna pila.
         */
        public boolean esIgual(Pila<E> otraPila) {
            if (otraPila == null) return false;
            if (this.superior != otraPila.superior) return false; // compara tamaños
            for (int i = 0; i <= this.superior; i++) {
                if (!Objects.equals(this.elementos[i], otraPila.elementos[i])) return false;
            }
            return true;
        }
        /** Muestra del fondo (0) al tope (superior), sin modificar. */
        public void mostrar() {
            if (estaVacia()) { System.out.println("[Pila vacía]"); return; }
            System.out.print("[fondo] ");
            for (int i = 0; i <= superior; i++) {
                System.out.print(elementos[i]);
                if (i < superior) System.out.print(" -> ");
            }
            System.out.println(" [tope]");
        }
        // Excepciones
        public static class ExcepcionPilaLlena extends RuntimeException { public ExcepcionPilaLlena(String m){super(m);} }
        public static class ExcepcionPilaVacia extends RuntimeException { public ExcepcionPilaVacia(String m){super(m);} }
    }
    // ===== Menú =====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pila<Integer> pilaA = new Pila<>(10);
        Pila<Integer> pilaB = new Pila<>(10);

        while (true) {
            System.out.println("\n=== MENÚ PILAS (Integer) ===");
            System.out.println("1) Push en Pila A");
            System.out.println("2) Pop  en Pila A");
            System.out.println("3) Push en Pila B");
            System.out.println("4) Pop  en Pila B");
            System.out.println("5) Mostrar A y B");
            System.out.println("6) Comparar A y B con esIgual");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String in = sc.nextLine().trim();

            int op;
            try { op = Integer.parseInt(in); } catch (NumberFormatException e) { System.out.println("Opción inválida."); continue; }

            try {
                switch (op) {
                    case 1 -> {
                        System.out.print("Valor entero para PUSH en A: ");
                        int v = Integer.parseInt(sc.nextLine().trim());
                        pilaA.push(v);
                        System.out.println("OK: Insertado " + v + " en A.");
                    }
                    case 2 -> System.out.println("Pop A -> " + pilaA.pop());
                    case 3 -> {
                        System.out.print("Valor entero para PUSH en B: ");
                        int v = Integer.parseInt(sc.nextLine().trim());
                        pilaB.push(v);
                        System.out.println("OK: Insertado " + v + " en B.");
                    }
                    case 4 -> System.out.println("Pop B -> " + pilaB.pop());
                    case 5 -> {
                        System.out.println("Pila A:"); pilaA.mostrar();
                        System.out.println("Pila B:"); pilaB.mostrar();
                    }
                    case 6 -> System.out.println("¿A es igual a B? " + (pilaA.esIgual(pilaB) ? "Sí" : "No"));
                    case 0 -> { System.out.println("Saliendo..."); sc.close(); return; }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Pila.ExcepcionPilaLlena | Pila.ExcepcionPilaVacia e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un entero.");
            }
        }
    }
}
