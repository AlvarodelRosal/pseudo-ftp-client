package alvarodelrosal.ftp.ui;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ElementoDeToolbar {

    private String descripcion;
    private String icono;
    private JButton boton;

    public ElementoDeToolbar(String descripcion, String icono) {
        this.descripcion = descripcion;
        this.icono = icono;

        if ("Separador".equals(icono)) {
            boton = null;
        } else {
            boton = new JButton(new ImageIcon(getClass().getResource("/iconos/" + icono + ".png")));
            boton.setToolTipText(descripcion);
        }
    }

    public void agregarActionListener(ActionListener listener) {
        boton.addActionListener(listener);
    }

    public String verDescripcion() {
        return descripcion;
    }

    public String verIcono() {
        return icono;
    }

    public boolean esUnSeparador() {
        return "Separador".equals(descripcion) || "Separador".equals(icono);
    }

    public JButton obtenerElemento() {
        if (esUnSeparador()) {
            return null;
        } else {
            return boton;
        }
    }
}
