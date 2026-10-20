import java.util.ArrayList;
import java.util.List;

public class Invernadero {
    private String nombre;
    private List<Sensor> sensores = new ArrayList<>();
    private SistemaRiego riego;
    public Invernadero(String nombre){
        this.nombre = nombre;
        this.riego = new SistemaRiego();
    }
    public void agregarSensor(Sensor sensor){sensores.add(sensor);}
    public List<Sensor> getSensores(){return this.sensores;}
    public String lecturaGeneral(){
        StringBuilder texto = new StringBuilder("Invernadero: " + nombre + "\n");
        for (Sensor sensor : sensores) {
            texto.append(sensor.getId())
                 .append(" - ")
                 .append(sensor.getNombreVariable())
                 .append(": ")
                 .append(sensor.getUltimaLectura())
                 .append(" ")
                 .append(sensor.getUnidad())
                 .append(" (")
                 .append(sensor.interpretarLectura())
                 .append(") | ")
                 .append(sensor.isActivo() ? "activo" : "apagado")
                 .append("\n");
        }
        return texto.toString();
    }
    public void decidirRiego(){}
    public Boolean isRiegoActivo(){return this.riego.isActivo();}
    private SensorHumedadSuelo buscarSensorSuelo(){
        for (Sensor sensor : sensores) {
            if (sensor instanceof SensorHumedadSuelo) {
                return (SensorHumedadSuelo) sensor;
            }
        }
        return null;
    }
}
