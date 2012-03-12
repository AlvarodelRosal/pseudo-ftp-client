package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.FTPAction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPRead implements FTPAction {
    
    private String entrada;
    private Conexion conexion;

    public FTPRead(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public String verNombre() {
        return "Read";
    }

    @Override
    public List<String> obtenerDatos() {
        ArrayList elemento = new ArrayList();
        elemento.add(entrada);
        return elemento;
    }

    @Override
    public void ejecutar(List<String> parametros) {
        String comando = "Read<:@:>" + parametros.get(0) + "<:@:>" + parametros.get(1);
        conexion.escribir(comando);
        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}