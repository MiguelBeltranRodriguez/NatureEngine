package NatureEngine.NatureEngineGenoma.SpeciesBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.CaracteristicaHeredableAgente;
import NatureEngine.NatureEngineGenoma.Integration.IntegrationHandler;
import NatureEngine.NatureEngineGenoma.Attributes.AttributesHandler;

abstract class GenericIndividualBuilder {

	protected List<HashMap<String, CaracteristicaHeredableAgente>> CrearListaDeAtributosParaNuevaEspecie(Integer numeroIndividuos, HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie) {
		HashMap<String,HashMap<String,Object>> listadeTiposMinimosYMaximosDeCadaAtributo = IntegrationHandler.ObtenerTipoMinimoYMaximoDeCadaAtributo();
		
		List<HashMap<String, CaracteristicaHeredableAgente>> listaIndividuos= new ArrayList<HashMap<String, CaracteristicaHeredableAgente>>();
		
		while(numeroIndividuos>0) {
			HashMap<String, CaracteristicaHeredableAgente> atributosNuevoIndividuo = VariarIndividuo(listaDeValoresDeAtributosDeLaEspecie,listadeTiposMinimosYMaximosDeCadaAtributo);
			listaIndividuos.add(atributosNuevoIndividuo);
			numeroIndividuos--;
		}
		return listaIndividuos;
	}
	
	private HashMap<String, CaracteristicaHeredableAgente> VariarIndividuo(HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie,HashMap<String,HashMap<String,Object>> listadeTiposMinimosYMaximosDeCadaAtributo){
		HashMap<String, Object> fenotipo = new HashMap<String, Object>();
		HashMap<String, List<Alelo>> genotipo = new HashMap<String, List<Alelo>>();
		for (HashMap.Entry<String, Object> entry : listaDeValoresDeAtributosDeLaEspecie.entrySet()) {
			String nombreAtributo = entry.getKey();
			Object valorBaseAtributo = entry.getValue();
			HashMap<String,Object> tipoYLimitessDelAtributo=listadeTiposMinimosYMaximosDeCadaAtributo.get(nombreAtributo);
			Object nuevoValorAtributo = AttributesHandler.VariarAtributo(tipoYLimitessDelAtributo,valorBaseAtributo);
			List<Alelo> gen = AttributesHandler.CalcularGen(tipoYLimitessDelAtributo, nuevoValorAtributo);
			fenotipo.put(nombreAtributo, nuevoValorAtributo);
			genotipo.put(nombreAtributo, gen);
		}
		HashMap<String, CaracteristicaHeredableAgente> genotipofenotipo = IntegrationHandler.ConstruirGenotipoFenotipoNuevo(genotipo, fenotipo);
		return genotipofenotipo;
	}

}
