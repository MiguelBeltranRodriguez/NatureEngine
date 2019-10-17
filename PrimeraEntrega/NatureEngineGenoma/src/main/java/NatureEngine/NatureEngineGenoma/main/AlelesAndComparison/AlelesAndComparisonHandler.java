package NatureEngine.NatureEngineGenoma.main.AlelesAndComparison;


import NatureEngine.Modelo.Alelo;

public class AlelesAndComparisonHandler extends AlelesAndComparisonSupport{

	// constructor privado para evitar ser llamado sin singleton
	private AlelesAndComparisonHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final AlelesAndComparisonHandler instancia = new AlelesAndComparisonHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static AlelesAndComparisonHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}
	
	//public Float GradoDeSimilitudEntreDosIndividuos(List<Alelo> alelosIndividuoUno,List<Alelo> alelosIndividuoDos){
		//Float gradodesimilitud = GradoDeSimilitudEntreDosIndividuosInner(alelosIndividuoUno, alelosIndividuoDos);
		//	return gradodesimilitud;
	//}
	
	public Alelo CrearNuevoAlelo(Integer dominancia, Object valorAtributo, String alelopadreNameId){
		Alelo alelonuevo = CrearNuevoAleloInner(dominancia, valorAtributo, alelopadreNameId);
		return alelonuevo;
	}

}
