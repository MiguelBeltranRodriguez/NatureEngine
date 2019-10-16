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
import NatureEngine.Modelo.CaracteristicaHeredableAgente;
import NatureEngine.Modelo.Casilla;
import NatureEngine.Modelo.Desires.Desire;
import NatureEngine.Modelo.Desires.DesireAlimentarme;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.RMI.ServiciosController;
import NatureEngine.Utils.VarGlobalGame;
import NatureEngine.Utils.VarGlobalVista;

public class Agente extends ObjetoDistribuido implements Dibujable, Serializable, Runnable  {

	private static final long serialVersionUID = 1L;
	private Color color;  //prueba <- ??
	private int x;	//prueba <- ??
	private int y; //prueba <- ??
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
	private Map<String, CaracteristicaHeredableAgente> caracteristicasHeredablesAgente;
	private int tamañoActual;
	private int edadActual;  
	private int casillasMovidas;
	private float energiaActual;
	private float velocidadActual;
	
	public Agente(Long ID, Color color, int x, int y,
			ServiciosController servicios, Map<String, CaracteristicaHeredableAgente> caracteristicasHeredablesAgente) throws RemoteException {
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
		this.energiaActual = (float)getCaracteristicaHeredable(AtributosBasicos.ENERGIA_MAXIMA_);
		this.edadActual = 0;
		this.timeOutBloqueo = 3;
		this.moverse = 0;
		this.percepcion = new ArrayList<ObjetoDistribuido>();
		this.desireAnterior = null;
		this.casillasMovidas = 0;
		this.velocidadActual = 0;
	}


	public int getDistanciaPercepcion() {
		return (int)getCaracteristicaHeredable(AtributosBasicos.PERCEPCION_);
	}

	// No activo =============================================================================
	@Override
	public void update() {

	}

	public void run() {
		int DELAY = VarGlobalGame.DELAY;
		int MIU_DE_FRICCION = VarGlobalGame.MIU_DE_FRICCION; // TODO: revisar donde se usa
		long t_0 = 0;
		long t_1 = 0;
		long delta = 0;
		int ticks = 0;
		while(true) {
			t_0 = System.currentTimeMillis();
			this.pensar();
			// this.belief.pensar();
			if(ticks==(VarGlobalGame.FRECUENCIA_TICKS_CONSUMO)) {
				ticks = 0;
				consumo();
				// TODO: Matar agente
			}
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
			ticks++;
		}
	}
	private void consumo() {
		this.velocidadActual = ActualizacionAtributosDependientes
				.actualizarVelocidadActual(casillasMovidas, VarGlobalGame.UNIDAD_TIEMPO_VELOCIDAD);

		List<AtributosEnergia> atributosEnergia = new ArrayList<AtributosEnergia>();
		atributosEnergia.add(new AtributosEnergia((float) this.getCaracteristicaHeredable(AtributosBasicos.ENERGIA_MAXIMA_), VarGlobalGame.COHEFICIENTE_ENERGIA_MAXIMA, VarGlobalGame.DIVISION));
		atributosEnergia.add(new AtributosEnergia(this.potenciaActual, VarGlobalGame.COHEFICIENTE_POTENCIA_ACTUAL, VarGlobalGame.EXPONENTE));
		atributosEnergia.add(new AtributosEnergia((float) this.getCaracteristicaHeredable(AtributosBasicos.AGUA_MAXIMA_), VarGlobalGame.COHEFICIENTE_AGUA_MAXIMA, VarGlobalGame.DIVISION));
		atributosEnergia.add(new AtributosEnergia(Float.parseFloat(this.getCaracteristicaHeredable(AtributosBasicos.PERCEPCION_).toString()), VarGlobalGame.COHEFICIENTE_PERCEPCION, VarGlobalGame.MULTIPLICACION));
		atributosEnergia.add(new AtributosEnergia(this.tamañoActual, VarGlobalGame.COHEFICIENTE_TAMAÑO_ACTUAL, VarGlobalGame.EXPONENTE));
		atributosEnergia.add(new AtributosEnergia((float) this.getCaracteristicaHeredable(AtributosBasicos.TOLERANCIA_HUMEDAD_), VarGlobalGame.COHEFICIENTE_TOLERANCIA_HUMEDAD, VarGlobalGame.MULTIPLICACION));
		atributosEnergia.add(new AtributosEnergia(this.velocidadActual, VarGlobalGame.COHEFICIENTE_VELOCIDAD_ACTUAL, VarGlobalGame.EXPONENTE));
		
		this.energiaActual = ActualizacionAtributosDependientes.actualizarEnergiaGrupo(energiaActual, atributosEnergia);
		System.out.println(energiaActual + " BANANA");
		try {
			this.servicios.actualizarAgente((ObjetoDistribuido)this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		casillasMovidas = 0;
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
				if(desireSeleccionado.tengoHabilidad()) {
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
	public synchronized  void cambiarPosicion(int x2, int y2) {
		try {
			ServiciosController servicios = getServicios();
			servicios.moverAgente(x2, y2, (ObjetoDistribuido)this);
			this.setX(x2);
			this.setY(y2);
			casillasMovidas++;
			
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
				"#energía: "+this.energiaActual+
				"#velocidad: "+this.potenciaActual+
				"#percepción: "+this.getPercepciones()+
				"#tamaño: "+this.tamañoActual;
	}


	public ServiciosController getServicios() {
		return servicios;
	}
	public void setServicios(ServiciosController servicios) {
		this.servicios = servicios;
	}


	public List<ObjetoDistribuido> getPercepciones() {
		return percepcion;
	}


	public void setPercepcion(List<ObjetoDistribuido> percepcion) {
		this.percepcion = percepcion;
	}

	public Object getCaracteristicaHeredable(String nombre) {
		return this.caracteristicasHeredablesAgente.get(nombre).getValorCaracteristica();
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public boolean isResaltado() {
		return resaltado;
	}


	public void setResaltado(boolean resaltado) {
		this.resaltado = resaltado;
	}


	public float getPotenciaActual() {
		return potenciaActual;
	}


	public void setPotenciaActual(float potenciaActual) {
		this.potenciaActual = potenciaActual;
	}


	public Desire getDesireAnterior() {
		return desireAnterior;
	}


	public void setDesireAnterior(Desire desireAnterior) {
		this.desireAnterior = desireAnterior;
	}


	public Map<String, CaracteristicaHeredableAgente> getCaracteristicasHeredablesAgente() {
		return caracteristicasHeredablesAgente;
	}


	public void setCaracteristicasHeredablesAgente(
			Map<String, CaracteristicaHeredableAgente> caracteristicasHeredablesAgente) {
		this.caracteristicasHeredablesAgente = caracteristicasHeredablesAgente;
	}


	public int getTamañoActual() {
		return tamañoActual;
	}


	public void setTamañoActual(int tamañoActual) {
		this.tamañoActual = tamañoActual;
	}


	public int getEdadActual() {
		return edadActual;
	}


	public void setEdadActual(int edadActual) {
		this.edadActual = edadActual;
	}


	public int getCasillasMovidas() {
		return casillasMovidas;
	}


	public void setCasillasMovidas(int casillasMovidas) {
		this.casillasMovidas = casillasMovidas;
	}


	public float getEnergiaActual() {
		return energiaActual;
	}


	public void setEnergiaActual(float energiaActual) {
		this.energiaActual = energiaActual;
	}


	public float getVelocidadActual() {
		return velocidadActual;
	}


	public void setVelocidadActual(float velocidadActual) {
		this.velocidadActual = velocidadActual;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
