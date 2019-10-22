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
		if(nuevoValor==null) {
			throw new Exception(nombreAtributo +": Esto no debería ser null");
		}
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
	
	protected GenAtributo CrearGenAtributoSexual() throws Exception {
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
		if(nuevoValorAtributo==null) {
			throw new Exception(nombreAtributo +": Esto no debería ser null");
		}
		
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
			Boolean valortmptwo = randomextendido.RandomBooleanoConLimite(null);
			nuevoValorAtributo = (Object) valortmptwo;
		} else {
			if (tipo.equals("java.lang.Integer") || tipo.equals("java.lang.Float")) {
				Float valorInicial = RandomExtendido.ObjectAFloat(valorBaseAtributo);
				Float minimo = RandomExtendido.ObjectAFloat(preminimo);
				Float maximo = RandomExtendido.ObjectAFloat(premaximo);
				Float variabilidad = RandomExtendido.ObjectAFloat(prevariabilidad);	
				Float valortmp = randomextendido.RandomGaussianoLimitadoFloat(valorInicial, variabilidad, minimo, maximo);
				if (tipo.equals("java.lang.Integer")){
					int valortmptwo = (int) Math.round(valortmp);
					nuevoValorAtributo = (Object) valortmptwo;
				}else {
					nuevoValorAtributo = (float) valortmp;
					nuevoValorAtributo = (Object) valortmp;	
				}
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}
		}
		return nuevoValorAtributo;
	}
	


	protected Object FenotipoCodominancia(String nombreAtributo, Object prevalorUno, Object prevalorDos)
			throws Exception {
		if(prevalorUno==null) {
			throw new Exception(nombreAtributo+" fenotipo parental Uno resulto en null");
		}
		if(prevalorDos==null) {
			throw new Exception(nombreAtributo+" fenotipo parental Dos resulto en null");
		}
		
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
					int TmpInteger = (int) Math.round(TmpFloat);
					nuevoFenotipo = (Object) TmpInteger;
				}else {
					float TmpFloattwo = (float)TmpFloat;
					nuevoFenotipo = (Object) TmpFloattwo;
				}
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}
		}
		if(nuevoFenotipo==null) {
			throw new Exception(nombreAtributo+" codominancia resulto en null");
		}
		return nuevoFenotipo;
	}

	protected Object FenotipoDominancia(String nombreAtributo, Object valorDominante, Object valorRecesivo) throws Exception {
		if(valorDominante==null) {
			throw new Exception(nombreAtributo+" dominancia tradicional resulto en null");
		}
		return valorDominante;
	}

}
