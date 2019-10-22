package NatureEngine.NatureEngineGenoma.main.Attributes;

import java.util.Random;

import NatureEngine.NatureEngineGenoma.main.AlelesAndComparison.AlelesAndComparisonHandler;
import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ParametersHandler;
import NatureEngine.Utils.RandomExtendido;
import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.AtributosBasicos;

abstract class AttributesVariator {

	////////////////////////////////////////////
	///////// FUNCIONES SECUNDARIAS
	///////////////////////////////////////////

	protected Alelo mutarAlelo(String nombreAtributo, Object valorBase, Integer dominanciaBase, String alelopadreNameId,
			AlelesAndComparisonHandler generadordealelos) throws Exception {
		Object nuevoValor = null;
		Float MultiplicadorDeVariacionPorMutacion = (float) ParametersHandler.getMultiplicadorDeVariacionPorMutacion();
		nuevoValor = VariarAtributoUsandoDistribucionNormal(nombreAtributo, valorBase,
				MultiplicadorDeVariacionPorMutacion);
		Float randonormal = new Random().nextFloat();
		Float probabilidadMutarDominancia = (float) ParametersHandler.getProbabilidadMutarDominancia();
		Integer nuevaDominancia = dominanciaBase;
		if (randonormal > probabilidadMutarDominancia) {
			nuevaDominancia = generarNuevaDominancia();
		}
		if (nuevaDominancia != dominanciaBase || nuevoValor != valorBase) {
			Alelo nuevoAlelo = generadordealelos.CrearNuevoAlelo(nuevaDominancia, nuevoValor, alelopadreNameId);
			return nuevoAlelo;
		} else {
			return null;
		}
	}

	protected Alelo crearAlelo(String nombreAtributo, Object valorBaseAtributo,
			AlelesAndComparisonHandler generadordealelos) throws Exception {
		Object nuevoValorAtributo = null;

		nuevoValorAtributo = VariarAtributoUsandoDistribucionNormal(nombreAtributo, valorBaseAtributo, 1.0f);

		Integer nuevaDominancia = generarNuevaDominancia();
		Alelo nuevoAlelo = generadordealelos.CrearNuevoAlelo(nuevaDominancia, nuevoValorAtributo, "root");
		return nuevoAlelo;
	}

	private Integer generarNuevaDominancia() {
		Integer gradosDominancia = (int) ParametersHandler.getGradosDeDominancia();
		Integer nuevaDominancia = (int) new Random().nextFloat() * gradosDominancia;
		return nuevaDominancia;
	}

	private Object VariarAtributoUsandoDistribucionNormal(String nombreAtributo, Object valorBaseAtributo,
			Float multiplicadorDeVariabilidad) throws Exception {

		Object preminimo = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getValorMinimo();
		Object premaximo = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getValorMaximo();
		String tipo = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getTipoCaracteristica();
		Object prevariabilidad = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getVariabilidad();
		RandomExtendido randomextendido = new RandomExtendido();
		Object nuevoValorAtributo = null;
		if (tipo.equals("java.lang.Boolean")) {
			nuevoValorAtributo = (Object) randomextendido.RandomBooleanoConLimite(null);
		} else {
			if (tipo.equals("java.lang.Integer") || tipo.equals("java.lang.Float")) {
				Float variabilidad = Float.parseFloat(prevariabilidad.toString());
				if (multiplicadorDeVariabilidad != null) {
					variabilidad = variabilidad * multiplicadorDeVariabilidad;
				}
				Float tmp = null;
				if (tipo.equals("java.lang.Integer")) {
					Integer pretwominimo = (Integer) preminimo;
					Integer pretwomaximo = (Integer) premaximo;
					Integer pretmvalorBaseAtributo = (Integer) valorBaseAtributo;
					Float minimo = (float) pretwominimo;
					Float maximo = (float) pretwomaximo;
					Float tmpvalorBaseAtributo = (float) pretmvalorBaseAtributo;
					Float rango = maximo - minimo;
					tmp = randomextendido.RandomGaussianoLimitadoFloat(tmpvalorBaseAtributo, variabilidad, minimo,
							maximo);
					nuevoValorAtributo = (Object) ((int) Math.round(tmp));
				}
				if (tipo.equals("java.lang.Float")) {
					Float minimo = (Float) preminimo;
					Float maximo = (Float) premaximo;
					Float rango = maximo - minimo;
					Float tmpvalorBaseAtributo = (float) valorBaseAtributo;
					tmp = randomextendido.RandomGaussianoLimitadoFloat(tmpvalorBaseAtributo, variabilidad, minimo,
							maximo);
					nuevoValorAtributo = (Object) tmp;
				}
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}
		}
		return nuevoValorAtributo;
	}

	protected Object FenotipoCodominancia(String nombreAtributo, Object prevalorUno, Object prevalorDos)
			throws Exception {
		String tipo = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getTipoCaracteristica();
		Object nuevoFenotipo = null;
		if (tipo.equals("java.lang.Boolean")) {
			RandomExtendido randomextendido = new RandomExtendido();
			Boolean TmpBool = randomextendido.RandomBooleanoConLimite(null);
			nuevoFenotipo = (Object) TmpBool;
		} else {
			if (tipo.equals("java.lang.Integer") || tipo.equals("java.lang.Float")) {
				if (tipo.equals("java.lang.Float")) {
					Float valorUno = (Float) prevalorUno;
					Float valorDos = (Float) prevalorDos;
					Float TmpFloat = ((valorUno + valorDos) / 2);
					nuevoFenotipo = (Object) TmpFloat;
				} else {
					int valorUno = (int) prevalorUno;
					int valorDos = (int) prevalorDos;
					Float TmpFloat = ((float) (valorUno + valorDos) / 2);
					Integer TmpInteger = (int) Math.round(TmpFloat);
					nuevoFenotipo = (Object) TmpInteger;
				}
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}
		}
		return nuevoFenotipo;
	}

	protected Object FenotipoDominancia(Object valorDominante, Object valorRecesivo) {
		return valorDominante;
	}

}
