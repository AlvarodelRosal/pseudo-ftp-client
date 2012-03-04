package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeCarpeta implements FactoriaDeElementosSeparados {

    private List<ElementoDeToolbar> elementos = new ArrayList();
    
    public FactoriaDeElementosDeCarpeta() {
        ElementoDeToolbar carpetaNueva = new ElementoDeToolbar("Crear una carpeta nueva",
                "folder-plus");
        elementos.add(carpetaNueva);
        ElementoDeToolbar borarCarpeta = new ElementoDeToolbar("Borrar el elemento seleccionado",
                "folder-minus");
        elementos.add(borarCarpeta);
    }

    @Override
    public List<ElementoDeToolbar> obtenerLosElementos() {
        return elementos;
    }
    
}
