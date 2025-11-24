import javax.swing.*;

public class AppTemperaturas {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Temperaturas Semanales");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField[] txtTemps = new JTextField[7];
        String[] dias = {"Lun","Mar","Mie","Jue","Vie","Sab","Dom"};

        JPanel panelInputs = new JPanel();
        for (int i = 0; i < 7; i++) {
            txtTemps[i] = new JTextField(3);
            panelInputs.add(new JLabel(dias[i]));
            panelInputs.add(txtTemps[i]);
        }

        PanelGrafico panel = new PanelGrafico();
        JButton btnMostrar = new JButton("Mostrar Gráfico");

        btnMostrar.addActionListener(e -> {
            try {
                int[] t = new int[7];
                for (int i = 0; i < 7; i++) t[i] = Integer.parseInt(txtTemps[i].getText());
                panel.setTemperaturas(t);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Datos inválidos");
            }
        });

        frame.add(panelInputs, "North");
        frame.add(panel, "Center");
        frame.add(btnMostrar, "South");

        frame.setVisible(true);
    }
}
