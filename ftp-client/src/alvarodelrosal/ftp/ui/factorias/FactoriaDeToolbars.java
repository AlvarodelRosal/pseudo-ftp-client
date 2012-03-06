package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.Toolbar;
import alvarodelrosal.ftp.ui.VentanaPrincipal;

public class FactoriaDeToolbars {

    private VentanaPrincipal ventana;

    public FactoriaDeToolbars(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }
    
    public Toolbar obtener() {
        
        Toolbar acciones = new Toolbar("Acciones");
        acciones.agregarLos(new FactoriaDeElementosDeAcciones(ventana).obtenerLosElementos());
        
        return acciones;
    }
    
}
