package controlador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import modelo.Mundo;
import utils.VarGlobalVista;
import vista.Menu;
import vista.Pantalla;

public class Renderizador2D {

	private BufferStrategy bs;
	private Graphics2D g;
	private Pantalla pantalla;
	
	public Renderizador2D() {
		pantalla = Pantalla.getPantalla();
	}
	
	public void renderizar(Mundo mundo, Menu menu) {
		bs = pantalla.getCanvas().getBufferStrategy();
		if(bs == null) {
			pantalla.getCanvas().createBufferStrategy(2);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.clearRect(0, 0, VarGlobalVista.WIDHT_PANTALLA, VarGlobalVista.HEIGTH_PANTALLA);
		//empieza a dibujar
		mundo.dibujar(this);
		
		menu.dibujar(this);
		//termina de dibujar
		
		bs.show();
		g.dispose();
	}
	
	public void dibujarRectangulo(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
		
	}
	public void dibujarOvalo(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}

	public void dibujarContornoRectangular(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.drawRect(x, y, width, height);
		
	}

	public void dibujarContornoOvalo(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.drawOval(x, y, width, height);
	}

	public void dibujarString(Color c, int x, int y, String info) {
		g.setColor(c);
		g.drawString(info, x, y);
	}

}
