package alvarodelrosal.ftp.ui;

import java.util.List;
import javax.swing.JToolBar;

public class Toolbar extends ElementoDeVentana {
    
    private JToolBar toolbar;
    
    public Toolbar(String titulo) {
        toolbar = new JToolBar(titulo);
    }
    
    public void agregarElementos(List<ElementoDeToolbar> elementos) {
        for (ElementoDeToolbar elemento : elementos) {
            if (elemento.esUnSeparador()) {
                toolbar.addSeparator();
            } else {
                toolbar.add(elemento.obtenerElemento());
            }
        }
    }
    
    public JToolBar generarToolbar() {
        return toolbar;
    }
    
}
