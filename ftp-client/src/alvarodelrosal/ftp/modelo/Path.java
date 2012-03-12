package alvarodelrosal.ftp.modelo;

import java.util.Date;

public class Path {

    private String path;
    private String nombre;
    private boolean legible;
    private boolean escribible;
    private boolean oculto;
    private boolean esUnaCarpeta;
    private String ultimaModificacion;
    private long peso;

    public Path(String path, String nombre) {
        this.path = path;
        this.nombre = nombre;
    }

    public String verNombre() {
        return nombre;
    }

    public void esUnaCarpeta(boolean esUnaCarpeta) {
        this.esUnaCarpeta = esUnaCarpeta;
    }

    public void esEscribible(boolean escribible) {
        this.escribible = escribible;
    }

    public void esLegible(boolean legible) {
        this.legible = legible;
    }

    public void estaOculto(boolean oculto) {
        this.oculto = oculto;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }
    
    public long verNumeroDeBytes() {
        return peso;
    }

    public void ultimaModificacion(long ultimaModificacion) {
        Date fecha = new Date(ultimaModificacion);
        this.ultimaModificacion = fecha.toString();
    }

    public String verTipo() {
        if (esUnaCarpeta) {
            return "Carpeta";
        } else {
            return "Archivo";
        }
    }

    public String verPath() {
        return path;
    }

    public String verPathCompleto() {
        String direccion = path + nombre;
        if (esUnaCarpeta){
            direccion += "/";
        }
        if (acabaConDosBarrasLa(direccion)) {
            direccion = direccion.substring(0,direccion.length() - 1);
        }
        return direccion;
    }

    private boolean acabaConDosBarrasLa(String direccion) {
        return direccion.endsWith("//");
    }

    public boolean esUnaCarpeta() {
        return esUnaCarpeta;
    }

    public String ultimaModificacion() {
        return ultimaModificacion;
    }

    public boolean estaOculto() {
        return oculto;
    }

    public boolean esEscribible() {
        return escribible;
    }

    public boolean esLegible() {
        return legible;
    }

    public String verPeso() {
        if (esUnaCarpeta) {
            return "--";
        } else {
            String tamanoConTodosLosDecimales = calculaElTamanoConTodosLosDecimalesDePrecision();
            String unidad = calculaLaUnidad();
            String tamanoConDosDecimales = "";

            if (tieneDecimales(tamanoConTodosLosDecimales)) {
                int posicionDelSeparadorDecimal = tamanoConTodosLosDecimales.lastIndexOf(".");
                if (soloTieneUnDecimal(posicionDelSeparadorDecimal, tamanoConTodosLosDecimales)) {
                    tamanoConDosDecimales = tamanoConTodosLosDecimales;
                } else {
                    tamanoConDosDecimales = tamanoConTodosLosDecimales.substring(0, posicionDelSeparadorDecimal + 2);
                }
            } else {
                tamanoConDosDecimales = tamanoConTodosLosDecimales;
            }

            return tamanoConDosDecimales + " " + unidad;
        }


    }

    private boolean tieneDecimales(String tamanoConTodosLosDecimales) {
        return tamanoConTodosLosDecimales.contains(".");
    }

    private boolean soloTieneUnDecimal(int posicionDelSeparadorDecimal, String tamanoConTodosLosDecimales) {
        return posicionDelSeparadorDecimal + 1 == tamanoConTodosLosDecimales.length();
    }

    private String calculaElTamanoConTodosLosDecimalesDePrecision() {
        String[] unidades = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        int unidad = 0;

        double tamano = (double) peso;
        while (tamano >= 1024) {
            tamano = tamano / 1024;
            unidad++;
        }

        return String.valueOf(tamano);
    }

    private String calculaLaUnidad() {
        String[] unidades = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        int unidad = 0;

        double tamano = (double) peso;
        while (tamano >= 1024) {
            tamano = tamano / 1024;
            unidad++;
        }
        
        return unidades[unidad];
    }
}
