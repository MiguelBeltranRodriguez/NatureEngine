package NatureEngine.NatureEngineGenoma.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.AlelesAndComparison.AlelesAndComparisonHandler;
import NatureEngine.NatureEngineGenoma.Commons.ParametersHandler;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;

abstract class AttributesSupport extends RecombinationFecundation {

	protected List<GenAtributo> AlelosDeIndividuosDeNuevaEspecieInner(Integer numeroNuevosIndividousEspecie,
			String nombreDelAtributo, Object valorBaseAtributo) {
		List<GenAtributo> setDeValoresDeAtributosNuevaespecie = new ArrayList<GenAtributo>();
		try {
			Integer numerodeAlelosPorgenerar = calcularNumeroDeAlelosAgenerar(numeroNuevosIndividousEspecie);
			Object datosDelAtributo = ParametersHandler.ObtenerParametro(nombreDelAtributo);
			AlelesAndComparisonHandler generadordealelos = AlelesAndComparisonHandler.Singleton();
			List<Alelo> poolalelosiniciales = PooldeAlelosIniciales(numerodeAlelosPorgenerar, datosDelAtributo, valorBaseAtributo, generadordealelos);
			while(numeroNuevosIndividousEspecie>0) {
				Alelo aleloUno = SeleccionarAleloDePool(poolalelosiniciales);
				Alelo aleloDos = SeleccionarAleloDePool(poolalelosiniciales);
				GenAtributo gen = FecundacioncrearGenAtributo(datosDelAtributo,aleloUno, aleloDos,generadordealelos,true);
				setDeValoresDeAtributosNuevaespecie.add(gen);
				numeroNuevosIndividousEspecie--;
			}
		} catch (Exception ex) {
			ReproductionLogger.ReproductionError("AttributesHandlerCrearNuevaspp", ex);
		}
		return setDeValoresDeAtributosNuevaespecie;
	}
	
	protected List<GenAtributo> AlelosDeHijosInner(Integer numeroDeHijos, String nombreDelAtributo, GenAtributo genpadre,
			GenAtributo genmadre) {
		List<GenAtributo> listaGenAtributosHijos = new ArrayList<GenAtributo>();

		try {
			AlelesAndComparisonHandler generadordealelos = AlelesAndComparisonHandler.Singleton();
			Object datosDelAtributo = ParametersHandler.ObtenerParametro(nombreDelAtributo);
			while (numeroDeHijos > 0) {
				Alelo alelopadre = MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(genpadre);
				Alelo alelomadre = MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(genmadre);
				GenAtributo gen = FecundacioncrearGenAtributo(datosDelAtributo,alelopadre, alelomadre,generadordealelos,false);
				listaGenAtributosHijos.add(gen);
				numeroDeHijos--;
			}
		} catch (Exception ex) {
			ReproductionLogger.ReproductionError("AttributesHandlerCrearNuevaspp", ex);
		}
		return listaGenAtributosHijos;
	}
	
	//////////////////////////////////////////////////////
	////////// METODOS PRIVADOS
	/////////////////////////////////////////////

	private Alelo SeleccionarAleloDePool(List<Alelo> poolalelosiniciales) {
		Integer aleloUnoIndex = 1;
		Alelo alelo = poolalelosiniciales.get(aleloUnoIndex);
		return alelo;
	}

	private List<Alelo> PooldeAlelosIniciales(Integer numerodeAlelosPorgenerar, Object datosDelAtributo,
			Object valorBaseAtributo, AlelesAndComparisonHandler generadordealelos) {
		List<Alelo> poolAlelosIniciales = new ArrayList<Alelo>();
		try {
			while (numerodeAlelosPorgenerar > 0) {
				Alelo nuevoalelo = crearAlelo(datosDelAtributo, valorBaseAtributo, generadordealelos);
				poolAlelosIniciales.add(nuevoalelo);
				numerodeAlelosPorgenerar--;
			}
		} catch (Exception ex) {
			ReproductionLogger.ReproductionError("AttributesHandlerCrearNuevaspp", ex);
		}
		return poolAlelosIniciales;
	}

	private Integer calcularNumeroDeAlelosAgenerar(Integer numeroNuevosIndividousEspecie) {
		Float minimocompartidos =(float)ParametersHandler
				.ObtenerParametro("MinimoAlelosPorAtributo");
		Float maximocompartidos = (float)ParametersHandler
				.ObtenerParametro("MaximoAlelosPorAtributo");
		Float centro = (maximocompartidos + minimocompartidos) / 2;
		Float variabilidad = maximocompartidos - minimocompartidos;
		Float random = new Random().nextFloat();
		Integer numerodeAlelosPorgenerar = (int)(Math.round((random*variabilidad)+centro));
		return numerodeAlelosPorgenerar;
	}

}
