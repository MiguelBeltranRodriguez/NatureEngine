package NatureEngine.Mensajeria;

import java.rmi.RemoteException;

import NatureEngine.NatureEngineCommons.ObjetoDistribuido;

public class Mensaje extends ObjetoDistribuido{
	private int prioridad;
	private String tipo;
	private ObjetoDistribuido emisor;
	private ObjetoDistribuido receptor;
	public Mensaje(Long ID, int prioridad, String tipo, ObjetoDistribuido emisor,  ObjetoDistribuido receptor) throws RemoteException {
		super(ID);
		this.prioridad = prioridad;
		this.tipo = tipo;
		this.emisor = emisor;
		this.receptor = receptor;
	}
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ObjetoDistribuido getEmisor() {
		return emisor;
	}
	public void setEmisor(ObjetoDistribuido emisor) {
		this.emisor = emisor;
	}
	public ObjetoDistribuido getReceptor() {
		return receptor;
	}
	public void setReceptor(ObjetoDistribuido receptor) {
		this.receptor = receptor;
	}

	
}
