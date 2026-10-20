public class SensorHumedadAmbiental extends Sensor {
    private Double UMBRAL_MIN = 40.0;
    private Double UMBRAL_MAX = 70.0;
    public SensorHumedadAmbiental(String id, String ubicacion) {
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