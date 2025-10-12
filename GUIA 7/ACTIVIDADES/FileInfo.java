import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;

public class FileInfo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un archivo o directorio (ej: archivos/miarchivo.txt):");
        String input = sc.nextLine().trim();

        Path p = Paths.get(input);
        try {
            System.out.println("Nombre: " + (p.getFileName() != null ? p.getFileName().toString() : ""));
            System.out.println("Es ruta absoluta?: " + p.isAbsolute());
            System.out.println("Ruta (relativa): " + p);
            System.out.println("Ruta absoluta: " + p.toAbsolutePath());

            System.out.println("Existe?: " + Files.exists(p));
            System.out.println("Es archivo?: " + Files.isRegularFile(p));
            System.out.println("Es directorio?: " + Files.isDirectory(p));

            if (Files.exists(p)) {
                FileTime lastMod = Files.getLastModifiedTime(p);
                System.out.println("Última modificación: " + lastMod.toString());

                try {
                    long size = Files.size(p);
                    System.out.println("Tamaño (bytes): " + size);
                } catch (IOException e) {
                    System.out.println("No se pudo obtener el tamaño: " + e.getMessage());
                }

                // Si es directorio, listar contenido
                if (Files.isDirectory(p)) {
                    System.out.println("\nContenido del directorio:");
                    try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)) {
                        for (Path entry : ds) {
                            System.out.println(" - " + entry.getFileName());
                        }
                    } catch (IOException e) {
                        System.out.println("Error listando directorio: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("El path no existe en el disco.");
            }
        } catch (IOException ex) {
            System.out.println("Error al obtener información del archivo: " + ex.getMessage());
        } finally {
            sc.close();
        }
    }
}
