import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.io.File;

public class VideoExample {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Reproducción de Video");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        frame.setVisible(true);

        Platform.runLater(() -> {
            try {
                String videoPath = new File("video.mp4").toURI().toString();
                MediaPlayer mp = new MediaPlayer(new Media(videoPath));
                MediaView mv = new MediaView(mp);

                jfxPanel.setScene(new Scene(new javafx.scene.Group(mv)));
                mp.play();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
