package eje3;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.sound.sampled.*;

public class ReproductorEfectosSonido extends JFrame {

    public ReproductorEfectosSonido() {
        setTitle("Reproductor de Efectos de Sonido");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        // Cargar imágenes desde el paquete
        JButton btnAplausos = new JButton(new ImageIcon(getClass().getResource("/eje3/aplausos.png")));
        JButton btnCampana = new JButton(new ImageIcon(getClass().getResource("/eje3/campana.png")));
        JButton btnExplosion = new JButton(new ImageIcon(getClass().getResource("/eje3/explosion.png")));

        // Listeners
        btnAplausos.addActionListener(e -> reproducirSonido("/eje3/aplausos.wav"));
        btnCampana.addActionListener(e -> reproducirSonido("/eje3/campana.wav"));
        btnExplosion.addActionListener(e -> reproducirSonido("/eje3/explosion.wav"));

        add(btnAplausos);
        add(btnCampana);
        add(btnExplosion);
    }

    private void reproducirSonido(String nombreArchivo) {
        try {
            URL recurso = getClass().getResource(nombreArchivo);
            if (recurso == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el archivo: " + nombreArchivo);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(recurso);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al reproducir el sonido: " + nombreArchivo + "\n" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReproductorEfectosSonido().setVisible(true));
    }
}
