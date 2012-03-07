package alvarodelrosal.ftp.modelo;

import java.util.List;

public interface FTPAction {
    
    public String verNombre();
    public void ejecutar(List<String> parametros);
    public List<String> obtenerDatos();
    public Object respuestaEnObjeto();
    
}
