package NatureEngine.NatureEngineGenoma;

import java.util.HashMap;
import java.util.List;

import NatureEngine.Modelo.GenAtributo;

public interface Reproducible {
	
	public List<HashMap<String, GenAtributo>> Reproducirse(Integer numerohijos, HashMap<String, GenAtributo> genomaMadre, HashMap<String, GenAtributo> genomaPadre);

}
