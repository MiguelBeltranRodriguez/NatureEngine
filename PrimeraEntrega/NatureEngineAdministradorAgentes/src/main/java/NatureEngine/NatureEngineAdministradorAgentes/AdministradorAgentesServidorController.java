package NatureEngine.NatureEngineAdministradorAgentes;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.RMI.ServiciosAdministradorAgentes;
import NatureEngine.RMI.ServiciosController;
import NatureEngine.NatureEngineAgente.Agente;

public class AdministradorAgentesServidorController extends UnicastRemoteObject implements ServiciosAdministradorAgentes, Serializable {

	
	private Map<Long, Agente> agentes;
	private ServiciosController serviciosController;
	
	protected AdministradorAgentesServidorController(ServiciosController serviciosController) throws RemoteException {
		super();
		this.serviciosController = serviciosController;
		agentes = new HashMap<Long, Agente>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void agregarAgente(ObjetoDistribuido agente) throws RemoteException {
		Agente ag = (Agente)agente;
		
		agentes.put(ag.getID(), ag);
		ag.setServicios(serviciosController);
	}

	public Map<Long, Agente> getAgentes() {
		return agentes;
	}

	public void setAgentes(Map<Long, Agente> agentes) {
		this.agentes = agentes;
	}

	@Override
	public void update() throws RemoteException {
		agentes.forEach((k, v) -> v.run());
	}
	
}
