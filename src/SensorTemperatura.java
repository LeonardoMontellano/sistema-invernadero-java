public class SensorTemperatura extends Sensor{
    private Double UMBRAL_MIN = 18.0;
    private Double UMBRAL_MAX = 30.0;
    public SensorTemperatura(String id, String ubicacion) {
        super(id, ubicacion);
    }
    @Override
    public String getNombreVariable() {
        return "Temperatura";
    }
    @Override
    public String getUnidad() {
        return "°C";
    }
    @Override
    public String interpretarLectura() {
        if (!isActivo()) return "Sin datos (sensor apagado)";
        if (getUltimaLectura() < UMBRAL_MIN) return "Baja";
        if (getUltimaLectura() <= UMBRAL_MAX) return "Adecuada";
        return "Alta";
    }
}
