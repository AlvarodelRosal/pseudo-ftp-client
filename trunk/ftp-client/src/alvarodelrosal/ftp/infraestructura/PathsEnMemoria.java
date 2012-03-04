package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.Path;

public class PathsEnMemoria {

    private Path[] paths = new Path[1];
    
    public PathsEnMemoria() {
        paths[0] = new Path("/", "test.tex");
        paths[0].esEscribible(false);
        paths[0].esLegible(true);
        paths[0].estaOculto(false);
        paths[0].setPeso(1234567);
        paths[0].ultimaModificacion("miercoles, 12 de octubre de 1492");
    }
    
    public Path[] obtenerTodosLosPath() {
        return paths;
    } 
    
}
