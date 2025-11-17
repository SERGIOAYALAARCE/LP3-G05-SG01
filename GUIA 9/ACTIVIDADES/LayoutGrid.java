import javax.swing.*;
import java.awt.*;

public class LayoutGrid extends JFrame {

    public LayoutGrid() {
        super("GridLayout - Integrantes: Roy Santos y Sergio Ayala");

        setLayout(new GridLayout(2, 3)); // 2 filas, 3 columnas

        add(new JButton("Botón 1"));
        add(new JButton("Botón 2"));
        add(new JButton("Botón 3"));
        add(new JButton("Botón 4"));
        add(new JButton("Botón 5"));
        add(new JButton("Botón 6"));

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LayoutGrid();
    }
}
