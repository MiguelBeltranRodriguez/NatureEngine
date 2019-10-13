package NatureEngine.NatureEngineGenoma.Attributes;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;

abstract class AttributesTranscriptor {

	protected static Object CalcularValorAtributoInner(HashMap<String,Object> tipoYLimitesDelAtributo, List<Alelo> alelos){
		Object fenotiponuevo = null;
		ReproductionLogger.ReproductionNotImplemented("CalcularFenotipo@Transcriptor");
		return fenotiponuevo;
	}
	
	protected static List<Alelo> CalcularGenInner(HashMap<String,Object> tipoYLimitesDelAtributo,Object valorAtributo){
		List<Alelo> gen = null;
		ReproductionLogger.ReproductionNotImplemented("CalcularFenotipo@Transcriptor");
		return gen;
	}
	
}
