import javax.swing.*;
import java.awt.*;

public class PanelGrafico extends JPanel {

    private int[] temperaturas = new int[7];
    private boolean dibujar = false;

    public void setTemperaturas(int[] t) {
        temperaturas = t;
        dibujar = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!dibujar) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 50;
        int separacion = 60;

        for (int i = 0; i < 6; i++) {
            int x1 = x + i * separacion;
            int y1 = 300 - temperaturas[i];

            int x2 = x + (i + 1) * separacion;
            int y2 = 300 - temperaturas[i + 1];

            g2.drawLine(x1, y1, x2, y2);
            g2.fillOval(x1 - 3, y1 - 3, 7, 7);
        }

        // último punto
        g2.fillOval(x + 6 * separacion - 3, 300 - temperaturas[6] - 3, 7, 7);
    }
}
