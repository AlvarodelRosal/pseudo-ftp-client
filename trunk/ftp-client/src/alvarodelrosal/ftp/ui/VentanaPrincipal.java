package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.infraestructura.RepositorioDePaths;
import alvarodelrosal.ftp.ui.factorias.FactoriaDeToolbars;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class VentanaPrincipal extends Ventana {

    private JFrame ventanaPrincipal;
    private JToolBar barraDeHerramientas;
    private JScrollPane tablaConScroll;

    public VentanaPrincipal() {
        this.ventanaPrincipal = null;
        this.barraDeHerramientas = null;
        this.tablaConScroll = null;
    }

    public void crear() {
        this.agregaContenido();
        this.crearVentanaPrincipal();
        this.actualizarContenido();
    }

    private void agregaContenido() {
        agregarToolbar(new FactoriaDeToolbars().obtener());
        construirTablaConScroll(new TablaConScroll(
                new modeloDeTablaDeArchivos(
                new RepositorioDePaths())).obtener());
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
        if(tablaConScroll != null) {
            this.ventanaPrincipal.getContentPane().
                    add(tablaConScroll,BorderLayout.CENTER);
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

    public void construirTablaConScroll(JScrollPane tablaConScroll) {
        this.tablaConScroll = tablaConScroll;
    }
}
