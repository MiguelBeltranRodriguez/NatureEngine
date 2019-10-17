package NatureEngine.NatureEngineGenoma.main.SpeciesBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.Attributes.AttributesHandler;

public class SpeciesBuilderHandler
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
	
	
	public List<HashMap<String, GenAtributo>> CrearListaDeGenAtributosParaNuevaEspecie(Integer numeroIndividuos, HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie) throws Exception {
	
		List<HashMap<String, GenAtributo>> listadeGenAtributosdeindividuos = new ArrayList<HashMap<String, GenAtributo>>();
		AttributesHandler manejadoratributos = AttributesHandler.Singleton(); 
		Boolean primeravuelta = true;
		for (HashMap.Entry<String, Object> entry : listaDeValoresDeAtributosDeLaEspecie.entrySet()) {
			String nombreAtributo = entry.getKey();
			Object valorBaseAtributo = entry.getValue();
			List<GenAtributo> listadealelosdeindividuos = manejadoratributos.AlelosDeIndividuosDeNuevaEspecie(numeroIndividuos, nombreAtributo, valorBaseAtributo);
			if(primeravuelta==true) {
				for (int index = 0; index < listadealelosdeindividuos.size(); index++) {
					HashMap<String, GenAtributo> IndividuoActual = new HashMap<String, GenAtributo>();
					listadeGenAtributosdeindividuos.add(IndividuoActual);
				}	
				primeravuelta=false;
			}
			for (int index = 0; index < listadealelosdeindividuos.size(); index++) {
				HashMap<String, GenAtributo> IndividuoActual =listadeGenAtributosdeindividuos.get(index); 
				IndividuoActual.put(nombreAtributo,listadealelosdeindividuos.get(index));
				listadeGenAtributosdeindividuos.set(index, IndividuoActual);
			}
		}
		return listadeGenAtributosdeindividuos;
	}
	
}
