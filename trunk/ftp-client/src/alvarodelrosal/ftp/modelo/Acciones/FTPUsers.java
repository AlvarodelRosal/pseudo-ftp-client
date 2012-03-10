package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.FTPAction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPUsers implements FTPAction {
    
    private String entrada;
    private Conexion conexion;
    
    public FTPUsers(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<String> obtenerDatos() {
        String [] parametrosLeidos = entrada.split("<:@:>");
        List<String> parametros = new ArrayList();
        parametros.addAll(Arrays.asList(parametrosLeidos));
        return parametros;
    }

    @Override
    public String verNombre() {
        return "Users";
    }

    @Override
    public void ejecutar(List<String> parametros) {
        String comando = "Users";
        conexion.escribir(comando);
        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
