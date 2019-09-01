package modelo;

import java.awt.Color;
import java.util.Random;

import controlador.Renderizador2D;
import utils.VarGlobalGame;
import utils.VarGlobalVista;

public class Agente implements Dibujable, Runnable {

	
	private Thread thread;
	private Color color;  //prueba
	private int x;	//prueba
	private int y; //prueba
	private int radio;  //prueba
	private int velocidadPXs;
	private Mundo mundo;
	
	
	public Agente(Color color, int x, int y, int radio, int velocidad, Mundo mundo) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidadPXs = velocidad;
		this.mundo = mundo;
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
		long newTime = 0;
		long oldTime = System.currentTimeMillis();
		long time = 0;

		while(true) {
			try {
				newTime = System.currentTimeMillis();
				time = newTime-oldTime;
				oldTime = time;
				movimientoAleatorio();
				if(time>VarGlobalGame.UNIDAD_DE_TIEMPO_BASE_MS) {
					Thread.sleep(VarGlobalGame.UNIDAD_DE_TIEMPO_BASE_MS);
				}else {
					Thread.sleep(VarGlobalGame.UNIDAD_DE_TIEMPO_BASE_MS-time);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	

		
	}

	private void movimientoAleatorio() {
		Random rnd = new Random();
		int newX = 0;
		do {
			newX =  x + rnd.nextInt(velocidadPXs + 1 +velocidadPXs) - velocidadPXs;
			if(newX>=VarGlobalVista.WIDHT_PANTALLA || newX <0) {
				newX =  x + rnd.nextInt(velocidadPXs + 1 + velocidadPXs) - velocidadPXs;
			}else {
				break;
			}
		}while(true);
		int newY = 0;
		do {
			newY =  y + rnd.nextInt(velocidadPXs + 1 +velocidadPXs) - velocidadPXs;
			if(newY>=VarGlobalVista.HEIGTH_PATALLA || newY<0) {
				newY =  y +  rnd.nextInt(velocidadPXs + 1 +velocidadPXs) - velocidadPXs;
			}else {
				break;
			}
		}while(true);
		if(mundo.celdaVacia(newX,newY)) {
			cambiarPosicion(newX, newY);
		}
		
	}
	private void cambiarPosicion(int x2, int y2) {
		synchronized (mundo) {
			mundo.moverAgente(x2, y2, this);
		}
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
		start();
		
	}
	
}
