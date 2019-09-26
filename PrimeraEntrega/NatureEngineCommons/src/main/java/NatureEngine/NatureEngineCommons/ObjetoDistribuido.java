package NatureEngine.NatureEngineCommons;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Clase abstracta para todos aquellos objetos que van a ser transmitidos por RMI.
 */
public abstract class ObjetoDistribuido implements Serializable{




	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El ID único en todo el sistema */
	protected Long ID;


	/**
	 * Constructor del objeto distribuido
	 *
	 * @param ID el ID
	 * @throws RemoteException Excepción remota
	 */
	public ObjetoDistribuido(Long ID) throws RemoteException {
		super();
		this.ID = ID;
	}


}
