package alvarodelrosal.ftp.ui.ventanas;

import alvarodelrosal.ftp.modelo.Path;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaDePropiedades extends Ventana {

    private JFrame ventanaDePropiedades;
    private JLabel etiquetaNombre;
    private JLabel etiquetaPeso;
    private JLabel etiquetaClase;
    private JLabel etiquetaUbicacion;
    private JLabel etiquetaModificacion;
    private JLabel etiquetaPermisos;
    private JCheckBox checkBoxOculto;

    public VentanaDePropiedades() {
        ventanaDePropiedades = null;
    }

    public void crear(Path path) {
        if (laVentanaNoExiste()) {
            ventanaDePropiedades = new JFrame();

            parametrosDeVentana();
            ventanaDePropiedades.setResizable(false);

            generaLaEtiquetaNombre(path);
            ventanaDePropiedades.add(etiquetaNombre);

            generaLaEtiquetaPeso();
            ventanaDePropiedades.add(etiquetaPeso);

            ventanaDePropiedades.add(crearEtiquetaColumna1("Clase:", 0));
            ventanaDePropiedades.add(crearEtiquetaColumna1("Ubicación:", 1));
            ventanaDePropiedades.add(crearEtiquetaColumna1("Modificación:", 2));
            ventanaDePropiedades.add(crearEtiquetaColumna1("Permisos:", 3));
            ventanaDePropiedades.add(crearEtiquetaColumna1("Oculto:", 4));
            
            etiquetaClase = crearEtiquetaColumna2(0);
            ventanaDePropiedades.add(etiquetaClase);
            etiquetaUbicacion = crearEtiquetaColumna2(1);
            ventanaDePropiedades.add(etiquetaUbicacion);
            etiquetaModificacion = crearEtiquetaColumna2(2);
            ventanaDePropiedades.add(etiquetaModificacion);
            etiquetaPermisos = crearEtiquetaColumna2(3);
            ventanaDePropiedades.add(etiquetaPermisos);
            checkBoxOculto = crearCheckboxColumna2(4);
            ventanaDePropiedades.add(checkBoxOculto);
        }
        ventanaDePropiedades.setVisible(true);

        etiquetaNombre.setText(path.verNombre());
        etiquetaPeso.setText(path.verPeso());
        etiquetaClase.setText(path.verTipo());
        etiquetaUbicacion.setText(path.verPath());
        etiquetaModificacion.setText(path.ultimaModificacion());
        etiquetaPermisos.setText(determinarPermisos(path));
        checkBoxOculto.setSelected(path.estaOculto());
    }

    private void generaLaEtiquetaNombre(Path path) {
        etiquetaNombre = new JLabel();

        etiquetaNombre.setText(path.verNombre());
        Font fuenteNombre = new Font(etiquetaNombre.getFont().getName(),
                Font.BOLD, etiquetaNombre.getFont().getSize() + 2);
        etiquetaNombre.setFont(fuenteNombre);
        etiquetaNombre.setBounds(10, 10, 240, 20);
    }

    private boolean laVentanaNoExiste() {
        return ventanaDePropiedades == null;
    }

    private void parametrosDeVentana() throws HeadlessException {
        ventanaDePropiedades.setTitle("Información");
        ventanaDePropiedades.setSize(340, 200);
        ventanaDePropiedades.setLocation(0, 20);
        ventanaDePropiedades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ventanaDePropiedades.setLayout(null);
    }

    private JLabel crearEtiquetaColumna1(String texto, int posicion) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setBounds(20, 50 + 20 * posicion, 90, 20);
        etiqueta.setHorizontalAlignment(JLabel.RIGHT);
        return etiqueta;
    }

    private JLabel crearEtiquetaColumna2(int posicion) {
        JLabel etiqueta = new JLabel();
        etiqueta.setBounds(120, 50 + 20 * posicion, 320, 20);
        etiqueta.setHorizontalAlignment(JLabel.LEFT);
        return etiqueta;
    }
    
    private JCheckBox crearCheckboxColumna2(int posicion) {
        JCheckBox checkbox = new JCheckBox();
        checkbox.setBounds(120, 50 + 20 * posicion, 320, 20);
        checkbox.setEnabled(false);
        return checkbox;
    }

    public void hacerVisible() {
        ventanaDePropiedades.setVisible(true);
    }

    public void hacerInvisible() {
        ventanaDePropiedades.setVisible(false);
    }

    private void generaLaEtiquetaPeso() {
        etiquetaPeso = new JLabel();
        Font fuentePeso = new Font(etiquetaPeso.getFont().getName(),
                Font.BOLD, etiquetaPeso.getFont().getSize());
        etiquetaPeso.setFont(fuentePeso);
        etiquetaPeso.setBounds(270, 10, 300, 20);
    }

    private String determinarPermisos(Path path) {
        if (path.esEscribible() && path.esLegible()) {
            return "Leer y escribir";
        } else if(path.esEscribible()) {
            return "Escribir";
        } else if(path.esLegible()) {
            return "Leer";
        } else {
            return "Sin acceso";
        }
    }
}
