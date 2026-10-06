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

### Sensor

Representa a cualquier sensor del invernadero, sin importar qué magnitud mide.

Debería existir como objeto porque los tres sensores comparten casi todo: se
identifican, tienen ubicación, se encienden y se apagan, guardan una última lectura y
saben explicar qué significa esa lectura. Como eso es igual para los tres, no tiene
sentido escribirlo tres veces. Lo que cambia es la unidad y la forma de interpretar el
número, y eso puede quedar resuelto más adelante, con las clases hijas.

De esto se encargaría: guardar identificación, ubicación, estado y última lectura, y
exponer la interpretación de esa lectura.

### Sensor de temperatura

Representa al sensor que mide la temperatura del aire. Hereda de Sensor.

Lo que tiene propio es la unidad, que es el grado Celsius, y sus umbrales.
También conviene que ofrezca un dato extra que los otros no tienen, por ejemplo el
nombre de la variable medida, para poder mostrarlo junto al resto.

De esto se encargaría: registrar su lectura en grados e interpretarla con sus
umbrales, además de mostrar sus detalles propios.

### Sensor de humedad ambiental

Representa al sensor que mide la humedad del aire. También hereda de Sensor.

Lo propio es que su unidad es el porcentaje. Nótese que un
32 % en este sensor significa humedad baja, mientras que en el sensor de suelo esa
misma cifra significa algo distinto. Justamente por eso cada sensor interpreta su
propia lectura.

De esto se encargaría: registrar su lectura como porcentaje e interpretarla con sus
umbrales.

### Sensor de humedad del suelo

Representa al sensor que mide la humedad del suelo donde están las plantas. Hereda de
Sensor igual que los otros dos.

Lo propio es que su lectura tiene un significado especial: es la que decide si se
riega. Además, como es el sensor del que depende una acción, conviene que
diga de forma explícita si su lectura pide riego o no, en lugar de dejar que ese
razonamiento se haga afuera.

De esto se encargaría: registrar su lectura como porcentaje, interpretarla con sus
umbrales e indicar si esa lectura requiere regar.

### Invernadero

Representa el espacio completo: los sensores instalados y el estado del riego.

Debería existir como objeto porque es el lugar donde conviven los demás objetos. Es
quien los tiene guardados y quien puede dar una lectura general del cultivo. 
Como la decisión del riego depende de la humedad del suelo, y esa lectura está dentro del 
invernadero, tiene sentido que sea este objeto el que lea esa medición y cambie su estado de 
riego.

De esto se encargaría: guardar el conjunto de sensores, recorrerlos todos para mostrar
su estado en una sola lectura, y activar o desactivar el riego según lo que indique la
humedad del suelo.

## 3. Estado y comportamiento

| Objeto propuesto | Responsabilidad | Información que debe conservar | Comportamientos que debe realizar |
|---|---|---|---|
| Sensor | Representar un sensor del invernadero, con lo que todos los sensores tienen en común | Identificación, ubicación, si está encendido o apagado y la última lectura que tomó | Permitir conocer su identificación, su ubicación y si está activo; registrar una lectura nueva; permitir conocer su última lectura; interpretar esa lectura diciendo si es baja, adecuada o alta; informar su unidad de medida |
| Sensor de temperatura| Interpretar la lectura en grados Celsius con los umbrales de la temperatura | Nada adicional: la unidad y los umbrales le son propios, pero se deducen del tipo | Interpretar su lectura: menor a 18 °C es baja, entre 18 y 30 °C adecuada, mayor a 30 °C alta; informar el nombre de la variable medida |
| Sensor de humedad ambiental | Interpretar la lectura como porcentaje de humedad del aire | Nada adicional: la unidad y los umbrales le son propios | Interpretar su lectura: menor a 40 % es baja, entre 40 y 70 % adecuada, mayor a 70 % alta |
| Sensor de humedad del suelo | Interpretar la lectura como porcentaje de humedad del suelo | Nada adicional: la unidad y los umbrales le son propios | Interpretar su lectura: menor a 30 % es baja, entre 30 y 70 % adecuada, mayor a 70 % alta; decir de forma explícita si esa lectura pide regar o no |
| Invernadero | Contener los sensores instalados y decidir cuándo se riega | El conjunto de sensores y si el riego está activo o inactivo | Guardar un sensor nuevo; permitir consultar todos los sensores juntos; recorrerlos y mostrar la interpretación de cada lectura; activar y desactivar el riego; preguntar si el riego está activo; decidir el riego a partir de la lectura del sensor de humedad del suelo |
