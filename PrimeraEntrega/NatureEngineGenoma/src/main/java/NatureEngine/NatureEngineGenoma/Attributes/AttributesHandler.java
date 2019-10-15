package NatureEngine.NatureEngineGenoma.Attributes;

import java.util.List;

import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;

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
			String nombreDelAtributo, Object valorBaseAtributo) {
		List<GenAtributo> listadealelosdeindividuos = null;
		try {
			listadealelosdeindividuos = AlelosDeIndividuosDeNuevaEspecieInner(numeroNuevosIndividousEspecie,
					nombreDelAtributo, valorBaseAtributo);
		} catch (Exception ex) {
			ReproductionLogger.ReproductionError("AttributesHandlerCrearNuevaspp", ex);
		}
		return listadealelosdeindividuos;
	}

	public List<GenAtributo> AlelosDeHijos(Integer numeroDeHijos, String nombreDelAtributo, GenAtributo genpadre,
			GenAtributo genmadre) {
		List<GenAtributo> listadealelosdeindividuos = null;
		try {
			listadealelosdeindividuos = AlelosDeHijosInner(numeroDeHijos, nombreDelAtributo, genpadre, genmadre);
		} catch (Exception ex) {
			ReproductionLogger.ReproductionError("AttributesHandlerMutar", ex);
		}
		return listadealelosdeindividuos;
	}

}