package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.modelo.Acciones.FTPBye;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import alvarodelrosal.ftp.ui.TablaConScroll;
import alvarodelrosal.ftp.ui.Toolbar;
import alvarodelrosal.ftp.ui.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeToolbars {

    private List<ElementoDeToolbar> elementos = new ArrayList();
    private VentanaPrincipal ventana;
    
    public FactoriaDeToolbars(VentanaPrincipal ventana) {
        this.ventana = ventana;
        componerBarra();
    }

    private void componerBarra() {
        ElementoDeToolbar desconectar = new ElementoDeToolbar("Desconectar", "control-power");
        desconectar.agregarActionListener(new DesconectarActionListener());
        elementos.add(desconectar);
        
        ElementoDeToolbar separador = new ElementoDeToolbar("Separador", "Separador");
        elementos.add(separador);
        
        ElementoDeToolbar crearCarpeta = new ElementoDeToolbar("Crear carpeta", "folder-plus");
        elementos.add(crearCarpeta);
        
        ElementoDeToolbar borrarCarpeta = new ElementoDeToolbar("Borrar carpeta", "folder-minus");
        elementos.add(borrarCarpeta);
        
        elementos.add(separador);
        
        ElementoDeToolbar subirArchivo = new ElementoDeToolbar("Subir archivo", "upload-cloud");
        elementos.add(subirArchivo);
        
        ElementoDeToolbar bajarArchivo = new ElementoDeToolbar("Dajar archivo", "download-cloud");
        elementos.add(bajarArchivo);
        
        elementos.add(separador);
        
        ElementoDeToolbar verInfo = new ElementoDeToolbar("Ver información del archivo", "information");
        elementos.add(verInfo);
        
        elementos.add(separador);
        
        ElementoDeToolbar administrarUsuarios = new ElementoDeToolbar("Administrar usuarios", "users");
        elementos.add(administrarUsuarios);
    }
    
    public Toolbar obtener() {
        Toolbar acciones = new Toolbar("Acciones");
        acciones.agregarElementos(elementos);
        return acciones;
    }
    
    class DesconectarActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            FTPBye bye = new FTPBye(ventana.obtenerLaConexion());
            bye.ejecutar(new ArrayList());
            System.exit(0);
        }

    }
    
}
