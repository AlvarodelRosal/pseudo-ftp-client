package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.FTPAction;
import alvarodelrosal.ftp.modelo.Conexion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPNewFolder implements FTPAction {
    
    private String entrada;
    private Conexion conexion;
    
    public FTPNewFolder(Conexion conexion) {
        this.conexion = conexion;
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

    @Override
    public String verNombre() {
        return "NewFolder";
    }

    @Override
    public void ejecutar(List<String> parametros) {
        String comando = "NewFolder<:@:>" + parametros.get(0);
        conexion.escribir(comando);
        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object respuestaEnObjeto() {
        return Boolean.parseBoolean(entrada);
    }
    
}
