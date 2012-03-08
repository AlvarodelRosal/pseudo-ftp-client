package alvarodelrosal.ftp.ui.ventanas;

import javax.swing.JOptionPane;

public class Dialogo {

    public static void pintarMensajeDeError(String titulo, String cuerpo) {
        JOptionPane.showMessageDialog(null, cuerpo, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static String pintarCuadroDeDialogo(String titulo, String mensaje) {
        return (String) JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public static int pintarMensajeDeConfirmacion(String titulo, String cuerpo) {
        Object[] respuestas = {"Sí", "No"};

        return JOptionPane.showOptionDialog(null, cuerpo, titulo, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, respuestas, respuestas[0]);
    }
}
