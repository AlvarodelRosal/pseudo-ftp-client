package alvarodelrosal.ftp.ui;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ElementoDeToolbar {
    
    private final String nombre;
    private final String nombreDelIcono;
    private ActionListener actionListener;

    public ElementoDeToolbar(String nombre, String nombreDelIcono) {
        this.nombre = nombre;
        this.nombreDelIcono = nombreDelIcono;
        this.actionListener = null;
    }

    public void agregarActionListener (ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    
    public JButton generarBotonDeToolbar() {
        JButton boton = generaElBotonConIcono();
        agregaAyudaAlBoton(boton);
        siTieneActionListenerLoAgrega(boton);
        
        return boton;
    }

    private void agregaAyudaAlBoton(JButton boton) {
        boton.setToolTipText(this.nombre);
    }

    private JButton generaElBotonConIcono() {
        JButton boton = new JButton(new ImageIcon(getClass().
                getResource("/iconos/" + this.nombreDelIcono + ".png")));
        return boton;
    }

    private void siTieneActionListenerLoAgrega(JButton boton) {
        if (tieneActionListener()) {
            boton.addActionListener(this.actionListener);
        }
    }

    private boolean tieneActionListener() {
        return this.actionListener != null;
    }
}
