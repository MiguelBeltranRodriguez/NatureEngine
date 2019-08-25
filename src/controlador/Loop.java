package controlador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import modelo.Agente;
import modelo.Dibujable;
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
		
		Agente a1 = new Agente(Color.green, 20, 20, 20);
		Agente a2 = new Agente(Color.red, 20, 50, 18);
		Agente a3 = new Agente(Color.BLUE, 30, 60, 21);
		
		
		agentes.add(a1);
		agentes.add(a2);
		agentes.add(a3);
		
	}	
	
	//Parte l�gica
	private void update() {
		dibujables.clear();
		dibujables.addAll(agentes);
	}
	//Parte visual
	private void renderizar() {
		render2D.renderizar(dibujables);
	}
	
	@Override
	public void run() {
		inicio();
		while(corriendo) {
			update();
			renderizar();
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
