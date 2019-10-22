package NatureEngine.Modelo.Desires;

import java.rmi.RemoteException;
import java.util.Stack;

import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.NatureEngineAgente.Agente;

public class DesireReproducirmeHembra extends Desire {

	public DesireReproducirmeHembra(Agente agente) {
		this.agente = agente;
		this.intenciones = new Stack<Intention>();
	}

	@Override
	public boolean tengoCapacidad() {
		if(this.agente.getEdadActual()>=(int)this.agente.getCaracteristicaHeredable(AtributosBasicos.MADUREZ_REPRODUCTIVA)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void init(Desire desireAnterior) {
		
	}
}
