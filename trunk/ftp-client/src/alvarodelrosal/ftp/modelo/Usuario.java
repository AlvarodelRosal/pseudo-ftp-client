package alvarodelrosal.ftp.modelo;

public class Usuario {
    
    private String nombre;
    private boolean esAdmin;

    public Usuario(String nombre, boolean esAdmin) {
        this.nombre = nombre;
        this.esAdmin = esAdmin;
    }

    public boolean esAdmin() {
        return esAdmin;
    }

    public String verNombre() {
        return nombre;
    }
    
}
