package NatureEngine.NatureEngineCommons;

import java.io.Serializable;
import java.rmi.RemoteException;


public abstract class ObjetoDistribuido implements Serializable{




	private static final long serialVersionUID = 1L;
	protected Long ID;


	public ObjetoDistribuido(Long ID) throws RemoteException {
		super();
		this.ID = ID;
	}


}
