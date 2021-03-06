package NatureEngine.NatureEngineGenoma.main.Attributes;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ParametersHandler;
import NatureEngine.Utils.RandomExtendido;

abstract class RecombinationFecundation extends AttributesVariator {

	protected GenAtributo FecundacioncrearGenAtributo(String nombreAtributo, Alelo aleloUno, Alelo aleloDos) throws Exception {
		if(!(nombreAtributo.equals("Sexo"))) {
			aleloUno = MutarOSeguir(nombreAtributo, aleloUno);
			aleloDos = MutarOSeguir(nombreAtributo, aleloDos);	
		}
		// Se hace en esta fase para simplificar, pero es equivalente a una mutación que
		// ocurre en la primera fase de la meiosis
		Object valorAtributo = FecundacionCalcularNuevoFenotipo(nombreAtributo, aleloUno, aleloDos);
		if(valorAtributo==null) {
			throw new Exception(nombreAtributo+" la fecundación resulto en null");
		}
		GenAtributo genatributo = new GenAtributo(nombreAtributo, valorAtributo, aleloUno, aleloDos);
		return genatributo;
	}

	protected Alelo MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(GenAtributo genparental) {
		RandomExtendido randomextendido = new RandomExtendido();
		Alelo aleloabuela = genparental.getaleloUno();
		Alelo aleloabuelo = genparental.getaleloDos();
		Alelo aleloredeabuelo = randomextendido.RandomBooleanoConLimite(null) ? aleloabuela : aleloabuelo;
		return aleloredeabuelo;
	}

	private Object FecundacionCalcularNuevoFenotipo(String nombreAtributo, Alelo aleloUno, Alelo aleloDos)
			throws Exception {
		Object valorFenotipo = null;
		Object valorUno = aleloUno.getValor();
		Object valorDos = aleloDos.getValor();
		if(nombreAtributo.equals("Sexo")) {
			if((Boolean)valorUno==true && (Boolean)valorDos==true) {
				throw new Exception("NO SE PUEDEN REPRODUCIR DOS MACHOS");
			}else {
				if(((Boolean)valorUno==false) && ((Boolean)valorDos)==false) {
					valorFenotipo = (Object)false;
				}else {
					valorFenotipo = (Object)true;
				}	
				
			}
			return valorFenotipo;
		}
		Integer DominanciaUno = aleloUno.getDominancia();
		Integer DominanciaDos = aleloDos.getDominancia();
		if (DominanciaUno == DominanciaDos) {
			valorFenotipo = null;
			valorFenotipo = FenotipoCodominancia(nombreAtributo, valorUno, valorDos);
		} else {
			if (DominanciaUno > DominanciaDos) {
				valorFenotipo = FenotipoDominancia(nombreAtributo,valorUno, valorDos);
			} else {
				valorFenotipo = FenotipoDominancia(nombreAtributo,valorDos, valorUno);
			}
		}
		return valorFenotipo;
	}

	private Alelo MutarOSeguir(String nombreAtributo, Alelo alelo) throws Exception {
		if (SeDebeMutarONo() == true) {
			Object valorBaseAtributo = alelo.getValor();
			Integer DominanciaBaseAtributo = alelo.getDominancia();
			Alelo tmpalelo = mutarAlelo(nombreAtributo, valorBaseAtributo, DominanciaBaseAtributo);
			if (tmpalelo != null) {
				alelo = tmpalelo;
			}
		}
		return alelo;
	}

	private Boolean SeDebeMutarONo() {
		RandomExtendido randomextendido = new RandomExtendido();
		Float probabilidadDeMutacion = null;
		probabilidadDeMutacion = (float) ParametersHandler.getTasaDeMutacion();
		return randomextendido.RandomBooleanoConLimite(probabilidadDeMutacion);
	}

}
