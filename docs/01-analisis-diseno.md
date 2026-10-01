# Análisis y diseño

## 1. Descripción del problema

### ¿Qué sistema se representa?

El sistema que se va a representar es un invernadero inteligente, reducido a lo suficiente
para resolverlo con clases y objetos: un lugar de cultivo del que se obtienen tres mediciones
y sobre el que se puede ejecutar una única acción, el riego.

### ¿Qué información se maneja?

Valores medidos: Cada sensor guarda su última lectura. La temperatura se expresa en grados 
Celsius y las dos humedades en porcentajes de 0 a 100.

Datos del sensor: Un sensor también necesita saber quién es, dónde está instalado y si está
encendido o apagado. Un sensor apagado no debería estar entregando información.

Estado del riego: El sistema de riego necesita recordar si está regando o detenido, porque ese
estado es el resultado de una decisión tomada a partir de una medición.

### ¿Qué elementos intervienen?

Tres sensores, uno por cada variable: temperatura del aire, humedad ambiental y humedad del 
suelo. Los tres se parecen en mucho, pero se diferencian en lo importante: En qué unidad miden 
y cómo se interpreta su lectura. El mismo número significa cosas distintas según el sensor: 
32 °C es "temperatura alta", mientras que 32 % de humedad del suelo es un valor intermedio
considerado adecuado.

Un sistema de riego, que no mide nada. Solo tiene dos estados posibles y su
estado se decide a partir de la humedad del suelo.

### ¿Qué operaciones debe realizar?

- Registrar cada sensor con su identificación, ubicación y estado inicial. 
- Tomar una medición y guardarla como última lectura. 
- Interpretar la medición y devolver un texto con su significado. 
- Informar el estado actual y los datos de cada sensor. 
- Recorrer el conjunto de sensores aplicando la misma operación a todos. 
- Mostrar la interpretación de cada sensor en una lectura general del invernadero. 
- Activar, desactivar e informar el estado del sistema de riego. 
- Decidir el riego a partir de la humedad del suelo.

## 2. Identificación de objetos