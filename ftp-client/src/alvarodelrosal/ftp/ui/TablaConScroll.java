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

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        tabla.addMouseListener(new ListenerDeDobleClick());
    }

    public JPanel obtenerTabla() {
        return contenedorDeTabla;
    }
    
    public Path pathSeleccionado() {
        if(tabla.getSelectedRow() < 0) {
            return null;
        } else {
            return modelo.elementoEn(tabla.getSelectedRow());
        }
    }
    
    public Path pathActual() {
        return pathActual;
    }
    
    public void irAlPath(Path path) {
        this.pathActual = path;
        modelo.actualizar(path.verPathCompleto());
        mensajes.setVisible(false);
    }

    public void generarMensajeDeErrorDeFicheros(String mensaje) {
        contenedorDeTabla.setBackground(Color.ORANGE);
                mensajes.setText(" " + mensaje);
                mensajes.setVisible(true);
    }
    
    class ListenerDeDobleClick implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            Path pathObjetivo = pathSeleccionado();

            if (me.getClickCount() % 2 == 0) {
                if (laCarpetaEsLegibleYEsCarpeta(pathObjetivo)) {
                    mostrarCarpeta(pathObjetivo);
                } else {
                    muestraSiEsUnaCarpetaSiEsUnArchivoDaUnMensajeDeError(pathObjetivo);
                    muestraSiEsLegibleYSiNoMuestraUnMensajeDeError(pathObjetivo);
                }
            }
        }

        private boolean laCarpetaEsLegibleYEsCarpeta(Path pathObjetivo) {
            return pathObjetivo.esLegible() && pathObjetivo.esUnaCarpeta();
        }

        private void muestraSiEsUnaCarpetaSiEsUnArchivoDaUnMensajeDeError(Path pathObjetivo) {
            if (!pathObjetivo.esUnaCarpeta()) {
                generarMensajeDeErrorDeFicheros("Para descargar un archivo, utilice la barra de herramientas");
                mensajes.setVisible(true);
            }
        }

        private void mostrarCarpeta(Path pathObjetivo) {
            irAlPath(pathObjetivo);
            mensajes.setVisible(false);
        }
        

        private void muestraSiEsLegibleYSiNoMuestraUnMensajeDeError(Path pathObjetivo) {
            if (!pathObjetivo.esLegible()) {
                generarMensajeDeErrorDeFicheros("No tiene privilegios de lectura para ese directorio");
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
