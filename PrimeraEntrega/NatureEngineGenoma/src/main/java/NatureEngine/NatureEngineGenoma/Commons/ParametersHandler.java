package NatureEngine.NatureEngineGenoma.Commons;

import java.util.HashMap;

import NatureEngine.NatureEngineGenoma.Integration.IntegrationHandler;

public class ParametersHandler extends ParametersRepository {

	// constructor privado para evitar ser llamado sin singleton
	private ParametersHandler() {
	}

	// Colaborador de singleton usando el metodo de Bill Pugh para evitar problemas
	// de sincronización
	private static class ColaboradorDeSingleton {
		private static final ParametersHandler instancia = new ParametersHandler();
	}

	// Metodo estatico a llamar para usar la clase singleton
	public static ParametersHandler Singleton() {
		return ColaboradorDeSingleton.instancia;
	}

	final public void ModificarParametros(Object usuario, HashMap<String, Object> parameters) {
		if (VerificarSeguridad(usuario)) {
			setParameters(parameters);
		}
	}
	
	final static public Object ObtenerParametro(String NombreDelParametro) {
			return getParameter(NombreDelParametro);
	}

	final private Boolean VerificarSeguridad(Object usuario) {
		return IntegrationHandler.NatureEngineSecurity(usuario);
	}

}
