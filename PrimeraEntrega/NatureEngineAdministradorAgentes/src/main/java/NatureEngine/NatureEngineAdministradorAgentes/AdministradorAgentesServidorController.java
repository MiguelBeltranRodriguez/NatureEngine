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
	private String port;
	
	protected AdministradorAgentesServidorController(ServiciosController serviciosController, String port) throws RemoteException {
		super();
		this.serviciosController = serviciosController;
		agentes = new HashMap<Long, Agente>();
		this.setPort(port);
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
		ag.init();
	}

	public Map<Long, Agente> getAgentes() {
		return agentes;
	}

	public void setAgentes(Map<Long, Agente> agentes) {
		this.agentes = agentes;
	}

	@Override
	public void update() throws RemoteException {
		
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}
