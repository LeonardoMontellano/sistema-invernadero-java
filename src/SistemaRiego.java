public class SistemaRiego {
    private Boolean activo;
    public SistemaRiego(){this.activo=false;}
    public void activar(){this.activo=true;}
    public void desactivar(){this.activo=false;}
    public Boolean isActivo(){return this.activo;}
}
