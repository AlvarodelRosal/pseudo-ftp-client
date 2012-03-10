package alvarodelrosal.ftp.modelo;

public class Usuario {
    
    private String nombre;
    private String username;
    private boolean esAdmin;

    public Usuario(String nombre, String username, boolean esAdmin) {
        this.nombre = nombre;
        this.username = username;
        this.esAdmin = esAdmin;
    }

    public boolean esAdmin() {
        return esAdmin;
    }
    
    public String verUsername() {
        return username;
    }

    public String verNombre() {
        return nombre;
    }
    
    public String verNivel() {
        if (esAdmin) {
            return "Administrador";
        } else {
            return "Usuario";
        }
    }
    
}
