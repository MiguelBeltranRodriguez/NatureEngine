package NatureEngine.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import NatureEngine.Mensajeria.Mensaje;
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

	public List<ObjetoDistribuido> percibir(Long idAgente, int x, int y) throws RemoteException;

	public void actualizarAgente(ObjetoDistribuido agente) throws RemoteException;
	
	public ObjetoDistribuido getCasilla(int x, int y) throws RemoteException;
	
	public void morir(ObjetoDistribuido agente) throws RemoteException;
	
	public float consumirPlata(ObjetoDistribuido agente, ObjetoDistribuido planta) throws RemoteException;

	public Mensaje enviarMensaje(Mensaje mensaje) throws RemoteException;

}
