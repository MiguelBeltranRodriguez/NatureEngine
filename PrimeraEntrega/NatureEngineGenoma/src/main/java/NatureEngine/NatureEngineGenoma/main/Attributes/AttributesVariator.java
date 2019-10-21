package NatureEngine.NatureEngineGenoma.main.Attributes;

import java.util.Random;

import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ParametersHandler;
import NatureEngine.Utils.RandomExtendido;
import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.GenAtributo;

abstract class AttributesVariator {

	////////////////////////////////////////////
	///////// FUNCIONES SECUNDARIAS
	///////////////////////////////////////////

	protected Alelo mutarAlelo(String nombreAtributo, Object valorBase, Integer dominanciaBase) throws Exception {
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
			Alelo nuevoAlelo = CrearAlelo(nuevaDominancia, nuevoValor);
			return nuevoAlelo;
		} else {
			return null;
		}
	}
	
	protected GenAtributo CrearGenAtributoSexual() {
		Alelo aleloUno = new Alelo(0,(Object)false);
		RandomExtendido randomextendido = new RandomExtendido();
		Object valorAleloDos = (Object) randomextendido.RandomBooleanoConLimite(null);
		Alelo aleloDos = new Alelo(0,valorAleloDos);
		GenAtributo genatributo = new GenAtributo("Sexo",valorAleloDos,aleloUno,aleloDos);
		return genatributo;
	}

	protected Alelo CrearNuevoAlelo(String nombreAtributo, Object valorBaseAtributo) throws Exception {
		Object nuevoValorAtributo = null;

		nuevoValorAtributo = VariarAtributoUsandoDistribucionNormal(nombreAtributo, valorBaseAtributo,1.0f);
		Integer nuevaDominancia = generarNuevaDominancia();
		Alelo nuevoAlelo = CrearAlelo(nuevaDominancia, nuevoValorAtributo);
		return nuevoAlelo;
	}
	
	protected Alelo CrearAlelo(Integer nuevaDominancia, Object nuevoValorAtibuto) {
		Alelo alelo = new Alelo(nuevaDominancia,nuevoValorAtibuto);
		return alelo;
	}

	private Integer generarNuevaDominancia() {
		Integer gradosDominancia = ParametersHandler.getGradosDeDominancia();
		Integer nuevaDominancia = (int) (new Random().nextFloat() * gradosDominancia);
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
				Float valorInicial = RandomExtendido.ObjectAFloat(valorBaseAtributo);
				Float minimo = RandomExtendido.ObjectAFloat(preminimo);
				Float maximo = RandomExtendido.ObjectAFloat(premaximo);
				Float variabilidad = RandomExtendido.ObjectAFloat(prevariabilidad);	
				Float valortmp = randomextendido.RandomGaussianoLimitadoFloat(valorInicial, variabilidad, minimo, maximo);
				if((Math.abs(valorInicial-valortmp)/valorInicial)>1.5){
					System.out.println(valortmp+"/"+valorInicial);
				}
				nuevoValorAtributo = (Object) valortmp;
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
				Float valorUno = RandomExtendido.ObjectAFloat(prevalorUno);
						Float valorDos = RandomExtendido.ObjectAFloat(prevalorDos); 
						Float TmpFloat = ((valorUno + valorDos) / 2);
						
				if (tipo.equals("java.lang.Integer")) {
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
