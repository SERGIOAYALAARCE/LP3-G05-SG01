package eje4;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.net.URL;

public class ControladorMusica extends JFrame {

    private Clip clip;
    private long pausaPosition = 0; // Guardar la posición de pausa

    public ControladorMusica() {
        setTitle("Controlador de Música");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        // Crear botones con imágenes desde el paquete eje4
        JButton btnReproducir = new JButton(new ImageIcon(getClass().getResource("/eje4/reproducir.png")));
        JButton btnPausar = new JButton(new ImageIcon(getClass().getResource("/eje4/pausar.png")));
        JButton btnReanudar = new JButton(new ImageIcon(getClass().getResource("/eje4/reanudar.png")));

        // Agregar listeners
        btnReproducir.addActionListener(e -> reproducirMusica("/eje4/musica.wav"));
        btnPausar.addActionListener(e -> pausarMusica());
        btnReanudar.addActionListener(e -> reanudarMusica());

        // Añadir botones al frame
        add(btnReproducir);
        add(btnPausar);
        add(btnReanudar);
    }

    private void reproducirMusica(String nombreArchivo) {
        try {
            // Si ya hay un clip cargado, detenerlo para reproducir desde el inicio
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            URL recurso = getClass().getResource(nombreArchivo);
            if (recurso == null) {
                JOptionPane.showMessageDialog(this, "Archivo no encontrado: " + nombreArchivo);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(recurso);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            pausaPosition = 0; // Reiniciar posición de pausa
            clip.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al reproducir la música:\n" + ex.getMessage());
        }
    }

    private void pausarMusica() {
        if (clip != null && clip.isRunning()) {
            pausaPosition = clip.getMicrosecondPosition(); // Guardar posición actual
            clip.stop();
        }
    }

    private void reanudarMusica() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(pausaPosition); // Reanudar desde posición guardada
            clip.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ControladorMusica().setVisible(true));
    }
}
