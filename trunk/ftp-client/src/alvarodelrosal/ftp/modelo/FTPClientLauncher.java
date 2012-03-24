package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.ui.ventanas.VentanaDeLogin;
import java.io.IOException;

public class FTPClientLauncher {

    public static void main(String[] args) throws IOException {
        
        VentanaDeLogin ventanaDeLogin = new VentanaDeLogin();
        ventanaDeLogin.crear();
    }
    
}
