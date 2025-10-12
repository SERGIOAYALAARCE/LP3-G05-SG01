import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;

public class FileChooserInfoGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileChooserInfoGUI::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Info archivo con JFileChooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JTextArea out = new JTextArea();
        out.setEditable(false);
        JScrollPane scroll = new JScrollPane(out);

        JButton chooseBtn = new JButton("Seleccionar archivo/directorio");
        chooseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int r = chooser.showOpenDialog(frame);
            if (r == JFileChooser.APPROVE_OPTION) {
                Path p = chooser.getSelectedFile().toPath();
                StringBuilder sb = new StringBuilder();
                sb.append("Nombre: ").append(p.getFileName()).append("\n");
                sb.append("Ruta: ").append(p).append("\n");
                sb.append("Ruta absoluta: ").append(p.toAbsolutePath()).append("\n");
                sb.append("Existe?: ").append(Files.exists(p)).append("\n");
                sb.append("Es directorio?: ").append(Files.isDirectory(p)).append("\n");
                sb.append("Es archivo?: ").append(Files.isRegularFile(p)).append("\n");
                try {
                    FileTime ft = Files.getLastModifiedTime(p);
                    sb.append("Última modificación: ").append(ft).append("\n");
                } catch (IOException ex) {
                    sb.append("No se pudo obtener la última modificación: ").append(ex.getMessage()).append("\n");
                }
                try {
                    long size = Files.size(p);
                    sb.append("Tamaño (bytes): ").append(size).append("\n");
                } catch (IOException ex) {
                    sb.append("No se pudo obtener tamaño: ").append(ex.getMessage()).append("\n");
                }
                if (Files.isDirectory(p)) {
                    sb.append("\nContenido:\n");
                    try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)) {
                        for (Path entry : ds) {
                            sb.append(" - ").append(entry.getFileName()).append("\n");
                        }
                    } catch (IOException ex) {
                        sb.append("Error listando: ").append(ex.getMessage()).append("\n");
                    }
                }
                out.setText(sb.toString());
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(chooseBtn, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
