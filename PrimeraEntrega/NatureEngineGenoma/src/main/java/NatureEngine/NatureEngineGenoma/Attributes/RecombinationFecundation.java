package NatureEngine.NatureEngineGenoma.Attributes;

import java.util.List;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.AlelesAndComparison.AlelesAndComparisonHandler;
import NatureEngine.NatureEngineGenoma.Commons.ParametersHandler;
import NatureEngine.Utils.RandomExtendido;

abstract class RecombinationFecundation extends AttributesVariator{

	protected GenAtributo FecundacioncrearGenAtributo(Object datosAtributo, Alelo aleloUno, Alelo alelodos,AlelesAndComparisonHandler generadordealelos,Boolean Es) {
		aleloUno = MutarOSeguir(datosAtributo, aleloUno,generadordealelos);
		alelodos = MutarOSeguir(datosAtributo, alelodos,generadordealelos);
		// Se hace en esta fase para simplificar, pero es equivalente a una mutación que
		// ocurre en la primera fase de la meiosis
		Object valorAtributo = FecundacionCalcularNuevoFenotipo(datosAtributo, aleloUno, alelodos);
		GenAtributo genatributo = new GenAtributo(aleloUno, alelodos, valorAtributo);
		return genatributo;
	}

	protected Alelo MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(GenAtributo genparental) {
		RandomExtendido randomextendido = new RandomExtendido();
		List<Alelo> alelospadre = genparental.getAlelos();
		Integer indexalelolegido = randomextendido.RandomBooleanoGaussiano(null) ? 1 : 0;
		Alelo aleloredeabuelo = alelospadre.get(indexalelolegido);
		return aleloredeabuelo;
	}

	private Object FecundacionCalcularNuevoFenotipo(Object datosAtributo, Alelo aleloUno, Alelo aleloDos) {
		Object valorFenotipo = null;
		Object valorUno = aleloUno.getValor();
		Object valorDos = aleloDos.getValor();
		Integer DominanciaUno = aleloUno.getDominancia();
		Integer DominanciaDos = aleloDos.getDominancia();
		if (DominanciaUno == DominanciaDos) {
			valorFenotipo=null;
			try {
				valorFenotipo = FenotipoCodominancia(datosAtributo, valorUno, valorDos);
			} catch (Exception ex) {
				NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger.ReproductionError("errorenvalorfenotipo", ex);
			}
		} else {
			if (DominanciaUno > DominanciaDos) {
				valorFenotipo = FenotipoDominancia(datosAtributo, valorUno, valorDos);
			} else {
				valorFenotipo = FenotipoDominancia(datosAtributo, valorDos, valorUno);
			}
		}
		return valorFenotipo;
	}
	
	private Alelo MutarOSeguir(Object datosAtributo, Alelo alelo,AlelesAndComparisonHandler generadordealelos) {
		if (SeDebeMutarONo() == true) {
			String nameIdAleloPadre = alelo.getnameId();
			Object valorBaseAtributo = alelo.getValor();
			Integer DominanciaBaseAtributo = alelo.getDominancia();
			Alelo tmpalelo = mutarAlelo(datosAtributo, nameIdAleloPadre, valorBaseAtributo, DominanciaBaseAtributo,
					generadordealelos);
			if (tmpalelo != null) {
				alelo = tmpalelo;
			}
		}
		return alelo;
	}

	private Boolean SeDebeMutarONo() {
		RandomExtendido randomextendido = new RandomExtendido();
		Float probabilidadDeMutacion = (float) ParametersHandler.ObtenerParametro("TasaDeMutacion");
		return randomextendido.RandomBooleanoGaussiano(probabilidadDeMutacion);
	}

}
