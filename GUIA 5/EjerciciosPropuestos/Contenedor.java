package Sesion05.EjerciciosPropuestos;

import java.util.ArrayList;

public class Contenedor<F, S> {
    private ArrayList<Par<F, S>> pares;

    // Constructor
    public Contenedor() {
        pares = new ArrayList<>();
    }

    // Método para agregar un nuevo par
    public void agregarPar(F primero, S segundo) {
        Par<F, S> nuevoPar = new Par<>(primero, segundo);
        pares.add(nuevoPar);
    }

    // Método para obtener un par en una posición específica
    public Par<F, S> obtenerPar(int indice) {
        return pares.get(indice);
    }

    // Método para obtener todos los pares
    public ArrayList<Par<F, S>> obtenerTodosLosPares() {
        return pares;
    }

    // Método para mostrar todos los pares almacenados
    public void mostrarPares() {
        for (Par<F, S> par : pares) {
            System.out.println(par);
        }
    }

    public static void main(String[] args) {
        Contenedor<String, Integer> contenedor = new Contenedor<>();
        
        // Agregando pares
        contenedor.agregarPar("Edad", 25);
        contenedor.agregarPar("Altura", 180);

        // Mostrando todos los pares
        contenedor.mostrarPares();

        // Obteniendo un par específico
        Par<String, Integer> parObtenido = contenedor.obtenerPar(0);
        System.out.println("Par obtenido: " + parObtenido);
    }
}
