package alvarodelrosal.ftp.ui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderDeNombreDeArchivo extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if ("Carpeta".equals(table.getValueAt(row, 3).toString())) {
            this.setIcon(new ImageIcon(getClass().getResource("/iconos/folder-horizontal.png")));
        } else {
            this.setIcon(new ImageIcon(getClass().getResource("/iconos/document.png")));
        }
        this.setText(value.toString());
        return this;
    }
}
