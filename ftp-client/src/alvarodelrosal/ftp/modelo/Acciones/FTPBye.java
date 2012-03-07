package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.FTPAction;
import java.util.ArrayList;
import java.util.List;

public class FTPBye implements FTPAction {

    private Conexion conexion;
    
    public FTPBye(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<String> obtenerDatos() {
        return new ArrayList();
    }

    @Override
    public String verNombre() {
        return "Bye";
    }

    @Override
    public void ejecutar(List<String> parametros) {
        conexion.escribir("Bye");
    }

    @Override
    public Object respuestaEnObjeto() {
        return null;
    }
}
