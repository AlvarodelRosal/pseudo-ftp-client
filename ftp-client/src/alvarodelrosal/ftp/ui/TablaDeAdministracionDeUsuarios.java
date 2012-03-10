package alvarodelrosal.ftp.ui;

import alvarodelrosal.ftp.modelo.Usuario;

public class TablaDeAdministracionDeUsuarios {
    
    private TablaConScroll tabla;
    private ModeloDeTablaDeUsuarios modelo;

    public TablaDeAdministracionDeUsuarios(ModeloDeTablaDeUsuarios modelo) {
        this.modelo = modelo;
        this.tabla = new TablaConScroll(modelo);
    }
    
    public TablaConScroll obtenerTabla() {
        return tabla;
    }
    
    public Usuario usuarioSeleccionado() {
        if(tabla.filaSeleccionada() < 0) {
            return null;
        } else {
            return modelo.elementoEn(tabla.filaSeleccionada());
        }
    }
    
    public void generarMensajeDeTabla(String mensaje) {
        tabla.generarMensaje(mensaje);
    }
    
    public void actualizarTabla() {
        modelo.actualizar();
    }
    
}
