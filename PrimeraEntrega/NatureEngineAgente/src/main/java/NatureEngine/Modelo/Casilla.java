package NatureEngine.Modelo;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.Utils.VarGlobalGame;
import NatureEngine.Utils.VarGlobalVista;




public class Casilla extends ObjetoDistribuido implements Dibujable, Serializable {

	protected int x;
	protected int y;
	protected float humedadBase;
	protected float humedadActual;
	protected float humedadAnterior;
	protected Map<Long, Dibujable> dibujablesCasilla;
	
	public Casilla(int x, int y, float humedad) throws RemoteException {
		super((long)0);
		dibujablesCasilla = new HashMap<Long, Dibujable>();
		this.x = x;
		this.y = y;
		this.humedadBase = humedad;
		humedadActual = humedad;
		humedadAnterior = humedadActual;
		
	}

	public void dibujarCasillas(Renderizador2D r) {
		int texturaHumedad = (int) (humedadActual/10);
		r.dibujarRectangulo(VarGlobalGame.dibujoCasillas[texturaHumedad], x, y, VarGlobalVista.TAMANO_TEXTURA_CUADRICULA, VarGlobalVista.TAMANO_TEXTURA_CUADRICULA);
		
		
	}

	
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}


	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	

	public float getHumedad() {
		return humedadBase;
	}

	public void setHumedad(float humedad) {
		this.humedadBase = humedad;
	}

	

	public float getHumedadActual() {
		return humedadActual;
	}

	public void setHumedadActual(float humedadActual) {
		this.humedadActual = humedadActual;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Resaltar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DesResaltar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String info() {
		
		return "Sin info";
	}

	@Override
	public void update() {
	}

	public void cambiarHumedad(float estacion) {
		humedadAnterior = humedadActual;
		humedadActual = humedadBase * estacion;
		if(humedadActual>100f) {
			humedadActual = 100;
		}
		if(humedadActual<0f) {
			humedadActual = 0;
		}
	}

	public float getHumedadBase() {
		return humedadBase;
	}

	public void setHumedadBase(float humedadBase) {
		this.humedadBase = humedadBase;
	}

	public float getHumedadAnterior() {
		return humedadAnterior;
	}

	public void setHumedadAnterior(float humedadAnterior) {
		this.humedadAnterior = humedadAnterior;
	}


	public void agregarDibujable(Dibujable dibujable) {
		ObjetoDistribuido ob = (ObjetoDistribuido) dibujable;
		if(dibujable == null) {
			System.out.println("ERRRRROOOOO");
		}
		Long ID = ob.getID();
		dibujablesCasilla.put(ID, dibujable);
	}

	public void dibujarObjetos(Renderizador2D r) {
		if(!dibujablesCasilla.isEmpty()) {
			Collection<Dibujable> c = dibujablesCasilla.values();
			for (Dibujable dibujable : c) {
				dibujable.dibujar(r);
			}
		}
		
	}


	public boolean esVacia() {
		return dibujablesCasilla.isEmpty();
	}

	public void eliminarDibujable(Long id) {
		dibujablesCasilla.remove(id);
	}

	public Dibujable obtenerFirst() {
		Collection<Dibujable> c = dibujablesCasilla.values();
		for (Dibujable dibujable : c) {
			return dibujable;
		}
		return null;
		
	}

	public Dibujable buscarDibujable(Long id) {
		return dibujablesCasilla.get(id);
	}
	
	public Dibujable getElementoTipo(String tipo) {
		// TODO agregar esta palabra al diccionario en commons	
		Collection<Dibujable> listaDibujables = (Collection<Dibujable>) dibujablesCasilla.values();
		for (Dibujable dibujable : listaDibujables) {
			if(tipo.equals("planta")) {
				if (dibujable instanceof Planta) {
					return dibujable;
				}
			}else if(tipo.equals("agente")) {
				if (dibujable instanceof Agente) {
					return dibujable;
				}
			}
			// TODO agregar a furturo...
		}
		return null;
	}
	
	// TODO: getAgente...
	
}
