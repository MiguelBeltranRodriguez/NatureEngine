package NatureEngine.NatureEngineGenoma.main;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.GenAtributo;

public interface CreadorDeEspecies {
	
	public List<HashMap<String, GenAtributo>> NuevaEspecie(Integer numeroIndividuos, HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie) throws Exception;

}
