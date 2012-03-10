package alvarodelrosal.ftp.ui.ventanas;

import alvarodelrosal.ftp.modelo.Acciones.FTPBye;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.Path;
import alvarodelrosal.ftp.modelo.RepositorioDePaths;
import alvarodelrosal.ftp.modelo.Usuario;
import alvarodelrosal.ftp.ui.ModeloDeTablaDeArchivos;
import alvarodelrosal.ftp.ui.Statusbar;
import alvarodelrosal.ftp.ui.TablaDeNavegacionDeArchivos;
import alvarodelrosal.ftp.ui.Toolbar;
import alvarodelrosal.ftp.ui.factorias.FactoriaDeToolbarsDeAccionesDeLaVentanaPrincipal;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class VentanaPrincipal extends Ventana {

    private JFrame ventanaPrincipal;
    private Toolbar toolbar;
    private TablaDeNavegacionDeArchivos tabla;
    private Statusbar statusbar;
    
    private Conexion conexion;
    private Usuario usuario;

    public VentanaPrincipal(Conexion conexion, Usuario usuario) {
        this.conexion = conexion;
        this.usuario = usuario;
        pregeneraLaVentana();
        
        agregarToolbar(new FactoriaDeToolbarsDeAccionesDeLaVentanaPrincipal(this).obtener());
        agregarTabla(new TablaDeNavegacionDeArchivos(
                new ModeloDeTablaDeArchivos(new RepositorioDePaths(),
                new Path("/", ""), conexion)));
        this.irAlPath("/");
        agregarStatusbar(new Statusbar());
    }
    
    private void agregarToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        ventanaPrincipal.getContentPane().add(this.toolbar.generarToolbar(),
                BorderLayout.NORTH);
    }
    
    private void agregarStatusbar(Statusbar statusbar) {
        this.statusbar = statusbar;
        ventanaPrincipal.getContentPane().add(this.statusbar.obtenerElemento(),
                BorderLayout.SOUTH);
    }
    
    private void agregarTabla(TablaDeNavegacionDeArchivos tabla) {
        this.tabla = tabla;
        ventanaPrincipal.getContentPane().add(this.tabla.obtenerTabla().obtenerLaTablaConScroll(),
                BorderLayout.CENTER);
    }
    
    public Usuario obtenerElUsuario() {
        return usuario;
    }
    
    public Conexion obtenerLaConexion() {
        return conexion;
    }
    
    public void generar() {
        String contenidoDeStatusbar = " " + usuario.verNombre() + " ("
                + usuario.verUsername() + ") - " + usuario.verNivel();
        statusbar.cambiarTexto(contenidoDeStatusbar);
        
        ventanaPrincipal.setVisible(false);
        ventanaPrincipal.setVisible(true);
    }
    
    public Path pathActual() {
        return tabla.pathActual();
    }
    
    public Path pathSeleccionado() {
        return tabla.pathSeleccionado();
    }
    
    public void irAlPath(String path) {
        Path pathObjetivo = new Path(path, "");
        tabla.irAlPath(pathObjetivo);
    }
    
    public void agregarMensajeDeError(String mensaje) {
        tabla.generarMensajeDeErrorDeFicheros(mensaje);
    }
    
    private void pregeneraLaVentana() throws HeadlessException {
        estableceElTituloDeLaVentana();
        estableceDimensionesDeLaVentana();
        centraLaVentana();
        estableceElLayout();
        ventanaPrincipal.addWindowListener(
                new ListenerParaCerarLaConexionCuandoCierraLaVentana());
    }

    private void estableceElTituloDeLaVentana() throws HeadlessException {
        ventanaPrincipal = new JFrame("miniFTP - Alvaro del Rosal");
    }

    private void estableceElLayout() {
        ventanaPrincipal.setLayout(new BorderLayout());
    }

    private void centraLaVentana() throws HeadlessException {
        Toolkit toolkit = ventanaPrincipal.getToolkit();
        Dimension size = toolkit.getScreenSize();
        ventanaPrincipal.setLocation((size.width - ventanaPrincipal.getWidth()) / 2,
                (size.height - ventanaPrincipal.getHeight()) / 2);
    }

    private void estableceDimensionesDeLaVentana() {
        ventanaPrincipal.setSize(800, 600);
    }

    class ListenerParaCerarLaConexionCuandoCierraLaVentana implements WindowListener {

        @Override
        public void windowOpened(WindowEvent we) {}

        @Override
        public void windowClosing(WindowEvent we) {
            FTPBye bye = new FTPBye(conexion);
            bye.ejecutar(new ArrayList());
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
