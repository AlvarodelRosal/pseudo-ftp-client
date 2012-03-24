package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.RepositorioDeUsuarios;
import alvarodelrosal.ftp.modelo.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeloDeTablaDeUsuarios extends AbstractTableModel {

    private List<Usuario> elementos;
    private final RepositorioDeUsuarios repositorioDeUsuarios;
    private Conexion conexion;

    public ModeloDeTablaDeUsuarios(RepositorioDeUsuarios repositorioDeUsuarios,
            Conexion conexion) {
        this.conexion = conexion;
        elementos = repositorioDeUsuarios.obtenerTodosLosUsuarios(conexion);
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    @Override
    public int getRowCount() {
        return elementos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        if (columna == 0) {
            return elementos.get(fila).verNombre();
        }
        if (columna == 1) {
            return elementos.get(fila).verUsername();
        }
        if (columna == 2) {
            return elementos.get(fila).verNivel();
        }
        throw new RuntimeException("Esa columna no existe");
    }

    @Override
    public String getColumnName(int columna) {
        if (columna == 0) {
            return "Nombre";
        }
        if (columna == 1) {
            return "Nombre de usuario";
        }
        if (columna == 2) {
            return "Nivel de usuario";
        }
        throw new RuntimeException();
    }

    public void actualizar() {
        elementos = repositorioDeUsuarios.obtenerTodosLosUsuarios(conexion);
        fireTableDataChanged();
    }

    public Usuario elementoEn(int fila) {
        return elementos.get(fila);
    }
    
}
