package NatureEngine.NatureEngineGenoma.SpeciesBuilder;

import java.util.HashMap;
import java.util.List;
import NatureEngine.Modelo.CaracteristicaHeredableAgente;

public class SpeciesBuilderHandler extends GenericIndividualBuilder
{
	
	// constructor privado para evitar ser llamado sin singleton
	private SpeciesBuilderHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final SpeciesBuilderHandler instancia = new SpeciesBuilderHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static SpeciesBuilderHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}
	
    public List<HashMap<String,CaracteristicaHeredableAgente>> CrearEspecie(Integer numeroIndividuos, HashMap<String,Object> listaDeValoresDeAtributosDeLaEspecie)
    {
    	List<HashMap<String,CaracteristicaHeredableAgente>> listaDeAtributosDeIndividuosDeNuevaEspecie = CrearListaDeAtributosParaNuevaEspecie(numeroIndividuos, listaDeValoresDeAtributosDeLaEspecie);
        return listaDeAtributosDeIndividuosDeNuevaEspecie;
    }
}
