package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeAcciones {
    
    private List<List<ElementoDeToolbar>> elementosSeparados = new ArrayList();
    
    public FactoriaDeElementosDeAcciones() {
        elementosSeparados.add(new FactoriaDeElementosDeConexion().obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeCarpeta().obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeTransmision().obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeArchivo().obtenerLosElementos());
        elementosSeparados.add(new FactoriaDeElementosDeAdmin().obtenerLosElementos());
        
    }
    
    public List<List<ElementoDeToolbar>> obtenerLosElementos() {
        return elementosSeparados;
    }
    
}
