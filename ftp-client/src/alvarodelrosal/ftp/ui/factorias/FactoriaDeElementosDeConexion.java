package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.infraestructura.FTPBye;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeElementosDeConexion implements FactoriaDeElementosSeparados{

    private List<ElementoDeToolbar> elementos = new ArrayList();
    private Conexion conexion;
    
    public FactoriaDeElementosDeConexion(Conexion conexion) {
        this.conexion = conexion;
        ElementoDeToolbar conectar = new ElementoDeToolbar("Iniciar conexión",
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
