package modelo;

import java.awt.Color;

import controlador.Renderizador2D;

public class Agente implements Dibujable, Runnable {

	private Thread thread;
	private Color color;
	
	public synchronized void start() {	
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		//acciones del agente
		
	}

	@Override
	public void dibujar(Renderizador2D r) {
		r.dibujarOvalo(color, x, y, width, height);

	}

}
