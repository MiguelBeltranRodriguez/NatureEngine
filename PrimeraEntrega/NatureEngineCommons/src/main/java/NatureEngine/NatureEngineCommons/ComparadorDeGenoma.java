package NatureEngine.NatureEngineCommons;

import java.util.HashMap;
import NatureEngine.Modelo.GenAtributo;

public class ComparadorDeGenoma {
	
	public Boolean CompararGenoma(HashMap<String, GenAtributo> genomaHembra, HashMap<String, GenAtributo> genomaMacho){
	
		for (HashMap.Entry<String, GenAtributo> entry : genomaHembra.entrySet()) {
			String nombreAtributo = entry.getKey();
			GenAtributo genatributoHembra = entry.getValue();
			if(nombreAtributo=="Sexo") {
				continue;
			}
			GenAtributo genatributoMacho = genomaMacho.get(nombreAtributo);
			String tipoAtributo = genatributoMacho.getTipoCaracteristica();
			Object prevariabilidad = genatributoMacho.getVariabilidad();
			Float variabilidad = Float.parseFloat(prevariabilidad.toString());
			Boolean comparacionDeAtributos = null;
			try {
				comparacionDeAtributos = CompararAtributos(tipoAtributo, variabilidad, genatributoHembra,genatributoMacho);	
			}catch(Exception ex) {
				comparacionDeAtributos=false;
			}
			if(comparacionDeAtributos==false) {
				return false;
			}
		}
		return true;
	}
	
	private Boolean CompararAtributos(String tipo,Float variabilidad,GenAtributo genHembra, GenAtributo genMacho) throws Exception{
		Object objFenotipoHembra = genHembra.getFenotipo();
		Object objFenotipoMacho = genMacho.getFenotipo();
		Float valorBaseAtributoHembra = null;
		Float valorBaseAtributoMacho = null;
		Boolean Comparacion = false;
		if (tipo.equals("java.lang.Boolean")) {
			return true;
		} else {
			if (tipo.equals("java.lang.Integer") || tipo.equals("java.lang.Float")) {
				if (tipo.equals("java.lang.Integer")) {
					Integer prevalorBaseAtributoHembra = (Integer) objFenotipoHembra;
					Integer prevalorBaseAtributoMacho = (Integer) objFenotipoHembra;
					valorBaseAtributoHembra = (float) prevalorBaseAtributoHembra;
					valorBaseAtributoMacho = (float) prevalorBaseAtributoMacho;
				}
				if (tipo.equals("java.lang.Float")) {
					valorBaseAtributoHembra = (float) objFenotipoMacho;
					valorBaseAtributoMacho = (float) objFenotipoMacho;
				}
				Float diferencia = Math.abs(valorBaseAtributoHembra-valorBaseAtributoMacho);
				if(diferencia>variabilidad) {
					Comparacion = true;
				}else {
					Comparacion = false;
				}
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}
		}
		return Comparacion;
		
	}

}
