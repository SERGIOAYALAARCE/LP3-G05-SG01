package eje2;

import javax.swing.*;
import java.awt.*;

public class GraficoTemperaturas extends JFrame {

    private JTextField[] camposTemp;
    private int[] temperaturas = new int[7];
    private PanelGrafico panelGrafico;

    public GraficoTemperaturas() {
        setTitle("Gráfico de Temperaturas Semanales");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        camposTemp = new JTextField[7];

        JPanel panelInputs = new JPanel(new GridLayout(8, 2, 5, 5));
        panelInputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 7; i++) {
            panelInputs.add(new JLabel("Temperatura " + dias[i] + ":"));
            camposTemp[i] = new JTextField();
            panelInputs.add(camposTemp[i]);
        }

        JButton btnMostrar = new JButton("Mostrar Gráfico");
        panelInputs.add(btnMostrar);

        panelGrafico = new PanelGrafico();
        add(panelInputs, BorderLayout.NORTH);
        add(panelGrafico, BorderLayout.CENTER);

        btnMostrar.addActionListener(e -> actualizarGrafico());
    }

    private void actualizarGrafico() {
        try {
            for (int i = 0; i < 7; i++) {
                temperaturas[i] = Integer.parseInt(camposTemp[i].getText());
            }
            panelGrafico.setTemperaturas(temperaturas);
            panelGrafico.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ingresa solo números válidos para las temperaturas.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraficoTemperaturas().setVisible(true));
    }
}

// ======================== PANEL DEL GRÁFICO ========================
class PanelGrafico extends JPanel {

    private int[] temps = new int[7];

    public void setTemperaturas(int[] nuevas) {
        this.temps = nuevas;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int ancho = getWidth();
        int alto = getHeight();

        int margen = 40;
        int espacio = (ancho - 2 * margen) / 6;

        // Ejes
        g.drawLine(margen, alto - margen, ancho - margen, alto - margen); // X
        g.drawLine(margen, margen, margen, alto - margen); // Y

        // Calcular máximo para escalar el gráfico
        int max = 1;
        for (int t : temps) if (t > max) max = t;

        // Dibujar puntos y líneas
        int[] x = new int[7];
        int[] y = new int[7];

        for (int i = 0; i < 7; i++) {
            x[i] = margen + i * espacio;
            y[i] = alto - margen - (temps[i] * (alto - 2 * margen) / max);
        }

        // Líneas
        for (int i = 0; i < 6; i++) {
            g.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
        }

        // Puntos
        for (int i = 0; i < 7; i++) {
            g.fillOval(x[i] - 4, y[i] - 4, 8, 8);
        }

        // Nombres de días
        String[] dias = {"L", "M", "X", "J", "V", "S", "D"};
        for (int i = 0; i < 7; i++) {
            g.drawString(dias[i], x[i] - 5, alto - margen + 15);
        }
    }
}
