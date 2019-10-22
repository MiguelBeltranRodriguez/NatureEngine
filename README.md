# NatureEngine
Proyecto de simulación SMA
Versión ??

## Instalación

### Pre-requisitos
 1. Java JDK 1.8: [https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 2. Eclipse, para esta instalación se uso JEE  2019-06 (4.12.0)
 3. Maven: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi). Instalar el plugin de Maven en eclipse

### Pasos de instalación
 1. Clonar el repositorio
 2. Importar como proyecto maven todos los proyectos de la carpeta "PrimeraEntrega"
 3. Instalar el componente NatureEngineCommon (mvn install)
 4. Instalar el componente NatureEngineGenoma (mvn install)
 5. Instalar el componente NatureEngineGUI (mvn install)
 6. Instalar el componente NatureEngineAgente (mvn install)
 7. Instalar el componente NatureEngineAdministradorAgentes (mvn install)
 8. Instalar el componente NatureEngineController (mvn install)
 9. Ejecutar el proyecto NatureEngineController (Aplicación)
 10. Ejecutar el proyecto NatureEngineAdministradorAgentes con parámetro de entrada: El puerto al que se le va a asignar (Aplicación)

## Proyecto

A continuación se presentan los paquetes principales

### NatureEngine Core

### GenomeHandler - Simulador de reproducción y mutaciones

*El objetivo de este paquete es emular el proceso de transferencia genética de padres a hijos, desde la perspectiva de la evolución por lo que se tiene en cuenta la creación de mutaciones, y el fenotipo producido por un genoma al mayor detalle posible.
Se busco optimizar el componente de modo que se realizaran la menor cantidad de operaciones posibles y se consumiera la menor cantidad de memoria*
 
#### Funciones core del componente
**NuevaEspecie:** (Interfaz => CreadorDeEspecies)
 
* **Requiere:** Número de Individuos a crear **(Integer)**, Lista de valores de cada atributo de el individuo patrón para crear la especie **(HashMap<String,Object>)**
* **Entrega:** Lista de individuos. Cada elemento de la lista es un hashmap con el nombre de cada atributo y el objeto GenAtributo que contiene el fenotipo y genotipo para esa característica (List<HashMap<String, GenAtributo>>)
	
**Reproducirse:** (Interfaz => Reproducible)
* **Requiere:** Número de hijos a procrear **(Integer)**, y genotipo y fenotipo tanto de padre como de madre: Dos Hashmap, uno de padre y otro de madre **2x (HashMap<String, GenAtributo>)** con el string nombre de cada atributo y el objeto GenAtributo que contiene el fenotipo y genotipo para esa característica: 
* **Entrega:** Lista de hijos. Cada elemento de la lista es un hashmap con el string nombre de cada atributo y el objeto GenAtributo que contiene el fenotipo y genotipo para esa característica **(List<HashMap<String, GenAtributo>>)**

#### Simuladores (JUnit)
 
**Clase AtributosTest:** *Testea n-veces la creación de atributos desde cero. Es decir muestra todos los valores posibles que podrían llegar a tomar cada atributo a lo largo de la simulación*
* **Inputs modificables:** Número de sets de atributos creados a mostrar
	
**Clase SimuladorTest:** *Crea los atributos desde cero (Sin control del usuario), posteriormente crea una especie con n-individuos especificados por el usuario a partir de esos atributos creados y a continuación simula generación por generación la reproducción de los individuos en cada generación, la recombinación y mutación de sus genes y la creación de los hijos. No todos los individuos logran reproducirse y no todos los hijos sobreviven. Existe un número máximo de individuos por generación (Se puede exceder en una camada)*
* **Inputs modificables:** Número de generaciones a simular (Entero), Número de individuos iniciales en la especie (Entero), Número de individuos máximos por generación (Entero)
* **Inputs modifcables por personalizar (Ya están implemantadas, pero no bajo control del usuario):** Tasa de individuos que se reproducen por generación (Float), Tasa de sobrevivencia de los hijos (Float), Promedio de hijos por cada individuo (Float)

#### ¿Cómo incluirlo en otro componente?

**Funciones principales:**
 
```
import NatureEngine.NatureEngineGenoma.main.GenomaHandler.java
import NatureEngine.NatureEngineGenoma.main.CreadorDeEspecies.java //Interface
import Reproducible //Interface

GenomaHandler genomahandler = GenomaHandler.Singleton();
public List<HashMap<String, GenAtributo>> genomahandler.NuevaEspecie(Integer numeroIndividuos, HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie) throws Exception;
public List<HashMap<String, GenAtributo>> genomahandler.Reproducirse(Integer numerohijos, HashMap<String, GenAtributo> genomaMadre, HashMap<String, GenAtributo> genomaPadre);
```
	
**Tests:**
 
```
import NatureEngine.NatureEngineGenoma.Tests.AtributosTest.java
import NatureEngine.NatureEngineGenoma.Tests.SimuladorTest.java
 
AtributosTest atributostest = new AtributosTest();
SimuladorTest simuladortest = new SimuladorTest();
atributostest.TestDeAtributos();
simuladortest.Simuladordegeneraciones();
```

#### Glosario

* **Reproducción sexual:** Se heredan características del padre y la madre. Se producen células sexuales o gametos mediante la meiosis, durante la cual ocurre recombinación. En la fecundación se juntan los gametos y se produce el embrión 
* **Gametos:** Células sexuales que contienen la mitad del material genético. Los humanos tenemos 23 pares de cromosomas. Un gameto humano tiene 23 cromosomas, uno de cada par.
* **Par de cromosomas:** Cada par de cromosomas contiene dos versiones de la misma información (Excepto sexuales, X y Y).
* **Locus:** Un locus corresponde a la ubicación de una característica en un par de cromosomas. Hace referencia a ambos cromosomas. Una característica puede estar dada por varios genes, por lo que un locus es un concepto más amplio que un gen
* **Gen:** Es una sección de un locus (O uno completo), presente en ambos cromosomas del par de cromosomas. Está compuesto por 2 alelos, uno en cada cromosoma.
* **Alelo:** Es una variante de un gen. Un individuo solo puede tener dos alelos para un gen. Sin embargo pueden existir muchas variantes del mismo gen. Por ejemplo, de 15 variantes que se encuentren en la naturaleza, un individuo podría tener máximo 2. Está compuesto por código genético
* **Código genético:** Es una cadena de nucleotidos.
* **Recombinación:** Durante la meiosis, se mezclan los genes de ambos cromosomas de un padre, produciendo cromosomas hibridos. Podría decirse que, para un hijo, se mezclan los cromosomas de los gametos de sus abuelos.
* **Fecundación:** Se juntan los gametos con los cromosomas recombinados de los padres
* **Genotipo:** Incluye todos los genes y código genético de un individuo
* **Fenotipo:** Expresión del fenotipo, dada por el ambiente, la expresión genética y los genes. En esta simulación no tendremos en cuenta el ambiente

 
------
------

## Copyright

NatureEngine © LosAmigosDeMiguel, 2019.

## Autores

* **Miguel Beltrán**
* **Marvin Cely**
* **Andrea Gutierrez**
* **Daniel Nieto** CEO - [Digital MedTools](Http://digitalmedtools.blogspot.com). Consultor - Bireme | PAHO | WHO (2019)
