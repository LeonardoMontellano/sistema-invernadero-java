# Sistema de Invernadero Inteligente

Sistema desarrollado en Java que modela un invernadero con tres sensores y un sistema
de riego. Cada sensor registra una lectura y la interpreta según sus propios umbrales;
el invernadero recorre los sensores y decide si debe activar el riego a partir de la
humedad del suelo.

## Clases implementadas

- `Sensor`
- `SensorTemperatura`
- `SensorHumedadAmbiental`
- `SensorHumedadSuelo`
- `SistemaRiego`
- `Invernadero`
- `Main` (ejecuta las pruebas de integración)

## Responsabilidad de cada clase

| Clase | Responsabilidad |
|---|---|
| `Sensor` | Superclase abstracta. Guarda `id`, `ubicacion`, `activo` y `ultimaLectura`, y concentra los métodos comunes: activar, desactivar, registrar y consultar la lectura. Define el contrato de `getNombreVariable()`, `getUnidad()` e `interpretarLectura()`. |
| `SensorTemperatura` | Interpreta la temperatura en °C: menor a 18 es baja, entre 18 y 30 adecuada, y mayor a 30 alta. |
| `SensorHumedadAmbiental` | Interpreta la humedad del aire en %: menor a 40 es baja, entre 40 y 70 adecuada, y mayor a 70 alta. |
| `SensorHumedadSuelo` | Interpreta la humedad del suelo en % con umbrales de 30 y 70. Además, `pideRiego()` indica si la lectura está por debajo de 30. |
| `SistemaRiego` | Mantiene si el riego está activo o apagado y permite activarlo o desactivarlo. |
| `Invernadero` | Contiene una `List<Sensor>` y un `SistemaRiego`. Genera la lectura general y decide el riego según la humedad del suelo. |
| `Main` | Ejecuta escenarios de prueba y verifica los resultados esperados. |

## Instrucciones para ejecutar

Requiere JDK 17 o superior. Desde la raíz del proyecto:

```bash
javac -d out src/*.java
java -cp out Main
```

También se puede abrir el proyecto en IntelliJ IDEA y ejecutar la clase `Main`.

Resultado esperado:

```text
Resultado final: 30 pruebas OK, 0 fallidas.
```

## Conceptos de POO aplicados

| Concepto | Dónde se observa en el programa |
|---|---|
| **Encapsulación** | Los atributos de `Sensor` son `private` y se consultan mediante `getId()`, `getUbicacion()`, `isActivo()` y `getUltimaLectura()`. La lectura solo cambia con `registrarLectura()`. Lo mismo pasa con `activo` en `SistemaRiego` y con `sensores` en `Invernadero`. |
| **Herencia** | `SensorTemperatura`, `SensorHumedadAmbiental` y `SensorHumedadSuelo` usan `extends Sensor`. Heredan los atributos y métodos comunes y solo agregan lo propio. |
| **`super`** | Los constructores de las tres subclases llaman a `super(id, ubicacion)` para que `Sensor` inicialice los datos heredados. |
| **Sobrescritura** | Con `@Override`, las subclases implementan `getNombreVariable()`, `getUnidad()` e `interpretarLectura()`. Cada una devuelve su unidad y aplica sus propios umbrales. |
| **Polimorfismo** | `Invernadero.lecturaGeneral()` recorre una `List<Sensor>` y llama a los mismos métodos para todos. En `Main` también se guardan los tres tipos de sensor en una lista de `Sensor`. |
| **Enlace dinámico** | En el recorrido de `lecturaGeneral()`, la llamada `sensor.interpretarLectura()` ejecuta la versión de la clase real del objeto, no la de la variable de referencia. |
| **`instanceof`** | `Invernadero.buscarSensorSuelo()` comprueba `sensor instanceof SensorHumedadSuelo` antes de usar el sensor específico. `Main` también lo usa para verificar el tipo. |
| **Casting** | Después de comprobar el tipo se hace `(SensorHumedadSuelo) sensor`. Así se puede llamar a `pideRiego()`, que no existe en la superclase. |

## Pruebas realizadas

Las pruebas se ejecutan desde `Main` y comparan el resultado esperado con el obtenido:

| Caso | Resultado |
|---|---|
| Crear los sensores y consultar sus datos | OK |
| Temperatura baja, adecuada y alta | OK |
| Humedad ambiental baja, adecuada y alta | OK |
| Humedad del suelo baja, adecuada y alta | OK |
| Procesar los sensores mediante referencias `Sensor` | OK |
| Recorrer la colección de sensores | OK |
| Identificar el tipo con `instanceof` | OK |
| Hacer casting y llamar a `pideRiego()` | OK |
| Humedad del suelo baja: activar riego | OK |
| Humedad del suelo adecuada o alta: riego inactivo | OK |

Resultado final: **30 pruebas OK, 0 fallidas**.

## Análisis y diseño

El análisis, las decisiones de diseño y los diagramas UML están en
[`docs/01-analisis-diseno.md`](docs/01-analisis-diseno.md).

## Conclusión

El proyecto aplica herencia y polimorfismo para tratar distintos sensores mediante un
tipo común, sin que `Invernadero` tenga que conocer cada subclase. La encapsulación
mantiene el estado bajo control y el enlace dinámico permite que cada sensor responda
con su propia interpretación. Las pruebas confirman el funcionamiento conjunto de los
sensores, el invernadero y el sistema de riego.
