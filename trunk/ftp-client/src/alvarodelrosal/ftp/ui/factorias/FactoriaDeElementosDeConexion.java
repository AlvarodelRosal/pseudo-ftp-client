package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.infraestructura.FTPBye;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import alvarodelrosal.ftp.ui.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeConexion implements FactoriaDeElementosSeparados{

    private List<ElementoDeToolbar> elementos = new ArrayList();
    private Conexion conexion;
    
    public FactoriaDeElementosDeConexion(VentanaPrincipal ventana) {
        this.conexion = ventana.obtenerLaConexion();
        ElementoDeToolbar conectar = new ElementoDeToolbar("Cerrar conexión",
                "control-power");
        conectar.agregarActionListener(new ActionListenerDeConectar());
        elementos.add(conectar);
    }
    
    public List<ElementoDeToolbar> obtenerLosElementos() {
        return elementos;
    }
    
    class ActionListenerDeConectar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            FTPBye bye = new FTPBye();
            bye.cerrar(conexion);
            System.exit(0);
        }

    }
    
}
