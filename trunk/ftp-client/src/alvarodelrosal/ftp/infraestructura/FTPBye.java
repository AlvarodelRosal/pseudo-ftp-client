package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.Conexion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTPBye implements FTPAction {

    public void cerrar(Conexion conexion) {
        conexion.escribir("Bye");
    }

    @Override
    public List<String> obtenerDatos() {
        return new ArrayList();
    }
}
