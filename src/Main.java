import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int pruebasOk = 0;
    private static int pruebasFallidas = 0;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE INVERNADERO: PRUEBAS DE INTEGRACION ===");

        probarCreacionYMetodosHeredados();
        probarInterpretaciones();
        probarPolimorfismoEInstanceof();
        probarRiego();

        System.out.printf("%nResultado final: %d pruebas OK, %d fallidas.%n",
                pruebasOk, pruebasFallidas);

        if (pruebasFallidas > 0) {
            System.exit(1);
        }
    }

    private static void probarCreacionYMetodosHeredados() {
        System.out.println("\n1. Creacion de objetos y metodos heredados");

        SensorTemperatura temperatura = new SensorTemperatura("ST-01", "Sector A");
        SensorHumedadAmbiental ambiental = new SensorHumedadAmbiental("SHA-01", "Sector A");
        SensorHumedadSuelo suelo = new SensorHumedadSuelo("SHS-01", "Sector B");

        verificar("ID de SensorTemperatura", "ST-01", temperatura.getId());
        verificar("Ubicacion de SensorTemperatura", "Sector A", temperatura.getUbicacion());
        verificar("Estado inicial activo", true, temperatura.isActivo());
        verificar("Nombre de SensorHumedadAmbiental", "Humedad ambiental",
                ambiental.getNombreVariable());
        verificar("Unidad de SensorHumedadAmbiental", "%", ambiental.getUnidad());
        verificar("Nombre de SensorHumedadSuelo", "Humedad del suelo",
                suelo.getNombreVariable());

        temperatura.registrarLectura(22.5);
        verificar("registrarLectura() heredado", 22.5, temperatura.getUltimaLectura());

        temperatura.desactivar();
        verificar("desactivar() heredado", false, temperatura.isActivo());

        temperatura.activar();
        verificar("activar() heredado", true, temperatura.isActivo());
    }

    private static void probarInterpretaciones() {
        System.out.println("\n2. Interpretacion de lecturas");

        SensorTemperatura temperatura = new SensorTemperatura("ST-02", "Sector A");
        temperatura.registrarLectura(10.0);
        verificar("Temperatura 10 C -> Baja", "Baja", temperatura.interpretarLectura());
        temperatura.registrarLectura(24.0);
        verificar("Temperatura 24 C -> Adecuada", "Adecuada", temperatura.interpretarLectura());
        temperatura.registrarLectura(35.0);
        verificar("Temperatura 35 C -> Alta", "Alta", temperatura.interpretarLectura());

        SensorHumedadAmbiental ambiental = new SensorHumedadAmbiental("SHA-02", "Sector A");
        ambiental.registrarLectura(20.0);
        verificar("Humedad ambiental 20 % -> Baja", "Baja", ambiental.interpretarLectura());
        ambiental.registrarLectura(55.0);
        verificar("Humedad ambiental 55 % -> Adecuada", "Adecuada", ambiental.interpretarLectura());
        ambiental.registrarLectura(80.0);
        verificar("Humedad ambiental 80 % -> Alta", "Alta", ambiental.interpretarLectura());

        SensorHumedadSuelo suelo = new SensorHumedadSuelo("SHS-02", "Sector B");
        suelo.registrarLectura(15.0);
        verificar("Humedad del suelo 15 % -> Baja", "Baja", suelo.interpretarLectura());
        suelo.registrarLectura(50.0);
        verificar("Humedad del suelo 50 % -> Adecuada", "Adecuada", suelo.interpretarLectura());
        suelo.registrarLectura(85.0);
        verificar("Humedad del suelo 85 % -> Alta", "Alta", suelo.interpretarLectura());
    }

    private static void probarPolimorfismoEInstanceof() {
        System.out.println("\n3. Polimorfismo, instanceof y casting");

        List<Sensor> sensores = new ArrayList<>();

        SensorTemperatura temperatura = new SensorTemperatura("ST-03", "Sector A");
        SensorHumedadAmbiental ambiental = new SensorHumedadAmbiental("SHA-03", "Sector A");
        SensorHumedadSuelo suelo = new SensorHumedadSuelo("SHS-03", "Sector B");

        temperatura.registrarLectura(10.0);
        ambiental.registrarLectura(55.0);
        suelo.registrarLectura(85.0);

        sensores.add(temperatura);
        sensores.add(ambiental);
        sensores.add(suelo);

        String[] esperados = {"Baja", "Adecuada", "Alta"};

        for (int i = 0; i < sensores.size(); i++) {
            Sensor sensor = sensores.get(i);
            System.out.printf("  %s (%s): %s %s -> %s%n",
                    sensor.getId(),
                    sensor.getNombreVariable(),
                    sensor.getUltimaLectura(),
                    sensor.getUnidad(),
                    sensor.interpretarLectura());
            verificar("Enlace dinamico en " + sensor.getId(),
                    esperados[i], sensor.interpretarLectura());
        }

        Sensor referencia = sensores.get(2);

        verificar("instanceof SensorHumedadSuelo", true,
                referencia instanceof SensorHumedadSuelo);
        verificar("instanceof SensorTemperatura (falso)", false,
                referencia instanceof SensorTemperatura);

        if (referencia instanceof SensorHumedadSuelo) {
            SensorHumedadSuelo sueloCasteado = (SensorHumedadSuelo) referencia;
            sueloCasteado.registrarLectura(20.0);
            verificar("Casting permite llamar a pideRiego()", true,
                    sueloCasteado.pideRiego());
        }
    }

    private static void probarRiego() {
        System.out.println("\n4. Sistema de riego y decision automatica");

        Invernadero invernadero = new Invernadero("Invernadero Central");
        SensorTemperatura temperatura = new SensorTemperatura("ST-04", "Sector A");
        SensorHumedadAmbiental ambiental = new SensorHumedadAmbiental("SHA-04", "Sector A");
        SensorHumedadSuelo suelo = new SensorHumedadSuelo("SHS-04", "Sector B");

        temperatura.registrarLectura(24.0);
        ambiental.registrarLectura(55.0);

        invernadero.agregarSensor(temperatura);
        invernadero.agregarSensor(ambiental);
        invernadero.agregarSensor(suelo);

        SistemaRiego riego = new SistemaRiego();
        verificar("SistemaRiego inicia apagado", false, riego.isActivo());

        riego.activar();
        verificar("SistemaRiego.activar()", true, riego.isActivo());

        riego.desactivar();
        verificar("SistemaRiego.desactivar()", false, riego.isActivo());

        suelo.registrarLectura(20.0);
        invernadero.decidirRiego();
        verificar("Suelo bajo activa el riego", true, invernadero.isRiegoActivo());

        suelo.registrarLectura(50.0);
        invernadero.decidirRiego();
        verificar("Suelo adecuado desactiva el riego", false, invernadero.isRiegoActivo());

        suelo.registrarLectura(85.0);
        invernadero.decidirRiego();
        verificar("Suelo alto mantiene el riego inactivo", false, invernadero.isRiegoActivo());

        System.out.println("\nLectura general del invernadero:");
        System.out.print(invernadero.lecturaGeneral());
    }

    private static void verificar(String caso, Object esperado, Object obtenido) {
        boolean correcto = esperado.equals(obtenido);
        System.out.printf("%s %-55s esperado: %-18s obtenido: %s%n",
                correcto ? "OK   " : "FALLO", caso, esperado, obtenido);

        if (correcto) {
            pruebasOk++;
        } else {
            pruebasFallidas++;
        }
    }
}
