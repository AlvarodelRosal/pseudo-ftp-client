package alvarodelrosal.ftp.modelo;

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

    public void ultimaModificacion(String ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }
    
    public String verTipo() {
        if(esUnaCarpeta) {
            return "Carpeta";
        } else {
            return "Archivo";
        }
    }
    
    public String verPath() {
        return path;
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
            String[] unidades = {"B","KB","MB","GB","TB","PB","HB"};
            int unidad = 0;
            
            double tamano = (double) peso;
            while (tamano >= 1024) {
                tamano = tamano/1024;
                unidad++;
            }
            return String.valueOf(tamano) + " " + unidades[unidad];
        }
    }
    
}
