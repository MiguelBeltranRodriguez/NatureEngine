# NatureEngine
Proyecto de simulación SMA
Versión ??

## Instalación

### Pre-requisitos

```

```

### Pasos de instalación

## Proyecto

A continuación se presentan los paquetes principales

### NatureEngine Core

### GenomeHandler

El objetivo de este paquete es emular el proceso de transferencia genética de padres a hijos, desde la perspectiva de la evolución por lo que se tiene en cuenta la creación de mutaciones, y el fenotipo producido por un genoma

Se busco optimizar el componente de modo que se realizaran la menor cantidad de operaciones posibles y se consumiera la menor cantidad de memoria

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
* **Expresión Genética:** El código genético no se expresa tal como está escrito. Para expresarse es necesario transcribirlo a proteínas a través de ARN Mensajeros.
* **ARN Mensajeros:**  Transportan pequeñas copias del ADN en formato ARN hasta los sitios de elaboración de proteínas para que sean transcritas. Los ARN mensajeros solo transportan los exones e ignoran los intrones
* **Exones:** Partes del código genético que están en las regiones codificantes, es decir, es la parte visible, que se utiliza para construir proteínas
* **Intrones:** Partes del código genético que no codifican. No son inutiles, ya que protegen los extremos de los genes y cromosomas, evitando que se eliminen las cadenas de inicio y fin, y ocurran mutaciones graves. Son como guardachoques de los genes y cromosomas
* **Cadenas de cierre:** Son pequeños fragmentos de códigos (Como 3 bases) que, luego de que se ha hecho una copia del ADN en formato ARN (ARN Mensajero), indican en qué partes se debe cortar este para luego producir las proteínas
* **Cadenas de inicio:** Son pequeños fragmentos de código (Como 3 baases) que indican al ARN transcriptor desde donde debe comenzar a leer una de las cadenas previamente cortadas. El código que esté antes de la cadena de inicio, podría considerarse un intrón porque no se lee, mientras que el código después de la cadena de inicio se puede considerar un exón
* **Transcripción de proteínas:** Los exones en ARN de transcripción, son transcritos a aminoacidos (en grupos de a 3 bases nucleotidas en la vida real, para 64 combinaciones posibles ), de modo que de 64 combinaciones posibles, se producen solo 20 tipos de aminoacidos. Varias combinaciones traducen para el mismo aminoamiacido. Es por esto, que muchas mutaciones no generan un cambio en la proteína producida
* **Resultado en fitness y atributos:** Las proteínas producidas son las que construyen al organismo, de modo que son sus proteínas las que le generan una ventaja o desventaja ante el ambiente
 
#### Componentes

**GenomeHandler** *extends* **CombinationHandler** *extends* **MutationsHandler** *extends* **AttributesCalculator** *extends* **GenomaCreator**

------
------
 **1. GenomeHandler.java:** *Entrada y Salida del componente. Tiene dos funciones*


   - **1.1 GenerarAtributosHijo:** *A partir de la génetica de dos padres, se genera la genética y atributos de un hijo* 
```
Requiere AttrPadre (AtributosAgente) y AttrMadre (AtributosAgente). Entrega AttrHijo (AtributosAgente)
```

   - **1.2 GenerarAtributosAgenteSinPadres:** *Genera una génetica y atributos para un nuevo agente, desde cero, sin padres*
```
Entrega AttrNuevoAgente (AtributosAgente)
```

   - **1.3 GenerarAtributos *(Privada)*:** *Función intermedia. Genera atributos a partir de la genética y los entrega a las dos funciones previas*
```
Requiere genoma (Map<String, List<String>>). Entrega atributosagente (AtributosAgente)
```
------
------

 **2. CombinationsHandler.java:** *Componente donde se realizan las principales operaciones secundarias al componente anterior*
   - **2.1 MeiosisUno:** *En este componente se recombinan los cromosomas de cada padre (Entre sí mismos)*
```
Requiere genoma (Map<String, List<String>>). Entrega genoma Map<String, List<String>>.
```

   - **2.2 MeiosisDos:** *Se selecciona un solo cromosoma a heredar*
```
Requiere genoma (Map<String, List<String>>). Entrega halfgenoma Map<String, String>>.
```

   - **2.3 Fecundacion:** *Se juntan los gametos de ambos padres. Es decir se crea la genética del hijo a partir del cromosoma heredado de cada padre*
```
Requiere halfgenomaPadre (Map<String, String>) y halfgenomaMadre  (Map<String, String>). Entrega genoma (Map<String, List<String>>).
```

   - **2.4 SeleccionarPadreoMadre *(Privada)*:** *Tiene una función random que permite elegir el cromosoma*
```
Entrega valor (int)
```

   - **2.5 RecombinarLocus *(Privada)*:** *Requiere los locus de ambos cromosomas. Cada locus tiene varios genes. Luego procede a mezclar los locus. Es decir recombina los cromosomas. Finalmente devuelve ambos locus, cada uno con características mezcladas del otro*
