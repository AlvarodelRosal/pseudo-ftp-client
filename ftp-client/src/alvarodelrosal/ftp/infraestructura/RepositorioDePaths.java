package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.Path;

public class RepositorioDePaths {
    
    private PathsEnMemoria path = new PathsEnMemoria();
    
    public Path[] obtenerTodosLosPath() {
        return path.obtenerTodosLosPath();
    }
    
}
