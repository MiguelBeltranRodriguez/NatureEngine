package NatureEngine.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import NatureEngine.Mensajeria.Mensaje;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;

/**
 * Interfaz remota que provee servicios dados por el componente NatureEngineAdministradorAgentes.
 */
public interface ServiciosAdministradorAgentes extends Remote {

	/**
	 * Agrega un agente en un contenedor de NatureEngineAdministradorAgentes.
	 *
	 * @param agente el agente a agregar
	 * @throws RemoteException Excepción remota
	 */
	public void agregarAgente(ObjetoDistribuido agente) throws RemoteException;

	/**
	 * Actualizar todos los componentes del contenedor NatureEngineAdministradorAgentes, en este permitir que los agentes perciban, piensen y actuen
	 *
	 * @throws RemoteException Excepción remota
	 */
	public void  update()throws RemoteException;

	public String getID() throws RemoteException;

	public Mensaje enviarMensaje(Mensaje mensaje) throws RemoteException;

	public void morir(Long id)  throws RemoteException;
	
}
