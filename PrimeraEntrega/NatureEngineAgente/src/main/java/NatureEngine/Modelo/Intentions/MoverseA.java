package NatureEngine.Modelo.Intentions;

import java.rmi.RemoteException;

import NatureEngine.Modelo.Planta;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.RMI.ServiciosController;
import NatureEngine.Utils.VarGlobalGame;

public class MoverseA extends Intention {
	private int objetivoX;
	private int objetivoY;
	private int delX;
	private int delY;
	private int agentePosicionX;
	private int agentePosicionY;
	private int direccionX;
	private int direccionY;
	
	public MoverseA(Agente agente, int objetivoX, int objetivoY) {
		super();
		this.agente = agente;
		this.agentePosicionX = this.agente.getX();
		this.agentePosicionY = this.agente.getY();
		this.direccionX = this.agente.getDireccionX();
		this.direccionY = this.agente.getDireccionY();
		this.objetivoX = objetivoX;
		this.objetivoY = objetivoY;
		this.delX = Math.abs(agente.getX() - this.objetivoX);
		this.delY = Math.abs(agente.getY() - this.objetivoY);
		this.finalizado = false;
	}

	@Override
	public void ejecutar() throws RemoteException {
		float moverse = this.agente.getMoverse();
		float velocidadPXs = this.agente.getVelocidadPXs();
		
		if(moverse >= VarGlobalGame.MIU_DE_FRICCION) {
			moverse = moverse - VarGlobalGame.MIU_DE_FRICCION;
			this.agente.setMoverse(moverse);
			this.setVectorDireccion();
			this.actualizarPosicion();
		} else {
			this.agente.setMoverse(moverse + velocidadPXs);
		}

		if(delX == 0 && delY == 0) {
			this.finalizado = true;
		}
	}
	
	public int actualizarVectorDireccion(int agentePosicion, int objetivo, int direccion) {
		if (objetivo > agentePosicion) {
			direccion = 1;
		} else if(objetivo < agentePosicion) {
			direccion = -1;
		} else {
			direccion = 0;
		}
		
		return direccion;
	}
	
	public void setVectorDireccion() {	
		this.direccionX = this.actualizarVectorDireccion(this.agentePosicionX, this.objetivoX, this.direccionX);
		this.direccionY = this.actualizarVectorDireccion(this.agentePosicionY, this.objetivoY, this.direccionY);
		this.agente.setDireccionX(this.direccionX);
		this.agente.setDireccionY(this.direccionY);
	}
	
	public void actualizarPosicion() {	
		if(delX > 0 && delY > 0) {
			this.agente.cambiarPosicion(agentePosicionX+(direccionX), agentePosicionY+(direccionY));
			delX--;
			delY--;
		}else if(delX>0) {
			this.agente.cambiarPosicion(agentePosicionX+(direccionX), agentePosicionY);
			delX--;
		}else if(delY>0) {
			this.agente.cambiarPosicion(agentePosicionX, agentePosicionY+(direccionY));
			delY--;
		}
	}
	
	
}
