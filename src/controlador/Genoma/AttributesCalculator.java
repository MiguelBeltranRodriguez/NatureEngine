package controlador.Genoma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AttributesCalculator extends GenomaCreator {

    Map<String, Integer> CalcularAtributos(Map<String, List<String>> genomaHijo) {
        Map<String, Integer> attributeValues = new HashMap<String, Integer>();
        for (Map.Entry<String, List<String>> entry : genomaHijo.entrySet()) {
            String nombreAtributo = entry.getKey();
            List<String> locus = entry.getValue();
            int value = ValorAtributo(locus, nombreAtributo);
            attributeValues.put(nombreAtributo, value);
        }
        return attributeValues;
    }

    private int ValorAtributo(List<String> locus, String nombreAtributo) {
        //Para cadena de ADN de cada locus el RNA Mensajero
        //rompe en los sitios de inicio y fin, generando multiples
        //subcadenas, que pueden ser proteinas distinas, o subcomponentes de una
        //macroproteina
        List<String> rnaMensajerosUno = this.RNATrascription(locus.get(0));
        List<String> rnaMensajerosDos = this.RNATrascription(locus.get(1));
        List<String> rnaMensajeros = new ArrayList<String>();
        rnaMensajeros.addAll(rnaMensajerosUno);
        rnaMensajeros.addAll(rnaMensajerosDos);
        // Las cadenas de RNA mensajero se tranforma en proteinas, de modo que distintos códigos
        // Pueden traducir a producir la misma proteína
        // Transforma String en letras [a-z][A-Z] a números [0-9]
        List<String> proteins = this.ProteinTranslate(rnaMensajeros);
        // Posteriormente se estima mediante un algoritmo el valor de fitness
        // Teniendo en cuenta todas las cadenas
        return this.ProteinValues(proteins);
    }

    private List<String> RNATrascription(String locusSingleDNA) {
        List<String> rnaMensajeros = new ArrayList<>();
        // En esta sección la cadena se parte por los puntos de corte de inicio y de finalización
        //Si entre dos puntos de inicio no existe un punto de corte, no se
        //Si hay dos puntos de finalización seguidos, se cortan ambos
        String[] splitchains = locusSingleDNA.split(CloseChain());
        //Solo se leerán las cadenas que tengan un codigo de inicio, las demás se descartan
        for (int i = 0; i < splitchains.length; i++) {
            int position = splitchains[i].indexOf(OpenChain());
            if(position>-1){
                String expressed = splitchains[i].substring(position);
                rnaMensajeros.add(expressed);
            }
        }
        return rnaMensajeros;
    }

    private List<String> ProteinTranslate(List<String> rnaMensajeros) {
        List<String> proteins = new ArrayList<>();
        proteins = rnaMensajeros;
        return proteins;
    }

    private int ProteinValues(List<String> proteins) {
        int valor = 0;
        return valor;
    }

}