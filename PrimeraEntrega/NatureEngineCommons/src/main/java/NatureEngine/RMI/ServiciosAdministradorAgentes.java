package NatureEngine.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import NatureEngine.NatureEngineCommons.ObjetoDistribuido;

public interface ServiciosAdministradorAgentes extends Remote {

	void agregarAgente(ObjetoDistribuido agente) throws RemoteException;

	void  update()throws RemoteException;

}
