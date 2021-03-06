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
	private int timeOutBloqueo;

	public Agente(Color color, int x, int y, int radio, int percepcion, Mundo mundo, int velocidad) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.percepcion = percepcion+radio;
		this.mundo = mundo;
		this.direccionX = 0;
		this.direccionY = 0;
		this.delX = 0;
		this.delY = 0;
		resaltado = false;
		this.velocidadPXs = velocidad;
		this.timeOutBloqueo = 3;
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
		long newTime = 0;
		long oldTime = System.currentTimeMillis();
		long time = 0;
		while(true) {
			try {
				newTime = System.currentTimeMillis();
				time = newTime-oldTime;
				oldTime = time;
				if(delX == 0 && delY == 0) {
					movimientoAleatorio();
				}
				else {
					if(moverse>=25) {
						moverse();
						moverse = moverse-25;
					}else {
						moverse += velocidadPXs;
					}

				}
				if(time>VarGlobalGame.UNIDAD_DE_TIEMPO_BASE_MS) {
					Thread.sleep(VarGlobalGame.UNIDAD_DE_TIEMPO_BASE_MS);
				}else {
					Thread.sleep(VarGlobalGame.UNIDAD_DE_TIEMPO_BASE_MS-time);
				}
			}
			catch (InterruptedException e) {
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
			}else {
				timeOutBloqueo--;
			}if(timeOutBloqueo==0) {
				if(mundo.celdaVacia(x-(direccionX), y-(direccionY))) {
					cambiarPosicion(x-(direccionX), y-(direccionY));
				}
				timeOutBloqueo = 4;
			}
		}else if(delX>0) {
			if(mundo.celdaVacia(x+(direccionX), y)) {
				cambiarPosicion(x+(direccionX), y);
				delX--;
			}else {
				timeOutBloqueo--;
			}if(timeOutBloqueo==0) {
				if(mundo.celdaVacia(x-(direccionX), y)) {
					cambiarPosicion(x-(direccionX), y);
				}
				timeOutBloqueo = 4;
			}
		}else if(delY>0) {
			if(mundo.celdaVacia(x, y+(direccionY))) {
				cambiarPosicion(x, y+(direccionY));
				delY--;
			}else {
				timeOutBloqueo--;
			}
			if(timeOutBloqueo==0) {
				if(mundo.celdaVacia(x, y-(direccionY))) {
					cambiarPosicion(x, y-(direccionY));
				}
				timeOutBloqueo = 3;
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
	public  void dibujar(Renderizador2D r) {
		r.dibujarRectangulo(color, x-(radio/2), y-(radio/2), radio, radio);
		if(resaltado) {
			r.dibujarContornoRectangular(Color.darkGray, x-(radio/2), y-(radio/2), radio, radio);
			r.dibujarContornoRectangular(Color.BLACK, x-percepcion, y-percepcion, percepcion*2, percepcion*2);
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
	
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
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
				"#x: "+ this.x + " y: "+this.y+
				"#velocidad: "+velocidadPXs+
				"#percepci�n: "+percepcion+
				"#tama�o: "+this.radio;
	}

}
