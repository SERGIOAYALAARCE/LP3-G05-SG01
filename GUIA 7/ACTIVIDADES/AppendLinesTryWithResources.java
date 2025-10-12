import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AppendLinesTryWithResources {
    private static final String RUTA = "archivos/datos.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe líneas para añadir a 'archivos/datos.txt'. Escribe 'SALIR' para terminar.");

        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA, true))) {
            while (true) {
                String linea = sc.nextLine();
                if ("SALIR".equalsIgnoreCase(linea)) {
                    System.out.println("Guardado finalizado.");
                    break;
                }
                pw.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo en el archivo: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
