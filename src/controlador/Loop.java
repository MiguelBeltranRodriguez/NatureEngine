package controlador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import modelo.Agente;
import modelo.Dibujable;
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
	private List<Agente> agentes;
	private List<Dibujable> dibujables;
	
	//Parte encargada de inicializar todo lo necesario
	private void inicio() {
		setPantalla(Pantalla.getPantalla());
		render2D = new Renderizador2D();
		agentes = new ArrayList<Agente>();
		dibujables = new ArrayList<Dibujable>();
		
		Agente a1 = new Agente(Color.green, 20, 20, 20, 1);
		Agente a2 = new Agente(Color.red, 20, 50, 18, 2);
		Agente a3 = new Agente(Color.BLUE, 30, 60, 21, 3);
		
		
		agentes.add(a1);
		agentes.add(a2);
		agentes.add(a3);
		
		for (int i = 0; i <3000; i++) {
			agentes.add(new Agente(Color.CYAN, 100, 100, 20, 2));
		}
		for (Agente agente : agentes) {
			agente.start();
		}
		dibujables.addAll(agentes);
		
	}	
	
	//Parte lógica
	private void update() {

		
	}
	//Parte visual
	private void renderizar() {
		render2D.renderizar(dibujables);
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
			VarGlobalGame.delta += (now -lastTime)/timerPerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(VarGlobalGame.delta>=1) {
				update();
				renderizar();
				ticks++;
				VarGlobalGame.delta--;
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
