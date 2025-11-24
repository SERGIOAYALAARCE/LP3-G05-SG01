import javax.swing.*;

public class AppProducto {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Binding de Producto");
        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Producto producto = new Producto("Sin nombre", 0.0, 0, "Ninguna");

        JTextField txtNombre = new JTextField(15);
        JTextField txtPrecio = new JTextField(15);
        JTextField txtStock = new JTextField(15);
        JTextField txtCategoria = new JTextField(15);

        JButton btnActualizar = new JButton("Actualizar Producto");
        JLabel lblInfo = new JLabel("Información del producto aparecerá aquí");

        btnActualizar.addActionListener(e -> {
            try {
                producto.setNombre(txtNombre.getText());
                producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                producto.setCantidadStock(Integer.parseInt(txtStock.getText()));
                producto.setCategoria(txtCategoria.getText());

                lblInfo.setText(producto.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Datos inválidos");
            }
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(new JLabel("Nombre:"));
        frame.add(txtNombre);
        frame.add(new JLabel("Precio:"));
        frame.add(txtPrecio);
        frame.add(new JLabel("Stock:"));
        frame.add(txtStock);
        frame.add(new JLabel("Categoría:"));
        frame.add(txtCategoria);
        frame.add(btnActualizar);
        frame.add(lblInfo);

        frame.setVisible(true);
    }
}
