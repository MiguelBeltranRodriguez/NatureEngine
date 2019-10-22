package NatureEngine.Modelo.Intentions;

import java.rmi.RemoteException;

import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;

public class TenerHijo extends Intention{
	private ObjetoDistribuido hembra;
	
	public TenerHijo(Agente agente, Agente hembra) {
		super();
		this.agente = agente;
		this.hembra = hembra;
	}
	@Override
	public void ejecutar() throws RemoteException {
		this.agente.reproducirse(hembra);
		this.agente.setTiempoDescansoReproduccion(1000);
		this.finalizado = true;
	}

}
