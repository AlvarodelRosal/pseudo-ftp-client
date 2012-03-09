package alvarodelrosal.ftp.ui.factorias;

import alvarodelrosal.ftp.ui.ElementoDeToolbar;
import alvarodelrosal.ftp.ui.Toolbar;
import alvarodelrosal.ftp.ui.ventanas.VentanaDeUsuarios;
import alvarodelrosal.ftp.ui.ventanas.VentanaPrincipal;
import java.util.ArrayList;
import java.util.List;

public class FactoriaDeToolbarsDeAdministracionDeUsuarios {
    
    private List<ElementoDeToolbar> elementos = new ArrayList();
    private VentanaDeUsuarios ventanaDeUsuarios;

    public FactoriaDeToolbarsDeAdministracionDeUsuarios(VentanaDeUsuarios ventanaDeUsuarios) {
        this.ventanaDeUsuarios = ventanaDeUsuarios;
        componerBarra();
    }

    private void componerBarra() {
        ElementoDeToolbar agregarUsuario = new ElementoDeToolbar("Agregar usuario", "control-power");
        elementos.add(agregarUsuario);
        
        ElementoDeToolbar editarUsuario = new ElementoDeToolbar("Editar usuario", "control-power");
        elementos.add(editarUsuario);
        
        ElementoDeToolbar borrarUsuario = new ElementoDeToolbar("Borrar usuario", "control-power");
        elementos.add(borrarUsuario);
    }

    public Toolbar obtener() {
        Toolbar acciones = new Toolbar("Administracion");
        acciones.agregarElementos(elementos);
        return acciones;
    }
    
}
