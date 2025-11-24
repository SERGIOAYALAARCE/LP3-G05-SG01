package eje1;

import javax.swing.*;
import java.awt.*;

public class AppProducto extends JFrame {

    private Producto producto;

    // Componentes Swing
    private JTextField txtNombre, txtPrecio, txtCantidad, txtCategoria;
    private JLabel lblResultado;

    public AppProducto() {
        setTitle("Gestión de Producto - Tienda");
        setSize(400, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Modelo inicial vacío
        producto = new Producto("", 0.0, 0, "");

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Campos
        panel.add(new JLabel("Nombre del Producto:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        panel.add(new JLabel("Cantidad en Stock:"));
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        panel.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        // Botón
        JButton btnActualizar = new JButton("Actualizar Producto");
        panel.add(btnActualizar);

        // Etiqueta resultado
        lblResultado = new JLabel("Información del producto aparecerá aquí");
        panel.add(lblResultado);

        add(panel);

        // Acción del botón
        btnActualizar.addActionListener(e -> actualizarProducto());
    }

    private void actualizarProducto() {
        try {
            // Actualizamos el modelo
            producto.setNombre(txtNombre.getText());
            producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
            producto.setCantidadStock(Integer.parseInt(txtCantidad.getText()));
            producto.setCategoria(txtCategoria.getText());

            // Se actualiza la etiqueta usando toString()
            lblResultado.setText(producto.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en los datos ingresados. Verifique los campos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppProducto().setVisible(true));
    }
}