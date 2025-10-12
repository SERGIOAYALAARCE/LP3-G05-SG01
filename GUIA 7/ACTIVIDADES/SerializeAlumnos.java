import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeAlumnos {
    private static final String RUTA = "archivos/alumnos.dat";

    public static void main(String[] args) {
        // Crear 3 objetos Alumno y serializarlos
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Carlos Perez", new Fecha(10, 5, 2000), 12345));
        alumnos.add(new Alumno("Ana Gomez", new Fecha(2, 11, 1999), 67890));
        alumnos.add(new Alumno("María Díaz", new Fecha(29, 2, 2004), 54321));

        // Escribir objetos
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA))) {
            for (Alumno a : alumnos) {
                oos.writeObject(a);
            }
            System.out.println("Se escribieron " + alumnos.size() + " objetos Alumno en " + RUTA);
        } catch (IOException e) {
            System.out.println("Error escribiendo objetos: " + e.getMessage());
        }

        // Leer objetos
        System.out.println("\nLeyendo objetos desde el archivo:");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Alumno) {
                        System.out.println((Alumno) obj);
                    }
                } catch (EOFException eof) {
                    break; // fin del archivo
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo objetos: " + e.getMessage());
        }
    }
}

class Fecha implements Serializable {
    private static final long serialVersionUID = 1L;
    private int dia, mes, anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia; this.mes = mes; this.anio = anio;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, anio);
    }
}

class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String nombre;
    protected Fecha fechaNacimiento;

    public Persona(String nombre, Fecha fechaNacimiento) {
        this.nombre = nombre; this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return nombre + " (" + fechaNacimiento + ")";
    }
}

class Alumno extends Persona {
    private static final long serialVersionUID = 1L;
    private int codigo;

    public Alumno(String nombre, Fecha f, int codigo) {
        super(nombre, f);
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return String.format("Alumno{nombre=%s, codigo=%d, fecha=%s}", nombre, codigo, fechaNacimiento);
    }
}
