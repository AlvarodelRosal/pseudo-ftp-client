package alvarodelrosal.ftp.ui.ventanas;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
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

    public static File pintarMensajeDeGuardar() {
        JFileChooser ventana = new JFileChooser();
        int respuestaDeBoton = ventana.showSaveDialog(null);

        if (respuestaDeBoton == JFileChooser.APPROVE_OPTION) {
            File elementoSeleccionado = ventana.getSelectedFile();
            if (elementoSeleccionado.exists()) {
                int respuesta = Dialogo.pintarMensajeDeConfirmacion("Reemplazar archivo",
                        "El archivo seleccionado existe. ¿Desea reemplazarlo?");
                if (respuesta == 0) {
                    elementoSeleccionado.delete();
                    try {
                        elementoSeleccionado.createNewFile();
                    } catch (IOException ex) {
                        Dialogo.pintarMensajeDeError("Error de escriture",
                                "No se ha podido crear el archivo solicitado");
                    }
                    return elementoSeleccionado;
                }
                return null;
            } else {
                try {
                        elementoSeleccionado.createNewFile();
                    } catch (IOException ex) {
                        Dialogo.pintarMensajeDeError("Error de escriture",
                                "No se ha podido crear el archivo solicitado");
                    }
                return elementoSeleccionado;
            }
        } else {
            return null;
        }
    }

    public static File pintarMensajeDeAbrir() {
        JFileChooser ventana = new JFileChooser();
        int respuestaDeBoton = ventana.showOpenDialog(null);

        if (respuestaDeBoton == JFileChooser.APPROVE_OPTION) {
            File elementoSeleccionado = ventana.getSelectedFile();
            if (!elementoSeleccionado.canRead()) {
                pintarMensajeDeError("Error de lectura",
                        "El archivo seleccionado no tiene permisos de lectura");
                return null;
            } else {
                return elementoSeleccionado;
            }
        } else {
            return null;
        }
    }
}
