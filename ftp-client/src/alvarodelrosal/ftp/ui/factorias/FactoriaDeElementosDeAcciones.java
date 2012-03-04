package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeAcciones {
    
    private List<List<ElementoDeToolbar>> elementosSeparados = new ArrayList();
    
    public FactoriaDeElementosDeAcciones(Conexion conexion) {
        elementosSeparados.add(new FactoriaDeElementosDeConexion(conexion).obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeCarpeta(conexion).obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeTransmision(conexion).obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeArchivo(conexion).obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeAdmin(conexion).obtenerLosElementos());
    }
    
    public List<List<ElementoDeToolbar>> obtenerLosElementos() {
        return elementosSeparados;
    }
    
}
