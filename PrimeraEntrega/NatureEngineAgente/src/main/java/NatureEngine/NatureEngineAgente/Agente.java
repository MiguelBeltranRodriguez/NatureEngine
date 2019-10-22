package NatureEngine.NatureEngineAgente;

import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.GenAtributo;
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
	private ServiciosController servicios;
	private boolean resaltado;
	private int delX;
	private int delY;
	private int direccionX;
	private int direccionY;
	private float potenciaActual;
	private int timeOutBloqueo;
	private float moverse;
	// TODO: pasar a una clase BDI
	private List<ObjetoDistribuido> percepcion;
	private Desire desireAnterior;
	private Map<String, GenAtributo> caracteristicasHeredablesAgente;
	private int tamañoActual;
	private int edadActual;  
	
	public Agente(Long ID, Color color, int x, int y,
			ServiciosController servicios, Map<String, GenAtributo> caracteristicasHeredablesAgente) throws RemoteException {
		super(ID);
		this.caracteristicasHeredablesAgente = caracteristicasHeredablesAgente;
		this.color = color;
		this.x = x;
		this.y = y;
		this.servicios = servicios;
		this.direccionX = 0;
		this.direccionY = 0;
		this.delX = 0;
		this.delY = 0;
		resaltado = false;
		this.potenciaActual = (float)getCaracteristicaHeredable(AtributosBasicos.POTENCIA_MAXIMA_);
		this.tamañoActual = (int)getCaracteristicaHeredable(AtributosBasicos.TAMANO_MAXIMO_);
		this.edadActual = 0;
		this.timeOutBloqueo = 3;
		moverse = 0;
		this.percepcion = new ArrayList<ObjetoDistribuido>();
		this.desireAnterior = null;
	}


	public int getDistanciaPercepcion() {
		return (int)getCaracteristicaHeredable(AtributosBasicos.PERCEPCION_);
	}

	// No activo =============================================================================
	@Override
	public void update() {

		if(delX == 0 && delY == 0) {
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
				moverse += potenciaActual;
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
			cambiarPosicion(x+(direccionX), y+(direccionY));
			delX--;
			delY--;
			cambiarPosicion(x-(direccionX), y-(direccionY));
		}else if(delX>0) {
			cambiarPosicion(x+(direccionX), y);
			delX--;
		}else if(delY>0) {
			cambiarPosicion(x, y+(direccionY));
			delY--;
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


	public float getVelocidadPXs() {
		return potenciaActual;
	}


	public void setVelocidadPXs(float velocidadPXs) {
		this.potenciaActual = velocidadPXs;
	}


	public int getTimeOutBloqueo() {
		return timeOutBloqueo;
	}


	public void setTimeOutBloqueo(int timeOutBloqueo) {
		this.timeOutBloqueo = timeOutBloqueo;
	}


	public float getMoverse() {
		return moverse;
	}


	public void setMoverse(float moverse) {
		this.moverse = moverse;
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
	public void dibujar(Renderizador2D r) {
		int tamañoActual = this.tamañoActual;
		int distanciaPercepcion = this.getDistanciaPercepcion();
		r.dibujarRectangulo(color, x-(tamañoActual/2), y-(tamañoActual/2), tamañoActual, tamañoActual);
		r.dibujarLinea(Color.BLACK, this.x, this.y, this.direccionX, this.direccionY, distanciaPercepcion);
		if(resaltado) {
			r.dibujarContornoRectangular(Color.darkGray, x-(tamañoActual/2), y-(tamañoActual/2), tamañoActual, tamañoActual);
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
				"#velocidad: "+this.potenciaActual+
				"#percepción: "+this.getPercepcion()+
				"#tamaño: "+this.tamañoActual;
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

	public Object getCaracteristicaHeredable(String nombre) {
		return this.caracteristicasHeredablesAgente.get(nombre).getValorCaracteristica();
	}
}
