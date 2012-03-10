package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.modelo.Acciones.FTPDeleteUser;
import alvarodelrosal.ftp.modelo.RepositorioDeUsuarios;
import alvarodelrosal.ftp.modelo.Usuario;
import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import alvarodelrosal.ftp.ui.Toolbar;
import alvarodelrosal.ftp.ui.ventanas.Dialogo;
import alvarodelrosal.ftp.ui.ventanas.VentanaDeAgregarUsuario;
import alvarodelrosal.ftp.ui.ventanas.VentanaDeUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeToolbarsDeAdministracionDeUsuarios {

    private List<ElementoDeToolbar> elementos = new ArrayList();
    private VentanaDeUsuarios ventanaDeUsuarios;

    public FactoriaDeToolbarsDeAdministracionDeUsuarios(VentanaDeUsuarios ventanaDeUsuarios) {
        this.ventanaDeUsuarios = ventanaDeUsuarios;
        componerBarra();
    }

    private void componerBarra() {
        ElementoDeToolbar agregarUsuario = new ElementoDeToolbar("Agregar usuario", "plus");
        agregarUsuario.agregarActionListener(new ActionListenerParaAgregarUsuario());
        elementos.add(agregarUsuario);

        ElementoDeToolbar editarUsuario = new ElementoDeToolbar("Editar usuario", "pencil");
        editarUsuario.agregarActionListener(new ActionListenerParaModificarUsuario());
        elementos.add(editarUsuario);

        ElementoDeToolbar borrarUsuario = new ElementoDeToolbar("Borrar usuario", "minus");
        borrarUsuario.agregarActionListener(new ActionListenerDeBorrarUsuario());
        elementos.add(borrarUsuario);
    }

    public Toolbar obtener() {
        Toolbar acciones = new Toolbar("Administracion");
        acciones.agregarElementos(elementos);
        return acciones;
    }

    class ActionListenerParaAgregarUsuario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventanaDeUsuarios.obtenerLaTabla().obtenerTabla().ocultarMensaje();
            VentanaDeAgregarUsuario ventana = new VentanaDeAgregarUsuario(ventanaDeUsuarios,
                    ventanaDeUsuarios.obtenerConexion(), new RepositorioDeUsuarios());
            ventana.crear(null);
        }
    }

    class ActionListenerParaModificarUsuario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventanaDeUsuarios.obtenerLaTabla().obtenerTabla().ocultarMensaje();
            Usuario usuario = ventanaDeUsuarios.obtenerLaTabla().usuarioSeleccionado();

            if (usuario != null) {
                VentanaDeAgregarUsuario ventana = new VentanaDeAgregarUsuario(ventanaDeUsuarios,
                        ventanaDeUsuarios.obtenerConexion(), new RepositorioDeUsuarios());
                ventana.crear(usuario);
                
            } else {
                ventanaDeUsuarios.obtenerLaTabla().generarMensajeDeTabla("Seleccione algún usuario para modificar");
            }
        }
    }

    class ActionListenerDeBorrarUsuario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventanaDeUsuarios.obtenerLaTabla().obtenerTabla().ocultarMensaje();
            Usuario usuario = ventanaDeUsuarios.obtenerLaTabla().usuarioSeleccionado();

            if (usuario != null) {
                int respuesta = Dialogo.pintarMensajeDeConfirmacion("Borrar usuario", "¿Seguro que quiere borrar este usuario?");
                if (respuesta == 0) {
                    List<String> parametros = new ArrayList();
                    parametros.add(usuario.verNombre());
                    parametros.add(usuario.verUsername());
                    FTPDeleteUser delete = new FTPDeleteUser(ventanaDeUsuarios.obtenerConexion());
                    delete.ejecutar(parametros);

                    ventanaDeUsuarios.obtenerLaTabla().actualizarTabla();
                    ventanaDeUsuarios.obtenerLaTabla().generarMensajeDeTabla("El usuario se ha borrado correctamente");
                }
            } else {
                ventanaDeUsuarios.obtenerLaTabla().generarMensajeDeTabla("Seleccione algún usuario antes de borrar");
            }
        }
    }
}
