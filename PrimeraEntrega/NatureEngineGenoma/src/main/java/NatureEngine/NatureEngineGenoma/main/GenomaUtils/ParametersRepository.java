package NatureEngine.NatureEngineGenoma.main.GenomaUtils;

import java.util.HashMap;

public abstract class ParametersRepository {

	private static HashMap<String, Object> parameters= new HashMap<String, Object>();
	
	private static Float TasaDeMutacion=0.05f;
	private static Float ProbabilidadMutarDominancia=0.2f;
	private static Float MultiplicadorDeVariacionPorMutacion=1f;
	private static Integer GradosDeDominancia=4;
	private static Integer MinimoAlelosPorAtributo=1;
	private static Integer MaximoAlelosPorAtributo=3;
	
	
	public static Float getTasaDeMutacion() {
		return TasaDeMutacion;
	}
	public static void setTasaDeMutacion(Float tasaDeMutacion) {
		TasaDeMutacion = tasaDeMutacion;
	}
	public static Float getProbabilidadMutarDominancia() {
		return ProbabilidadMutarDominancia;
	}
	public static void setProbabilidadMutarDominancia(Float probabilidadMutarDominancia) {
		ProbabilidadMutarDominancia = probabilidadMutarDominancia;
	}
	public static Float getMultiplicadorDeVariacionPorMutacion() {
		return MultiplicadorDeVariacionPorMutacion;
	}
	public static void setMultiplicadorDeVariacionPorMutacion(Float multiplicadorDeVariacionPorMutacion) {
		MultiplicadorDeVariacionPorMutacion = multiplicadorDeVariacionPorMutacion;
	}
	public static Integer getMinimoAlelosPorAtributo() {
		return MinimoAlelosPorAtributo;
	}
	public static void setMinimoAlelosPorAtributo(Integer minimoAlelosPorAtributo) {
		MinimoAlelosPorAtributo = minimoAlelosPorAtributo;
	}
	public static Integer getMaximoAlelosPorAtributo() {
		return MaximoAlelosPorAtributo;
	}
	public static void setMaximoAlelosPorAtributo(Integer maximoAlelosPorAtributo) {
		MaximoAlelosPorAtributo = maximoAlelosPorAtributo;
	}
	public static Integer getGradosDeDominancia() {
		return GradosDeDominancia;
	}
	public static void setGradosDeDominancia(Integer gradosDeDominancia) {
		GradosDeDominancia = gradosDeDominancia;
	}
	



	
}
