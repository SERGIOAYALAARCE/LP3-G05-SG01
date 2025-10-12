import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FileViewerSwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileViewerSwing::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Visualizador de archivo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        JButton loadBtn = new JButton("Cargar archivo (archivos/TestFile.java)");
        loadBtn.addActionListener(e -> {
            File f = new File("archivos/TestFile.java");
            if (!f.exists()) {
                JOptionPane.showMessageDialog(frame, "No existe archivos/TestFile.java.\nColoca el archivo o usa otro programa con JFileChooser.", "Archivo no encontrado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try (FileInputStream fis = new FileInputStream(f);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader br = new BufferedReader(isr)) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                textArea.setText(sb.toString());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error leyendo el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(loadBtn, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
