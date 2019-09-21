package NatureEngine.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiciosController extends Remote {
	public void conectarAlServidorAgentes(int port) throws RemoteException;
}
