package NatureEngine.NatureEngineGenoma.Simulator;

import java.util.HashMap;
import java.util.Random;
import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.CaracteristicaHeredablePrototype;

public class SimuladorSupport {

	protected HashMap<String, Object> SimulacionCrearMapaInicialDeAtributos() throws Exception {
		HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie = new HashMap<String, Object>();
		HashMap<String, CaracteristicaHeredablePrototype> listaatributos = AtributosBasicos.getAtributosBasicosByName();
		for (HashMap.Entry<String, CaracteristicaHeredablePrototype> entry : listaatributos.entrySet()) {
			String nombreAtributo = entry.getKey();
			System.out.print(nombreAtributo + "=");
			Object valorAtributo = SimulacionCrearAtributo(nombreAtributo);
			System.out.println(valorAtributo);
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
