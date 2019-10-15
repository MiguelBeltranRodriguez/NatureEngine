package NatureEngine.Modelo;



import java.io.Serializable;


/**
 * Representa una caracteristica heredable específica de un agente
 * 
 * @author Andrea Gutierrez
 *
 *         Fecha Autor Descripcion 20191006 Andrea Gutierrez Version inicial
 */

public class CaracteristicaHeredableAgente extends CaracteristicaHeredablePrototype implements Serializable {

	protected Object fenotipo;
	private GenAtributo genatributo;

	public CaracteristicaHeredableAgente(String nombreCaracteristica, GenAtributo genatributo) {
		this.genatributo = genatributo;
		this.nombreCaracteristica = nombreCaracteristica;
		this.id = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getId();
		this.tipoCaracteristica = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica)
				.getTipoCaracteristica();
		this.nombreCaracteristica = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica)
				.getNombreCaracteristica();
		this.valorMaximo = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getValorMaximo();
		this.valorMinimo = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getValorMinimo();
		this.fenotipo = genatributo.getValorAtributo();

	}

	public GenAtributo getGenAtributo() {
		return this.genatributo;
	}

	public Object getValorMinimo() {
		return AtributosBasicos.getAtributosBasicosByName().get(this.nombreCaracteristica).getValorMinimo();
	}

	public Object getValorMaximo() {
		return AtributosBasicos.getAtributosBasicosByName().get(this.nombreCaracteristica).getValorMaximo();
	}

	public Object getValorCaracteristica() {
		Object valor = null;


		if (this.fenotipo != null) {

			if(this.fenotipo != null)
			{

				valor = new Object();

				try {
					Class<?> clazz = Class.forName(this.tipoCaracteristica);
					valor = clazz.cast(this.fenotipo);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		}
		return valor;
	}


	public void setValorCaracteristica(Object valorCaracteristica) {
		this.fenotipo = valorCaracteristica;
	}


}
