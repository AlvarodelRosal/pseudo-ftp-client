package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.ui.ventanas.Dialogo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conexion {

    private Socket socketCliente = null;
    private PrintWriter salida = null;
    private BufferedReader entrada = null;

    public Conexion(String host, int puerto) {
        try {
            socketCliente = new Socket(host, puerto);
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        } catch (UnknownHostException ex) {
            Dialogo.pintarMensajeDeError("Error de conexión",
                    "El host indicado no se puede alcanzar. Compruebe su conexion.");
        } catch (IOException ex) {
            Dialogo.pintarMensajeDeError("Error de conexión",
                    "El host indicado no se puede alcanzar. Compruebe que el servidor está iniciado.");
            try {
                if (socketCliente != null) {socketCliente.close();}
            } catch (IOException ex1) {
            } finally {
                if (salida != null) {salida.close();}
            }
        }
    }

    public void escribir(String cadena) {
        if (salida != null) {
            salida.println(cadena);
        }
    }

    public String leer() throws IOException {
        if (entrada != null) {
            String lectura = entrada.readLine();
            return lectura;
        } else {
            return "";
        }
    }
}
