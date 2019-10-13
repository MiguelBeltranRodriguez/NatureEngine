package NatureEngine.NatureEngineGenoma;

import java.util.HashMap;

import NatureEngine.Modelo.CaracteristicaHeredableAgente;

public interface Reproducible {
	
	public HashMap<String, CaracteristicaHeredableAgente> Reproducirse(HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoPadre,HashMap<String,CaracteristicaHeredableAgente> genotipofenotipoMadre);

}
