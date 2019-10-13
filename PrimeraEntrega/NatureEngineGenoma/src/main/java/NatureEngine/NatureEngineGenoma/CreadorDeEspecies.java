package NatureEngine.NatureEngineGenoma;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.CaracteristicaHeredableAgente;

public interface CreadorDeEspecies {
	
	public List<HashMap<String,CaracteristicaHeredableAgente>> CrearEspecie(Integer numeroIndividuos, HashMap<String,Object> listaDeValoresDeAtributosDeLaEspecie);

}
