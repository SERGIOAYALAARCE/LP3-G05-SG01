package ejeswing;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        CompraPasajes ventana = new CompraPasajes();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(350, 550);
        ventana.setVisible(true);
    }
}
