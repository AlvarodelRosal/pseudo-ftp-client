package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.modelo.Path;
import alvarodelrosal.ftp.modelo.RepositorioDePaths;
import alvarodelrosal.ftp.ui.ventanas.VentanaPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class TablaDeNavegacionDeArchivos extends ElementoDeVentana {

    private TablaConScroll tabla;
    private Path pathActual;
    private ModeloDeTablaDeArchivos modelo;
    private VentanaPrincipal ventana;

    public TablaDeNavegacionDeArchivos(TableModel modelo, TableCellRenderer render,
            VentanaPrincipal ventana) {
        this.modelo = (ModeloDeTablaDeArchivos) modelo;
        tabla = new TablaConScroll(modelo);
        tabla.agregarClickListenerALaTabla(new ListenerDeDobleClick());
        tabla.agregarRender(0, render);
        this.ventana = ventana;
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
        RepositorioDePaths repositorio = new RepositorioDePaths();
        this.pathActual = repositorio.obtenerElPath(path.verPathCompleto(), "", ventana.obtenerLaConexion());
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
            ventana.irAlPath(pathObjetivo.verPathCompleto());
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
