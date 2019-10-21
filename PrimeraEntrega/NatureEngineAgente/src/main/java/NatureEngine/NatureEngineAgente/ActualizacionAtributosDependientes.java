package NatureEngine.NatureEngineAgente;

import java.util.List;

public class ActualizacionAtributosDependientes {
	public static float actualizarVelocidadActual(int numeroCasillas, int milisegundos) {
		return numeroCasillas / milisegundos;
	}
	
	public static float actualizarEnergiaGrupo(float energiaActual, List<AtributosParaCalcular> atributoEnergia) {
		for (AtributosParaCalcular atributosEnergia : atributoEnergia) {
			energiaActual -= atributosEnergia.calcular();
		}

		return energiaActual;
	}

	// TODO: Agregar modelo de crecimiento y potencia
}
