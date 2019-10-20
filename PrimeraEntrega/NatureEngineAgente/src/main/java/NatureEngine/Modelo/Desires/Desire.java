package NatureEngine.Modelo.Desires;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Stack;

import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.NatureEngineAgente.Agente;

public abstract class Desire implements Serializable {
	protected int prioridad;
	protected Agente agente;
	protected Stack<Intention> intenciones;
	public abstract boolean tengoHabilidad();
	public abstract void init(Desire desireAnterior);
	public Stack<Intention> getIntenciones() {
		return intenciones;
	}
	public void setIntenciones(Stack<Intention> intenciones) {
		this.intenciones = intenciones;
	}
	public abstract void ejecutar() throws RemoteException;
}
