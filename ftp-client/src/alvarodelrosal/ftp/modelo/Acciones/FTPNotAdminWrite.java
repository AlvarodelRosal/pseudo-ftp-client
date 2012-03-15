package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.FTPAction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPNotAdminWrite implements FTPAction {
    
    private String entrada;
    private Conexion conexion;

    public FTPNotAdminWrite(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public String verNombre() {
        return "NotAdminWrite";
    }

    @Override
    public List<String> obtenerDatos() {
        return new ArrayList();
    }

    @Override
    public void ejecutar(List<String> parametros) {
        StringBuffer comando = new StringBuffer("NotAdminWrite");
        for(String parametro : parametros) {
            comando.append("<:@:>");
            comando.append(parametro);
        }
        conexion.escribir(comando.toString());
        try {
            entrada = conexion.leer();
            System.out.println(entrada);
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
