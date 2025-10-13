package contador;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class ContadorPalabras {

    public static void main(String[] args) {
        File archivo = seleccionarArchivo();

        while (archivo == null || !archivo.exists() || !archivo.isFile()) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ Archivo inválido o inexistente.\nPor favor selecciona otro archivo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            archivo = seleccionarArchivo();
        }

        procesarArchivo(archivo);
    }

    // Método para abrir un JFileChooser y devolver el archivo seleccionado
    private static File seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona el archivo de texto a analizar");
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    // Método principal que realiza el conteo
    private static void procesarArchivo(File archivo) {
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                totalLineas++;

                // Contar caracteres (sin incluir salto de línea)
                totalCaracteres += linea.length();

                // Tokenizar palabras
                StringBuilder palabraActual = new StringBuilder();

                for (char c : linea.toCharArray()) {
                    if (Character.isLetterOrDigit(c)) {
                        palabraActual.append(Character.toLowerCase(c));
                    } else if (palabraActual.length() > 0) {
                        String palabra = palabraActual.toString();
                        totalPalabras++;
                        frecuenciaPalabras.put(palabra,
                                frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                        palabraActual.setLength(0);
                    }
                }

                // Captura la última palabra si la línea no termina en espacio o signo
                if (palabraActual.length() > 0) {
                    String palabra = palabraActual.toString();
                    totalPalabras++;
                    frecuenciaPalabras.put(palabra,
                            frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                }
            }

            // Calcular promedio
            double promedioPalabras = (totalLineas > 0)
                    ? (double) totalPalabras / totalLineas
                    : 0.0;

            // Mostrar resultados
            System.out.println("\n--- RESULTADOS DEL ANÁLISIS ---");
            System.out.println("Archivo: " + archivo.getName());
            System.out.println("Total de líneas: " + totalLineas);
            System.out.println("Total de palabras: " + totalPalabras);
            System.out.println("Total de caracteres: " + totalCaracteres);
            System.out.printf("Promedio de palabras por línea: %.2f%n", promedioPalabras);

            // Palabras más frecuentes
            System.out.println("\n--- PALABRAS MÁS FRECUENTES ---");
            List<Entry<String, Integer>> listaFrecuencia = new ArrayList<>(frecuenciaPalabras.entrySet());

            // Ordenar por frecuencia descendente
            listaFrecuencia.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            // Mostrar las 10 más frecuentes
            int limite = Math.min(10, listaFrecuencia.size());
            for (int i = 0; i < limite; i++) {
                Entry<String, Integer> e = listaFrecuencia.get(i);
                System.out.println(e.getKey() + " → " + e.getValue() + " veces");
            }

        } catch (FileNotFoundException e) {
            System.err.println("⚠️ No se encontró el archivo: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("⚠️ Error al leer el archivo: " + e.getMessage());
        }
    }
}
