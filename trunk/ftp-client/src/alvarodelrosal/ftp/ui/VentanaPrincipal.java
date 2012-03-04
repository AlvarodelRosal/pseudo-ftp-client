package alvarodelrosal.ftp.ui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class VentanaPrincipal extends Ventana {

    private JFrame ventanaPrincipal;
    private JToolBar barraDeHerramientas;

    public VentanaPrincipal() {
        this.ventanaPrincipal = null;
        this.barraDeHerramientas = null;
    }

    public void crear() {
        this.crearVentanaPrincipal();
        this.actualizarContenido();
    }

    public void agregarToolbar(Toolbar toolbar) {
        barraDeHerramientas = toolbar.generarToolbar();
    }

    private void crearVentanaPrincipal() {
        if (laVentanaNoExiste()) {
            cargaLosParametrosDeLaVentana();
        }
        if(barraDeHerramientas != null) {
            this.ventanaPrincipal.getContentPane().
                    add(barraDeHerramientas,BorderLayout.NORTH);
        }
    }

    private void cargaLosParametrosDeLaVentana() throws HeadlessException {
        this.ventanaPrincipal = new JFrame();
        this.ventanaPrincipal.setTitle("miniFTP - Alvaro del Rosal");
        this.ventanaPrincipal.setSize(800, 600);
        this.ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventanaPrincipal.getContentPane().setLayout(new BorderLayout());
    }

    private boolean laVentanaNoExiste() {
        return ventanaPrincipal == null;
    }

    private void actualizarContenido() {
        this.ventanaPrincipal.setVisible(false);
        this.ventanaPrincipal.setVisible(true);
    }
}
