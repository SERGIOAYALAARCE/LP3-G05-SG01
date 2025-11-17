package jlist;

import javax.swing.JFrame;

public class PruebaLista
{
    public static void main(String[] args)
    {
        MarcoLista marcoLista = new MarcoLista();
        marcoLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoLista.setSize(350, 150);
        marcoLista.setVisible(true);
    }
}
