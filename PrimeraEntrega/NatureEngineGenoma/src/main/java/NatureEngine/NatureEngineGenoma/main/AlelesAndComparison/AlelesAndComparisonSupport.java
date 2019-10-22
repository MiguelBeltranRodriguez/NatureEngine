package NatureEngine.NatureEngineGenoma.main.AlelesAndComparison;

import java.util.Random;

import NatureEngine.Modelo.Alelo;

public abstract class AlelesAndComparisonSupport {

	
	protected Alelo CrearNuevoAleloInner(Integer dominancia, Object valorAtributo, String alelopadreNameId){
		String nameId = GenerarIdAlelo();
		Alelo alelonuevo = new Alelo(dominancia,valorAtributo,nameId);
		if(alelopadreNameId==null) {
			alelopadreNameId="root";
		}
		GuardarNuevoAleloEnElArbol(alelopadreNameId,nameId);
		return alelonuevo;
	}
	
	private String GenerarIdAlelo(){
		Random r = new Random();
		char buf[] = new char[10];
		for (int i=0;i<buf.length;i++) {
		  int index = r.nextInt(subset.length);
		  buf[i] = subset[index];
		}

		return new String(buf);
	}
	
	private static final char [] subset = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	private void GuardarNuevoAleloEnElArbol(String alelopadreNameId,String nameId) {
		//Guardar posisciones en el amros
	}
	
	//private Float CalcularSimilitudEntreAlelos(String nameIdUno,String nameIdDos){
	//	//Comparar posiciones en el arbol entre ambos alelosString
	//	Float gradosimilitud = null;
	//	return gradosimilitud;
	//}
	

}
