package alvarodelrosal.ftp.ui;

import javax.swing.JOptionPane;

public class Dialogo {
    
    public static void pintarMensajeDeError(String titulo, String cuerpo) {
        JOptionPane.showMessageDialog(null, cuerpo, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
}
