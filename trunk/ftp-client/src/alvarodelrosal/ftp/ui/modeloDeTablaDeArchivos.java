package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.infraestructura.RepositorioDePaths;
import alvarodelrosal.ftp.modelo.Path;
import javax.swing.table.AbstractTableModel;

public class modeloDeTablaDeArchivos extends AbstractTableModel{

    private Path[] elementos;
    private final RepositorioDePaths repositorioDePaths;

    public modeloDeTablaDeArchivos(RepositorioDePaths repositorioDePaths) {
         elementos = repositorioDePaths.obtenerTodosLosPath();
         this.repositorioDePaths = repositorioDePaths;
    }

    public int getRowCount() {
        return elementos.length;
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) return elementos[row].verNombre();
        if (col == 1) return elementos[row].ultimaModificacion();
        if (col == 2) return elementos[row].verPeso();
        if (col == 3) return elementos[row].verTipo();
        throw new RuntimeException("no se que me estas pidiendo");
    }

    @Override
    public String getColumnName(int col) {
        if (col == 0) return "Nombre";
        if (col == 1) return "fecha de modificación";
        if (col == 2) return "Tamaño";
        if (col == 3) return "Tipo";
        throw new RuntimeException();
    }

    public void actualizar() {
        elementos = repositorioDePaths.obtenerTodosLosPath();
        fireTableDataChanged();
    }

    
    
    
}
