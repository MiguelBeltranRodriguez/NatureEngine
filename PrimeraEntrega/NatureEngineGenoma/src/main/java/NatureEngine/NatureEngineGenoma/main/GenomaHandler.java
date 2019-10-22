package NatureEngine.NatureEngineGenoma.main;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ParametersHandler;
import NatureEngine.NatureEngineGenoma.main.Reproduction.ReproductionHandler;
import NatureEngine.NatureEngineGenoma.main.SpeciesBuilder.SpeciesBuilderHandler;

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
	
	public void ModificarParametros(Object usuario, HashMap<String, Object> parameters){
		ParametersHandler parametrizador = ParametersHandler.Singleton();
		//parametrizador.ModificarParametros(usuario, parameters);
	}
	
	public List<HashMap<String, GenAtributo>> NuevaEspecie(Integer numeroIndividuos, HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie) throws Exception{
		SpeciesBuilderHandler especiador = SpeciesBuilderHandler.Singleton();
		List<HashMap<String, GenAtributo>> listaDeAtributosDeIndividuosDeNuevaEspecie = especiador.CrearListaDeGenAtributosParaNuevaEspecie(numeroIndividuos,listaDeValoresDeAtributosDeLaEspecie);
		return listaDeAtributosDeIndividuosDeNuevaEspecie;
	}
	
	public List<HashMap<String, GenAtributo>> Reproducirse(Integer numerohijos, HashMap<String, GenAtributo> genomaMadre, HashMap<String, GenAtributo> genomaPadre) {
		ReproductionHandler reproductor = ReproductionHandler.Singleton();
		List<HashMap<String, GenAtributo>> genotipofenotipohijo = reproductor.CrearListaDeGenAtributosParaHijos(numerohijos, genomaMadre, genomaPadre);
		return genotipofenotipohijo;
	}
	
}
