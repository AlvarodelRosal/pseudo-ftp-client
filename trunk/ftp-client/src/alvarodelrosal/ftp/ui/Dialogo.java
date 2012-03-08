package alvarodelrosal.ftp.ui;

import javax.swing.JOptionPane;

public class Dialogo {
    
    public static void pintarMensajeDeError(String titulo, String cuerpo) {
        JOptionPane.showMessageDialog(null, cuerpo, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public static String pintarCuadroDeDialogo(String titulo, String mensaje) {
            return (String)JOptionPane.showInputDialog(null,mensaje,titulo,JOptionPane.PLAIN_MESSAGE);
    }
}
