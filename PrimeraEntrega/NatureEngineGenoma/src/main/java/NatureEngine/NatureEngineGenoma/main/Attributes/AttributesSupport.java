package NatureEngine.NatureEngineGenoma.main.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.AlelesAndComparison.AlelesAndComparisonHandler;
import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ParametersHandler;

abstract class AttributesSupport extends RecombinationFecundation {

	protected List<GenAtributo> AlelosDeIndividuosDeNuevaEspecieInner(Integer numeroNuevosIndividousEspecie,
			String nombreAtributo, Object valorBaseAtributo) throws Exception {
		List<GenAtributo> setDeValoresDeAtributosNuevaespecie = new ArrayList<GenAtributo>();
			Integer numerodeAlelosPorgenerar = calcularNumeroDeAlelosAgenerar(numeroNuevosIndividousEspecie);
			AlelesAndComparisonHandler generadordealelos = AlelesAndComparisonHandler.Singleton();
			List<Alelo> poolalelosiniciales = PooldeAlelosIniciales(numerodeAlelosPorgenerar, nombreAtributo, valorBaseAtributo, generadordealelos);
			while(numeroNuevosIndividousEspecie>0) {
				Alelo aleloUno = SeleccionarAleloDePool(poolalelosiniciales);
				Alelo aleloDos = SeleccionarAleloDePool(poolalelosiniciales);
				GenAtributo gen = FecundacioncrearGenAtributo(nombreAtributo,aleloUno, aleloDos,generadordealelos,true);
				setDeValoresDeAtributosNuevaespecie.add(gen);
				numeroNuevosIndividousEspecie--;
			}
		return setDeValoresDeAtributosNuevaespecie;
	}
	
	protected List<GenAtributo> AlelosDeHijosInner(Integer numeroDeHijos, String nombreAtributo, GenAtributo genpadre,
			GenAtributo genmadre) throws Exception {
		List<GenAtributo> listaGenAtributosHijos = new ArrayList<GenAtributo>();


			AlelesAndComparisonHandler generadordealelos = AlelesAndComparisonHandler.Singleton();
			while (numeroDeHijos > 0) {
				Alelo alelopadre = MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(genpadre);
				Alelo alelomadre = MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(genmadre);
				GenAtributo gen = FecundacioncrearGenAtributo(nombreAtributo,alelopadre, alelomadre,generadordealelos,false);
				listaGenAtributosHijos.add(gen);
				numeroDeHijos--;
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

	private List<Alelo> PooldeAlelosIniciales(Integer numerodeAlelosPorgenerar, String nombreAtributo,
			Object valorBaseAtributo, AlelesAndComparisonHandler generadordealelos) throws Exception {
		List<Alelo> poolAlelosIniciales = new ArrayList<Alelo>();

			while (numerodeAlelosPorgenerar > 0) {
				Alelo nuevoalelo = crearAlelo(nombreAtributo, valorBaseAtributo, generadordealelos);
				poolAlelosIniciales.add(nuevoalelo);
				numerodeAlelosPorgenerar--;
			}

		return poolAlelosIniciales;
	}

	private Integer calcularNumeroDeAlelosAgenerar(Integer numeroNuevosIndividousEspecie) {
		Integer minimocompartidos =ParametersHandler.getMaximoAlelosPorAtributo();
		Integer maximocompartidos = ParametersHandler.getMaximoAlelosPorAtributo();
		Integer centro = (maximocompartidos + minimocompartidos) / 2;
		Integer rango = maximocompartidos - minimocompartidos;
		Float random = new Random().nextFloat();
		Integer numerodeAlelosPorgenerar = (int)(Math.round((random*rango)+centro));
		return numerodeAlelosPorgenerar;
	}

}
