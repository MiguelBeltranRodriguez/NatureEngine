package NatureEngine.NatureEngineAgente;

import NatureEngine.Utils.VarGlobalGame;

public class AtributoAgua {
	private float HumedadCasilla;
	private float HumedadIdeal;
	private float toleranciaHumedad;
	private float velocidadActual;

	public AtributoAgua(float humedadCasilla, float humedadIdeal, float toleranciaHumedad, float velocidadActual) {
		this.HumedadCasilla = humedadCasilla;
		this.HumedadIdeal = humedadIdeal;
		this.toleranciaHumedad = toleranciaHumedad;
		this.velocidadActual = velocidadActual;
	}

	public float calcularFactorHumedad() {
		float factorhumedad = (float) Math.pow((double) (this.HumedadCasilla - this.HumedadIdeal), (double) VarGlobalGame.POTENCIA_HUMEDAD);
		float factorVelocidad = (float) Math.pow((double) this.velocidadActual,(double) VarGlobalGame.COHEFICIENTE_VELOCIDAD);
		float toleranciaHumedadInverso = 1 / this.toleranciaHumedad;

		return (factorhumedad * toleranciaHumedadInverso) + factorVelocidad;
	}
}
