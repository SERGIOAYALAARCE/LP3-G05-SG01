package ejeswing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CompraPasajes extends JFrame {

    private JTextField campoNombre, campoDNI, campoFecha;
    private JCheckBox chkAudifonos, chkManta, chkRevistas;
    private JRadioButton rbtnPrimerPiso, rbtnSegundoPiso;
    private JComboBox<String> comboOrigen, comboDestino;
    private JList<String> listaCalidad;
    private JButton btnMostrar, btnLimpiar;

    public CompraPasajes() {
        super("Compra de Pasajes Sergio Ayala Arce y Santos Laquise Roy Roger");
        setLayout(new FlowLayout());

        // Etiquetas y campos de texto
        add(new JLabel("Nombre:"));
        campoNombre = new JTextField(15);
        add(campoNombre);

        add(new JLabel("DNI:"));
        campoDNI = new JTextField(10);
        add(campoDNI);

        add(new JLabel("Fecha de Viaje (dd/mm/aaaa):"));
        campoFecha = new JTextField(10);
        add(campoFecha);

        // Casillas de verificación
        add(new JLabel("Servicios opcionales:"));
        chkAudifonos = new JCheckBox("Audífonos");
        chkManta = new JCheckBox("Manta");
        chkRevistas = new JCheckBox("Revistas");
        add(chkAudifonos);
        add(chkManta);
        add(chkRevistas);

        // Botones de opción
        add(new JLabel("Piso del bus:"));
        rbtnPrimerPiso = new JRadioButton("1er piso", true);
        rbtnSegundoPiso = new JRadioButton("2do piso");
        ButtonGroup grupoPiso = new ButtonGroup();
        grupoPiso.add(rbtnPrimerPiso);
        grupoPiso.add(rbtnSegundoPiso);
        add(rbtnPrimerPiso);
        add(rbtnSegundoPiso);

        // Combo origen y destino
        add(new JLabel("Lugar de origen:"));
        comboOrigen = new JComboBox<>(new String[]{"Lima", "Arequipa", "Cusco", "Piura"});
        add(comboOrigen);

        add(new JLabel("Lugar de destino:"));
        comboDestino = new JComboBox<>(new String[]{"Lima", "Arequipa", "Cusco", "Piura"});
        add(comboDestino);

        // Lista de calidad de servicio
        add(new JLabel("Calidad de servicio:"));
        String[] calidad = {"Económico", "Standard", "VIP"};
        listaCalidad = new JList<>(calidad);
        listaCalidad.setVisibleRowCount(3);
        listaCalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listaCalidad));

        // Botones
        btnMostrar = new JButton("Mostrar Datos");
        btnLimpiar = new JButton("Limpiar");

        add(btnMostrar);
        add(btnLimpiar);

        // Acción del botón Mostrar
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String servicios = "";
                if (chkAudifonos.isSelected()) servicios += "Audífonos ";
                if (chkManta.isSelected()) servicios += "Manta ";
                if (chkRevistas.isSelected()) servicios += "Revistas ";

                String piso = rbtnPrimerPiso.isSelected() ? "1er piso" : "2do piso";

                String calidadSel = listaCalidad.getSelectedValue();
                if (calidadSel == null) calidadSel = "No seleccionado";

                String mensaje = 
                    "=== RESUMEN DEL PASAJE ===\n" +
                    "Nombre: " + campoNombre.getText() + "\n" +
                    "DNI: " + campoDNI.getText() + "\n" +
                    "Fecha de viaje: " + campoFecha.getText() + "\n\n" +
                    "Servicios Opcionales: " + servicios + "\n" +
                    "Piso del bus: " + piso + "\n" +
                    "Origen: " + comboOrigen.getSelectedItem() + "\n" +
                    "Destino: " + comboDestino.getSelectedItem() + "\n" +
                    "Calidad de servicio: " + calidadSel;

                JOptionPane.showMessageDialog(null, mensaje);
            }
        });

        // Acción del botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNombre.setText("");
                campoDNI.setText("");
                campoFecha.setText("");
                chkAudifonos.setSelected(false);
                chkManta.setSelected(false);
                chkRevistas.setSelected(false);
                rbtnPrimerPiso.setSelected(true);
                comboOrigen.setSelectedIndex(0);
                comboDestino.setSelectedIndex(0);
                listaCalidad.clearSelection();
            }
        });
    }
}
