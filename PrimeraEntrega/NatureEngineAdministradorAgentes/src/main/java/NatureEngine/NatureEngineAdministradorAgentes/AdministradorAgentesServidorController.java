package NatureEngine.NatureEngineAdministradorAgentes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import NatureEngine.RMI.ServiciosAdministradorAgentes;

public class AdministradorAgentesServidorController extends UnicastRemoteObject implements ServiciosAdministradorAgentes {

	protected AdministradorAgentesServidorController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
