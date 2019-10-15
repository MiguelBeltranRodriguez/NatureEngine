package NatureEngine.NatureEngineGenoma.AlelesAndComparison;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.Alelo;

public abstract class AlelesAndComparisonSupport {

	protected Float GradoDeSimilitudEntreDosIndividuosInner(List<Alelo> alelosIndividuoUno,List<Alelo> alelosIndividuoDos){
		Float suma=0f;
		Integer numeroalelos = alelosIndividuoUno.size();
		for (int index = 0; index <numeroalelos ; index++) {
			String nameIdUno = alelosIndividuoUno.get(index).getnameId();
			String nameIdDos = alelosIndividuoDos.get(index).getnameId();
			suma = suma + CalcularSimilitudEntreAlelos(nameIdUno,nameIdDos);
		}
		Float gradodesimilitud = suma/numeroalelos;
		return gradodesimilitud;
	}
	
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
	
	private Float CalcularSimilitudEntreAlelos(String nameIdUno,String nameIdDos){
		//Comparar posiciones en el arbol entre ambos alelosString
		Float gradosimilitud = null;
		return gradosimilitud;
	}
	

}
