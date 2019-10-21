package NatureEngine.NatureEngineGenoma.Simulator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;

public class SimuladorLogger {

	public static void ReproductionResultsLogTest(Integer generacion,
			List<HashMap<String, GenAtributo>> listaDeIndividuosConAtributos) {
		System.out.println("************************");
		System.out.println("************************");
		System.out.println("************************");
		System.out.println("Generacion " + generacion);
		for (int i = 0; i < listaDeIndividuosConAtributos.size(); i++) {
			System.out.println("------------------------");
			System.out.println("Individuo #" + (i + 1));
			HashMap<String, GenAtributo> individuo = listaDeIndividuosConAtributos.get(i);
			individuo.entrySet().forEach(entry -> {
				String nombreAtributo = entry.getKey();
				GenAtributo genatributo = entry.getValue();
				Object fenotipo = genatributo.getFenotipo();
				String tipo = genatributo.getTipoCaracteristica();
				String aleloUnoTxt = AleloTxt(tipo, genatributo.getaleloUno());
				String aleloDosTxt = AleloTxt(tipo, genatributo.getaleloDos());
				System.out.println(nombreAtributo + "=" + fenotipo + "[" + aleloUnoTxt + " + " + aleloDosTxt + "]");
			});
		}
	}

	private static String AleloTxt(String tipo, Alelo alelo) {
		String txt = "=>D" + alelo.getDominancia();
		Object valor = alelo.getValor();
		if (tipo.equals("java.lang.Float")) {
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			return numberFormat.format((float) valor) + txt;
		}
		if (tipo.equals("java.lang.Integer")) {
			return valor.toString();
		}
		if (tipo.equals("java.lang.Boolean")) {
		 return ((Boolean)valor).toString()+ txt;
		}
		return "TIPODESCONOCIDO.."+tipo;
	}

}
