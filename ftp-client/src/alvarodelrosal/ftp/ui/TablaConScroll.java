package alvarodelrosal.ftp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TablaConScroll {

    private JTable tabla;
    private JScrollPane panelDeScroll;
    private JPanel contenedorDeTabla;
    private JLabel mensajes = new JLabel("");

    public TablaConScroll(TableModel modelo) {

        tabla = new JTable(modelo);
        panelDeScroll = new JScrollPane(tabla);

        contenedorDeTabla = new JPanel(new BorderLayout());

        contenedorDeTabla.add(panelDeScroll, BorderLayout.CENTER);
        contenedorDeTabla.add(mensajes, BorderLayout.NORTH);
        mensajes.setVisible(false);

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
    }

    public JPanel obtenerLaTablaConScroll() {
        return contenedorDeTabla;
    }

    public int filaSeleccionada() {
        return tabla.getSelectedRow();
    }

    public void agregarClickListenerALaTabla(MouseListener mouseListener) {
        tabla.addMouseListener(mouseListener);
    }

    public void ocultarMensaje() {
        mensajes.setVisible(false);
    }

    public void generarMensaje(String mensaje) {
        contenedorDeTabla.setBackground(Color.ORANGE);
        mensajes.setText(" " + mensaje);
        mensajes.setVisible(true);
    }
}
