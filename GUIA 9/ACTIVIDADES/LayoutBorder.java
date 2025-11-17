import javax.swing.*;
import java.awt.*;

public class LayoutBorder extends JFrame {

    public LayoutBorder() {
        super("BorderLayout - Integrantes: Roy Santos y Sergio Ayala");

        setLayout(new BorderLayout());

        add(new JButton("NORTE"), BorderLayout.NORTH);
        add(new JButton("SUR"), BorderLayout.SOUTH);
        add(new JButton("ESTE"), BorderLayout.EAST);
        add(new JButton("OESTE"), BorderLayout.WEST);
        add(new JLabel("CENTRO", SwingConstants.CENTER), BorderLayout.CENTER);

        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LayoutBorder();
    }
}
