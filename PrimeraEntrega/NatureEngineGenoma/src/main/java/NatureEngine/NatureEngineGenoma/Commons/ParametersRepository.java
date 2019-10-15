package NatureEngine.NatureEngineGenoma.Commons;

import java.util.HashMap;

import NatureEngine.NatureEngineGenoma.Integration.IntegrationHandler;

public abstract class ParametersRepository {

	private static HashMap<String, Object> parameters;
	static {
		HashMap<String, Object> parametersSet = new HashMap<String, Object>();
		parametersSet.put("TasaDeMutacion", 0.05f);
		parametersSet.put("GradosDeDominancia", 4);
		parametersSet.put("ProbabilidadMutarDominancia", 0.2f);
		parametersSet.put("MultiplicadorDeVariacionPorMutacion", 1f);
		parametersSet.put("MinimoAlelosPorAtributo", 1);
		parametersSet.put("MaximoAlelosPorAtributo", 3);
		 HashMap<String,HashMap<String,Object>> listaatributos = ListaDeAtributos();
		for (HashMap.Entry<String,HashMap<String,Object>> entry : listaatributos.entrySet()) {
			String attibuteName = entry.getKey();
			HashMap<String,Object> attributeData = entry.getValue();
			parametersSet.put(attibuteName,attributeData);
		}

	}

	final protected static Object getParameter(String key) {
		if (existsParameter(key)) {
			return parameters.get(key);
		}
		return null;
	}

	final protected static void setParameters(HashMap<String, Object> parametersToAdd) {
		for (HashMap.Entry<String, Object> entry : parametersToAdd.entrySet()) {
			String key = entry.getKey();
			if (existsParameter(key)) {
				Object value = entry.getValue();
				parameters.replace(key, value);
			}
		}
	}

	final private static Boolean existsParameter(String parameterName) {
		if (parameters.containsKey(parameterName)) {
			return true;
		}
		ReproductionLogger.ReproductionWarning("ParameterRepository", "Parameter doesnt exist: " + parameterName);
		return false;
	}
	
	final private static HashMap<String,HashMap<String,Object>> ListaDeAtributos(){
		HashMap<String,HashMap<String,Object>> listadeTiposMinimosYMaximosDeCadaAtributo = IntegrationHandler.ObtenerTipoMinimoYMaximoDeCadaAtributo();
		return listadeTiposMinimosYMaximosDeCadaAtributo;
	}
	
}
