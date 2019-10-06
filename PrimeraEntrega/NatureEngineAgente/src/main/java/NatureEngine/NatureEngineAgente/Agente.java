package NatureEngine.NatureEngineAgente;

import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import NatureEngine.Modelo.Casilla;
import NatureEngine.Modelo.Desire;
import NatureEngine.Modelo.Desires.DesireAlimentarme;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.RMI.ServiciosController;
import NatureEngine.Utils.VarGlobalGame;
import NatureEngine.Utils.VarGlobalVista;

public class Agente extends ObjetoDistribuido implements Dibujable, Serializable, Runnable  {

	private static final long serialVersionUID = 1L;
	private Color color;  //prueba
	private int x;	//prueba
	private int y; //prueba
	private int radio;  //prueba
	private int distanciaPercepcion;
	private ServiciosController servicios;
	private boolean resaltado;
	private int delX;
	private int delY;
	private int direccionX;
	private int direccionY;
	private int velocidadPXs;
	private int timeOutBloqueo;
	private int moverse;
	// TODO: pasar a una clase BDI
	private List<ObjetoDistribuido> percepcion;
	private Desire desireAnterior;
	
	public Agente(Long ID, Color color, int x, int y, int radio, int distanciaPercepcion, int velocidad,ServiciosController servicios) throws RemoteException {
		super(ID);
		this.color = color;
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.distanciaPercepcion = distanciaPercepcion;
		this.servicios = servicios;
		this.direccionX = 0;
		this.direccionY = 0;
		this.delX = 0;
		this.delY = 0;
		resaltado = false;
		this.velocidadPXs = velocidad;
		this.timeOutBloqueo = 3;
		moverse = 0;
		this.percepcion = new ArrayList<ObjetoDistribuido>();
		this.desireAnterior = null;
	}


  public int getDistanciaPercepcion() {
		return distanciaPercepcion;
	}


	public void setDistanciaPercepcion(int distanciaPercepcion) {
		this.distanciaPercepcion = distanciaPercepcion;
	}


	// No activo =============================================================================
	@Override
	public void update() {

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

	public void run() {
		int DELAY = VarGlobalGame.DELAY;
		int VELOCIDAD_MAX_CASILLA = VarGlobalGame.VELOCIDAD_MAX_CASILLA;
		long t_0 = 0;
		long t_1 = 0;
		long delta = 0;

		while(true) {
			t_0 = System.currentTimeMillis();
			
			/*if(delX == 0 && delY == 0) {
				movimientoAleatorio();
			} else {
				if(moverse>=VELOCIDAD_MAX_CASILLA) {
					try {
						moverse();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					moverse = moverse-VELOCIDAD_MAX_CASILLA;
				} else {
					moverse += velocidadPXs;
				}
			}*/
			pensar();
			
			t_1 = System.currentTimeMillis();
			delta = t_1 - t_0;
			if(!(delta >= DELAY)) {
				try {
					Thread.sleep(DELAY-delta);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private void pensar() {
		try {
			percepcion = servicios.percibir(this.ID, this.x, this.y); // BRF
			/* for (int i = 0; i < percepcion.size(); i++) {
				Casilla temporal = (Casilla) percepcion.get(i);
				System.out.println(temporal.getHumedad());
			} */
			Stack<Desire> desires = new Stack<Desire>();
			desires.push(new DesireAlimentarme(this));
			
			// TODO: crear dssirePorDefecto
			// TODO: pasar a función OPTION + FILTER
			Desire desireSeleccionado = null;
			
			while(!desires.isEmpty()) {
				desireSeleccionado = desires.pop();
				if(desireSeleccionado.tengoCapacidad()) {
					desireSeleccionado.init(this.desireAnterior);
					desireSeleccionado.ejecutar();
					break;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void moverse() throws RemoteException {
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
	}
	
	public int getDelX() {
		return delX;
	}


	public void setDelX(int delX) {
		this.delX = delX;
	}


	public int getDelY() {
		return delY;
	}


	public void setDelY(int delY) {
		this.delY = delY;
	}


	public int getDireccionX() {
		return direccionX;
	}


	public void setDireccionX(int direccionX) {
		this.direccionX = direccionX;
	}


	public int getDireccionY() {
		return direccionY;
	}


	public void setDireccionY(int direccionY) {
		this.direccionY = direccionY;
	}


	public int getVelocidadPXs() {
		return velocidadPXs;
	}


	public void setVelocidadPXs(int velocidadPXs) {
		this.velocidadPXs = velocidadPXs;
	}


	public int getTimeOutBloqueo() {
		return timeOutBloqueo;
	}


	public void setTimeOutBloqueo(int timeOutBloqueo) {
		this.timeOutBloqueo = timeOutBloqueo;
	}


	public int getMoverse() {
		return moverse;
	}


	public void setMoverse(int moverse) {
		this.moverse = moverse;
	}


	private void movimientoAleatorio() {
		Random rnd = new Random();
		int newX = 0;
		do {
			newX =  x + rnd.nextInt(distanciaPercepcion + 1 +distanciaPercepcion) - distanciaPercepcion;
			if(newX>=VarGlobalVista.widht_pantalla_map || newX <0) {
				newX =  x + rnd.nextInt(distanciaPercepcion + 1 + distanciaPercepcion) - distanciaPercepcion;
			}else {
				break;
			}
		}while(true);
		int newY = 0;
		do {
			newY =  y + rnd.nextInt(distanciaPercepcion + 1 +distanciaPercepcion) - distanciaPercepcion;
			if(newY>=VarGlobalVista.heigth_pantalla_map || newY<0) {
				newY =  y +  rnd.nextInt(distanciaPercepcion + 1 +distanciaPercepcion) - distanciaPercepcion;
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
	private synchronized  void cambiarPosicion(int x2, int y2) {
		try {
			
			servicios.moverAgente(x2, y2, (ObjetoDistribuido)this);
			this.x = x2;
			this.y = y2;
			
			
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
			r.dibujarContornoRectangular(Color.BLACK, x-distanciaPercepcion, y-distanciaPercepcion, distanciaPercepcion*2, distanciaPercepcion*2);
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
		Thread th = new Thread(this);
		th.start();
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
				"#percepci�n: "+distanciaPercepcion+
				"#tama�o: "+this.radio;
	}


	public ServiciosController getServicios() {
		return servicios;
	}
	public void setServicios(ServiciosController servicios) {
		this.servicios = servicios;
	}


	public List<ObjetoDistribuido> getPercepcion() {
		return percepcion;
	}


	public void setPercepcion(List<ObjetoDistribuido> percepcion) {
		this.percepcion = percepcion;
	}
	
	
}
