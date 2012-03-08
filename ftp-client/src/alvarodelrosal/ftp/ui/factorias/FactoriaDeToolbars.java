package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.modelo.Acciones.FTPBye;
import alvarodelrosal.ftp.modelo.Acciones.FTPIsFolder;
import alvarodelrosal.ftp.modelo.Acciones.FTPNewFolder;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.ui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeToolbars {

    private List<ElementoDeToolbar> elementos = new ArrayList();
    private VentanaPrincipal ventana;

    public FactoriaDeToolbars(VentanaPrincipal ventana) {
        this.ventana = ventana;
        componerBarra();
    }

    private void componerBarra() {
        ElementoDeToolbar desconectar = new ElementoDeToolbar("Desconectar", "control-power");
        desconectar.agregarActionListener(new DesconectarActionListener());
        elementos.add(desconectar);

        ElementoDeToolbar separador = new ElementoDeToolbar("Separador", "Separador");
        elementos.add(separador);

        ElementoDeToolbar crearCarpeta = new ElementoDeToolbar("Crear carpeta", "folder-plus");
        crearCarpeta.agregarActionListener(new ActionListenerParaCrearCarpeta());
        elementos.add(crearCarpeta);

        ElementoDeToolbar borrarCarpeta = new ElementoDeToolbar("Borrar carpeta", "folder-minus");
        elementos.add(borrarCarpeta);

        elementos.add(separador);

        ElementoDeToolbar subirArchivo = new ElementoDeToolbar("Subir archivo", "upload-cloud");
        elementos.add(subirArchivo);

        ElementoDeToolbar bajarArchivo = new ElementoDeToolbar("Dajar archivo", "download-cloud");
        elementos.add(bajarArchivo);

        elementos.add(separador);

        ElementoDeToolbar subirNivel = new ElementoDeToolbar("Subir un nivel", "node-select");
        subirNivel.agregarActionListener(new ActionListenerParaSubirUnNivelEnElSistema());
        elementos.add(subirNivel);

        elementos.add(separador);

        ElementoDeToolbar verInfo = new ElementoDeToolbar("Ver información del archivo", "information");
        elementos.add(verInfo);

        elementos.add(separador);

        ElementoDeToolbar administrarUsuarios = new ElementoDeToolbar("Administrar usuarios", "users");
        elementos.add(administrarUsuarios);
    }

    public Toolbar obtener() {
        Toolbar acciones = new Toolbar("Acciones");
        acciones.agregarElementos(elementos);
        return acciones;
    }

    class DesconectarActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            FTPBye bye = new FTPBye(ventana.obtenerLaConexion());
            bye.ejecutar(new ArrayList());
            System.exit(0);
        }
    }

    class ActionListenerParaSubirUnNivelEnElSistema implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String pathActual = ventana.pathActual().verPath();
            if (pathActual != null) {
                ventana.irAlPath(pathActual);
            }
        }
    }

    class ActionListenerParaCrearCarpeta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            if (ventana.pathActual().esEscribible()) {
                String nombreCarpeta = Dialogo.pintarCuadroDeDialogo("Carpeta nueva",
                        "Introduzca el nombre de la carpeta nueva");
                String direccion = ventana.pathActual().verPathCompleto() + nombreCarpeta;

                ArrayList parametrosDeLaNuevaCarpeta = new ArrayList();
                parametrosDeLaNuevaCarpeta.add(direccion);

                FTPNewFolder nuevaCarpeta = new FTPNewFolder(ventana.obtenerLaConexion());
                nuevaCarpeta.ejecutar(parametrosDeLaNuevaCarpeta);
            } else {
                ventana.agregarMensajeDeError("No tiene privilegios para crear una carpeta en la ubicación actual");
            }
        }
    }
}
