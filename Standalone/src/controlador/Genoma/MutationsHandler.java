package controlador.Genoma;



abstract class MutationsHandler extends AttributesCalculator {

    //TODAVIA FALTA ESTA PARTE

}

//    String MutacionesRecombinacion(String gen1, String gen2) {
//
//       return gen1;
//    }

//    Map<String, List<String>> PortalMutaciones (Map<String, List<String>> genoma) {
//        for (Map.Entry<String, List<String>> entry : genoma.entrySet()) {
//            String locusname = entry.getKey();
//        }
//        return genoma;
//    }
//
//
//    private String MutacionAccion() {
//        Map<Integer, String> Accion = new HashMap<Integer, String>();
//        Accion.put(5,"Salta");
//        Accion.put(10,"CopiaAlLado");
//        Accion.put(15,"CopiaMismoGen");
//        Accion.put(20,"CopiaMismoLocus");
//        Accion.put(25,"CopiaOtroLocus");
//        return "notready";
//    }
//
//
//
//    private String SelectAction(Map<Integer, String> probArray) {
//        int value = this.N_In_MillionProb();
//        for (Map.Entry<Integer, String> entry : probArray.entrySet()) {
//            int Prob = entry.getKey();
//            if (Prob < 1 || Prob > 1000000) {
//                //throw
//            }
//            String action = entry.getValue();
//            if (value < Prob) {
//                return action;
//            }
//        }
//        return "";
//    }
//
//    private int MutationMagnitude(String GenCode) {
//        Random aleatorio = new Random(System.currentTimeMillis());
//        double val = Math.sqrt(aleatorio.nextInt(10000));
//        return (int)(GenCode.length()*val);
//    }
//
//    private int N_In_MillionProb() {
//        Random aleatorio = new Random(System.currentTimeMillis());
//        return aleatorio.nextInt(10000);
//    }
//
//}