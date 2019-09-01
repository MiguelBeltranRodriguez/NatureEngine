package modelo;

import java.util.ArrayList;
import java.util.List;

import controlador.Renderizador2D;

public class Casilla implements Dibujable {

	private List<Dibujable> objetosEnCasilla;
	private int x;
	private int y;
	
	public Casilla(int x, int y) {
		this.x = x;
		this.y = y;
		objetosEnCasilla = new ArrayList<Dibujable>();
	}
	
	@Override
	public void  dibujar(Renderizador2D r) {
		for (Dibujable dibujable : objetosEnCasilla) {
			dibujable.dibujar(r);
		}
	}

	public void agregarDibujable(Dibujable ag) {
		objetosEnCasilla.add(ag);
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	public void quitarDibujable(Dibujable ag) {
		objetosEnCasilla.remove(ag);
	}

	@Override
	public synchronized  void init() {
		for (Dibujable dibujable : objetosEnCasilla) {
			dibujable.init();
		}
		
	}

	public boolean esVacia() {
		if(objetosEnCasilla.isEmpty()) {
			return true;
		}else {
			System.out.println("Colision");
			return false;
		}
	}

}
