package NatureEngine.NatureEngineAgente;

import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Random;

import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.RMI.ServiciosController;
import NatureEngine.Utils.VarGlobalVista;

public class Agente extends ObjetoDistribuido implements Dibujable, Runnable, Serializable  {

	private static final long serialVersionUID = 1L;
	private Color color;  //prueba
	private int x;	//prueba
	private int y; //prueba
	private int radio;  //prueba
	private int percepcion;
	private ServiciosController servicios;
	private boolean resaltado;
	private int delX;
	private int delY;
	private int direccionX;
	private int direccionY;
	private int velocidadPXs;
	private int timeOutBloqueo;
	private int moverse;
	public Agente(Long ID, Color color, int x, int y, int radio, int percepcion, int velocidad,ServiciosController servicios) throws RemoteException {
		super(ID);
		this.color = color;
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.percepcion = percepcion+radio;
		this.servicios = servicios;
		this.direccionX = 0;
		this.direccionY = 0;
		this.delX = 0;
		this.delY = 0;
		resaltado = false;
		this.velocidadPXs = velocidad;
		this.timeOutBloqueo = 3;
		moverse = 0;
	}



	@Override
	public void run() {


		if(delX == 0 && delY == 0) {
			movimientoAleatorio();
		}
		else {
			if(moverse>=100) {
				try {
					moverse();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				moverse = moverse-100;
			}else {
				moverse += velocidadPXs;
			}

		}


	}

	private void moverse() throws RemoteException {
		if(delX>0 && delY >0) {
			if(servicios.celdaVacia(x+(direccionX), y+(direccionY))) {
				cambiarPosicion(x+(direccionX), y+(direccionY));
				delX--;
				delY--;
			}else {
				timeOutBloqueo--;
			}if(timeOutBloqueo==0) {
				if(servicios.celdaVacia(x-(direccionX), y-(direccionY))) {
					cambiarPosicion(x-(direccionX), y-(direccionY));
				}
				timeOutBloqueo = 4;
			}
		}else if(delX>0) {
			if(servicios.celdaVacia(x+(direccionX), y)) {
				cambiarPosicion(x+(direccionX), y);
				delX--;
			}else {
				timeOutBloqueo--;
			}if(timeOutBloqueo==0) {
				if(servicios.celdaVacia(x-(direccionX), y)) {
					cambiarPosicion(x-(direccionX), y);
				}
				timeOutBloqueo = 4;
			}
		}else if(delY>0) {
			if(servicios.celdaVacia(x, y+(direccionY))) {
				cambiarPosicion(x, y+(direccionY));
				delY--;
			}else {
				timeOutBloqueo--;
			}
			if(timeOutBloqueo==0) {
				if(servicios.celdaVacia(x, y-(direccionY))) {
					cambiarPosicion(x, y-(direccionY));
				}
				timeOutBloqueo = 3;
			}
		}	
	}
	private void movimientoAleatorio() {
		Random rnd = new Random();
		int newX = 0;
		do {
			newX =  x + rnd.nextInt(percepcion + 1 +percepcion) - percepcion;
			if(newX>=VarGlobalVista.WIDHT_PANTALLA_GAME || newX <0) {
				newX =  x + rnd.nextInt(percepcion + 1 + percepcion) - percepcion;
			}else {
				break;
			}
		}while(true);
		int newY = 0;
		do {
			newY =  y + rnd.nextInt(percepcion + 1 +percepcion) - percepcion;
			if(newY>=VarGlobalVista.HEIGTH_PANTALLA_GAME || newY<0) {
				newY =  y +  rnd.nextInt(percepcion + 1 +percepcion) - percepcion;
			}else {
				break;
			}
		}while(true);
		decisionIrA(newX, newY);
	}
	private void decisionIrA(int newX, int newY) {
		delX = Math.abs(newX-x);
		delY = Math.abs(newY-y);
		if(newX > x) {
			direccionX = 1;
		}else {
			direccionX = -1;
		}
		if(newY > y) {
			direccionY = 1;
		}else {
			direccionY = -1;
		}
	}
	private void cambiarPosicion(int x2, int y2) {
		try {
			
			boolean moverse = servicios.moverAgente(x2, y2, (ObjetoDistribuido)this);
			if(moverse) {
				this.x = x2;
				this.y = y2;
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public  void dibujar(Renderizador2D r) {
		r.dibujarRectangulo(color, x-(radio/2), y-(radio/2), radio, radio);
		if(resaltado) {
			r.dibujarContornoRectangular(Color.darkGray, x-(radio/2), y-(radio/2), radio, radio);
			r.dibujarContornoRectangular(Color.BLACK, x-percepcion, y-percepcion, percepcion*2, percepcion*2);
		}
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public void init() {
	}

	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}
	@Override
	public void Resaltar() {
		resaltado = true;
	}
	@Override
	public void DesResaltar() {
		resaltado = false;
	}
	@Override
	public String info() {
		return ID+ 
				"#x: "+ this.x + " y: "+this.y+
				"#velocidad: "+velocidadPXs+
				"#percepci�n: "+percepcion+
				"#tama�o: "+this.radio;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	public ServiciosController getServicios() {
		return servicios;
	}
	public void setServicios(ServiciosController servicios) {
		this.servicios = servicios;
	}

}
