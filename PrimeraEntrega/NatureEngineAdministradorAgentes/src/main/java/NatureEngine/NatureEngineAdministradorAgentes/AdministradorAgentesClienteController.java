package NatureEngine.NatureEngineAdministradorAgentes;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import NatureEngine.RMI.ServiciosAdministradorAgentes;
import NatureEngine.RMI.ServiciosController;

public class AdministradorAgentesClienteController implements ServiciosAdministradorAgentes {
	
	private static ServiciosController ServiciosController;
	public AdministradorAgentesClienteController() {
		try {
			ServiciosController = (ServiciosController) Naming.lookup("rmi://localhost:6005/controller");
			System.out.println("Conectado a servidor");
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
	}
	public static void main(String args[]) {
		new AdministradorAgentesClienteController();
	}
	public static ServiciosController getLook_up() {
		return ServiciosController;
	}
	public static void setLook_up(ServiciosController look_up) {
		AdministradorAgentesClienteController.ServiciosController = look_up;
	}
	
	
	
}
