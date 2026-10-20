public abstract class Sensor {
    private String id;
    private String ubicacion;
    private Boolean activo;
    private Double ultimaLectura;
    public Sensor(String id, String ubicacion){this.id = id; this.ubicacion = ubicacion; this.activo = true; this.ultimaLectura = 0.0;}
    public String getId() {return this.id;}
    public String getUbicacion() {return this.ubicacion;}
    public Boolean isActivo() {return this.activo;}
    public Double getUltimaLectura() {return this.ultimaLectura;}
    public void desactivar(){this.activo = false;}
    public void activar(){this.activo = true;}
    public void registrarLectura(Double valor){ this.ultimaLectura = valor;}
    public abstract String getNombreVariable();
    public abstract String getUnidad();
    public abstract String interpretarLectura();
}
