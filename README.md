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
 
## Copyright

NatureEngine © LosAmigosDeMiguel, 2019.

## Agradecimientos

Enormes agradecimientos al profesor Enrique González y a su libro en francés

## Autores

* **Miguel Beltrán**
* **Marvin Cely**
* **Andrea Gutierrez**
* **Daniel Nieto**