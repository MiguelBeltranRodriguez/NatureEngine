package NatureEngine.NatureEngineAgente;

public class AtributosEnergia {
	private int coheficiente;
	private float atributo;
	private String tipoOperacion;

	public AtributosEnergia(int coheficiente, float atributo, String tipoOperacion) {
		this.coheficiente = coheficiente;
		this.atributo = atributo;
		this.tipoOperacion = tipoOperacion;
	}
	
	public float calcular() {
		float resultado = 0;
		if (tipoOperacion.equals("exponente")) {
			resultado = calcularExponente();
		} else if (tipoOperacion.equals("inverso")) {
			resultado = calcularInverso();
		}else if (tipoOperacion.equals("multiplicacion")) {
			resultado = calcularMultiplicacion();
		} else if (tipoOperacion.equals("division")) {
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
