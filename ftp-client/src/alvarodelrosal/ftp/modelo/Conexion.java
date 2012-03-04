package alvarodelrosal.ftp.modelo;

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
        } catch (UnknownHostException ex) {
            System.out.println("El host indicado no se puede alcanzar. Compruebe su conexion");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            salida = new PrintWriter(socketCliente.getOutputStream(), true);
        } catch (IOException ex) {
            try {
                socketCliente.close();
            } catch (IOException ex1) {
            }
        }
        try {
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        } catch (IOException ex) {
            try {
                socketCliente.close();
            } catch (IOException ex1) {
            } finally {
                salida.close();
            }
        }
        
    }
    
    public void escribir(String cadena) {
        System.out.println("SALIDA:");
        System.out.println(cadena);
        salida.println(cadena);
    }
    
    public String leer() throws IOException {
        String lectura = entrada.readLine();
        System.out.println("ENTRADA:");
        System.out.println(lectura);
        return lectura;
    }
    
}
