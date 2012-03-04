package alvarodelrosal.ftp.ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TablaConScroll {

    private JTable tabla;
    private JScrollPane scroll;

    public TablaConScroll(TableModel modeloTabla) {
        tabla = new JTable(modeloTabla);
        scroll = new JScrollPane(tabla);
        scroll.setWheelScrollingEnabled(true);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
    }
    
    public JScrollPane obtener() {
        return this.scroll;
    }
    
}
