package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeAdmin implements FactoriaDeElementosSeparados {

    public List<ElementoDeToolbar> elementos = new ArrayList();
    
    public FactoriaDeElementosDeAdmin() {
        ElementoDeToolbar administracionUsuarios = new ElementoDeToolbar("Administración de usuarios",
                "users");
        elementos.add(administracionUsuarios);
    }

    @Override
    public List<ElementoDeToolbar> obtenerLosElementos() {
        return elementos;
    }
    
}
