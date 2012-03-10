package alvarodelrosal.ftp.ui.ventanas;

import alvarodelrosal.ftp.modelo.Acciones.FTPLogin;
import alvarodelrosal.ftp.modelo.Conexion;
import alvarodelrosal.ftp.modelo.Usuario;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VentanaDeLogin extends Ventana {

    private JFrame ventanaDeLogin;
    private JTextField campoUsuario;
    private JTextField campoClave;
    private JTextField campoHost;
    private JTextField campoPuerto;

    public VentanaDeLogin() {
        ventanaDeLogin = null;
    }

    public void crear() {
        if (laVentanaNoExiste()) {
            ventanaDeLogin = new JFrame();

            parametrosDeVentana();

            JLabel etiquetaUsuario = crearEtiqueta("Nombre de usuario:", 0);
            ventanaDeLogin.add(etiquetaUsuario);
            campoUsuario = crearCampo(0);
            campoUsuario.setText("adr");
            ventanaDeLogin.add(campoUsuario);

            JLabel etiquetaClave = crearEtiqueta("Contraseña:", 1);
            ventanaDeLogin.add(etiquetaClave);
            campoClave = crearCampoClave(1);
            campoClave.setText("123456");
            ventanaDeLogin.add(campoClave);

            JLabel etiquetaHost = crearEtiqueta("Host:", 2);
            ventanaDeLogin.add(etiquetaHost);
            campoHost = crearCampo(2);
            campoHost.setText("192.168.2.106");
            ventanaDeLogin.add(campoHost);

            JLabel etiquetaPuerto = crearEtiqueta("Puerto:", 3);
            ventanaDeLogin.add(etiquetaPuerto);
            campoPuerto = crearCampo(3);
            campoPuerto.setText("9999");
            ventanaDeLogin.add(campoPuerto);

            ventanaDeLogin.add(botonDeConectar());

            ventanaDeLogin.add(botonDeSalir());

            ventanaDeLogin.setResizable(false);
            ventanaDeLogin.setVisible(true);
        }
    }

    private JButton botonDeConectar() {
        ImageIcon iconoCandado = new ImageIcon(getClass().getResource("/iconos/lock.png"));

        JButton conectar = new JButton("Conectar", iconoCandado);
        conectar.setBounds(170, 130, 100, 30);
        ventanaDeLogin.getRootPane().setDefaultButton(conectar);
        conectar.addActionListener(new actionListenerDeConexion());
        return conectar;
    }

    private JButton botonDeSalir() {
        ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/iconos/door-open.png"));

        JButton salir = new JButton("Salir", iconoSalir);
        salir.setBounds(60, 130, 100, 30);
        salir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        return salir;
    }

    private boolean laVentanaNoExiste() {
        return ventanaDeLogin == null;
    }

    private void parametrosDeVentana() throws HeadlessException {
        ventanaDeLogin.setTitle("Iniciar sesión");
        ventanaDeLogin.setSize(340, 200);

        Toolkit toolkit = ventanaDeLogin.getToolkit();
        Dimension size = toolkit.getScreenSize();
        ventanaDeLogin.setLocation((size.width - ventanaDeLogin.getWidth()) / 2,
                (size.height - ventanaDeLogin.getHeight()) / 2);
        ventanaDeLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventanaDeLogin.setLayout(null);
    }

    private JLabel crearEtiqueta(String nombre, int posicion) {
        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setBounds(20, 10 + 30 * posicion, 130, 30);
        etiqueta.setHorizontalAlignment(JLabel.RIGHT);
        return etiqueta;
    }

    private JTextField crearCampo(int posicion) {
        JTextField campo = new JTextField();
        campo.setBounds(170, 10 + 30 * posicion, 150, 30);
        return campo;
    }

    private JPasswordField crearCampoClave(int posicion) {
        JPasswordField campo = new JPasswordField();
        campo.setBounds(170, 10 + 30 * posicion, 150, 30);
        return campo;
    }

    class actionListenerDeConexion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int puerto = new Integer(campoPuerto.getText());

            Conexion conexion = new Conexion(campoHost.getText(), puerto);
            FTPLogin login = new FTPLogin(conexion);
            List<String> parametrosDeLogin = new ArrayList();
            parametrosDeLogin.add(campoUsuario.getText());
            parametrosDeLogin.add(campoClave.getText());

            login.ejecutar(parametrosDeLogin);
            Usuario usuario = login.respuestaEnObjeto();

            if (existeEl(usuario)) {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(conexion, usuario);
                ventanaDeLogin.setVisible(false);
                ventanaPrincipal.generar();
            } else {
                Dialogo.pintarMensajeDeError("Datos incorrectos",
                        "Datos de conexión incorrectos.\nPor favor, revíselos.");
            }
        }

        ;

            private boolean existeEl(Usuario usuario) {
            return usuario != null;
        }
    }
}
