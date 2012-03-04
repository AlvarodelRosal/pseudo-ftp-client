package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.infraestructura.FTPLogin;
import alvarodelrosal.ftp.modelo.Conexion;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            ventanaDeLogin.add(campoUsuario);

            JLabel etiquetaClave = crearEtiqueta("Contraseña:", 1);
            ventanaDeLogin.add(etiquetaClave);
            campoClave = crearCampoClave(1);
            ventanaDeLogin.add(campoClave);

            JLabel etiquetaHost = crearEtiqueta("Host:", 2);
            ventanaDeLogin.add(etiquetaHost);
            campoHost = crearCampo(2);
            ventanaDeLogin.add(campoHost);

            JLabel etiquetaPuerto = crearEtiqueta("Puerto:", 3);
            ventanaDeLogin.add(etiquetaPuerto);
            campoPuerto = crearCampo(3);
            ventanaDeLogin.add(campoPuerto);

            JButton conectar = new JButton("Conectar");
            conectar.setBounds(170, 130, 100, 30);
            conectar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    int puerto = new Integer(campoPuerto.getText());

                    Conexion conexion = new Conexion(campoHost.getText(), puerto);
                    FTPLogin login = new FTPLogin();
                    if (login.existe(campoUsuario.getText(), campoClave.getText(), conexion)) {
                        List<String> parametros = login.obtenerDatos();
                        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(conexion);
                        ventanaPrincipal.establecerNombre(parametros.get(0));
                        ventanaPrincipal.establecerAdministrador(Boolean.valueOf(parametros.get(1)));
                        
                        ventanaPrincipal.crear();
                    }
                }
            });

            ventanaDeLogin.add(conectar);

            JButton salir = botonDeSalir();
            ventanaDeLogin.add(salir);

            ventanaDeLogin.setResizable(false);
            ventanaDeLogin.setVisible(true);
        }
    }

    private JButton botonDeSalir() {
        JButton salir = new JButton("Salir");
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
}
