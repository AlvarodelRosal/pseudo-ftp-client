package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.modelo.Acciones.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDePaths {
    
    public List<Path> obtenerLosPathDe(String pathRemoto, Conexion conexion) {
        List<Path> paths = new ArrayList();
        
        FTPLook look = new FTPLook(conexion);
        
        List<String> parametros = new ArrayList();
        parametros.add(pathRemoto);
        look.ejecutar(parametros);
        List<String> nombreDeLosPath = look.obtenerDatos();
        
        for(String nombreDelPath : nombreDeLosPath) {
            paths.add(obtenerElPath(pathRemoto, nombreDelPath, conexion));
        }
        return paths;
    }

    public Path obtenerElPath(String pathRemoto, String nombreDelPath, Conexion conexion) {
        Path path = new Path(pathRemoto, nombreDelPath);
        String direccionCompleta = pathRemoto + nombreDelPath;
        path.esEscribible(compruebaSiSePuedeEscribirEn(direccionCompleta, conexion));
        path.esLegible(compruebaSiSePuedeLeerEn(direccionCompleta, conexion));
        path.esUnaCarpeta(compruebaSiEsUnaCarpeta(direccionCompleta, conexion));
        path.estaOculto(compruebaSiEstaOculto(direccionCompleta, conexion));
        path.setPeso(obtieneElPeso(direccionCompleta, conexion));
        path.ultimaModificacion(obtieneLaUltimaModificacion(direccionCompleta, conexion));
        return path;
    }

    private boolean compruebaSiSePuedeEscribirEn(String direccionCompleta, Conexion conexion) {
        FTPCanWrite escribible = new FTPCanWrite(conexion);
        
        List<String> parametros = new ArrayList();
        parametros.add(direccionCompleta);
        escribible.ejecutar(parametros);
        return Boolean.parseBoolean(escribible.obtenerDatos().get(0));
    }

    private boolean compruebaSiSePuedeLeerEn(String direccionCompleta, Conexion conexion) {
        FTPCanRead legible = new FTPCanRead(conexion);
        
        List<String> parametros = new ArrayList();
        parametros.add(direccionCompleta);
        legible.ejecutar(parametros);
        return Boolean.parseBoolean(legible.obtenerDatos().get(0));
    }

    private boolean compruebaSiEsUnaCarpeta(String direccionCompleta, Conexion conexion) {
        FTPIsFolder carpeta = new FTPIsFolder(conexion);
        
        List<String> parametros = new ArrayList();
        parametros.add(direccionCompleta);
        carpeta.ejecutar(parametros);
        return Boolean.parseBoolean(carpeta.obtenerDatos().get(0));
    }

    private boolean compruebaSiEstaOculto(String direccionCompleta, Conexion conexion) {
        FTPIsHidden oculto = new FTPIsHidden(conexion);
        
        List<String> parametros = new ArrayList();
        parametros.add(direccionCompleta);
        oculto.ejecutar(parametros);
        return Boolean.parseBoolean(oculto.obtenerDatos().get(0));
    }

    private long obtieneElPeso(String direccionCompleta, Conexion conexion) {
        FTPSize peso = new FTPSize(conexion);
        
        List<String> parametros = new ArrayList();
        parametros.add(direccionCompleta);
        peso.ejecutar(parametros);
        return peso.respuestaEnObjeto();
    }

    private long obtieneLaUltimaModificacion(String direccionCompleta, Conexion conexion) {
        FTPLastModified modificacion = new FTPLastModified(conexion);
        
        List<String> parametros = new ArrayList();
        parametros.add(direccionCompleta);
        modificacion.ejecutar(parametros);
        return modificacion.respuestaEnObjeto();
    }

}
