package modelo;

import java.awt.Color;
import java.util.Random;

import Utils.VarGlobalVista;
import controlador.Renderizador2D;

public class Agente implements Dibujable, Runnable {

	
	private Thread thread;
	private Color color;  //prueba
	private int x;	//prueba
	private int y; //prueba
	private int radio;  //prueba
	private int velocidadPXs;
	
	
	public Agente(Color color, int x, int y, int radio, int velocidad) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidadPXs = velocidad;
	}
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
		
		movimientoAleatorio();
		
	}

	private void movimientoAleatorio() {
		Random rnd = new Random();
		int newX = 0;
		do {
			newX =  x + rnd.nextInt(velocidadPXs + 1 +velocidadPXs) - velocidadPXs;
			if(newX>VarGlobalVista.widhtPantalla || newX <0) {
				newX =  x + rnd.nextInt(velocidadPXs + 1 + velocidadPXs) - velocidadPXs;
			}else {
				break;
			}
		}while(true);
		int newY = 0;
		do {
			newY =  y + rnd.nextInt(velocidadPXs + 1 +velocidadPXs) - velocidadPXs;
			if(newY>VarGlobalVista.heightPantalla || newY<0) {
				newY =  y +  rnd.nextInt(velocidadPXs + 1 +velocidadPXs) - velocidadPXs;
			}else {
				break;
			}
		}while(true);
		cambiarPosicion(newX, newY);
	}
	private synchronized void cambiarPosicion(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}
	@Override
	public synchronized void dibujar(Renderizador2D r) {
		r.dibujarOvalo(color, x, y, radio, radio);
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
