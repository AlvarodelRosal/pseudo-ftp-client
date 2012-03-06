package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.Conexion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPIsFolder implements FTPAction {
    
    String entrada;
    
    public void comprobarSiEsCarpeta(String path, Conexion conexion) {
        String comando = "IsFolder<:@:>" + path;
        conexion.escribir(comando);
        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<String> obtenerDatos() {
        String [] parametrosLeidos = entrada.split("<:@:>");
        List<String> parametros = new ArrayList();
        for(int posicion = 0; posicion < parametrosLeidos.length; posicion++) {
            parametros.add(parametrosLeidos[posicion]);
        }
        return parametros;
    }
    
}
