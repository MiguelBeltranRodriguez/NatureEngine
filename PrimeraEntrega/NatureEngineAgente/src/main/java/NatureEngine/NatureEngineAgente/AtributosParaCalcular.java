package NatureEngine.NatureEngineAgente;

import NatureEngine.Utils.VarGlobalGame;

public class AtributosParaCalcular {
	private float valor1;
	private float valor2;
	private int tipoOperacion;

	public AtributosParaCalcular(float valor1, float valor2, int tipoOperacion) {
		this.valor1 = valor1;
		this.valor2 = valor2;
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
		return (float) (Math.pow((double) this.valor1, (double) this.valor2));
	}
	
	public float calcularInverso() {
		return (float) 1 / calcularExponente();
	}
	
	public float calcularMultiplicacion() {
		return (float) this.valor1 * this.valor2;
	}
	
	public float calcularDivision() {
		return (float) this.valor1 / this.valor2;
	}
	
	public static float getDeltaHumedad(float humedadCasillaActual, float humedadIdeal) {
		return (float) Math.pow(humedadCasillaActual - humedadIdeal, 3);
	}
	
	public static float getToleranciaHumedadInverso(float toleranciaHumedadIdeal) {
		return 1 / toleranciaHumedadIdeal;
	}

	public float getCoheficiente() {
		return valor2;
	}

	public float getAtributo() {
		return valor1;
	}
}
