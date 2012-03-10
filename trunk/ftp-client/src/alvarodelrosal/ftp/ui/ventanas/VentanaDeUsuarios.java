package alvarodelrosal.ftp.ui.ventanas;

import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.RepositorioDeUsuarios;
import alvarodelrosal.ftp.ui.ModeloDeTablaDeUsuarios;
import alvarodelrosal.ftp.ui.TablaDeAdministracionDeUsuarios;
import alvarodelrosal.ftp.ui.Toolbar;
import alvarodelrosal.ftp.ui.factorias.FactoriaDeToolbarsDeAdministracionDeUsuarios;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class VentanaDeUsuarios extends Ventana {
    
    private JFrame ventanaDeUsuarios;
    private Toolbar toolbar;
    private TablaDeAdministracionDeUsuarios tabla;
    
    private Conexion conexion;
    
    public VentanaDeUsuarios(Conexion conexion) {
        this.conexion = conexion;
        pregeneraLaVentana();
        
        agregarTabla(new TablaDeAdministracionDeUsuarios(
                new ModeloDeTablaDeUsuarios(new RepositorioDeUsuarios(), this.conexion)));
        
        agregarToolbar(new FactoriaDeToolbarsDeAdministracionDeUsuarios(this).obtener());
    }
    
    private void agregarToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        ventanaDeUsuarios.getContentPane().add(this.toolbar.generarToolbar(),
                BorderLayout.NORTH);
    }

    private void agregarTabla(TablaDeAdministracionDeUsuarios tabla) {
        this.tabla = tabla;
        ventanaDeUsuarios.getContentPane().add(this.tabla.obtenerTabla().obtenerLaTablaConScroll(),
                BorderLayout.CENTER);
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
        ventanaDeUsuarios.setSize(400, 300);
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
    
    public TablaDeAdministracionDeUsuarios obtenerLaTabla() {
        return tabla;
    }
    
    public Conexion obtenerConexion() {
        return conexion;
    }    
}
