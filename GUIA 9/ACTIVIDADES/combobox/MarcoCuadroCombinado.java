package combobox;

import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MarcoCuadroCombinado extends JFrame
{
    private final JComboBox<String> imagenesJComboBox;
    private final JLabel etiqueta;

    private static final String nombres[] =
        {"insecto1.gif", "insecto2.gif", "insectviaje.gif", "insectanim.gif"};

    private final Icon[] iconos = {
        new ImageIcon(getClass().getResource(nombres[0])),
        new ImageIcon(getClass().getResource(nombres[1])),
        new ImageIcon(getClass().getResource(nombres[2])),
        new ImageIcon(getClass().getResource(nombres[3]))
    };

    public MarcoCuadroCombinado()
    {
        super("Prueba de JComboBox Sergio Ayala Arce y Santos Laquise Roy Roger");
        setLayout(new FlowLayout());

        imagenesJComboBox = new JComboBox<>(nombres);
        imagenesJComboBox.setMaximumRowCount(3);

        imagenesJComboBox.addItemListener(
            new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent evento)
                {
                    if (evento.getStateChange() == ItemEvent.SELECTED)
                        etiqueta.setIcon(iconos[imagenesJComboBox.getSelectedIndex()]);
                }
            }
        );

        add(imagenesJComboBox);

        etiqueta = new JLabel(iconos[0]);
        add(etiqueta);
    }
}
