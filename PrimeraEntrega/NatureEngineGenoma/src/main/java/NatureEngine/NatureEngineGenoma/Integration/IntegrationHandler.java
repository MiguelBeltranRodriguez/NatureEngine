package NatureEngine.NatureEngineGenoma.Integration;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.CaracteristicaHeredableAgente;

public class IntegrationHandler extends IntegrationSupport{

	// constructor privado para evitar ser llamado sin singleton
	private IntegrationHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final IntegrationHandler instancia = new IntegrationHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static IntegrationHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}
	
	final public static Boolean NatureEngineSecurity(Object usuario) {
		return true;
	}
	
	final public static List<Alelo> getAlelosDelAtributo(CaracteristicaHeredableAgente caracteristicasDelAtributo) {
		List<Alelo> alelos = caracteristicasDelAtributo.getAlelos();
		return alelos;
	}
	
	final public static HashMap<String, CaracteristicaHeredableAgente> ConstruirGenotipoFenotipoNuevo(HashMap<String, List<Alelo>> genotipo, HashMap<String, Object> fenotipo){
		HashMap<String, CaracteristicaHeredableAgente> genotipofenotiponuevo = ConstruirGenotipoFenotipoIntegracion(genotipo, fenotipo);
		return genotipofenotiponuevo;
	}
	
	final public static HashMap<String,HashMap<String,Object>> ObtenerTipoMinimoYMaximoDeCadaAtributo(){
		HashMap<String,HashMap<String,Object>> tiposMinimosYMaximos = null;
		return tiposMinimosYMaximos;
	}
	
	

}
