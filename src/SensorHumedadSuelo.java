public class SensorHumedadSuelo extends Sensor{
    private Double UMBRAL_MIN = 30.0;
    private Double UMBRAL_MAX = 70.0;
    public SensorHumedadSuelo(String id, String ubicacion) {
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
