package NatureEngine.NatureEngineAgente;

import java.util.List;

public class ActualizacionAtributosDependientes {
	public static float actualizarVelocidadActual(int numeroCasillas, int milisegundos) {
		return numeroCasillas / milisegundos;
	}
	
	public static float actualizarEnergiaGrupo(float energiaActual, List<AtributosEnergia> atributoEnergia) {
		for (AtributosEnergia atributosEnergia : atributoEnergia) {
			energiaActual -= atributosEnergia.calcular();
		}

		return energiaActual;
	}
	
	public static float actualizarAgua(float aguaActual, AtributoAgua atributoAgua) {
		return aguaActual - atributoAgua.calcularFactorHumedad();
	}

	// TODO: Agregar modelo de crecimiento y potencia
}
