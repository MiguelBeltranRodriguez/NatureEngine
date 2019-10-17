package NatureEngine.Modelo;

import java.rmi.RemoteException;

import NatureEngine.NatureEngineAgente.Agente;

public abstract class Intention {
	protected Agente agente;
	protected boolean completado;
	public abstract void ejecutar() throws RemoteException;

	public boolean isCompletado() {
		return completado;
	}
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	
}
