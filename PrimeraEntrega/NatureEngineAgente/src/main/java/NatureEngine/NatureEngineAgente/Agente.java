package NatureEngine.NatureEngineAgente;

import java.awt.Color;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

public class Agente extends ObjetoDistribuido implements Dibujable, Serializable, Runnable  {

	private static final long serialVersionUID = 1L;
	private Color color;
	private int x;
	private int y;
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
	private float aguaActual;
	private float velocidadActual;
	private float humedadIdeal;
	private float toleranciaHumedad;
	private int longevidad;
	private int madurezReproductiva;
	private int tamañoMaximo;
	private float potenciaMaxima;
	
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
		this.resaltado = false;
		
		this.edadActual = 0;
		this.potenciaMaxima = (float) this.getCaracteristicaHeredable(AtributosBasicos.POTENCIA_MAXIMA_);
		this.tamañoMaximo = (int) this.getCaracteristicaHeredable(AtributosBasicos.TAMANO_MAXIMO_);
		this.potenciaActual = this.cambioSegunEdad(this.potenciaMaxima);
		this.tamañoActual = (int) this.cambioSegunEdad((float) this.tamañoMaximo);
		
		this.energiaActual = (float) this.getCaracteristicaHeredable(AtributosBasicos.ENERGIA_MAXIMA_);
		this.aguaActual = (float) this.getCaracteristicaHeredable(AtributosBasicos.AGUA_MAXIMA_);
		this.humedadIdeal = (float) this.getCaracteristicaHeredable(AtributosBasicos.HUMEDAD_IDEAL_);
		this.toleranciaHumedad = (float) this.getCaracteristicaHeredable(AtributosBasicos.TOLERANCIA_HUMEDAD_);
		this.longevidad = (int) this.getCaracteristicaHeredable(AtributosBasicos.LONGEVIDAD_);
		this.madurezReproductiva = (int) this.getCaracteristicaHeredable(AtributosBasicos.MADUREZ_REPRODUCTIVA);
		
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
				
				System.out.println("|Energía Actual: "+this.energiaActual
						+" | Agua actual: "+this.aguaActual
						+" | Tamaño actual: "+this.tamañoActual
						+" | Potencia actual: "+this.potenciaActual
						+" | Edad Actual: "+this.edadActual);
				
				ticks = 0;
				this.edadActual++;
				this.velocidadActual = ActualizacionAtributosDependientes.actualizarVelocidadActual(this.casillasMovidas, VarGlobalGame.UNIDAD_TIEMPO_VELOCIDAD);
				this.consumoCorporal();
				this.potenciaActual = this.cambioSegunEdad(this.potenciaMaxima);
				this.tamañoActual = (int) this.cambioSegunEdad((float) this.tamañoMaximo);
				// TODO: Matar agente
				
