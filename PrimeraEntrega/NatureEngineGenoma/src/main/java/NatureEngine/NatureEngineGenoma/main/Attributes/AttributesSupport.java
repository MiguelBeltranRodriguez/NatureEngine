package NatureEngine.NatureEngineGenoma.main.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ParametersHandler;

abstract class AttributesSupport extends RecombinationFecundation {
	
	protected List<GenAtributo> AlelosDeIndividuosDeNuevaEspecieInner(Integer numeroNuevosIndividousEspecie,
			String nombreAtributo, Object valorBaseAtributo) throws Exception {
		List<GenAtributo> setDeValoresDeAtributosNuevaespecie = new ArrayList<GenAtributo>();
		if(nombreAtributo.equals("Sexo")) {
			while(numeroNuevosIndividousEspecie>0) {
				GenAtributo gen = CrearGenAtributoSexual();
				setDeValoresDeAtributosNuevaespecie.add(gen);
				numeroNuevosIndividousEspecie--;
			}
		}else {
			Integer numerodeAlelosPorgenerar = calcularNumeroDeAlelosAgenerar(numeroNuevosIndividousEspecie);
			List<Alelo> poolalelosiniciales = PooldeAlelosIniciales(numerodeAlelosPorgenerar, nombreAtributo, valorBaseAtributo);
			if(nombreAtributo.equals("Energia maxima")) {
				System.out.print(nombreAtributo+" => Base: "+valorBaseAtributo+" =>");
				
			for (Alelo gen : poolalelosiniciales) 
			{ 
				System.out.print(gen.getValor()+"--");
			}
			System.out.println("");
			}
			while(numeroNuevosIndividousEspecie>0) {
				Alelo aleloUno = SeleccionarAleloDePool(poolalelosiniciales);
				Alelo aleloDos = SeleccionarAleloDePool(poolalelosiniciales);
				GenAtributo gen = FecundacioncrearGenAtributo(nombreAtributo,aleloUno, aleloDos);
				setDeValoresDeAtributosNuevaespecie.add(gen);
				numeroNuevosIndividousEspecie--;
			}
		}
		return setDeValoresDeAtributosNuevaespecie;
	}
	
	protected List<GenAtributo> ConstruirAlelosDeHijosInner(Integer numeroDeHijos, String nombreAtributo, GenAtributo genpadre,
			GenAtributo genmadre) throws Exception {
		List<GenAtributo> listaGenAtributosHijos = new ArrayList<GenAtributo>();
			while (numeroDeHijos > 0) {
				Alelo alelopadre = MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(genpadre);
				Alelo alelomadre = MeiosisEscogerCualquieraDeLosDosAlelosDeAbuelos(genmadre);
				GenAtributo gen = FecundacioncrearGenAtributo(nombreAtributo,alelopadre, alelomadre);
				listaGenAtributosHijos.add(gen);
				numeroDeHijos--;
			}

		return listaGenAtributosHijos;
	}
	
	//////////////////////////////////////////////////////
	////////// METODOS PRIVADOS
	/////////////////////////////////////////////

	private Alelo SeleccionarAleloDePool(List<Alelo> poolalelosiniciales) {
		Integer random = (new Random()).nextInt(poolalelosiniciales.size()-1);
		Alelo alelo = poolalelosiniciales.get(random);
		return alelo;
	}

	private List<Alelo> PooldeAlelosIniciales(Integer numerodeAlelosPorgenerar, String nombreAtributo,
			Object valorBaseAtributo) throws Exception {
		List<Alelo> poolAlelosIniciales = new ArrayList<Alelo>();

			while (numerodeAlelosPorgenerar > 0) {
				Alelo nuevoalelo = CrearNuevoAlelo(nombreAtributo, valorBaseAtributo);
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
