package alvarodelrosal.ftp.modelo.Acciones;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.FTPAction;
import alvarodelrosal.ftp.modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPLogin implements FTPAction {

    private String entrada;
    private Conexion conexion;

    public FTPLogin(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public String verNombre() {
        return "Login";
    }

    @Override
    public void ejecutar(List<String> parametros) {

        String salida = parametros.get(0) + "<:@:>" + parametros.get(1);
        conexion.escribir(salida);

        try {
            entrada = conexion.leer();
        } catch (IOException ex) {
            Logger.getLogger(FTPLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<String> obtenerDatos() {
        String[] parametrosLeidos = entrada.split("<:@:>");

        List<String> parametros = new ArrayList();
        for (int posicion = 0; posicion < parametrosLeidos.length; posicion++) {
            parametros.add(parametrosLeidos[posicion]);
        }
        return parametros;
    }

    public Usuario respuestaEnObjeto() {
        if (elUsuarioExiste()) {
            String[] parametrosLeidos = entrada.split("<:@:>");
            Usuario usuario = new Usuario(parametrosLeidos[0],
                    parametrosLeidos[1],
                    Boolean.parseBoolean(parametrosLeidos[2]));
            return usuario;
        } else {
            return null;
        }
    }

    private boolean elUsuarioExiste() {
        return entrada.contains("<:@:>");
    }
}
