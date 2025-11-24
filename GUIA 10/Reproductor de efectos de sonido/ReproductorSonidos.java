import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class AppSonidos {

    public static void reproducir(String ruta) {
        try {
            File f = new File(ruta);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Efectos de Sonido");
        frame.setLayout(new java.awt.FlowLayout());
        frame.setSize(300, 200);

        JButton aplausos = new JButton("Aplausos");
        JButton campana = new JButton("Campana");
        JButton explosion = new JButton("Explosión");

        aplausos.addActionListener(e -> reproducir("aplausos.wav"));
        campana.addActionListener(e -> reproducir("campana.wav"));
        explosion.addActionListener(e -> reproducir("explosion.wav"));

        frame.add(aplausos);
        frame.add(campana);
        frame.add(explosion);

        frame.setVisible(true);
    }
}
