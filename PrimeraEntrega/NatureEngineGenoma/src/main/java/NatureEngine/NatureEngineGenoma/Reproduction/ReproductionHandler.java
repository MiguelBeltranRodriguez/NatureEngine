package NatureEngine.NatureEngineGenoma.Reproduction;

import java.util.HashMap;

import NatureEngine.Modelo.CaracteristicaHeredableAgente;

public class ReproductionHandler extends ReproduccionBasica{
	
	// constructor privado para evitar ser llamado sin singleton
	private ReproductionHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final ReproductionHandler instancia = new ReproductionHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static ReproductionHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}

	public HashMap<String, CaracteristicaHeredableAgente> Reproducirse(HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoPadre,HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoMadre) {
		HashMap<String,CaracteristicaHeredableAgente> genotipofenotipohijo = Reproduccion(genotipofenotipoPadre, genotipofenotipoMadre);
		return genotipofenotipohijo;
	}

}
