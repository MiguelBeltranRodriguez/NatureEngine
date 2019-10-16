package NatureEngine.Modelo.Intentions;

import java.rmi.RemoteException;

import NatureEngine.NatureEngineAgente.Agente;

public abstract class Intention {
	protected Agente agente;
	protected boolean finalizado;
	public abstract void ejecutar() throws RemoteException;
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
}
