package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.modelo.Acciones.FTPBye;
import alvarodelrosal.ftp.modelo.Acciones.FTPDelete;
import alvarodelrosal.ftp.modelo.Acciones.FTPNewFolder;
import alvarodelrosal.ftp.modelo.Acciones.FTPRead;
import alvarodelrosal.ftp.modelo.Path;
import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import alvarodelrosal.ftp.ui.Toolbar;
import alvarodelrosal.ftp.ui.ventanas.Dialogo;
import alvarodelrosal.ftp.ui.ventanas.VentanaDePropiedades;
import alvarodelrosal.ftp.ui.ventanas.VentanaDeUsuarios;
import alvarodelrosal.ftp.ui.ventanas.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactoriaDeToolbarsDeAccionesDeLaVentanaPrincipal {

    private List<ElementoDeToolbar> elementos = new ArrayList();
    private VentanaPrincipal ventana;
    private VentanaDeUsuarios ventanaDeUsuarios = null;
    private VentanaDePropiedades ventanaDePropiedades = null;

    public FactoriaDeToolbarsDeAccionesDeLaVentanaPrincipal(VentanaPrincipal ventana) {
        this.ventana = ventana;
        componerBarra();
    }

    private void componerBarra() {
        ElementoDeToolbar desconectar = new ElementoDeToolbar("Desconectar", "control-power");
        desconectar.agregarActionListener(new DesconectarActionListener());
        elementos.add(desconectar);

        ElementoDeToolbar separador = new ElementoDeToolbar("Separador", "Separador");
        elementos.add(separador);

        if (ventana.obtenerElUsuario().esAdmin()) {
            ElementoDeToolbar crearCarpeta = new ElementoDeToolbar("Crear carpeta", "folder-plus");
            crearCarpeta.agregarActionListener(new ActionListenerParaCrearCarpeta());
            elementos.add(crearCarpeta);

            ElementoDeToolbar borrarCarpeta = new ElementoDeToolbar("Borrar", "eraser");
            borrarCarpeta.agregarActionListener(new ActionListenerParaBorrar());
            elementos.add(borrarCarpeta);

            elementos.add(separador);
        }

        ElementoDeToolbar subirArchivo = new ElementoDeToolbar("Subir archivo", "upload-cloud");
        elementos.add(subirArchivo);

        ElementoDeToolbar bajarArchivo = new ElementoDeToolbar("Bajar archivo", "download-cloud");
        bajarArchivo.agregarActionListener(new ActionListenerParaBajarArchivo());
        elementos.add(bajarArchivo);

        elementos.add(separador);

        ElementoDeToolbar subirNivel = new ElementoDeToolbar("Subir un nivel", "node-select");
        subirNivel.agregarActionListener(new ActionListenerParaSubirUnNivelEnElSistema());
        elementos.add(subirNivel);

        elementos.add(separador);

        ElementoDeToolbar verInfo = new ElementoDeToolbar("Ver información del archivo", "information");
        verInfo.agregarActionListener(new ActionListenerDeVerInformacion());
        elementos.add(verInfo);

        if (ventana.obtenerElUsuario().esAdmin()) {
            elementos.add(separador);

            ElementoDeToolbar administrarUsuarios = new ElementoDeToolbar("Administrar usuarios", "users");
            administrarUsuarios.agregarActionListener(new ActionListenerDeAdministracionDeUsuarios());
            elementos.add(administrarUsuarios);
        }
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

    class ActionListenerParaCrearCarpeta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (ventana.pathActual().esEscribible()) {
                String nombreCarpeta = Dialogo.pintarCuadroDeDialogo("Carpeta nueva",
                        "Introduzca el nombre de la carpeta nueva");
                if (!"null".equals(nombreCarpeta)) {
                    String direccion = ventana.pathActual().verPathCompleto() + nombreCarpeta;

                    ArrayList parametrosDeLaNuevaCarpeta = new ArrayList();
                    parametrosDeLaNuevaCarpeta.add(direccion);

                    FTPNewFolder nuevaCarpeta = new FTPNewFolder(ventana.obtenerLaConexion());
                    nuevaCarpeta.ejecutar(parametrosDeLaNuevaCarpeta);

                    ventana.irAlPath(ventana.pathActual().verPathCompleto());
                }
            } else {
                ventana.agregarMensajeDeError("No tiene privilegios para crear una carpeta en la ubicación actual");
            }
        }
    }

    class ActionListenerParaBorrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Path elementoABorrar = ventana.pathSeleccionado();

            if (elementoABorrar == null) {
                ventana.agregarMensajeDeError("Seleccione algún elemento para borrar");
            } else {
                int respuesta = Dialogo.pintarMensajeDeConfirmacion("Borrar",
                        "¿Seguro que quiere borrar \"" + elementoABorrar.verNombre() + "\"?");

                if (respuesta == 0) {
                    FTPDelete delete = new FTPDelete(ventana.obtenerLaConexion());
                    List<String> parametros = new ArrayList();
                    parametros.add(elementoABorrar.verPathCompleto());

                    delete.ejecutar(parametros);
                    ventana.irAlPath(ventana.pathActual().verPathCompleto());
                }
            }
        }
    }

    class ActionListenerParaBajarArchivo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Path elementoADescargar = ventana.pathSeleccionado();

            if (elementoADescargar.esUnaCarpeta()) {
                ventana.agregarMensajeDeError("Por favor, seleccione un archivo para descargar");
            } else if (!elementoADescargar.esLegible()) {
                ventana.agregarMensajeDeError("El archivo seleccionado no se puede leer");
            } else {
                File destino = null;
                try {
                    destino = Dialogo.pintarMensajeDeGuardar();
                } catch (IOException ex) {
                    Logger.getLogger(FactoriaDeToolbarsDeAccionesDeLaVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                FileOutputStream escritor = null;

                if (hayLugarSeleccionado(destino)) {

                    if (destino.canWrite() && destino.isFile()) {
                        try {
                            escritor = new FileOutputStream(destino);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }

                        long numeroDePartes = elementoADescargar.verNumeroDeBytes() / 1000;
                        FTPRead lectura = new FTPRead(ventana.obtenerLaConexion());

                        for (int parte = 0; parte <= numeroDePartes; parte++) {
                            List<String> parametros = new ArrayList();
                            parametros.add(elementoADescargar.verPathCompleto());
                            parametros.add(String.valueOf(parte));
                            lectura.ejecutar(parametros);

                            String lecturaDelArchivo = lectura.obtenerDatos().get(0);
                            byte[] bytesDelArchivo = lecturaDelArchivo.getBytes();
                            try {
                                escritor.write(bytesDelArchivo);
                            } catch (IOException ex) {
                            }
                        }
                    } else {
                        ventana.agregarMensajeDeError("No se puede escribir en el directorio elegido");
                    }
                }
            }

        }

        private boolean hayLugarSeleccionado(File destino) {
            return destino != null;
        }
    }

    class ActionListenerParaSubirUnNivelEnElSistema implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String pathActual = ventana.pathActual().verPathCompleto();
            if (!"//".equals(pathActual)) {
                String pathObjetivo = pathActual.substring(0, pathActual.length() - 2);
                pathObjetivo = pathObjetivo.substring(0, pathObjetivo.lastIndexOf("/") + 1);
                ventana.irAlPath(pathObjetivo);
            }
        }
    }

    class ActionListenerDeVerInformacion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (laVentanaDenformacionNoExiste()) {
                ventanaDePropiedades = new VentanaDePropiedades();
            }

            if (haySeleccionadaAlgunaFila()) {
                ventanaDePropiedades.crear(ventana.pathSeleccionado());
            } else {
                ventanaDePropiedades.crear(ventana.pathActual());
            }
            ventanaDePropiedades.hacerVisible();
        }

        private boolean haySeleccionadaAlgunaFila() {
            return ventana.pathSeleccionado() != null;
        }

        private boolean laVentanaDenformacionNoExiste() {
            return ventanaDePropiedades == null;
        }
    }

    class ActionListenerDeAdministracionDeUsuarios implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (laVentanaDeUsuariosNoExiste()) {
                ventanaDeUsuarios = new VentanaDeUsuarios(ventana.obtenerLaConexion());
                ventanaDeUsuarios.hacerVisible();
            } else {
                ventanaDeUsuarios.hacerVisible();
            }
        }

        private boolean laVentanaDeUsuariosNoExiste() {
            return ventanaDeUsuarios == null;
        }
    }
}
