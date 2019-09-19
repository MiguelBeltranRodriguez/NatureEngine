package controlador.Genoma;

import java.util.*;

abstract class CombinationHandler extends MutationsHandler {

    ///// En la Meiosis Uno, se mezclan ambas cadas de cada padre entre sí
    Map<String, List<String>> MeiosisUno(Map<String, List<String>> genoma) {
        for (Map.Entry<String, List<String>> entry : genoma.entrySet()) {
            String nombreAtributo = entry.getKey();
            List<String> locus = entry.getValue();
            locus = this.RecombinarLocus(locus);
            genoma.replace(nombreAtributo, locus);
        }
        return genoma;
    }

    ///// En la Meiosis Dos, se escoge uno solo de los dos cromosomas
    Map<String, String> MeiosisDos(Map<String, List<String>> genoma) {
        Map<String, String> halfgenoma = new HashMap<String, String>();
        int padreSeleccionado = this.SeleccionarPadreoMadre();
        for (Map.Entry<String, List<String>> entry : genoma.entrySet()) {
            String nombreAtributo = entry.getKey();
            List<String> locus = entry.getValue();
            String alele = locus.get(padreSeleccionado);
            halfgenoma.put(nombreAtributo, alele);
        }
        return halfgenoma;
    }

    ///// En la fecundación se juntan las dos mitades, de padre y madre, y se forma el nuevo cromosoma doble del hijo
    Map<String, List<String>> Fecundacion(Map<String, String> halfgenomaPadre, Map<String, String> halfgenomaMadre) {
        Map<String, List<String>> genomaHijo = new HashMap<String, List<String>>();
        for (Map.Entry<String, String> entry : halfgenomaPadre.entrySet()) {
            String nombreAtributo = entry.getKey();
            String aleloPadre = entry.getValue();
            String aleloMadre = halfgenomaMadre.get(nombreAtributo);
            List<String> locusHijo = new ArrayList<>();
            locusHijo.add(aleloPadre);
            locusHijo.add(aleloMadre);
            genomaHijo.put(nombreAtributo, locusHijo);
        }
        return genomaHijo;
    }

    private int SeleccionarPadreoMadre() {
        ///Padre = 0. Madre = 1.
        int valor = 0;

        return valor;
    }

    @SuppressWarnings("unused")
	private List<String> RecombinarLocus(List<String> locus) {
        //Se crea un molde de copia de cada ADN, en ARN y a partir de estos moldes se genera la mezcla
        List<String> moldeARNUno = this.RNAMeiosisCopy(locus.get(0));
        List<String> moldeARNDos = this.RNAMeiosisCopy(locus.get(1));
        List<String> mixedlocus = new ArrayList<>();
        //Aquí se mezclan las dos cadenas

        return mixedlocus;
    }

    private List<String> RNAMeiosisCopy(String locusSingleDNA) {
        // Se parte las cadenas por los puntos de corte
        String[] splitchains = locusSingleDNA.split(CloseChain());
        //Se copiarán todas las subcadenas, incluso las que no tienen una sección de inicio
        List<String> moldeARN = new ArrayList<>(Arrays.asList(splitchains));
        return moldeARN;
    }

}