package controlador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import Utils.VarGlobalVista;
import vista.Pantalla;

public class Renderizador2D {

	private BufferStrategy bs;
	private Graphics2D g;
	private Pantalla pantalla;
	
	public Renderizador2D() {
		pantalla = Pantalla.getPantalla();
	}
	
	public void renderizar() {
		bs = pantalla.getCanvas().getBufferStrategy();
		if(bs == null) {
			pantalla.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, VarGlobalVista.widhtPantalla, VarGlobalVista.heightPantalla);
		//empieza a dibujar
		g.setColor(Color.white);
		g.fillRect(0, 0, VarGlobalVista.widhtPantalla, VarGlobalVista.heightPantalla);
		
		
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
