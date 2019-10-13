package NatureEngine.NatureEngineGenoma.Attributes;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;

public class AttributesHandler extends AttributesVariator {

	// constructor privado para evitar ser llamado sin singleton
	private AttributesHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final AttributesHandler instancia = new AttributesHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static AttributesHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}
	
	public static Object CalcularValorAtributo(HashMap<String,Object> tipoYLimitesDelAtributo, List<Alelo> alelos){
		Object fenotiponuevo = CalcularValorAtributoInner(tipoYLimitesDelAtributo,alelos);
		return fenotiponuevo;
	}
	
	public static List<Alelo> CalcularGen(HashMap<String,Object> tipoYLimitesDelAtributo,Object valorAtributo){
		List<Alelo> gen = CalcularGenInner(tipoYLimitesDelAtributo,valorAtributo);
		return gen;
	}
	
	public static Object VariarAtributo(HashMap<String,Object> tipoYLimitesDelAtributo, Object valorBaseAtributo){
		Object nuevoValorAtributo=null;
		try {
			nuevoValorAtributo = VariarAtributoInner(tipoYLimitesDelAtributo, valorBaseAtributo);
		} catch (Exception ex) {
			ReproductionLogger.ReproductionError("AttributesHandler", ex);
		}
		return nuevoValorAtributo;
	}

}