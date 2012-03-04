package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.Toolbar;

public class FactoriaDeToolbars {

    public Toolbar obtener() {
        
        Toolbar acciones = new Toolbar("Acciones");
        acciones.agregarLos(new FactoriaDeElementosDeAcciones().obtenerLosElementos());
        
        return acciones;
    }
    
}