```
Requiere locus (List<String>). Entrega mixedlocus (List<String>).
```

   - **2.6 RNAMeiosisCopy *(Privada)*:** *Simula al proceso en el que el ARN mensajero parte el código genético de cada locus según los puntos de corte o cierre, para generar tanto intrones como exones. En la recombinación tanto intrones como exones se mezclan*
```
Requiere locusSingleDNA (String). Entrega moldeARN (List<String>). 
```
------
------

 **3. AttributesCalculator.java:** *Componente donde se realizan las operaciones de calcular los atributos según la genética. De genotipo a fenotipo*
   - **3.1 CalcularAtributos:** *Método principal. A partir de un genoma genera un fenotipo de valores de atributos. Realiza una iteración dentro de cada locus, para generar los valores de atributos para cada locus. Un locus es equivalente a un atributo*
```
Requiere genoma (Map<String, List<String>>). Entrega AttributeValues (Map<String, Integer>).
```

   - **3.2 ValorAtributo *(Privada)*:** *Requiere un locus, a partir del cual realiza todo el proceso para generar el valor del atributo*
```
Requiere locus (List<String>) y nombreAtributo(String). Entrega valor (int)).
```

   - **3.3 RNATrascription *(Privada)*:** *Corta cada cadena de código genético en cada locus en piezas dadas por los códigos de cierre o terminación. Además no tiene en cuenta las cadenas que no tienen código de inicio, ni el código genético previo al código de inicio. Es decir solo tiene en cuenta los exones*
```
Requiere locusSingleDNA (String). Entrega rnaMensajeros (List<String>).
```

   - **3.4 ProteinTranslate *(Privada)*:** *Transforma los rnaMensajeros, que son cadenas de código genético en proteínas, que son cadenas de aminoácidos. En la vida real los rnaMensajeros se agrupan en codones, que son grupos de a 3 nucleotidos del código genético. Generando 64 combinaciones posibles. Sin embargo solo existen 20 aminoácidos, por lo que varias combinaciones generan el mismo aminoácido. En nuestro simulador. El código genético se representa mediante letras en mayúsculas y minúsculas, generando 61 posibilidades distinas, mientras que los aminoacidos se representan mediante números, generando 10 posibilidades distinas. De una forma análoga, este método convierte cada letra (61 posibilidades) en un número (10 posibilidades)*
```
Requiere rnaMensajeros (List<String>). Entrega proteins (List<String>)
```

   - **3.5 ProteinValues *(Privada)*:** *Este método es el encargado de convertir los códigos generados de cada proteína en valores de atributos. Para esto, para cada atributo/locus se establecieron 5 patrones de 3 números almacenados como texto. (Ej. "125", "245", "657", "322", "005") a partir de los cuales se realizarán los calculos. Cada cálculo afectará dos variables, el atributo/locus en sí mismo, y la longevidad. Es muy importante tener en cuenta que cambios en las proteínas por lo general tienden a representar una desventaja e incluso ser mortales. Solo un pequeño porcentaje es beneficioso*
```
Requiere proteins (List<String>). Entrega valor (int).
```
------
------

 **4. MutationsHandler.java:** *Componente donde se calculan las probabiliades de que ocurran las mutaciones y donde se implementan las mismas. NO ESTA DISEÑADO TODAVIA*
 
------
------

 **5. GenomaCreator.java:** *Componente donde se realizan las operaciones de calcular los atributos según la genética. De genotipo a fenotipo*
   - **5.1 OpenChain *(static)*:** *Entrega cadena genética que representa el punto de aperta o inicio de codificación para el ARN mensajero. El inicio de un exón*
```
Entrega cadena (String)
```

   - **5.2 CloseChain *(static)*:** *Entrega cadena genética que representa el punto de cierre, terminación o corte de las subcadenas en el ARN mensajero*
```
Entrega cadena (String)
```

   - **5.3 getReleveantKeys :** *Obtiene las cadenas númericas con las que se establece el valor de los atributos, para el atributo solicitado. Estas se cargan previamente a la clase con el patrón singleton, desde el modelo baseAttributes*
```
Requiere key (String). Entrega Relevantkeys (List<String>).
```

   - **5.4 createNewGenoma :** *Genera un nuevo genoma en ambos cromosomas. Dejando el mismo código en ambos*
```
Entrega genoma (Map<String, List<String>>)
```

   - **5.5 createNewLocus *(Privada)*:** *Genera un nuevo genoma para cada locus. Genera 10 letras de exón, seguidas por un código de apertura, seguidas por 60 letras de exón, seguidas por un codigo de cierre, seguidas por 10 letras de exón.
```
Requiere relevantKeys (List<String>). Entrega locus (List<String>).
```

   - **5.6 StringGenerator *(Privada)*:** *Este método genera un String del largo solicitado*
```
Requiere length (int). Entrega cadena (String).
```
 
------
------

## Copyright

NatureEngine © LosAmigosDeMiguel, 2019.

## Autores

* **Miguel Beltrán**
* **Marvin Cely**
* **Andrea Gutierrez**
* **Daniel Nieto**