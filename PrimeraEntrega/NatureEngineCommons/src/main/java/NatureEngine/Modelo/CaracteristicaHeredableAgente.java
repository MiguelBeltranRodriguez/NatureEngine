package NatureEngine.Modelo;

import java.util.Arrays;
import java.util.List;

/**
 * Representa una caracteristica heredable específica de un agente
 * 
 * @author Andrea Gutierrez
 *
 *         Fecha Autor Descripcion 20191006 Andrea Gutierrez Version inicial
 */
public class CaracteristicaHeredableAgente extends CaracteristicaHeredablePrototype {

	protected Object valorCaracteristica;
	private List<Alelo> alelos;

	public CaracteristicaHeredableAgente(String nombreCaracteristica, Object valorCaracteristica, List<Alelo> alelos) {
		this.alelos = alelos;
		this.nombreCaracteristica = nombreCaracteristica;
		this.id = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getId();
		this.tipoCaracteristica = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica)
				.getTipoCaracteristica();
		this.nombreCaracteristica = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica)
				.getNombreCaracteristica();
		this.valorMaximo = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getValorMaximo();
		this.valorMinimo = AtributosBasicos.getAtributosBasicosByName().get(nombreCaracteristica).getValorMinimo();
		this.valorCaracteristica = valorCaracteristica;
	}

	public List<Alelo> getAlelos() {
		return this.alelos;
	}

	public Object getValorMinimo() {
		return AtributosBasicos.getAtributosBasicosByName().get(this.nombreCaracteristica).getValorMinimo();
	}

	public Object getValorMaximo() {
		return AtributosBasicos.getAtributosBasicosByName().get(this.nombreCaracteristica).getValorMaximo();
	}

	public Object getValorCaracteristica() {
		Object valor = null;

		if (this.valorCaracteristica != null) {
			valor = new Object();

			try {
				Class<?> clazz = Class.forName(this.tipoCaracteristica);
				valor = clazz.cast(this.valorCaracteristica);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return valor;
	}

}
