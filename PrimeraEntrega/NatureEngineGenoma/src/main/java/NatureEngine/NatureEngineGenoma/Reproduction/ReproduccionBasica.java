package NatureEngine.NatureEngineGenoma.Reproduction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.CaracteristicaHeredableAgente;
import NatureEngine.NatureEngineGenoma.Attributes.AttributesHandler;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;
import NatureEngine.NatureEngineGenoma.Integration.IntegrationHandler;
import NatureEngine.NatureEngineGenoma.NatureEngineReproduction.Recombinacion.RecombinationHandler;

abstract public class ReproduccionBasica {

	//Capa más basica por si la reproducción sexual falla, que se garatice la reproducción
	
	protected HashMap<String, CaracteristicaHeredableAgente> Reproduccion(HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoPadre,HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoMadre) {
		HashMap<String,HashMap<String,Object>> listadeTiposMinimosYMaximosDeCadaAtributo = IntegrationHandler.ObtenerTipoMinimoYMaximoDeCadaAtributo();
		
		HashMap<String, CaracteristicaHeredableAgente> genotipofenotipohijo=new HashMap<String, CaracteristicaHeredableAgente>();
		try {
			HashMap<String, List<Alelo>> genotipopadre = ObtenerSoloGenotipoParental(genotipofenotipoPadre);
			HashMap<String, List<Alelo>> genotipomadre = ObtenerSoloGenotipoParental(genotipofenotipoMadre);
			HashMap<String, Alelo> mediogenotipoPadre = RecombinationHandler.RecombinacionYMeiosis(genotipopadre);
			HashMap<String, Alelo> mediogenotipoMadre = RecombinationHandler.RecombinacionYMeiosis(genotipomadre);
			genotipofenotipohijo = FecundacionYAtributos(listadeTiposMinimosYMaximosDeCadaAtributo,mediogenotipoPadre,mediogenotipoMadre); 
		}catch(Exception ex) {
			ReproductionLogger.ReproductionError("ReproduccionSexual", ex);
			genotipofenotipohijo = genotipofenotipoMadre;
		}
		return genotipofenotipohijo;
	}	
	
	private HashMap<String, CaracteristicaHeredableAgente> FecundacionYAtributos(HashMap<String,HashMap<String,Object>> listadeTiposMinimosYMaximosDeCadaAtributo, HashMap<String, Alelo> mediogenotipoPadre,HashMap<String, Alelo>mediogenotipoMadre){
		HashMap<String, List<Alelo>> genotipohijo =new HashMap<String, List<Alelo>>();
		HashMap<String, Object> fenotipohijo = new HashMap<String, Object>();
		for (HashMap.Entry<String, Alelo> entry : mediogenotipoMadre.entrySet()) {
			String nombreAtributo = entry.getKey();
			Alelo alelomadre = entry.getValue();
			Alelo alelopadre = mediogenotipoPadre.get(nombreAtributo);
			List<Alelo> alelos = Arrays.asList(alelomadre,alelopadre);
			HashMap<String,Object> tipoYLimitesDelAtributo=listadeTiposMinimosYMaximosDeCadaAtributo.get(nombreAtributo);
			Object nuevoValorAtributo = AttributesHandler.CalcularValorAtributo(tipoYLimitesDelAtributo, alelos);
			genotipohijo.put(nombreAtributo, alelos);
			fenotipohijo.put(nombreAtributo, nuevoValorAtributo);
		}
		HashMap<String, CaracteristicaHeredableAgente> genotipofenotipohijo = IntegrationHandler.ConstruirGenotipoFenotipoNuevo(genotipohijo,fenotipohijo);
		return genotipofenotipohijo;
	}
	 
	private HashMap<String, List<Alelo>> ObtenerSoloGenotipoParental(HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoParental){
		HashMap<String, List<Alelo>> genotipoparental=new HashMap<String, List<Alelo>>(); 
		for (HashMap.Entry<String, CaracteristicaHeredableAgente> entry : genotipofenotipoParental.entrySet()) {
			String key = entry.getKey();
			CaracteristicaHeredableAgente caracteristicasDelAtributo = entry.getValue(); 
			List<Alelo> alelos = IntegrationHandler.getAlelosDelAtributo(caracteristicasDelAtributo);
			genotipoparental.put(key, alelos);
		}
		return genotipoparental;
	}
	
}
