package ejerciciosfiles;
import java.io.*;
import java.util.*;
public class Gestor {
    private List<Personaje> personajes;
    private File archivo;

    public Gestor(String nombreArchivo) {
        archivo = new File(nombreArchivo);
        personajes = new ArrayList<>();
        cargarDesdeArchivo();
    }
    // Carga los personajes desde el archivo de texto
    private void cargarDesdeArchivo() {
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String nombre = datos[0];
                    int vida = Integer.parseInt(datos[1]);
                    int ataque = Integer.parseInt(datos[2]);
                    int defensa = Integer.parseInt(datos[3]);
                    int alcance = Integer.parseInt(datos[4]);
                    personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    // Guarda los personajes en el archivo
    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                pw.println(p.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    // Añadir personaje (si no existe)
    public void agregarPersonaje(Personaje nuevo) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                System.out.println("El personaje '" + nuevo.getNombre() + "' ya existe.");
                return;
            }
        }
        personajes.add(nuevo);
        guardarEnArchivo();
        System.out.println("Personaje agregado correctamente.");
    }

    // Mostrar todos los personajes
    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("Personajes registrados:");
        for (Personaje p : personajes) {
            System.out.println(p.getNombre() + " -> Vida: " + p.getVida() +
                               ", Ataque: " + p.getAtaque() +
                               ", Defensa: " + p.getDefensa() +
                               ", Alcance: " + p.getAlcance());
        }
    }

    // Modificar personaje por nombre
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
        System.out.println("No se encontró el personaje con nombre: " + nombre);
    }

    // Borrar personaje por nombre
    public void borrarPersonaje(String nombre) {
        Iterator<Personaje> it = personajes.iterator();
        while (it.hasNext()) {
            Personaje p = it.next();
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                it.remove();
                guardarEnArchivo();
                System.out.println("Personaje borrado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró el personaje con nombre: " + nombre);
    }
}
