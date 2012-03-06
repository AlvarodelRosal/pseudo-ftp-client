package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.Conexion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPLook implements FTPAction {
    
    String entrada;
    
    public void buscarHijosDe(String path, Conexion conexion) {
        String comando = "Look<:@:>" + path;
        conexion.escribir(comando);
        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<String> obtenerDatos() {
        String [] hijosLeidos = entrada.split("<:@:>");
        List<String> hijos = new ArrayList();
        for(int posicion = 0; posicion < hijosLeidos.length; posicion++) {
            hijos.add(hijosLeidos[posicion]);
        }
        return hijos;
    }
    
}
