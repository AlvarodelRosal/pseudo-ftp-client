package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeArchivo implements FactoriaDeElementosSeparados {

    private List<ElementoDeToolbar> elementos = new ArrayList();
    
    public FactoriaDeElementosDeArchivo() {
        ElementoDeToolbar informacion = new ElementoDeToolbar("Ampliar información",
                "information");
        elementos.add(informacion);
    }

    @Override
    public List<ElementoDeToolbar> obtenerLosElementos() {
        return elementos;
    }
    
}
