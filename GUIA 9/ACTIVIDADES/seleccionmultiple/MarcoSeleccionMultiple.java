package seleccionmultiple;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MarcoSeleccionMultiple extends JFrame {

    private final JList<String> listaJListColores; // lista con los nombres de colores
    private final JList<String> listaJListCopia;   // lista donde se copian los seleccionados
    private JButton botonJButtonCopiar;            // botón para copiar los seleccionados

    private static final String[] nombresColores = {
        "Negro", "Azul", "Cyan", "Gris oscuro", "Gris",
        "Verde", "Gris claro", "Magenta", "Naranja",
        "Rosa", "Rojo", "Blanco", "Amarillo"
    };

    // Constructor
    public MarcoSeleccionMultiple() {
        super("Listas de selección múltiple Sergio Ayala Arce Santos Laquise Roy Roger");
        setLayout(new FlowLayout());

        // Lista de colores
        listaJListColores = new JList<>(nombresColores);
        listaJListColores.setVisibleRowCount(5);
        listaJListColores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(listaJListColores));

        // Botón para copiar
        botonJButtonCopiar = new JButton("Copiar >>>");
        botonJButtonCopiar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evento) {
                    listaJListCopia.setListData(
                        listaJListColores.getSelectedValuesList().toArray(new String[0])
                    );
                }
            }
        );

        add(botonJButtonCopiar);

        // Lista donde se pegan los seleccionados
        listaJListCopia = new JList<>();
        listaJListCopia.setVisibleRowCount(5);
        listaJListCopia.setFixedCellWidth(100);
        listaJListCopia.setFixedCellHeight(15);
        listaJListCopia.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        add(new JScrollPane(listaJListCopia));
    }
}
