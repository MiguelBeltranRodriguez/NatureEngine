package NatureEngine.NatureEngineGenoma.Simulator;

import java.util.HashMap;
import java.util.Random;
import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.CaracteristicaHeredablePrototype;

public class SimuladorSupport {

	
	protected HashMap<String, Object> AtributosIniciales() {
		HashMap<String, Object> fenotipo = new HashMap<String, Object>();
		fenotipo.put(AtributosBasicos.ENERGIA_MAXIMA_, 6000.0f);
		fenotipo.put(AtributosBasicos.AGUA_MAXIMA_, 3000.0f);
		fenotipo.put(AtributosBasicos.POTENCIA_MAXIMA_, 20.0f);
		fenotipo.put(AtributosBasicos.TAMANO_MAXIMO_, 10);
		fenotipo.put(AtributosBasicos.PERCEPCION_, 50);
		fenotipo.put(AtributosBasicos.SEXO_, false);
		fenotipo.put(AtributosBasicos.CAPACIDAD_REPRODUCTIVA_, 1);
		fenotipo.put(AtributosBasicos.ANSIEDAD_, 50);
		fenotipo.put(AtributosBasicos.HUMEDAD_IDEAL_, 0.5f);
		fenotipo.put(AtributosBasicos.TOLERANCIA_HUMEDAD_, 0.5f);
		fenotipo.put(AtributosBasicos.DIGESTION_VEGETAL_, 1);
		fenotipo.put(AtributosBasicos.AGRESIVIDAD_, 0.5f);
		fenotipo.put(AtributosBasicos.LONGEVIDAD_, 100);
		fenotipo.put(AtributosBasicos.MADUREZ_REPRODUCTIVA, 10);
		return fenotipo;
	}
	
	
	protected HashMap<String, Object> SimulacionCrearMapaInicialDeAtributos() throws Exception {
		HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie = new HashMap<String, Object>();
		HashMap<String, CaracteristicaHeredablePrototype> listaatributos = AtributosBasicos.getAtributosBasicosByName();
		for (HashMap.Entry<String, CaracteristicaHeredablePrototype> entry : listaatributos.entrySet()) {
			String nombreAtributo = entry.getKey();
			Object valorAtributo = SimulacionCrearAtributo(nombreAtributo);
			listaDeValoresDeAtributosDeLaEspecie.put(nombreAtributo, valorAtributo);
		}
		return listaDeValoresDeAtributosDeLaEspecie;
	}

	private Object SimulacionCrearAtributo(String nombreAtributo) throws Exception {
		Object preminimo = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getValorMinimo();
		Object premaximo = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getValorMaximo();
		String tipo = AtributosBasicos.getAtributosBasicosByName().get(nombreAtributo).getTipoCaracteristica();
		Object valorAtributo = null;
		if (!(tipo.equals("java.lang.Integer") || tipo.equals("java.lang.Boolean") || tipo.equals("java.lang.Float"))) {
			throw new Exception("Tipo argumento invalido.. " + tipo);
		}
		/// ES UN RANDOM PLANO PORQUE EL USUARIO PUEDE ESCOGER CUALQUIER VALOR
		Random random = new Random();

		if (tipo.equals("java.lang.Boolean")) {
			valorAtributo = (random.nextFloat() < 0.5);
		} else {
			if (tipo.equals("java.lang.Float")) {
				Float minimo = (float) preminimo;
				Float maximo = (float) premaximo;
				Float varianza = maximo - minimo;
				Float valor = minimo + random.nextFloat() * varianza;
				valorAtributo = valor;
			}
			if (tipo.equals("java.lang.Integer")) {
				Integer minimo = (int) preminimo;
				Integer maximo = (int) premaximo;
				Integer varianza = maximo - minimo;
				Float valor = minimo + random.nextFloat() * varianza;
				valorAtributo = (int) Math.round(valor);
			}
		}
		return valorAtributo;
	}

}
