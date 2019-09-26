package NatureEngine.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;


import NatureEngine.NatureEngineCommons.ObjetoDistribuido;

/**
 * Interfaz remota que provee servicios dados por el componente NatureEngineController.
 */
public interface ServiciosController extends Remote{
	
	/**
	 * Informa al NatureEngineController que se ha instanciado un contenedor NatureEngineAdministradorAgentes, para que este se conecte a los servicios del contenedor.
	 *
	 * @param port El puerto del contenedor
	 * @throws RemoteException Exepción remota
	 */
	public void conectarAlServidorAgentes(int port) throws RemoteException;

	/**
	 * Función remota para solicitar el movimiento de un agente.
	 *
	 * @param newX La nueva posición en x
	 * @param newY La nueva posición en y
	 * @param agente El agente a mover
	 * @throws RemoteException Exepción remota
	 */
	public void moverAgente(int newX, int newY, ObjetoDistribuido agente) throws RemoteException;

	/**
	 * Pregunta si la celda esta vacia.
	 *
	 * @param x el x de la celda
	 * @param y el y de la celda
	 * @return true, si la celda es vacia
	 * @throws RemoteException Exepción remota
	 */
	public boolean esCeldaVacia(int x, int y) throws RemoteException;

	

	

}
