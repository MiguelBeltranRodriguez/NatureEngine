package NatureEngine.NatureEngineGenoma;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.CaracteristicaHeredableAgente;
import NatureEngine.NatureEngineGenoma.Reproduction.ReproductionHandler;
import NatureEngine.NatureEngineGenoma.SpeciesBuilder.SpeciesBuilderHandler;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;
import NatureEngine.NatureEngineGenoma.Commons.ParametersHandler;

public class GenomaHandler implements Reproducible,CreadorDeEspecies, ModificadorDeParametrosReproductivos {

	// constructor privado para evitar ser llamado sin singleton
	private GenomaHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final GenomaHandler instancia = new GenomaHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static GenomaHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}

	public static void main(String[] args) {
		ReproductionLogger.ReproductionLog("Inicializando componente de genoma");
	}
	
	public void ModificarParametros(Object usuario, HashMap<String, Object> parameters){
		ParametersHandler parametrizador = ParametersHandler.Singleton();
		parametrizador.ModificarParametros(usuario, parameters);
	}
	
	public List<HashMap<String,CaracteristicaHeredableAgente>> CrearEspecie(Integer numeroIndividuos, HashMap<String,Object> listaDeValoresDeAtributosDeLaEspecie){
		SpeciesBuilderHandler especiador = SpeciesBuilderHandler.Singleton();
		List<HashMap<String,CaracteristicaHeredableAgente>> listaDeAtributosDeIndividuosDeNuevaEspecie = especiador.CrearEspecie(numeroIndividuos, listaDeValoresDeAtributosDeLaEspecie);
		return listaDeAtributosDeIndividuosDeNuevaEspecie;
	}
	
	public HashMap<String, CaracteristicaHeredableAgente> Reproducirse(HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoPadre,HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoMadre) {
		ReproductionHandler reproductor = ReproductionHandler.Singleton();
		HashMap<String,CaracteristicaHeredableAgente> genotipofenotipohijo = reproductor.Reproducirse(genotipofenotipoPadre, genotipofenotipoMadre);
		return genotipofenotipohijo;
	}
	
}
