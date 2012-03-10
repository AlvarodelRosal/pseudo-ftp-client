package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.FTPAction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPDeleteUser implements FTPAction {

    private String entrada;
    private Conexion conexion;

    public FTPDeleteUser(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public String verNombre() {
        return "DeleteUser";
    }

    @Override
    public void ejecutar(List<String> parametros) {

        String salida = "DeleteUser<:@:>" + parametros.get(0) + "<:@:>" + parametros.get(1);
        conexion.escribir(salida);

        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPDeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<String> obtenerDatos() {
        return new ArrayList();
    }
}
