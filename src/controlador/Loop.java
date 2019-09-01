package controlador;

import java.awt.Color;
import java.util.Random;

import modelo.Agente;
import modelo.Mundo;
import utils.VarGlobalGame;
import vista.Pantalla;

/*
 * Loop principal
 */
public class Loop implements Runnable {

	private Thread thread;
	private boolean corriendo = false;
	private Pantalla pantalla;
	private Renderizador2D render2D;
	private Mundo mundo;
	
	
	//Parte encargada de inicializar todo lo necesario
	private void inicio() {
		setPantalla(Pantalla.getPantalla());
		render2D = new Renderizador2D();
		mundo = new Mundo();
		Random aleatorio = new Random(System.currentTimeMillis());
		int aux = aleatorio.nextInt(255);
		for(int i = 0; i < 500; i++) {
			mundo.agregarAgente(new Agente(new Color(aleatorio.nextInt(255),  aux, aux, 255), 60+i, 50+(i), 20, 5, mundo));
		}
		
		
		mundo.init();
	}	
	
	//Parte lógica
	private void update() {

		
	}
	//Parte visual
	private void renderizar() {
		render2D.renderizar(mundo);
	}
	
	@Override
	public void run() {
		inicio();
		
		double timerPerTick = 1000000000 / VarGlobalGame.FPS;
		
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(corriendo) {
			now = System.nanoTime();
			VarGlobalGame.DELTA += (now -lastTime)/timerPerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(VarGlobalGame.DELTA>=1) {
				update();
				renderizar();
				ticks++;
				VarGlobalGame.DELTA--;
			}
			if(timer > 1000000000) {
				System.out.println("Tiquets y Frames: "+ ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		stop();
		
	}
	
	public synchronized void start() {
		if(corriendo) {
			return;
		}	
		thread = new Thread(this);
		thread.start();
		corriendo = true;
	}
	public synchronized void stop() {
		if(!corriendo) {
			return;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Pantalla getPantalla() {
		return pantalla;
	}

	public void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}
}
