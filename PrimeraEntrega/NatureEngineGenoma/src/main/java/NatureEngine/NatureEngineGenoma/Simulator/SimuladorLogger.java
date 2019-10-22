package NatureEngine.NatureEngineGenoma.Simulator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.Utils.RandomExtendido;

public class SimuladorLogger {

	public static void ReproductionResultsLogTest(Integer generacion,
			List<HashMap<String, GenAtributo>> listaDeIndividuosConAtributos) {
		if(generacion!=null) {
			System.out.println("************************");
			System.out.println("************************");
			System.out.println("************************");
			System.out.println("Generacion " + generacion);	
		}
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
		if (tipo.equals("java.lang.Boolean")) {
			return ((Boolean) valor).toString() + txt;
		} else {
			if (tipo.equals("java.lang.Float") || tipo.equals("java.lang.Integer")) {
				DecimalFormat numberFormat = new DecimalFormat("#.00");
				if (valor instanceof Integer) {
					return ((Integer) valor).toString() + txt;
				} else {
					return ((Float) valor).toString() + txt;
				}
			}
		}
		return "TIPODESCONOCIDO.." + tipo;
	}

	public static HashMap<String, List<Float>> AddToListaFinal(HashMap<String, List<Float>> listafinal,
			HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie) throws Exception {
		for (HashMap.Entry<String, Object> entry : listaDeValoresDeAtributosDeLaEspecie.entrySet()) {
			String key = entry.getKey();
			Object preval = entry.getValue();
			if (preval == null) {
				throw new Exception("Valor de " + key + " no puede ser null");
			}
			
			List<Float> list = listafinal.get(entry.getKey());	
			if (list==null || list.size()==0) {
				list = new ArrayList<Float>();
			}
			
			Float val = null;
			if (preval instanceof Boolean) {
				Boolean tmp = (Boolean) preval;
				val = (float) (tmp ? 1 : 0);
			} else {
				val = RandomExtendido.ObjectAFloat(preval);
			}
			if (val == null) {
				throw new Exception("Error en " + key + " esto no debería salir");
			}
			list.add(val);
			listafinal.put(key, list);
		}
		return listafinal;
	}

	public static void ReportGeneration(List<HashMap<String, GenAtributo>> generacionactual) throws Exception {
		HashMap<String, List<Float>> listafinal = new HashMap<String, List<Float>>();
				
		
		for (int index = 0; index < generacionactual.size(); index++) {
			HashMap<String, GenAtributo> tmp = generacionactual.get(index);
			HashMap<String, Object> listaatributos = new HashMap<String, Object>();		
			for (HashMap.Entry<String, GenAtributo> entry : tmp.entrySet()) {
				GenAtributo gen = entry.getValue();
				String nombreatributo = entry.getKey();
				if(gen==null) {
					throw new Exception(nombreatributo+": null en reportgeneration");
				}
				Object valoratributo = gen.getFenotipo();
				if(valoratributo==null) {
					throw new Exception(nombreatributo+": null en reportgeneration");
				}
				listaatributos.put(nombreatributo,valoratributo );
			}
			listafinal = SimuladorLogger.AddToListaFinal(listafinal, listaatributos);
		}
		ReportFinalData(listafinal);
	}

	public static void ReportFinalData(HashMap<String, List<Float>> listafinal) throws Exception {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		for (HashMap.Entry<String, List<Float>> entry : listafinal.entrySet()) {
			String key = entry.getKey();
			List<Float> list = entry.getValue();

			Float mean = (float) Mean(list);
			Float median = (float) Median(list);
			Float sd = (float) calculateStandardDeviation(list, mean);

			System.out.println(key + " = " + df.format(mean) + " ± " + df.format(sd));
		}
	}

	public static double Mean(List<Float> m) {
		double sum = 0;
		for (int i = 0; i < m.size(); i++) {
			sum += m.get(i);
		}
		return sum / m.size();
	}

	// the array double[] m MUST BE SORTED
	public static double Median(List<Float> m) {
		int middle = m.size() / 2;
		if (m.size() % 2 == 1) {
			return m.get(middle);
		} else {
			return (m.get(middle - 1) + m.get(middle)) / 2.0;
		}
	}

	public static double calculateStandardDeviation(List<Float> sd, Float mean) {

		double newSum = 0;

		for (int j = 0; j < sd.size(); j++) {
			// put the calculation right in there
			newSum = newSum + ((sd.get(j) - mean) * (sd.get(j) - mean));
		}
		double squaredDiffMean = (newSum) / (sd.size());
		double standardDev = (Math.sqrt(squaredDiffMean));

		return standardDev;
	}

}
