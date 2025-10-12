import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendaSearch {
    private static final String RUTA = "archivos/agenda.txt";

    public static void main(String[] args) {
        List<PersonaAgenda> lista = new ArrayList<>();
        File f = new File(RUTA);
        if (!f.exists()) {
            System.out.println("No se encontró " + RUTA + ". Crea el archivo con líneas: Nombre;Telefono;Direccion");
            return;
        }

        try (Scanner fileScanner = new Scanner(f)) {
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine().trim();
                if (linea.isEmpty()) continue;
                String[] partes = linea.split(";");
                if (partes.length >= 3) {
                    String nombre = partes[0].trim();
                    String telefono = partes[1].trim();
                    String direccion = partes[2].trim();
                    lista.add(new PersonaAgenda(nombre, telefono, direccion));
                } else {
                    System.out.println("Línea con formato inválido (se ignorará): " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Agenda cargada con " + lista.size() + " contactos.");
        System.out.print("Introduce el nombre a buscar: ");
        String busq = sc.nextLine().trim().toLowerCase();

        boolean encontrado = false;
        for (PersonaAgenda p : lista) {
            if (p.getNombre().toLowerCase().contains(busq)) {
                System.out.println("-> " + p);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron coincidencias para: " + busq);
        }
        sc.close();
    }
}

class PersonaAgenda {
    private String nombre;
    private String telefono;
    private String direccion;

    public PersonaAgenda(String nombre, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s | Tel: %s | Dir: %s", nombre, telefono, direccion);
    }
}
