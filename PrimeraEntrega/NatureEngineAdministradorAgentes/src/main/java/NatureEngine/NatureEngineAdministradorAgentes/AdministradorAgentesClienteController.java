package NatureEngine.NatureEngineAdministradorAgentes;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import NatureEngine.RMI.ServiciosController;
import NatureEngine.Utils.VarGlobalGame;

public class AdministradorAgentesClienteController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServiciosController serviciosController;
	public AdministradorAgentesClienteController(String port) {
		try {
			serviciosController = (ServiciosController) Naming.lookup("rmi://localhost:"+VarGlobalGame.PORT_CONTROLLER+"/controller");
			 System.out.println("Cliente del servidor controller ON "+VarGlobalGame.PORT_CONTROLLER);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			AdministradorAgentesServidorController aasc = new AdministradorAgentesServidorController(serviciosController, port);
			LocateRegistry.createRegistry(Integer.parseInt(port));
			Naming.rebind("rmi://localhost:"+port+"/controller", aasc);
			System.out.println("Servidor de agentes ON "+port);
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			serviciosController.conectarAlServidorAgentes(Integer.parseInt(port));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		new AdministradorAgentesClienteController(args[0]);
	}
	public static ServiciosController getLook_up() {
		return serviciosController;
	}
	public static void setLook_up(ServiciosController look_up) {
		AdministradorAgentesClienteController.serviciosController = look_up;
	}
	
	
	
	
}
