package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDePaths {
    
    public List<Path> obtenerLosPathDe(String pathRemoto, Conexion conexion) {
        List<Path> paths = new ArrayList();
        
        FTPLook look = new FTPLook();
        look.buscarHijosDe(pathRemoto, conexion);
        List<String> nombreDeLosPath = look.obtenerDatos();
        
        for(String nombreDelPath : nombreDeLosPath) {
            Path path = new Path(pathRemoto, nombreDelPath);
            
            String direccionCompleta = pathRemoto + nombreDelPath;
            
            path.esEscribible(compruebaSiSePuedeEscribirEn(direccionCompleta, conexion));
            path.esLegible(compruebaSiSePuedeLeerEn(direccionCompleta, conexion));
            path.esUnaCarpeta(compruebaSiEsUnaCarpeta(direccionCompleta, conexion));
            path.estaOculto(compruebaSiEstaOculto(direccionCompleta, conexion));
            paths.add(path);
        }
        return paths;
    }

    private boolean compruebaSiSePuedeEscribirEn(String direccionCompleta, Conexion conexion) {
        FTPCanWrite escribible = new FTPCanWrite();
        escribible.comprobarSiEsEscribible(direccionCompleta, conexion);
        return Boolean.parseBoolean(escribible.obtenerDatos().get(0));
    }

    private boolean compruebaSiSePuedeLeerEn(String direccionCompleta, Conexion conexion) {
        FTPCanRead legible = new FTPCanRead();
        legible.comprobarSiEsLegible(direccionCompleta, conexion);
        return Boolean.parseBoolean(legible.obtenerDatos().get(0));
    }

    private boolean compruebaSiEsUnaCarpeta(String direccionCompleta, Conexion conexion) {
        FTPIsFolder carpeta = new FTPIsFolder();
        carpeta.comprobarSiEsCarpeta(direccionCompleta, conexion);
        return Boolean.parseBoolean(carpeta.obtenerDatos().get(0));
    }

    private boolean compruebaSiEstaOculto(String direccionCompleta, Conexion conexion) {
        FTPIsHidden oculto = new FTPIsHidden();
        oculto.comprobarSiEstaOculto(direccionCompleta, conexion);
        return Boolean.parseBoolean(oculto.obtenerDatos().get(0));
    }
    
}
