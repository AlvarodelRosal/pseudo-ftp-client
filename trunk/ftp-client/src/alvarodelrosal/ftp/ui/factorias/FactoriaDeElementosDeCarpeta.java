package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.infraestructura.FTPNewFolder;
import alvarodelrosal.ftp.ui.Dialogo;
import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import alvarodelrosal.ftp.ui.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FactoriaDeElementosDeCarpeta implements FactoriaDeElementosSeparados {

    private List<ElementoDeToolbar> elementos = new ArrayList();
    private VentanaPrincipal ventana;
    
    public FactoriaDeElementosDeCarpeta(VentanaPrincipal ventana) {
        this.ventana = ventana;
        ElementoDeToolbar carpetaNueva = new ElementoDeToolbar("Crear una carpeta nueva",
                "folder-plus");
        carpetaNueva.agregarActionListener(new CrearUnaCarpetaActionListener());
        elementos.add(carpetaNueva);
        ElementoDeToolbar borarCarpeta = new ElementoDeToolbar("Borrar el elemento seleccionado",
                "folder-minus");
        elementos.add(borarCarpeta);
    }

    @Override
    public List<ElementoDeToolbar> obtenerLosElementos() {
        return elementos;
    }
    
    class CrearUnaCarpetaActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            String nombreDeLaCarpeta = Dialogo.pintarEscribible("Crear carpeta", "Introduzca el nombre de la carpeta");
            
            FTPNewFolder folder = new FTPNewFolder();
            folder.crearCarpeta("/" + nombreDeLaCarpeta, ventana.obtenerLaConexion());
        }

    }
    
}
