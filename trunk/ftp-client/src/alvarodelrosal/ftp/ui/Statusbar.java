package alvarodelrosal.ftp.ui;

import javax.swing.JLabel;

public class Statusbar extends ElementoDeVentana {
    
    private JLabel texto;
    
    public Statusbar() {
        this.texto = new JLabel();
    }
    
    public void cambiarTexto(String texto) {
        this.texto.setText(texto);
    }
    
    public JLabel obtenerElemento() {
        return texto;
    }
    
}
