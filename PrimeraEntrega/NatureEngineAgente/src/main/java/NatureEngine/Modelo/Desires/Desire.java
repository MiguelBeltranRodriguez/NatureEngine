package NatureEngine.Modelo.Desires;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Stack;

import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineGUI.Dibujable;

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
	public void ejecutar() throws RemoteException {
		if(!intenciones.empty()) {
			Intention intencion = this.intenciones.firstElement();
			if (!intencion.isFinalizado()) {
				intencion.ejecutar();
			} else {
				this.intenciones.pop();
			}
		}
		
	}
	public static double calcularDistancia(Dibujable objetivo, Dibujable agente) {
		int agenteX = agente.getX();
		int agenteY = agente.getY();
		int objetivoX = objetivo.getX();
		int objetivoY = objetivo.getY();

		double deltaX = Math.abs(agenteX - objetivoX);
		double deltaY = Math.abs(agenteY - objetivoY);
		double areasTotales = Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0);
 
		return Math.sqrt(areasTotales);
	}
}
