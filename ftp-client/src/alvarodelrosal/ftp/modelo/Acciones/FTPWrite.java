package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.FTPAction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPWrite implements FTPAction {
    
    private String entrada;
    private Conexion conexion;

    public FTPWrite(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public String verNombre() {
        return "write";
    }

    @Override
    public List<String> obtenerDatos() {
        return new ArrayList();
    }

    @Override
    public void ejecutar(List<String> parametros) {
        StringBuffer comando = new StringBuffer("Write");
        for(String parametro : parametros) {
            comando.append("<:@:>");
            comando.append(parametro);
        }
        conexion.escribir(comando.toString());
        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
