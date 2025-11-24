import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class AppMusica {

    static Clip clip;
    static long pausa = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Reproductor de Música");
        frame.setSize(300, 200);
        frame.setLayout(new java.awt.FlowLayout());

        JButton play = new JButton("Reproducir");
        JButton pause = new JButton("Pausar");
        JButton resume = new JButton("Reanudar");

        play.addActionListener(e -> {
            try {
                File file = new File("musica.wav");
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
                clip.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        pause.addActionListener(e -> {
            pausa = clip.getMicrosecondPosition();
            clip.stop();
        });

        resume.addActionListener(e -> {
            clip.setMicrosecondPosition(pausa);
            clip.start();
        });

        frame.add(play);
        frame.add(pause);
        frame.add(resume);

        frame.setVisible(true);
    }
}
