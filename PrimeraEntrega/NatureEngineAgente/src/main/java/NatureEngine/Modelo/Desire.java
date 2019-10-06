package NatureEngine.Modelo;

import java.rmi.RemoteException;
import java.util.Stack;

import NatureEngine.NatureEngineAgente.Agente;

public abstract class Desire {
	protected int prioridad;
	protected Agente agente;
	protected Stack<Intention> intenciones;
	public abstract boolean tengoCapacidad();
	public abstract void init(Desire desireAnterior);
	public Stack<Intention> getIntenciones() {
		return intenciones;
	}
	public void setIntenciones(Stack<Intention> intenciones) {
		this.intenciones = intenciones;
	}
	public abstract void ejecutar() throws RemoteException;
}
