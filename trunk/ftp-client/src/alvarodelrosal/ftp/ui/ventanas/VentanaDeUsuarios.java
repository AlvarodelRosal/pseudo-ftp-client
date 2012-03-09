package alvarodelrosal.ftp.ui.ventanas;

import alvarodelrosal.ftp.ui.Statusbar;
import alvarodelrosal.ftp.ui.Toolbar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class VentanaDeUsuarios extends Ventana {
    
    private JFrame ventanaDeUsuarios;
    private Toolbar toolbarDeAdministracion;
    private Statusbar statusbar;
    
    public VentanaDeUsuarios() {
        pregeneraLaVentana();
    }

    private void pregeneraLaVentana() {
        estableceElTituloDeLaVentana();
        estableceDimensionesDeLaVentana();
        centraLaVentana();
        estableceElLayout();
    }

    private void estableceElTituloDeLaVentana() {
        ventanaDeUsuarios = new JFrame("Administración de usuarios");
    }

    private void estableceDimensionesDeLaVentana() {
        ventanaDeUsuarios.setSize(800, 600);
    }

    private void centraLaVentana() {
        Toolkit toolkit = ventanaDeUsuarios.getToolkit();
        Dimension size = toolkit.getScreenSize();
        ventanaDeUsuarios.setLocation((size.width - ventanaDeUsuarios.getWidth()) / 2,
                (size.height - ventanaDeUsuarios.getHeight()) / 2);
    }

    private void estableceElLayout() {
        ventanaDeUsuarios.setLayout(new BorderLayout());
    }
    
    public void hacerVisible() {
        ventanaDeUsuarios.setVisible(true);
    }
    
    public void hacerInvisible() {
        ventanaDeUsuarios.setVisible(false);
    }
    
}