				try {
					this.servicios.actualizarAgente((ObjetoDistribuido)this);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	private float cambioSegunEdad(float atributoMaximo) {
		float coheficienteMadurez = (float) Math.pow(this.edadActual - this.madurezReproductiva, 2) * (-1);
		float coheficienteLogevidad = this.longevidad;

		if (this.edadActual > this.madurezReproductiva) {
			coheficienteLogevidad *= VarGlobalGame.COHEFICIENTE_GRADO_ENVEJECIMIENTO;
		} else {
			coheficienteLogevidad *= VarGlobalGame.COHEFICIENTE_GRADO_CRECIMIENTO;
		}

		return (coheficienteMadurez / coheficienteLogevidad) + atributoMaximo;
	}

	private void consumoCorporal() {
		List<AtributosParaCalcular> atributosEnergia = new ArrayList<AtributosParaCalcular>();
		List<AtributosParaCalcular> atributosAgua = new ArrayList<AtributosParaCalcular>();
		float deltaHumedad = AtributosParaCalcular.getDeltaHumedad(this.getHumedadCasillaActual(), this.humedadIdeal);
		float toleranciaHumedadInverso = 1 / this.toleranciaHumedad;
		
		atributosEnergia.add(new AtributosParaCalcular((float) this.getCaracteristicaHeredable(AtributosBasicos.ENERGIA_MAXIMA_), VarGlobalGame.COHEFICIENTE_ENERGIA_MAXIMA, VarGlobalGame.DIVISION));
		atributosEnergia.add(new AtributosParaCalcular(this.potenciaActual, VarGlobalGame.COHEFICIENTE_POTENCIA_ACTUAL, VarGlobalGame.EXPONENTE));
		atributosEnergia.add(new AtributosParaCalcular((float) this.getCaracteristicaHeredable(AtributosBasicos.AGUA_MAXIMA_), VarGlobalGame.COHEFICIENTE_AGUA_MAXIMA, VarGlobalGame.DIVISION));
		atributosEnergia.add(new AtributosParaCalcular(Float.parseFloat(this.getCaracteristicaHeredable(AtributosBasicos.PERCEPCION_).toString()), VarGlobalGame.COHEFICIENTE_PERCEPCION, VarGlobalGame.MULTIPLICACION));
		atributosEnergia.add(new AtributosParaCalcular(this.tamañoActual, VarGlobalGame.COHEFICIENTE_TAMAÑO_ACTUAL, VarGlobalGame.EXPONENTE));
		atributosEnergia.add(new AtributosParaCalcular((float) this.getCaracteristicaHeredable(AtributosBasicos.TOLERANCIA_HUMEDAD_), VarGlobalGame.COHEFICIENTE_TOLERANCIA_HUMEDAD, VarGlobalGame.MULTIPLICACION));
		atributosEnergia.add(new AtributosParaCalcular(this.velocidadActual, VarGlobalGame.COHEFICIENTE_VELOCIDAD_ACTUAL, VarGlobalGame.EXPONENTE));

		atributosAgua.add(new AtributosParaCalcular(this.velocidadActual, VarGlobalGame.COHEFICIENTE_VELOCIDAD_ACTUAL, VarGlobalGame.EXPONENTE));
		atributosAgua.add(new AtributosParaCalcular(deltaHumedad, toleranciaHumedadInverso, VarGlobalGame.MULTIPLICACION));
		
		this.energiaActual = ActualizacionAtributosDependientes.actualizarEnergiaGrupo(this.energiaActual, atributosEnergia);
		this.aguaActual = ActualizacionAtributosDependientes.actualizarEnergiaGrupo(this.aguaActual, atributosAgua);
		
		this.casillasMovidas = 0;
	}


	private float getHumedadCasillaActual() {
		Casilla casilla = null;
		try {
			casilla = (Casilla) servicios.getCasilla(this.x, this.y);
			return casilla.getHumedadActual();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO manejar cuando no encuentra humedad
		return (float) 0.000000000000000000000000001;
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
					desireAnterior = desireSeleccionado;
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

/**	private void decisionIrA(int newX, int newY) {
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
	}*/

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
	public void dibujar(Renderizador2D render) {
		int tamañoActual = this.tamañoActual;
		int distanciaPercepcion = this.getDistanciaPercepcion();
		int mitadTamaño = tamañoActual / 2;
		int diametroPercepcion = distanciaPercepcion * 2;
		
		render.dibujarRectangulo(color, x - mitadTamaño, y - mitadTamaño, tamañoActual, tamañoActual);
		render.dibujarLinea(Color.LIGHT_GRAY, this.x, this.y, this.direccionX, this.direccionY, distanciaPercepcion);
		if(resaltado) {
			render.dibujarContornoRectangular(Color.darkGray, x - mitadTamaño, y - mitadTamaño, tamañoActual, tamañoActual);
			render.dibujarContornoRectangular(Color.BLACK, x - distanciaPercepcion, y - distanciaPercepcion, diametroPercepcion, diametroPercepcion);
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
				"#energía: " + this.energiaActual+
				"#velocidad: " + this.potenciaActual+
				"#percepción: " + this.getPercepciones()+
				"#tamaño: "+ this.tamañoActual;
	}

	public ServiciosController getServicios() {
		return servicios;
	}

	public void setServicios(ServiciosController servicios) {
		this.servicios = servicios;
	}

	public float getAguaActual() {
		return aguaActual;
	}

	public void setAguaActual(float aguaActual) {
		this.aguaActual = aguaActual;
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
