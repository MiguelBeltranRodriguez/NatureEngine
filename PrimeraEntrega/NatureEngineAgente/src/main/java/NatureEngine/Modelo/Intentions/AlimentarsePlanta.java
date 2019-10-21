package NatureEngine.Modelo.Intentions;

import java.rmi.RemoteException;import org.omg.CORBA.OBJECT_NOT_EXIST;

import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;

public class AlimentarsePlanta extends Intention {
	private ObjetoDistribuido planta;
	
	public AlimentarsePlanta(Agente agente, Dibujable objetivo) {
		super();
		this.agente = agente;
		this.planta = (ObjetoDistribuido)objetivo;
	}

	@Override
	public void ejecutar() throws RemoteException {
		this.agente.consumirPlanta(planta);
		this.finalizado = true;
	}

}
