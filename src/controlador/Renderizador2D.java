package controlador;

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
		//empieza a dibujar
		g.fillRect(0, 0, VarGlobalVista.widhtPantalla, VarGlobalVista.heightPantalla);
		
		
		//termina de dibujar
		
		bs.show();
		g.dispose();
	}

}
