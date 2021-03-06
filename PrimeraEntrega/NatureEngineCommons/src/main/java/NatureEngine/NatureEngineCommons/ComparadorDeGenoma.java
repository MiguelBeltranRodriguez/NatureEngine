package NatureEngine.NatureEngineCommons;

import java.text.DecimalFormat;
import java.util.HashMap;
import NatureEngine.Modelo.GenAtributo;

public class ComparadorDeGenoma {
	
	static public Boolean SonMismaEspecieComparandoGenoma(HashMap<String, GenAtributo> genomaHembra, HashMap<String, GenAtributo> genomaMacho) throws Exception{
		DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
		for (HashMap.Entry<String, GenAtributo> entry : genomaHembra.entrySet()) {
			String nombreAtributo = entry.getKey();
			GenAtributo genatributoHembra = entry.getValue();
			if(nombreAtributo.equals("Sexo")) {
				continue;
			}
			GenAtributo genatributoMacho = genomaMacho.get(nombreAtributo);
			String tipoAtributo = genatributoMacho.getTipoCaracteristica();
			Object prevariabilidad = genatributoMacho.getVariabilidad();
			if(prevariabilidad==null) {
				throw new Exception("Variabilidad de "+nombreAtributo+" es null");
			}
			Float variabilidad = null;
			if(prevariabilidad instanceof Integer) {
				Integer tmpvariabilidad =(Integer)prevariabilidad;
				variabilidad =(float)tmpvariabilidad;
			}
			if(prevariabilidad instanceof Float) {
				variabilidad =(float)prevariabilidad;
			}
			
			Boolean comparacionDeAtributos = null;
			try {
				comparacionDeAtributos = TienenMismoAtributoComparandoGenoma(nombreAtributo,tipoAtributo, variabilidad, genatributoHembra,genatributoMacho,df);	
			}catch(Exception ex) {
				comparacionDeAtributos=false;
			}
			if(comparacionDeAtributos==false) {
				return false;
			}
		}
		return true;
	}
	
	static private Boolean TienenMismoAtributoComparandoGenoma(String nombreAtributo, String tipo,Float variabilidad,GenAtributo genHembra, GenAtributo genMacho,DecimalFormat df) throws Exception{
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
					Integer prevalorBaseAtributoMacho = (Integer) objFenotipoMacho;
					valorBaseAtributoHembra = (float) prevalorBaseAtributoHembra;
					valorBaseAtributoMacho = (float) prevalorBaseAtributoMacho;
				}
				if (tipo.equals("java.lang.Float")) {
					valorBaseAtributoHembra = (float) objFenotipoHembra;
					valorBaseAtributoMacho = (float) objFenotipoMacho;
				}
				Float diferencia = Math.abs(valorBaseAtributoHembra-valorBaseAtributoMacho);
				if(diferencia>(variabilidad*5)) {
					Comparacion = false;
				}else {
					Comparacion = true;
				}
			} else {
				throw new Exception("Tipo de variable desconocido: " + tipo);
			}
		}
		return true;//Comparacion;
		
	}

}
