package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.ui.factorias.FactoriaDeToolbars;
import alvarodelrosal.ftp.ui.VentanaPrincipal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class FTPClientLauncher {

    public static void main(String[] args) throws IOException {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.agregarToolbar(new FactoriaDeToolbars().obtener());
        //ventanaPrincipal.construirTablaConScroll();
        ventanaPrincipal.crear();
        
        String host = "localhost";
        int puerto = 9999;
        
        Socket socketCliente = null;
        try {
            socketCliente = new Socket(host, puerto);
        } catch (UnknownHostException ex) {
            System.out.println("El host indicado no se puede alcanzar. Compruebe su conexion");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

        salida.println("user<:@:>user");
        System.out.println(entrada.readLine());
        
        salida.println("Hello<:@:>Test");
        System.out.println(entrada.readLine());
        
        salida.println("Look<:@:>/Users");
        System.out.println("Look " + entrada.readLine());
        
        salida.println("CanRead<:@:>/Users");
        System.out.println("CanRead " + entrada.readLine());
        
        salida.println("CanWrite<:@:>/Users");
        System.out.println("CanWrite " + entrada.readLine());
        
        salida.println("IsHidden<:@:>/Users");
        System.out.println("IsHidden " + entrada.readLine());
        
        salida.println("LastModified<:@:>/Users");
        System.out.println("LastModified " + entrada.readLine());
        
        salida.println("Size<:@:>/Users");
        System.out.println("Size " + entrada.readLine());
        
        salida.println("IsFolder<:@:>/Users");
        System.out.println("IsFolder " + entrada.readLine());
        
        salida.println("IsFile<:@:>/Users");
        System.out.println("IsFile " + entrada.readLine());
        
        salida.println("Bye");
        System.out.println(entrada.readLine());
        
    }
    
}
