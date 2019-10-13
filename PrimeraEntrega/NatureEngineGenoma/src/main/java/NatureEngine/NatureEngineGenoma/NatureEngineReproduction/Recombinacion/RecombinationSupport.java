package NatureEngine.NatureEngineGenoma.NatureEngineReproduction.Recombinacion;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.Alelo;
import NatureEngine.NatureEngineGenoma.Commons.ParametersHandler;
import NatureEngine.NatureEngineGenoma.Commons.ReproductionLogger;

abstract class RecombinationSupport {
	
	protected static HashMap<String, Alelo>  RecombinarCromosomasDeUnPadreYRetornarUnoSolo(HashMap<String, List<Alelo>> genotipoparental) {
		Boolean seMuto=false;
		HashMap<String, Alelo> mediogenotipoparental=null;
		try {
			if(ocurreMutacion()) {
				mediogenotipoparental = RecombinarYMutarCromosomasDeUnPadreYRetornarUnoSolo(genotipoparental);
				seMuto=true;
			}	
		}catch(Exception ex) {
			ReproductionLogger.ReproductionError("Recombinacion", ex);
		}
		if(seMuto==false) {
			mediogenotipoparental = RecombinarSinMutarCromosomasDeUnPadreYRetornarUnoSolo(genotipoparental);
		}
		return mediogenotipoparental;
	}

	private static Boolean ocurreMutacion() {
		Random rand = new Random();
		Float val = rand.nextFloat();
		Float limit = (Float)ParametersHandler.ObtenerParametro("TasaDeMutacion");
		if(val<=limit) {
			return true;
		}
		return false;
	}
	
	private static HashMap<String, Alelo>  RecombinarYMutarCromosomasDeUnPadreYRetornarUnoSolo(HashMap<String, List<Alelo>> genotipofenotipoParental) {
		ReproductionLogger.ReproductionNotImplemented("RecombinarMutando@RecombinationHandler");
		HashMap<String, Alelo> mediogenotipoparental = null;
		return mediogenotipoparental;
	}
	
	private static HashMap<String, Alelo>  RecombinarSinMutarCromosomasDeUnPadreYRetornarUnoSolo(HashMap<String, List<Alelo>> genotipofenotipoParental) {
		ReproductionLogger.ReproductionNotImplemented("RecombinarSinMutar@RecombinationHandler");
		HashMap<String, Alelo> mediogenotipoparental = null;
		return mediogenotipoparental;
	}

}
