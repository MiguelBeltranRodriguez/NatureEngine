package NatureEngine.NatureEngineGenoma.Commons;

import java.util.Collections;
import java.util.HashMap;

public abstract class ParametersRepository {

	private static HashMap<String, Object> parameters;
	static {
		HashMap<String, Object> parametersSet = new HashMap<String, Object>();
		parametersSet.put("TasaDeMutacion", 0.05);

		setParameters((HashMap<String, Object>) Collections.unmodifiableMap(parametersSet));

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

}
