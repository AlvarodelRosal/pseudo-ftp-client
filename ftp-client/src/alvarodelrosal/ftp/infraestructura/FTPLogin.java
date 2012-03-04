package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.Conexion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPLogin implements FTPAction{

    String entrada;
    
    public boolean existe(String username, String password, Conexion conexion) {
        String comando = username + "<:@:>" + password;
        conexion.escribir(comando);
        try {
            entrada = conexion.leer();
            return entrada.contains("<:@:>");
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
