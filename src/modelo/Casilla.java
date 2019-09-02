package modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controlador.Renderizador2D;
import utils.VarGlobalVista;


public abstract class Casilla {

	protected List<Dibujable> objetosEnCasilla;
	protected int x;
	protected int y;
	protected int humedad;
	protected Color color;

	public Casilla(int x, int y, int humedad) {
		this.x = x;
		this.y = y;
		this.humedad = humedad;
		objetosEnCasilla = new ArrayList<Dibujable>();
	}

	public void dibujarCasillas(Renderizador2D r) {
		r.dibujarRectangulo(color, x, y, VarGlobalVista.TAMANO_TEXTURA, VarGlobalVista.TAMANO_TEXTURA);
	}

	public void agregarDibujable(Dibujable ag) {
		objetosEnCasilla.add(ag);
	}

	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}


	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	public synchronized void dibujar(Renderizador2D r) {

		for (Dibujable dibujable : objetosEnCasilla) {
			dibujable.dibujar(r);
		}
	}
	public void quitarDibujable(Dibujable ag) {
		objetosEnCasilla.remove(ag);
	}


	public synchronized  void init() {
		for (Dibujable dibujable : objetosEnCasilla) {
			dibujable.init();
		}

	}

	public boolean esVacia() {
		if(objetosEnCasilla.isEmpty()) {
			return true;
		}else {
			//System.out.println("Colision");
			return false;
		}
	}

}
