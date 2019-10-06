package NatureEngine.Modelo.Intentions;

import java.rmi.RemoteException;

import NatureEngine.Modelo.Intention;
import NatureEngine.Modelo.Planta;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.RMI.ServiciosController;

public class MoverseA extends Intention {
	private int objetivoX;
	private int objetivoY;
	private int delX;
	private int delY;
	
	public MoverseA(Agente agente, int objetivoX, int objetivoY) {
		super();
		this.agente = agente;
		this.objetivoX = objetivoX;
		this.objetivoY = objetivoY;
		this.delX = Math.abs(agente.getX() - this.objetivoX);
		this.delY = Math.abs(agente.getY() - this.objetivoY);
		this.completado = false;
	}

	@Override
	public void ejecutar() throws RemoteException {
		int moverse = this.agente.getMoverse();
		int velocidadPXs = this.agente.getVelocidadPXs();
		
		if(moverse>=100) {
			moverse = moverse-100;
			this.agente.setMoverse(moverse);
			int x = this.agente.getX();
			int y = this.agente.getY();
			int direccionX = this.agente.getDireccionX();
			int direccionY = this.agente.getDireccionY();
			if(objetivoX > x) {
				direccionX = 1;
			}else {
				direccionX = -1;
			}
			if(objetivoY > y) {
				direccionY = 1;
			}else {
				direccionY = -1;
			}
			
			
			int timeOutBloqueo = this.agente.getTimeOutBloqueo();
			ServiciosController servicios = agente.getServicios();
			if(delX>0 && delY >0) {
				if(servicios.esCeldaVacia(x+(direccionX), y+(direccionY))) {
					cambiarPosicion(x+(direccionX), y+(direccionY));
					delX--;
					delY--;
				}else {
					timeOutBloqueo--;
				}if(timeOutBloqueo==0) {
					if(servicios.esCeldaVacia(x-(direccionX), y-(direccionY))) {
						cambiarPosicion(x-(direccionX), y-(direccionY));
					}
					timeOutBloqueo = 4;
				}
			}else if(delX>0) {
				if(servicios.esCeldaVacia(x+(direccionX), y)) {
					cambiarPosicion(x+(direccionX), y);
					delX--;
				}else {
					timeOutBloqueo--;
				}if(timeOutBloqueo==0) {
					if(servicios.esCeldaVacia(x-(direccionX), y)) {
						cambiarPosicion(x-(direccionX), y);
					}
					timeOutBloqueo = 4;
				}
			}else if(delY>0) {
				if(servicios.esCeldaVacia(x, y+(direccionY))) {
					cambiarPosicion(x, y+(direccionY));
					delY--;
				}else {
					timeOutBloqueo--;
				}
				if(timeOutBloqueo==0) {
					if(servicios.esCeldaVacia(x, y-(direccionY))) {
						cambiarPosicion(x, y-(direccionY));
					}
					timeOutBloqueo = 3;
				}
			}
			
		}else {
			int vel = moverse + velocidadPXs;
			this.agente.setMoverse(vel);
		}
		if(delX == 0 && delY == 0) {
			this.completado = true;
		}
		
	}
	private synchronized  void cambiarPosicion(int x2, int y2) {
		try {
			ServiciosController servicios = agente.getServicios();
			servicios.moverAgente(x2, y2, (ObjetoDistribuido)this.agente);
			this.agente.setX(x2);
			this.agente.setY(y2);
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
