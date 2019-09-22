package NatureEngine.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;


import NatureEngine.NatureEngineCommons.ObjetoDistribuido;

public interface ServiciosController extends Remote{
	public void conectarAlServidorAgentes(int port) throws RemoteException;

	public boolean moverAgente(int x2, int y2, ObjetoDistribuido agente) throws RemoteException;

	public boolean celdaVacia(int i, int j) throws RemoteException;

}
