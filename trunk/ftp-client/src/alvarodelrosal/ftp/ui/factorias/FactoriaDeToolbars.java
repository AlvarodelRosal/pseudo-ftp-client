package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.ui.Toolbar;

public class FactoriaDeToolbars {

    private Conexion conexion;

    public FactoriaDeToolbars(Conexion conexion) {
        this.conexion = conexion;
    }
    
    public Toolbar obtener() {
        
        Toolbar acciones = new Toolbar("Acciones");
        acciones.agregarLos(new FactoriaDeElementosDeAcciones(conexion).obtenerLosElementos());
        
        return acciones;
    }
    
}
