package alvarodelrosal.ftp.ui;

import javax.swing.JOptionPane;

public class Dialogo extends Ventana{
    
    public static String pintarEscribible(String titulo, String mensaje) {
        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public static int pintarElegible(String titulo, String mensaje) {
        Object[] opciones = {"No", "Si"};
        return JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }
    
    public static void pintarMensajeDeWarning(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo,JOptionPane.WARNING_MESSAGE);
    }
    
}
