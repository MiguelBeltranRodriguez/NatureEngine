package NatureEngine.NatureEngineGenoma.main.Reproduction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.Attributes.AttributesHandler;
import NatureEngine.NatureEngineGenoma.main.GenomaUtils.ReproductionLogger;

abstract public class ReproduccionBasica {

	// Capa más basica por si la reproducción sexual falla, que se garatice la
	// reproducción

	protected List<GenAtributo> CrearListaDeGenAtributosParaHijosInner(Integer numerohijos, String nombreAtributo,
			GenAtributo genatributomadre, GenAtributo genatributopadre) {
		List<GenAtributo> listadealelosdeindividuos = null;
		Float random = new Random().nextFloat();
		try {
			listadealelosdeindividuos = ReproduccionSexual(numerohijos, nombreAtributo, genatributomadre,
					genatributopadre);
		} catch (Exception ex) {
			ReproductionLogger.ReproductionError("ReproduccionSexual", ex);
			listadealelosdeindividuos = new ArrayList<GenAtributo>();
			while (numerohijos > 0) {
				GenAtributo genNuevo = ((random < 0.5) ? genatributomadre : genatributopadre);
				listadealelosdeindividuos.add(genNuevo);
				numerohijos--;
			}
		}
		return listadealelosdeindividuos;
	}

	private List<GenAtributo> ReproduccionSexual(Integer numerohijos, String nombreAtributo,
			GenAtributo genatributomadre, GenAtributo genatributopadre) throws Exception {
		List<GenAtributo> listadealelosdeindividuos = null;
		AttributesHandler manejadoratributos = AttributesHandler.Singleton();
		listadealelosdeindividuos = manejadoratributos.AlelosDeHijos(numerohijos, nombreAtributo, genatributomadre,
				genatributopadre);
		return listadealelosdeindividuos;
	}

}
