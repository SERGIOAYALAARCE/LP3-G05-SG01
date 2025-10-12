import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class MatrixToBinary {
    private static final String RUTA = "archivos/matriz.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Número de filas: ");
        int filas = sc.nextInt();
        System.out.print("Número de columnas: ");
        int cols = sc.nextInt();

        double[][] m = new double[filas][cols];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("Valor [%d][%d]: ", i, j);
                m[i][j] = sc.nextDouble();
            }
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(RUTA))) {
            dos.writeInt(filas);
            dos.writeInt(cols);
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    dos.writeDouble(m[i][j]);
                }
            }
            System.out.println("Matriz guardada en " + RUTA);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo binario: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
