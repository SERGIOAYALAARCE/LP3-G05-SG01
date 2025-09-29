import java.util.ArrayList;
import java.util.List;

public class Contenedor<F, S> {
    private final List<Par<F, S>> pares = new ArrayList<>();

    // Agregar un nuevo par al contenedor
    public void agregarPar(F primero, S segundo) {
        pares.add(new Par<>(primero, segundo));
    }

    // Devolver el par en la posición indicada
    public Par<F, S> obtenerPar(int indice) {
        if (indice < 0 || indice >= pares.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        return pares.get(indice);
    }

    // Devolver la lista completa (vista de solo lectura si prefieres)
    public List<Par<F, S>> obtenerTodosLosPares() {
        return new ArrayList<>(pares);
    }

    // Imprimir todos los pares
    public void mostrarPares() {
        if (pares.isEmpty()) {
            System.out.println("[Contenedor vacío]");
            return;
        }
        for (int i = 0; i < pares.size(); i++) {
            System.out.println(i + ": " + pares.get(i));
        }
    }
}
