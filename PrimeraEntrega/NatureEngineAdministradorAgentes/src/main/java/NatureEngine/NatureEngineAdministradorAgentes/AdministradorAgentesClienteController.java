package NatureEngine.NatureEngineAdministradorAgentes;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import NatureEngine.RMI.ServiciosController;

public class AdministradorAgentesClienteController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServiciosController serviciosController;
	public AdministradorAgentesClienteController() {
		try {
			serviciosController = (ServiciosController) Naming.lookup("rmi://localhost:6005/controller");
			 System.out.println("Cliente de controller ON 6005");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			AdministradorAgentesServidorController aasc = new AdministradorAgentesServidorController(serviciosController);
			LocateRegistry.createRegistry(6006);
			Naming.rebind("rmi://localhost:6006/controller", aasc);
			System.out.println("Servidor agentes ON 6006");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			serviciosController.conectarAlServidorAgentes(6006);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		new AdministradorAgentesClienteController();
	}
	public static ServiciosController getLook_up() {
		return serviciosController;
	}
	public static void setLook_up(ServiciosController look_up) {
		AdministradorAgentesClienteController.serviciosController = look_up;
	}
	
	
	
	
}
