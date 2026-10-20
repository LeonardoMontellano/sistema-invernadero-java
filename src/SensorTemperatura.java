public class SensorTemperatura extends Sensor{
    private Double UMBRAL_MIN = 18.0;
    private Double UMBRAL_MAX = 30.0;
    public SensorTemperatura(String id, String ubicacion) {
        super(id, ubicacion);
    }
    @Override
    public String getNombreVariable() {
        return "";
    }
    @Override
    public String getUnidad() {
        return "";
    }
    @Override
    public String interpretarLectura() {
        return "";
    }
}
