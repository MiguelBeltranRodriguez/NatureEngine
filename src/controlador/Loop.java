package controlador;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


import modelo.Agente;
import modelo.Mundo;
import utils.VarGlobalGame;
import vista.Menu;
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
	private Menu menu;
	
	
	//Parte encargada de inicializar todo lo necesario
	private void inicio() {
		setPantalla(Pantalla.getPantalla());
		render2D = new Renderizador2D();
		mundo = new Mundo();
		Random aleatorio = new Random(System.currentTimeMillis());
		int aux = aleatorio.nextInt(255);
		for(int i = 0; i < 1000; i++) {
			mundo.agregarAgente(new Agente(new Color(aleatorio.nextInt(255),  aux, aux, 255), 150+(i/2), 150+(i/2), 12, 10, mundo, 6));
		}
		pantalla.getCanvas().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionar(e);
			}
		});
		
		mundo.init();
		menu = new Menu(mundo);
	}	
	
	protected void seleccionar(MouseEvent e) {
		if(!mundo.addPopUp(e.getX(), e.getY())) {
			mundo.limpiarPopUp();
		}
	}

	//Parte l�gica
	private void update() {

		
	}
	//Parte visual
	private void renderizar() {
		render2D.renderizar(mundo, menu);
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
