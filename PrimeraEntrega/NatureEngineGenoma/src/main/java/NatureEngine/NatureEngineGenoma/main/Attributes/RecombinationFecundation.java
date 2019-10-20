package NatureEngine.NatureEngineGenoma.main.Attributes;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ParametersHandler;
import NatureEngine.Utils.RandomExtendido;

abstract class RecombinationFecundation extends AttributesVariator {

	protected GenAtributo FecundacioncrearGenAtributo(String nombreAtributo, Alelo aleloUno, Alelo aleloDos) throws Exception {
		aleloUno = MutarOSeguir(nombreAtributo, aleloUno);
		aleloDos = MutarOSeguir(nombreAtributo, aleloDos);
		// Se hace en esta fase para simplificar, pero es equivalente a una mutación que
		// ocurre en la primera fase de la meiosis
		Object valorAtributo = FecundacionCalcularNuevoFenotipo(nombreAtributo, aleloUno, aleloDos);
		GenAtributo genatributo = new GenAtributo(nombreAtributo, valorAtributo, aleloUno, aleloDos);
		return genatributo;
	}

	protected Alelo MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(GenAtributo genparental) {
		RandomExtendido randomextendido = new RandomExtendido();
		Alelo aleloabuela = genparental.getaleloUno();
		Alelo aleloabuelo = genparental.getaleloUno();
		Alelo aleloredeabuelo = randomextendido.RandomBooleanoConLimite(null) ? aleloabuela : aleloabuelo;
		return aleloredeabuelo;
	}

	private Object FecundacionCalcularNuevoFenotipo(String nombreAtributo, Alelo aleloUno, Alelo aleloDos)
			throws Exception {
		Object valorFenotipo = null;
		Object valorUno = aleloUno.getValor();
		Object valorDos = aleloDos.getValor();
		if(nombreAtributo=="Sexo") {
			if((Boolean)valorUno==(Boolean)valorDos) {
				valorFenotipo = (Object)true;
			}else {
				valorFenotipo = (Object)false;
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
				valorFenotipo = FenotipoDominancia(valorUno, valorDos);
			} else {
				valorFenotipo = FenotipoDominancia(valorDos, valorUno);
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
