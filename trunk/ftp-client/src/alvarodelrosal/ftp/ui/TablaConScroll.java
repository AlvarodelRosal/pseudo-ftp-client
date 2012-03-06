package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.modelo.Path;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TablaConScroll {

    private JTable tabla;
    private JScrollPane scroll;
    private modeloDeTablaDeArchivos modelo;

    public TablaConScroll(TableModel modeloTabla, VentanaPrincipal ventana) {
        this.modelo = (modeloDeTablaDeArchivos) modeloTabla;
        
        tabla = new JTable(modeloTabla);
        tabla.addMouseListener(new FilaSeleccionadaMouseListener());
        
        scroll = new JScrollPane(tabla);
        scroll.setWheelScrollingEnabled(true);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
    }
    
    public JScrollPane obtener() {
        return this.scroll;
    }
    
    public JTable obtenerTabla() {
        return tabla;
    }
    
    class FilaSeleccionadaMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() % 2 == 0) {
                int filaElegida = tabla.getSelectedRow();
                Path path = modelo.elementoEn(filaElegida);
                modelo.actualizar(path.verPath() + "/" +  path.verNombre());
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
