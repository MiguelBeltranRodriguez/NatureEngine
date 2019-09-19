package controlador.Genoma;

import java.util.*;

abstract class GenomaCreator{

    Map<String, List<String>> atributesAndKeys;
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    static String OpenChain() { return "aaa"; }
    static String CloseChain() {
        return "ZZZ";
    }

    List<String> getReleveantKeys(String key){
        List<String> Relevantkeys = atributesAndKeys.get(key);
        return Relevantkeys;
    }

    Map<String, List<String>> createNewGenoma() {
        Map<String, List<String>> genoma = new HashMap<String, List<String>>();
        for (Map.Entry<String, List<String>> entry : atributesAndKeys.entrySet()) {
            String nombreAtributo = entry.getKey();
            List<String> Relevantkeys = entry.getValue();
            List<String> locus = this.createNewLocus(Relevantkeys);
            genoma.put(nombreAtributo, locus);
        }
        return genoma;
    }

    private List<String> createNewLocus(List<String> relevantKeys) {
        String gen = OpenChain()+this.StringGenerator(60)+CloseChain();
        List<String> locus = new ArrayList<>();
        locus.add(gen);
        locus.add(gen);
        return locus;
    }

    private String StringGenerator(int length) {
        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER;
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

}