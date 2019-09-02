package controlador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import modelo.Mundo;
import vista.Pantalla;

public class Renderizador2D {

	private BufferStrategy bs;
	private Graphics2D g;
	private Pantalla pantalla;
	
	public Renderizador2D() {
		pantalla = Pantalla.getPantalla();
	}
	
	public void renderizar(Mundo mundo) {
		bs = pantalla.getCanvas().getBufferStrategy();
		if(bs == null) {
			pantalla.getCanvas().createBufferStrategy(2);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		//empieza a dibujar
		mundo.dibujar(this);
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

}
