package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeTransmision implements FactoriaDeElementosSeparados{

    private List<ElementoDeToolbar> elementos = new ArrayList();
    
    public FactoriaDeElementosDeTransmision() {
        ElementoDeToolbar bajarArchivo = new ElementoDeToolbar("Descargar el archivo seleccionado",
                "download-cloud");
        elementos.add(bajarArchivo);
        ElementoDeToolbar subirArchivo = new ElementoDeToolbar("Subir un nuevo archivo",
                "upload-cloud");
        elementos.add(subirArchivo);
    }

    @Override
    public List<ElementoDeToolbar> obtenerLosElementos() {
        return elementos;
    }
    
}
