package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeConexion implements FactoriaDeElementosSeparados{

    private List<ElementoDeToolbar> elementos = new ArrayList();
    
    public FactoriaDeElementosDeConexion() {
        ElementoDeToolbar conectar = new ElementoDeToolbar("Iniciar conexión",
                "control-power");
        elementos.add(conectar);
    }
    
    public List<ElementoDeToolbar> obtenerLosElementos() {
        return elementos;
    }
    
}
