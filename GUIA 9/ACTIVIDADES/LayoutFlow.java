import javax.swing.*;
import java.awt.*;

public class LayoutFlow extends JFrame {

    public LayoutFlow() {
        super("FlowLayout - Integrantes: Roy Santos y Sergio Ayala");

        setLayout(new FlowLayout());

        add(new JLabel("Etiqueta 1"));
        add(new JButton("Botón 1"));
        add(new JButton("Botón 2"));
        add(new JTextField("Campo 1", 10));
        add(new JButton("Botón 3"));
        add(new JLabel("Etiqueta 2"));

        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LayoutFlow();
    }
}
