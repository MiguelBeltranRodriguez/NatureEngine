package NatureEngine.Modelo;

import java.util.*;

public class BaseAttributes { //Unir mediante singleton

    private Map<String, List<String>> attributes = new HashMap<String, List<String>>();

    private static BaseAttributes baseAttributes = null;

    public static BaseAttributes getBaseAttributes() {
        if(baseAttributes == null) {
            baseAttributes = new BaseAttributes();
            return baseAttributes;
        }else {
            return baseAttributes;
        }
    }

    private BaseAttributes() {
        String[] attrs = {
                "velocidadMaxima",
                "tamanoAdulto",
                "percepcion",
                "ansiedad",
                "humedadIdeal",
                "humedadTol",
                "tempIdeal",
                "tempTol",
                "dieta",
                "agresividad",
                "longevidad",
                "interesExploratorio",
                "contadorCambios",
                "velocidadMaxima",
                "tamanoAdulto",
                "percepcion",
                "ansiedad",
                "humedadIdeal",
                "humedadTol",
                "tempIdeal",
                "tempTol",
                "dieta",
                "agresividad",
                "longevidad",
                "interesExploratorio",
                "contadorCambios"
        };
        for (String attr : attrs) {
            this.generateAttribute(attr);
        }
    }

    private void generateAttribute(String attrName) {
        attributes.put(attrName, this.buildGeneticKeys(attrName));
    }

    private List<String> buildGeneticKeys(String attrName) {
        List<String> geneticKeys = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            geneticKeys.add(this.generateKey());
        }
        return geneticKeys;
    }


    /////Esta sección genera a partir de dos strings un string de 3 letras que va a ser de relevancia
    //// En la mejora del codigo genetico

    private String generateKey() {
        Random aleatorio = new Random(System.currentTimeMillis());
        int key = aleatorio.nextInt(999);
        String res = String.valueOf(key);
        int alpha = res.length();
        for (int i = alpha; i < 4; i++) {
            res = '0'+res;
        }
        return res;
    }

    public Map<String, List<String>> getAttributesAndKeys(){
        return attributes;
    }

}
