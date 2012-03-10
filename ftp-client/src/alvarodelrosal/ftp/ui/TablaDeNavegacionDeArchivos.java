package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.modelo.Path;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.TableModel;

public class TablaDeNavegacionDeArchivos extends ElementoDeVentana {

    private TablaConScroll tabla;
    private Path pathActual;
    private ModeloDeTablaDeArchivos modelo;

    public TablaDeNavegacionDeArchivos(TableModel modelo) {
        this.modelo = (ModeloDeTablaDeArchivos) modelo;
        tabla = new TablaConScroll(modelo);
        tabla.agregarClickListenerALaTabla(new ListenerDeDobleClick());
    }

    public TablaConScroll obtenerTabla() {
        return tabla;
    }
    
    public Path pathSeleccionado() {
        if(tabla.filaSeleccionada() < 0) {
            return null;
        } else {
            return modelo.elementoEn(tabla.filaSeleccionada());
        }
    }
    
    public Path pathActual() {
        return pathActual;
    }
    
    public void irAlPath(Path path) {
        this.pathActual = path;
        modelo.actualizar(path.verPathCompleto());
        tabla.ocultarMensaje();
    }

    public void generarMensajeDeErrorDeFicheros(String mensaje) {
        tabla.generarMensaje(mensaje);
    }
    
    class ListenerDeDobleClick implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            Path pathObjetivo = pathSeleccionado();

            if (me.getClickCount() % 2 == 0) {
                if (laCarpetaEsLegibleYEsCarpeta(pathObjetivo)) {
                    mostrarCarpeta(pathObjetivo);
                } else {
                    muestraSiEsUnaCarpetaSiEsUnArchivoDaUnMensajeDeError(pathObjetivo);
                    muestraSiEsLegibleYSiNoMuestraUnMensajeDeError(pathObjetivo);
                }
            }
        }

        private boolean laCarpetaEsLegibleYEsCarpeta(Path pathObjetivo) {
            return pathObjetivo.esLegible() && pathObjetivo.esUnaCarpeta();
        }

        private void muestraSiEsUnaCarpetaSiEsUnArchivoDaUnMensajeDeError(Path pathObjetivo) {
            if (!pathObjetivo.esUnaCarpeta()) {
                generarMensajeDeErrorDeFicheros("Para descargar un archivo, utilice la barra de herramientas");
            }
        }

        private void mostrarCarpeta(Path pathObjetivo) {
            irAlPath(pathObjetivo);
            tabla.ocultarMensaje();
        }
        

        private void muestraSiEsLegibleYSiNoMuestraUnMensajeDeError(Path pathObjetivo) {
            if (!pathObjetivo.esLegible()) {
                generarMensajeDeErrorDeFicheros("No tiene privilegios de lectura para ese directorio");
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
    }
}
