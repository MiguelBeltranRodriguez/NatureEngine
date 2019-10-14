package NatureEngine.Utils;

public class ActualizacionAtributosDependientes {
	public static float actualizarVelocidad(int numeroCasillas, int milisegundos) {
		return numeroCasillas / milisegundos;
	}
	
	public static float actualizarEnergia(float energiaActual, float atributo, int coheficiente) {
		return energiaActual - (float) (Math.pow((double) atributo, (double) coheficiente));
	}
	
	public static float actualizarEnergiaGrupo(float energiaActual) {
		return 0;
	}
	
	public static float actualizarAgua(float aguaActual, float HumedadCasilla, float HumedadIdeal, float toleranciaHumedad, float velocidadActual, int z) {
		int potenciaHumedad = 3;
		float factorhumedad = (float) Math.pow(HumedadCasilla - HumedadIdeal, potenciaHumedad);
		float factorVelocidad = (float) Math.pow((double) velocidadActual,(double) z);
		float toleranciaHumedadInverso = 1 / toleranciaHumedad;
		return aguaActual - (factorhumedad * toleranciaHumedadInverso) + factorVelocidad;
	}
}
