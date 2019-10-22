package NatureEngine.NatureEngineGenoma.main.Reproduction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.GenAtributo;

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

	public List<HashMap<String, GenAtributo>> CrearListaDeGenAtributosParaHijos(Integer numerohijos, HashMap<String, GenAtributo> genomaMadre, HashMap<String, GenAtributo> genomaPadre) {
		List<HashMap<String, GenAtributo>> listadeGenAtributoshijos = new ArrayList<HashMap<String, GenAtributo>>();
		Boolean primeravuelta = true;
		for (HashMap.Entry<String, GenAtributo> entry : genomaMadre.entrySet()) {
			String nombreAtributo = entry.getKey();
			GenAtributo genatributomadre = entry.getValue();
			GenAtributo genatributopadre = genomaPadre.get(nombreAtributo);
			List<GenAtributo> listadealelosdeindividuos = CrearListaDeGenAtributosParaHijosInner(numerohijos, nombreAtributo, genatributomadre,genatributopadre);
			if(primeravuelta==true) {
				for (int index = 0; index < listadealelosdeindividuos.size(); index++) {
					HashMap<String, GenAtributo> IndividuoActual = new HashMap<String, GenAtributo>();
					listadeGenAtributoshijos.add(IndividuoActual);
				}	
				primeravuelta=false;
			}
			for (int index = 0; index < listadealelosdeindividuos.size(); index++) {
				HashMap<String, GenAtributo> IndividuoActual =listadeGenAtributoshijos.get(index); 
				IndividuoActual.put(nombreAtributo,listadealelosdeindividuos.get(index));
				listadeGenAtributoshijos.set(index, IndividuoActual);
			}
		}
		return listadeGenAtributoshijos;
	}

}
