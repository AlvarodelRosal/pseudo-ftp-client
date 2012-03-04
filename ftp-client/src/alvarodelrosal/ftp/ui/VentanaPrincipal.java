package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.infraestructura.RepositorioDePaths;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.ui.factorias.FactoriaDeToolbars;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class VentanaPrincipal extends Ventana {

    private JFrame ventanaPrincipal;
    private JToolBar barraDeHerramientas;
    private JScrollPane tablaConScroll;
    private JLabel statusBar;
    
    private Conexion conexion;
    
    private String nombre;
    private boolean isAdmin;

    public VentanaPrincipal(Conexion conexion) {
        this.ventanaPrincipal = null;
        this.barraDeHerramientas = null;
        this.tablaConScroll = null;
        this.statusBar = null;
        this.conexion = conexion;
    }

    public void crear() {
        this.agregaContenido();
        this.crearVentanaPrincipal();
        this.actualizarContenido();
    }
    
    public void establecerNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void establecerAdministrador(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    private void agregaContenido() {
        agregarToolbar(new FactoriaDeToolbars(conexion).obtener());
        construirTablaConScroll(new TablaConScroll(
                new modeloDeTablaDeArchivos(
                new RepositorioDePaths(conexion))).obtener());
    }

    public void agregarToolbar(Toolbar toolbar) {
        barraDeHerramientas = toolbar.generarToolbar();
    }

    private void crearVentanaPrincipal() {
        cargaLaVentana();
        cargaElToolbar();
        cargaLaTable();
        if(hayQueCrearElStatusBar()) {
            String contenido = " " + nombre;
            if (isAdmin) {
                contenido += " - administrador";
            }
            statusBar = new JLabel(contenido);
            this.ventanaPrincipal.getContentPane().add(statusBar,
                    BorderLayout.SOUTH);
        }
    }

    private boolean hayQueCrearElStatusBar() {
        return statusBar == null;
    }

    private void cargaLaVentana() throws HeadlessException {
        if (laVentanaNoExiste()) {
            cargaLosParametrosDeLaVentana();
        }
    }

    private void cargaLaTable() {
        if(existeLaTabla()) {
            this.ventanaPrincipal.getContentPane().
                    add(tablaConScroll,BorderLayout.CENTER);
        }
    }

    private void cargaElToolbar() {
        if(existeElToolbar()) {
            this.ventanaPrincipal.getContentPane().
                    add(barraDeHerramientas,BorderLayout.NORTH);
        }
    }

    private boolean existeLaTabla() {
        return tablaConScroll != null;
    }

    private boolean existeElToolbar() {
        return barraDeHerramientas != null;
    }

    private void cargaLosParametrosDeLaVentana() throws HeadlessException {
        this.ventanaPrincipal = new JFrame();
        this.ventanaPrincipal.setTitle("miniFTP - Alvaro del Rosal");
        this.ventanaPrincipal.setSize(800, 600);
        
        Toolkit toolkit = ventanaPrincipal.getToolkit();
        Dimension size = toolkit.getScreenSize();
        ventanaPrincipal.setLocation((size.width - ventanaPrincipal.getWidth())/2,
                (size.height - ventanaPrincipal.getHeight())/2);
        
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
