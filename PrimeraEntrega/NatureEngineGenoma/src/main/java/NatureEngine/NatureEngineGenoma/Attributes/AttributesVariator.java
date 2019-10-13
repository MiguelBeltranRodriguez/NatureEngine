package NatureEngine.NatureEngineGenoma.Attributes;

import java.util.HashMap;

abstract class AttributesVariator extends AttributesTranscriptor {

	protected static Object VariarAtributoInner(HashMap<String,Object> tipoYLimitesDelAtributo, Object valorBaseAtributo) throws Exception{		
		String tipo = (String)tipoYLimitesDelAtributo.get("tipo");
		Object minimo = (Object)tipoYLimitesDelAtributo.get("minimo");
		Object maximo = (Object)tipoYLimitesDelAtributo.get("maximo");
		Object mediana = (Object)tipoYLimitesDelAtributo.get("mediana");
		Object nuevoValorAtributo=null;
		if(tipo=="Float") {
			nuevoValorAtributo = CalcularAtributoFloat((Float)minimo,(Float)maximo,(Float)mediana);
		}
		if(tipo=="Integer"){
			nuevoValorAtributo = CalcularAtributoInteger((Integer)minimo,(Integer)maximo,(Integer)mediana);
		}
		if(tipo=="Boolean"){
			nuevoValorAtributo = CalcularAtributoBoolean((Boolean)minimo,(Boolean)maximo,(Boolean)mediana);
		}
		if(nuevoValorAtributo==null){
			throw new Exception("Tipo de variable desconocido: "+tipo);	
		}
		return (Object)nuevoValorAtributo;
	}
	
	private static Object CalcularAtributoFloat(Float minimo,Float maximo,Float mediana){
		Object nuevoValorAtributo= null;
		return nuevoValorAtributo;
	}

	private static Object CalcularAtributoInteger(Integer minimo,Integer maximo,Integer mediana){
		Object nuevoValorAtributo= null;
		return nuevoValorAtributo;
	}
	
	private static Object CalcularAtributoBoolean(Boolean minimo,Boolean maximo,Boolean mediana){
		Object nuevoValorAtributo= null;
		return nuevoValorAtributo;
	}

}
