package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.modelo.Acciones.FTPUsers;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeUsuarios {

    public List<Usuario> obtenerTodosLosUsuarios(Conexion conexion) {

        List<Usuario> usuarios = new ArrayList();
        
        FTPUsers conexionDeUsuarios = new FTPUsers(conexion);
        conexionDeUsuarios.ejecutar(new ArrayList());
        List<String> datosEnBruto = conexionDeUsuarios.obtenerDatos();
        
        for(int numeroDeUsuario = 0; numeroDeUsuario < (datosEnBruto.size() / 3); numeroDeUsuario++) {
            Usuario usuario = new Usuario(datosEnBruto.get(3 * numeroDeUsuario),
                    datosEnBruto.get(3 * numeroDeUsuario + 1),
                    Boolean.parseBoolean(datosEnBruto.get(3 * numeroDeUsuario + 2)));
            usuarios.add(usuario);
        }
        
        return usuarios;
    }
    
}
