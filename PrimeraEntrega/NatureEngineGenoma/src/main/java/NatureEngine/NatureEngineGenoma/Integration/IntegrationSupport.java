package NatureEngine.NatureEngineGenoma.Integration;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.CaracteristicaHeredableAgente;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;

abstract class IntegrationSupport {
	
	protected static HashMap<String, CaracteristicaHeredableAgente> ConstruirGenotipoFenotipoIntegracion(HashMap<String, List<Alelo>> genotipo,HashMap<String, Object> fenotipo) {
		HashMap<String, CaracteristicaHeredableAgente> genotipofenotipo = new HashMap<String, CaracteristicaHeredableAgente>();
		for (HashMap.Entry<String, Object> entry : fenotipo.entrySet()) {
			String nombreAtributo = entry.getKey();
			Object valorAtributo = entry.getValue();
			List<Alelo> locusAtributo = genotipo.get(nombreAtributo);
			CaracteristicaHeredableAgente objetocaracteristica = IntegracionCrearCaracteristicaHeredable(nombreAtributo,locusAtributo,valorAtributo);
			genotipofenotipo.put(nombreAtributo, objetocaracteristica);
		}
		return genotipofenotipo;
	}

	private static CaracteristicaHeredableAgente IntegracionCrearCaracteristicaHeredable(String nombreAtributo,List<Alelo> locusAtributo,Object valorAtributo){
		ReproductionLogger.ReproductionNotImplemented("IntegracionCrearCaracteristicaHeredable@Integracion");
		CaracteristicaHeredableAgente ObjetoCaracteristicaHeredableAgente = null;
		return ObjetoCaracteristicaHeredableAgente;
	}
}
