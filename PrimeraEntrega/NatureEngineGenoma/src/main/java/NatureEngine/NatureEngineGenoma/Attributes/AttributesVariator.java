package NatureEngine.NatureEngineGenoma.Attributes;

import java.util.HashMap;
import java.util.Random;
import com.fasterxml.jackson.databind.ObjectMapper;

import NatureEngine.NatureEngineGenoma.Commons.ParametersHandler;
import NatureEngine.Utils.RandomExtendido;
import NatureEngine.Modelo.Alelo;
import NatureEngine.NatureEngineGenoma.AlelesAndComparison.AlelesAndComparisonHandler;

abstract class AttributesVariator {

	////////////////////////////////////////////
	///////// FUNCIONES SECUNDARIAS
	///////////////////////////////////////////

	protected Alelo mutarAlelo(Object datosDelAtributo, String nameIdAleloPadre, Object valorBase,
			Integer dominanciaBase, AlelesAndComparisonHandler generadordealelos) {
		Object nuevoValor = null;
		try {
			Float MultiplicadorDeVariacionPorMutacion = (float) ParametersHandler.ObtenerParametro("MultiplicadorDeVariacionPorMutacion");
			nuevoValor = VariarAtributoUsandoDistribucionNormal(datosDelAtributo, valorBase, MultiplicadorDeVariacionPorMutacion);
		} catch (Exception ex) {
			NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger.ReproductionError("mutaralelo", ex);
		}
		Float randonormal = new Random().nextFloat();
		Float probabilidadMutarDominancia = (float) ParametersHandler.ObtenerParametro("ProbabilidadMutarDominancia");
		Integer nuevaDominancia = dominanciaBase;
		if (randonormal > probabilidadMutarDominancia) {
			nuevaDominancia = generarNuevaDominancia();
		}
		if (nuevaDominancia != dominanciaBase || nuevoValor != valorBase) {
			Alelo nuevoAlelo = generadordealelos.CrearNuevoAlelo(nuevaDominancia, nuevoValor,
					nameIdAleloPadre);
			return nuevoAlelo;
		}else {
			return null;
		}
	}

	protected Alelo crearAlelo(Object datosDelAtributo, Object valorBaseAtributo,
			AlelesAndComparisonHandler generadordealelos) {
		Object nuevoValorAtributo = null;
		try {
			nuevoValorAtributo = VariarAtributoUsandoDistribucionNormal(datosDelAtributo, valorBaseAtributo, 1.0f);
		} catch (Exception ex) {
			NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger.ReproductionError("crearalelo", ex);
		}
		Integer nuevaDominancia = generarNuevaDominancia();
		Alelo nuevoAlelo = generadordealelos.CrearNuevoAlelo(nuevaDominancia, nuevoValorAtributo, "root");
		return nuevoAlelo;
	}

	private Integer generarNuevaDominancia() {
		Integer gradosDominancia = (int) ParametersHandler.ObtenerParametro("GradosDeDominancia");
		Integer nuevaDominancia = (int) new Random().nextFloat() * gradosDominancia;
		return nuevaDominancia;
	}

	private Object VariarAtributoUsandoDistribucionNormal(Object datosDelAtributo, Object valorBaseAtributo,
			Float multiplicadorDeVariabilidad) throws Exception {
		ObjectMapper oMapper = new ObjectMapper();
		HashMap<String, Object> tipoYLimitesDelAtributo = oMapper.convertValue(datosDelAtributo, HashMap.class);
		String tipo = (String) tipoYLimitesDelAtributo.get("tipo");
		Object minimo = (Object) tipoYLimitesDelAtributo.get("minimo");
		Object maximo = (Object) tipoYLimitesDelAtributo.get("maximo");
		Object variabilidad = (Object) tipoYLimitesDelAtributo.get("variabilidad");
		RandomExtendido randomextendido = new RandomExtendido();
		Object nuevoValorAtributo = null;
		if (!(variabilidad instanceof Number)) {
			throw new ClassCastException("variabilidad no es un número");
		}
		if (multiplicadorDeVariabilidad != null) {
			variabilidad = (Object) ((float) variabilidad * multiplicadorDeVariabilidad);
		}
		if (tipo == "Boolean") {
			nuevoValorAtributo = (Object) randomextendido.RandomBooleanoGaussiano(variabilidad);
		} else {
			if (tipo == "Integer") {
				nuevoValorAtributo = (Object) randomextendido.RandomGaussianoDeEntero(valorBaseAtributo, variabilidad,
						minimo, maximo);
			}
			if (tipo == "Float") {
				nuevoValorAtributo = (Object) randomextendido.RandomGaussianoDeObject(valorBaseAtributo, variabilidad,
						minimo, maximo);
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}

		}
		return nuevoValorAtributo;
	}
	
	protected Object FenotipoCodominancia(Object datosAtributo, Object valorUno, Object valorDos) throws Exception {
		ObjectMapper oMapper = new ObjectMapper();
		HashMap<String, Object> tipoYLimitesDelAtributo = oMapper.convertValue(datosAtributo, HashMap.class);
		String tipo = (String) tipoYLimitesDelAtributo.get("tipo");
		Object nuevoFenotipo=null;
		if (tipo == "Boolean") {
			RandomExtendido randomextendido = new RandomExtendido();
			nuevoFenotipo = (Object) randomextendido.RandomBooleanoGaussiano(null);
		} else {
			if (tipo == "Integer" || tipo =="float") {
				Float tmp = (((float)valorUno+(float)valorDos)/2);
				if (tipo == "Float") {
					nuevoFenotipo = (Object)tmp;
				}else {
					nuevoFenotipo = (Object)(int)Math.round(tmp);
				}
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}
		}
		return nuevoFenotipo;
	}

	protected Object FenotipoDominancia(Object datosAtributo, Object valorDominante, Object valorRecesivo) {
		return valorDominante;
	}

}
