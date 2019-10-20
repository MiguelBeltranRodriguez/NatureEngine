package NatureEngine.Modelo.Intentions;

import java.io.Serializable;
import java.rmi.RemoteException;

import NatureEngine.NatureEngineAgente.Agente;

public abstract class Intention implements Serializable {
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
