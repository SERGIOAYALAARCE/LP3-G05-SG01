package graficos2d;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class AppGraficos2D extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convertimos Graphics a Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        int rectWidth = 100;
        int rectHeight = 50;

        // RECTÁNGULO ORIGINAL
        g2d.setColor(Color.GRAY);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Guardamos el transform original
        AffineTransform originalTransform = g2d.getTransform();

        // --- TRASLACIÓN ---
        g2d.translate(200, 0);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos
        g2d.setTransform(originalTransform);

        // --- ROTACIÓN ---
        g2d.translate(0, 150); // Movemos hacia abajo
        g2d.rotate(Math.toRadians(45), 100, 75); // Rotación de 45°
        g2d.setColor(Color.GREEN);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos
        g2d.setTransform(originalTransform);

        // --- ESCALADO ---
        g2d.translate(200, 150);
        g2d.scale(1.5, 0.5); // Escala X1.5 y Y0.5
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos
        g2d.setTransform(originalTransform);

        // --- SESGADO (SHEAR) ---
        g2d.translate(0, 300);
        g2d.shear(0.5, 0); // Sesgado horizontal
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(50, 50, rectWidth, rectHeight);

        // Restauramos al final
        g2d.setTransform(originalTransform);
    }

    // MAIN
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo de Transformaciones con Graphics2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        frame.add(new AppGraficos2D());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
