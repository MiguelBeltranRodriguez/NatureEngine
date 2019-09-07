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
	private int percepcion;
	private Mundo mundo;
	private boolean resaltado;
	private int delX;
	private int delY;
	private int direccionX;
	private int direccionY;
	private int velocidadPXs;


	public Agente(Color color, int x, int y, int radio, int percepcion, Mundo mundo, int velocidad) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.percepcion = percepcion;
		this.mundo = mundo;
		this.direccionX = 0;
		this.direccionY = 0;
		this.delX = 0;
		this.delY = 0;
		resaltado = false;
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
		int moverse = 0;
		while(true) {

			if(VarGlobalGame.DELTA>=1) {
				if(delX == 0 && delY == 0) {
					movimientoAleatorio();
				}
				else {
					if(moverse==100) {
						moverse();
						moverse = 0;
					}else {
						moverse += velocidadPXs;
					}
					
				}
			}
			try {
				Thread.sleep(VarGlobalGame.UNIDAD_DE_TIEMPO_BASE_MS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}




	}

	private void moverse() {
		if(delX>0 && delY >0) {
			if(mundo.celdaVacia(x+(direccionX), y+(direccionY))) {
				cambiarPosicion(x+(direccionX), y+(direccionY));
				delX--;
				delY--;
			}
		}else if(delX>0) {
			if(mundo.celdaVacia(x+(direccionX), y)) {
				cambiarPosicion(x+(direccionX), y);
				delX--;
			}
		}else if(delY>0) {
			if(mundo.celdaVacia(x, y+(direccionY))) {
				cambiarPosicion(x, y+(direccionY));
				delY--;
			}
		}	
	}
	private void movimientoAleatorio() {
		Random rnd = new Random();
		int newX = 0;
		do {
			newX =  x + rnd.nextInt(percepcion + 1 +percepcion) - percepcion;
			if(newX>=VarGlobalVista.WIDHT_PANTALLA_GAME || newX <0) {
				newX =  x + rnd.nextInt(percepcion + 1 + percepcion) - percepcion;
			}else {
				break;
			}
		}while(true);
		int newY = 0;
		do {
			newY =  y + rnd.nextInt(percepcion + 1 +percepcion) - percepcion;
			if(newY>=VarGlobalVista.HEIGTH_PANTALLA_GAME || newY<0) {
				newY =  y +  rnd.nextInt(percepcion + 1 +percepcion) - percepcion;
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
	private void cambiarPosicion(int x2, int y2) {
		synchronized (mundo) {
			mundo.moverAgente(x2, y2, this);
		}
		this.x = x2;
		this.y = y2;
	}
	@Override
	public synchronized void dibujar(Renderizador2D r) {
		r.dibujarOvalo(color, x-(radio/2), y-(radio/2), radio, radio);
		if(resaltado) {
			r.dibujarContornoOvalo(Color.darkGray, x-(radio/2), y-(radio/2), radio, radio);
		}
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
		return this.thread.getName()+ 
				"#x: "+ this.x + " y: "+this.y;
	}

}
