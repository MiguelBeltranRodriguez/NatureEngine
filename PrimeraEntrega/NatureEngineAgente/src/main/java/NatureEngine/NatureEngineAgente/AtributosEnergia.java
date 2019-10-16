package NatureEngine.NatureEngineAgente;

import NatureEngine.Utils.VarGlobalGame;

public class AtributosEnergia {
	private int coheficiente;
	private float atributo;
	private int tipoOperacion;

	public AtributosEnergia(float atributo, int coheficiente, int tipoOperacion) {
		this.coheficiente = coheficiente;
		this.atributo = atributo;
		this.tipoOperacion = tipoOperacion;
	}
	
	public float calcular() {
		float resultado = 0;
		if (tipoOperacion == VarGlobalGame.EXPONENTE) {
			resultado = calcularExponente();
		} else if (tipoOperacion == VarGlobalGame.INVERSO) {
			resultado = calcularInverso();
		}else if (tipoOperacion == VarGlobalGame.MULTIPLICACION) {
			resultado = calcularMultiplicacion();
		} else if (tipoOperacion == VarGlobalGame.DIVISION) {
			resultado = calcularDivision();
		}
		
		return resultado;
	}
	
	
	public float calcularExponente() {
		return (float) (Math.pow((double) this.atributo, (double) this.coheficiente));
	}
	
	public float calcularInverso() {
		return (float) 1 / calcularExponente();
	}
	
	public float calcularMultiplicacion() {
		return (float) this.atributo * this.coheficiente;
	}
	
	public float calcularDivision() {
		return (float) this.atributo / this.coheficiente;
	}

	public int getCoheficiente() {
		return coheficiente;
	}

	public float getAtributo() {
		return atributo;
	}
}
