package alvarodelrosal.ftp.ui;

import java.util.List;
import javax.swing.JToolBar;

public class Toolbar {

    private String nombre;
    private JToolBar toolbar;

    public Toolbar(String nombre) {
        this.nombre = nombre;
        this.toolbar = null;
    }
    
    public void agregarLos(List<List<ElementoDeToolbar>> elementos) {
        siNoExisteLaBarraLaGenera();
        for(List<ElementoDeToolbar> elementosASeparar : elementos) {
            this.toolbar.addSeparator();
            for(ElementoDeToolbar elemento : elementosASeparar) {
                this.toolbar.add(elemento.generarBotonDeToolbar());
            }
        }
    }

    private void siNoExisteLaBarraLaGenera() {
        if (noExisteLaBarra()) {
            this.toolbar = new JToolBar(nombre);
        }
    }

    private boolean noExisteLaBarra() {
        return this.toolbar == null;
    }

    public JToolBar generarToolbar() {
        return this.toolbar;
    }
    
    public String obtenerNombre() {
        return this.nombre;
    }
    
}
