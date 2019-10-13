package NatureEngine.NatureEngineGenoma.NatureEngineReproduction.Recombinacion;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.Alelo;

public class RecombinationHandler extends RecombinationSupport{

	// constructor privado para evitar ser llamado sin singleton
	private RecombinationHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final RecombinationHandler instancia = new RecombinationHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static RecombinationHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}
	
	public static HashMap<String, Alelo> RecombinacionYMeiosis(HashMap<String, List<Alelo>> genotipoparental){
		HashMap<String, Alelo> mediogenotipoParental = RecombinarCromosomasDeUnPadreYRetornarUnoSolo(genotipoparental);
		return mediogenotipoParental;
	}
	
}
