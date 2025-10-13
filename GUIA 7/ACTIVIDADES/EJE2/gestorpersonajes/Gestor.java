package gestorpersonajes;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Gestor {
    private List<Personaje> personajes;
    private File archivo;

    public Gestor(String nombreArchivo) {
        archivo = new File(nombreArchivo);
        personajes = new ArrayList<>();
        cargarDesdeArchivo();
        cargarPersonajesAleatorios(); // Cargar algunos al inicio
    }

    // --- MÉTODOS BÁSICOS ---
    private void cargarDesdeArchivo() {
        if (!archivo.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Personaje p = Personaje.fromString(linea);
                if (p != null) personajes.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) pw.println(p.toString());
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void agregarPersonaje(Personaje nuevo) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                System.out.println("El personaje ya existe.");
                return;
            }
        }
        personajes.add(nuevo);
        guardarEnArchivo();
        System.out.println("Personaje agregado correctamente.");
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("Personajes registrados:");
        for (Personaje p : personajes) {
            System.out.println(p.getNombre() + " [Vida:" + p.getVida() + 
                ", Ataque:" + p.getAtaque() +
                ", Defensa:" + p.getDefensa() +
                ", Alcance:" + p.getAlcance() + 
                ", Nivel:" + p.getNivel() + "]");
        }
    }

    public void borrarPersonaje(String nombre) {
        if (personajes.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre))) {
            guardarEnArchivo();
            System.out.println("Personaje borrado correctamente.");
        } else {
            System.out.println("No se encontró el personaje.");
        }
    }

    public void modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                guardarEnArchivo();
                System.out.println("Personaje modificado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el personaje.");
    }

    // --- NUEVAS FUNCIONALIDADES ---

    // 1️⃣ Filtrar por atributo
    public void filtrarPor(String atributo) {
        Comparator<Personaje> comparador;
        switch (atributo.toLowerCase()) {
            case "vida": comparador = Comparator.comparing(Personaje::getVida).reversed(); break;
            case "ataque": comparador = Comparator.comparing(Personaje::getAtaque).reversed(); break;
            case "defensa": comparador = Comparator.comparing(Personaje::getDefensa).reversed(); break;
            case "alcance": comparador = Comparator.comparing(Personaje::getAlcance).reversed(); break;
            default:
                System.out.println("Atributo no válido.");
                return;
        }
        List<Personaje> ordenados = personajes.stream().sorted(comparador).collect(Collectors.toList());
        ordenados.forEach(p -> System.out.println(p.getNombre() + " -> " + atributo + ": " + valorDe(p, atributo)));
    }

    private int valorDe(Personaje p, String atributo) {
        return switch (atributo.toLowerCase()) {
            case "vida" -> p.getVida();
            case "ataque" -> p.getAtaque();
            case "defensa" -> p.getDefensa();
            case "alcance" -> p.getAlcance();
            default -> 0;
        };
    }

    // 2️⃣ Cargar personajes aleatorios
    public void cargarPersonajesAleatorios() {
        if (!personajes.isEmpty()) return; // Solo si el archivo estaba vacío
        String[] nombres = {"Caballero", "Guerrero", "Arquero", "Mago", "Ladrón"};
        Random r = new Random();
        for (String n : nombres) {
            personajes.add(new Personaje(n, 2 + r.nextInt(5), 2 + r.nextInt(5),
                    1 + r.nextInt(4), 1 + r.nextInt(8)));
        }
        guardarEnArchivo();
        System.out.println("Personajes aleatorios cargados automáticamente.");
    }

    // 3️⃣ Actualizar solo un atributo
    public void actualizarAtributo(String nombre, String atributo, int nuevoValor) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                switch (atributo.toLowerCase()) {
                    case "vida" -> p.setVida(nuevoValor);
                    case "ataque" -> p.setAtaque(nuevoValor);
                    case "defensa" -> p.setDefensa(nuevoValor);
                    case "alcance" -> p.setAlcance(nuevoValor);
                    default -> {
                        System.out.println("Atributo no válido.");
                        return;
                    }
                }
                guardarEnArchivo();
                System.out.println("Atributo actualizado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el personaje.");
    }

    // 4️⃣ Mostrar estadísticas
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        double promVida = personajes.stream().mapToInt(Personaje::getVida).average().orElse(0);
        double promAtaque = personajes.stream().mapToInt(Personaje::getAtaque).average().orElse(0);
        double promDefensa = personajes.stream().mapToInt(Personaje::getDefensa).average().orElse(0);
        double promAlcance = personajes.stream().mapToInt(Personaje::getAlcance).average().orElse(0);

        System.out.println("\n--- ESTADÍSTICAS ---");
        System.out.println("Total de personajes: " + personajes.size());
        System.out.printf("Promedio Vida: %.2f | Ataque: %.2f | Defensa: %.2f | Alcance: %.2f%n",
                promVida, promAtaque, promDefensa, promAlcance);
    }

    // 5️⃣ Importar personajes desde otro archivo
    public void importarDesdeArchivo(String ruta) {
        File archivoImport = new File(ruta);
        if (!archivoImport.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivoImport))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Personaje p = Personaje.fromString(linea);
                if (p != null) personajes.add(p);
            }
            guardarEnArchivo();
            System.out.println("Personajes importados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    // 6️⃣ Subir de nivel
    public void subirNivel(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.subirNivel();
                guardarEnArchivo();
                System.out.println("El personaje " + nombre + " subió al nivel " + p.getNivel());
                return;
            }
        }
        System.out.println("No se encontró el personaje.");
    }
}
