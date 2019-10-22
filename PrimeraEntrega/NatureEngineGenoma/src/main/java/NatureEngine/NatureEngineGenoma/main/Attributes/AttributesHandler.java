package NatureEngine.NatureEngineGenoma.main.Attributes;

import java.util.List;

import NatureEngine.Modelo.GenAtributo;

public class AttributesHandler extends AttributesSupport {

	// constructor privado para evitar ser llamado sin singleton
	private AttributesHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final AttributesHandler instancia = new AttributesHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static AttributesHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}

	public List<GenAtributo> AlelosDeIndividuosDeNuevaEspecie(Integer numeroNuevosIndividousEspecie,
			String nombreDelAtributo, Object valorBaseAtributo) throws Exception {
		List<GenAtributo> listadealelosdeindividuos = null;
			listadealelosdeindividuos = AlelosDeIndividuosDeNuevaEspecieInner(numeroNuevosIndividousEspecie,
					nombreDelAtributo, valorBaseAtributo);
		return listadealelosdeindividuos;
	}

	public List<GenAtributo> AlelosDeHijos(Integer numeroDeHijos, String nombreDelAtributo, GenAtributo genpadre,
			GenAtributo genmadre) throws Exception {
		List<GenAtributo> listadealelosdeindividuos = null;
		listadealelosdeindividuos = AlelosDeHijosInner(numeroDeHijos, nombreDelAtributo, genpadre, genmadre);
		return listadealelosdeindividuos;
	}

}