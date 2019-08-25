package controlador;

import vista.Pantalla;

/*
 * Loop principal
 */
public class Loop implements Runnable {

	private Thread thread;
	private boolean corriendo = false;
	private Pantalla pantalla;
	private Renderizador2D render2D;
	
	
	//Parte encargada de inicializar todo lo necesario
	private void inicio() {
		setPantalla(Pantalla.getPantalla());
		render2D = new Renderizador2D();
	}	
	
	//Parte lógica
	private void update() {
		
	}
	//Parte visual
	private void renderizar() {
		render2D.renderizar();
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
