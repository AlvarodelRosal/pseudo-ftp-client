package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.infraestructura.FTPBye;
import alvarodelrosal.ftp.infraestructura.RepositorioDePaths;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.ui.factorias.FactoriaDeToolbars;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class VentanaPrincipal extends Ventana {

    private JFrame ventanaPrincipal;
    private JToolBar barraDeHerramientas;
    private JScrollPane tablaConScroll;
    private JTable tabla;
    private JLabel statusBar;
    
    private Conexion conexion;
    
    private String nombre;
    private boolean isAdmin;

    public VentanaPrincipal(Conexion conexion) {
        this.ventanaPrincipal = null;
        this.barraDeHerramientas = null;
        this.tablaConScroll = null;
        this.tabla = null;
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
        agregarToolbar(new FactoriaDeToolbars(this).obtener());
        construirTablaConScroll(new TablaConScroll(
                new modeloDeTablaDeArchivos(
                new RepositorioDePaths(), "/", conexion),this));
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
        
        this.ventanaPrincipal.getContentPane().setLayout(new BorderLayout());
        
        this.ventanaPrincipal.addWindowListener(new WindowListenerDeCerrar());
    }

    private boolean laVentanaNoExiste() {
        return ventanaPrincipal == null;
    }

    private void actualizarContenido() {
        this.ventanaPrincipal.setVisible(false);
        this.ventanaPrincipal.setVisible(true);
    }

    public void construirTablaConScroll(TablaConScroll tablaConScroll) {
        this.tablaConScroll = tablaConScroll.obtener();
        this.tabla = tablaConScroll.obtenerTabla();
    }
    
    public Conexion obtenerLaConexion() {
        return this.conexion;
    }
    
    public JTable obtenerTabla() {
        return this.tabla;
    }
    
    class WindowListenerDeCerrar implements WindowListener {

        @Override
        public void windowOpened(WindowEvent we) {}

        @Override
        public void windowClosing(WindowEvent we) {
            FTPBye bye = new FTPBye();
            bye.cerrar(conexion);
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent we) {}

        @Override
        public void windowIconified(WindowEvent we) {}

        @Override
        public void windowDeiconified(WindowEvent we) {}

        @Override
        public void windowActivated(WindowEvent we) {}

        @Override
        public void windowDeactivated(WindowEvent we) {}

    }
}
