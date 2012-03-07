package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.modelo.Path;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TablaConScroll extends ElementoDeVentana {

    private JPanel contenedorDeTabla;
    private JTable tabla;
    private JScrollPane panelDeScroll;
    private Path pathActual;
    private JLabel mensajes = new JLabel("");
    private ModeloDeTablaDeArchivos modelo;

    public TablaConScroll(TableModel modelo) {
        this.modelo = (ModeloDeTablaDeArchivos) modelo;
        tabla = new JTable(modelo);
        panelDeScroll = new JScrollPane(tabla);
        contenedorDeTabla = new JPanel(new BorderLayout());

        contenedorDeTabla.add(panelDeScroll, BorderLayout.CENTER);
        
        contenedorDeTabla.add(mensajes, BorderLayout.NORTH);
        mensajes.setVisible(false);
        
        tabla.addMouseListener(new ListenerDeDobleClick());
    }

    public JPanel obtenerTabla() {
        return contenedorDeTabla;
    }

    class ListenerDeDobleClick implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {

            if (me.getClickCount() % 2 == 0) {
                Path pathObjetivo = modelo.elementoEn(tabla.getSelectedRow());
                muestraSiEsLegibleYSiNoMuestraUnMensajeDeError(pathObjetivo);
            }

        }

        private void muestraSiEsLegibleYSiNoMuestraUnMensajeDeError(Path pathObjetivo) {
            if (pathObjetivo.esLegible()) {
                modelo.actualizar(pathObjetivo.verPathCompleto());
                mensajes.setVisible(false);
            } else {
                mensajes.setBackground(Color.ORANGE);
                mensajes.setText("No tiene privilegios de lectura para ese directorio");
                mensajes.setVisible(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
    }
}
