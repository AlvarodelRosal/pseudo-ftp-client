package alvarodelrosal.ftp.ui.ventanas;

import alvarodelrosal.ftp.modelo.Acciones.FTPAddUser;
import alvarodelrosal.ftp.modelo.Acciones.FTPDelete;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.RepositorioDeUsuarios;
import alvarodelrosal.ftp.modelo.Usuario;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VentanaDeAgregarUsuario extends Ventana {

    private JFrame ventanaDeAgregarUsuario;
    private JComboBox campoTipoCuenta;
    private JTextField campoNombre;
    private JTextField campoNombreDeUsuario;
    private JTextField campoClave;
    private JTextField campoVerificarClave;
    private Conexion conexion;
    private RepositorioDeUsuarios usuarios;
    private VentanaDeUsuarios ventanaDeUsuarios;
    private Usuario usuario;

    public VentanaDeAgregarUsuario(VentanaDeUsuarios ventanaDeUsuarios, Conexion conexion, RepositorioDeUsuarios usuarios) {
        ventanaDeAgregarUsuario = null;
        this.ventanaDeUsuarios = ventanaDeUsuarios;
        this.conexion = conexion;
        this.usuarios = usuarios;
    }

    public void crear(Usuario usuario) {
        if (usuario == null) {
            this.usuario = new Usuario("", "", false);
        } else {
            this.usuario = usuario;
        }

        if (laVentanaNoExiste()) {
            ventanaDeAgregarUsuario = new JFrame();

            parametrosDeVentana();

            JLabel etiquetaTipoCuenta = crearEtiqueta("Nueva cuenta:", 0);
            ventanaDeAgregarUsuario.add(etiquetaTipoCuenta);
            campoTipoCuenta = crearCampoDesplegable(0);
            ventanaDeAgregarUsuario.add(campoTipoCuenta);

            JLabel etiquetaNombre = crearEtiqueta("Nombre completo:", 1);
            ventanaDeAgregarUsuario.add(etiquetaNombre);
            campoNombre = crearCampo(1, this.usuario.verNombre());
            ventanaDeAgregarUsuario.add(campoNombre);

            JLabel etiquetaNombreDeUsuario = crearEtiqueta("Nombre de usuario:", 2);
            ventanaDeAgregarUsuario.add(etiquetaNombreDeUsuario);
            campoNombreDeUsuario = crearCampo(2, this.usuario.verUsername());
            ventanaDeAgregarUsuario.add(campoNombreDeUsuario);

            JLabel etiquetaClave = crearEtiqueta("Contraseña:", 3);
            ventanaDeAgregarUsuario.add(etiquetaClave);
            campoClave = crearCampoClave(3);
            ventanaDeAgregarUsuario.add(campoClave);

            JLabel etiquetaVerifica = crearEtiqueta("Verificar:", 4);
            ventanaDeAgregarUsuario.add(etiquetaVerifica);
            campoVerificarClave = crearCampoClave(4);
            ventanaDeAgregarUsuario.add(campoVerificarClave);

            ventanaDeAgregarUsuario.add(botonDeAceptar());
            ventanaDeAgregarUsuario.add(botonDeCancelar());

            ventanaDeAgregarUsuario.setResizable(false);
        }

        ventanaDeAgregarUsuario.setVisible(true);
    }

    private JButton botonDeAceptar() {
        JButton conectar = new JButton("Aceptar");
        conectar.setBounds(170, 160, 100, 30);
        ventanaDeAgregarUsuario.getRootPane().setDefaultButton(conectar);
        conectar.addActionListener(new ActionListenerDeEnviar());
        return conectar;
    }

    private JButton botonDeCancelar() {
        JButton salir = new JButton("Cancelar");
        salir.setBounds(60, 160, 100, 30);
        salir.addActionListener(new ActionListenerDeCancelar());
        return salir;
    }

    private boolean laVentanaNoExiste() {
        return ventanaDeAgregarUsuario == null;
    }

    private void parametrosDeVentana() throws HeadlessException {
        ventanaDeAgregarUsuario.setTitle("Usuarios");
        ventanaDeAgregarUsuario.setSize(340, 220);

        Toolkit toolkit = ventanaDeAgregarUsuario.getToolkit();
        Dimension size = toolkit.getScreenSize();
        ventanaDeAgregarUsuario.setLocation((size.width - ventanaDeAgregarUsuario.getWidth()) / 2,
                (size.height - ventanaDeAgregarUsuario.getHeight()) / 2);
        ventanaDeAgregarUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ventanaDeAgregarUsuario.setLayout(null);
    }

    private JLabel crearEtiqueta(String nombre, int posicion) {
        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setBounds(20, 10 + 30 * posicion, 130, 30);
        etiqueta.setHorizontalAlignment(JLabel.RIGHT);
        return etiqueta;
    }

    private JTextField crearCampo(int posicion, String textoPorDefecto) {
        JTextField campo = new JTextField();
        campo.setText(textoPorDefecto);
        campo.setBounds(170, 10 + 30 * posicion, 150, 30);
        return campo;
    }

    private JPasswordField crearCampoClave(int posicion) {
        JPasswordField campo = new JPasswordField();
        campo.setBounds(170, 10 + 30 * posicion, 150, 30);
        return campo;
    }

    private JComboBox crearCampoDesplegable(int posicion) {
        String[] tiposDeCuenta = {"Usuario", "Administrador"};

        JComboBox tipos = new JComboBox(tiposDeCuenta);
        if (usuario.esAdmin()) {
            tipos.setSelectedIndex(1);
        } else {
            tipos.setSelectedIndex(0);
        }
        tipos.setBounds(170, 10 + 30 * posicion, 150, 30);

        return tipos;
    }

    class ActionListenerDeCancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventanaDeAgregarUsuario.setVisible(false);
        }
    }

    class ActionListenerDeEnviar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (lasClavesSonIgualesYNoVacias()) {
                if (esUnaModificacionDeUsuario()) {
                    List<String> parametrosDeBorrado = new ArrayList();
                    FTPDelete delete = new FTPDelete(conexion);

                    parametrosDeBorrado.add(usuario.verNombre());
                    parametrosDeBorrado.add(usuario.verUsername());

                    delete.ejecutar(parametrosDeBorrado);
                }

                if (!existeElUsername(campoNombreDeUsuario.getText())) {
                    List<String> parametros = new ArrayList();
                    parametros.add(campoNombre.getText());
                    parametros.add(campoNombreDeUsuario.getText());
                    parametros.add(campoClave.getText());
                    parametros.add(String.valueOf(elUsuarioEsAdmin()));

                    FTPAddUser addUser = new FTPAddUser(conexion);
                    addUser.ejecutar(parametros);

                    ventanaDeAgregarUsuario.setVisible(false);
                    ventanaDeUsuarios.obtenerLaTabla().actualizarTabla();
                    if (esUnaModificacionDeUsuario()) {
                        ventanaDeUsuarios.obtenerLaTabla().generarMensajeDeTabla("Usuario modificado correctamente");
                    } else {
                        ventanaDeUsuarios.obtenerLaTabla().generarMensajeDeTabla("Usuario añadido correctamente");
                    }
                } else {
                    Dialogo.pintarMensajeDeError("Usuario existente",
                            "El usuario introducido ya existe");
                }
            } else {
                Dialogo.pintarMensajeDeError("Contraseñas no coincidentes",
                        "Las contraseñas no conciden");
            }
            ventanaDeUsuarios.obtenerLaTabla().actualizarTabla();
        }

        private boolean esUnaModificacionDeUsuario() {
            return !"".equals(usuario.verNombre()) && !"".equals(usuario.verUsername());
        }

        private boolean lasClavesSonIgualesYNoVacias() {
            return (campoClave.getText() == null ? campoVerificarClave.getText() == null : campoClave.getText().equals(campoVerificarClave.getText())) && !"".equals(campoClave.getText());
        }

        private boolean existeElUsername(String username) {
            List<Usuario> usuariosLeidos = usuarios.obtenerTodosLosUsuarios(conexion);
            for (Usuario usuario : usuariosLeidos) {
                if (usuario.verUsername() == null ? username == null : usuario.verUsername().equals(username)) {
                    return true;
                }
            }
            return false;
        }

        private boolean elUsuarioEsAdmin() {
            return campoTipoCuenta.getSelectedIndex() == 1;
        }
    }
}
