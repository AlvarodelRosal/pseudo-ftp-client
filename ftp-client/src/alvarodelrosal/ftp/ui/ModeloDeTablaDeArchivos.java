package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.Path;
import alvarodelrosal.ftp.modelo.RepositorioDePaths;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeloDeTablaDeArchivos extends AbstractTableModel {

    private List<Path> elementos;
    private final RepositorioDePaths repositorioDePaths;
    private Conexion conexion;
    private Path path;

    public ModeloDeTablaDeArchivos(RepositorioDePaths repositorioDePaths,
            Path path, Conexion conexion) {
        this.path = path;
        this.conexion = conexion;
        elementos = repositorioDePaths.obtenerLosPathDe(path.verPath(), conexion);
        this.repositorioDePaths = repositorioDePaths;
    }

    public int getRowCount() {
        return elementos.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int fila, int columna) {
        if (columna == 0) {
            return elementos.get(fila).verNombre();
        }
        if (columna == 1) {
            return elementos.get(fila).ultimaModificacion();
        }
        if (columna == 2) {
            return elementos.get(fila).verPeso();
        }
        if (columna == 3) {
            return elementos.get(fila).verTipo();
        }
        throw new RuntimeException("Esa columna no existe");
    }

    @Override
    public String getColumnName(int columna) {
        if (columna == 0) {
            return "Nombre";
        }
        if (columna == 1) {
            return "fecha de modificación";
        }
        if (columna == 2) {
            return "Tamaño";
        }
        if (columna == 3) {
            return "Tipo";
        }
        throw new RuntimeException();
    }

    public void actualizar(String path) {
        elementos = repositorioDePaths.obtenerLosPathDe(path, conexion);
        fireTableDataChanged();
    }

    public Path elementoEn(int fila) {
        return elementos.get(fila);
    }
    
    public Path obtenerElPathActual() {
        return path;
    }
}
