package NatureEngine.Modelo;

import java.io.Serializable;

/**
 * Representa una caracteristica heredable específica de un agente
 * 
 * @author Andrea Gutierrez
 *
 *         Fecha Autor Descripcion 20191006 Andrea Gutierrez Version inicial
 */

public class GenAtributo extends CaracteristicaHeredablePrototype implements Serializable {

	protected Object fenotipo;
	private Alelo aleloUno;
	private Alelo aleloDos;	
	

	public Object getFenotipo() {
		return fenotipo;
	}
	
	public GenAtributo(String nombreCaracteristica, Object fenotipo, Alelo aleloUno, Alelo aleloDos) throws Exception {
		if(fenotipo==null) {
			throw new Exception(nombreCaracteristica+": no se puede crear un atributo con un valor null");
		}
		this.nombreCaracteristica = nombreCaracteristica;
		LlenarGen(fenotipo, aleloUno, aleloDos);
	}
	
	public void LlenarGen(Object fenotipo, Alelo aleloUno, Alelo aleloDos) {
		this.aleloUno = aleloUno;
		this.aleloDos = aleloDos;
		this.id = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getId();
		this.tipoCaracteristica = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica)
				.getTipoCaracteristica();
		this.nombreCaracteristica = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica)
				.getNombreCaracteristica();
		this.valorMaximo = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getValorMaximo();
		this.valorMinimo = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getValorMinimo();
		this.variabilidad = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getVariabilidad();
		this.fenotipo = fenotipo;
	}

	public Alelo getaleloUno() {
		return aleloUno;
	}

	public Alelo getaleloDos() {
		return aleloDos;
	}

	public Object getValorMinimo() {
		return AtributosBasicos.getAtributosBasicosByName().get(this.nombreCaracteristica).getValorMinimo();
	}

	public Object getValorMaximo() {
		return AtributosBasicos.getAtributosBasicosByName().get(this.nombreCaracteristica).getValorMaximo();
	}

	public Object getValorCaracteristica() {
		
		return fenotipo;
	}

	public void setValorCaracteristica(Object valorCaracteristica) {
		this.fenotipo = valorCaracteristica;
	}

}
