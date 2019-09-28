package NatureEngine.NatureEngineGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;


import NatureEngine.Utils.VarGlobalVista;


/**
 * La clase que pinta sobre el canvas
 */
public class Renderizador2D {

	/** Tipo de estrategia para dibujar con un buffer */
	private BufferStrategy bs;
	
	/** El graficador */
	private Graphics2D g;
	
	/** La pantalla */
	private Pantalla pantalla;
	
	/**
	 * Instancia un nuevo renderizador
	 */
	public Renderizador2D() {
		pantalla = Pantalla.getPantalla();
	}
	
	/**
	 * Pintar sobre el canvas
	 *
	 * @param mundo El mundo a dibujar
	 * @param menu El menu a dibujar
	 */
	public void renderizar(Dibujable mundo, Menu menu) {
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
	
	/**
	 * Dibujar rectangulo relleno.
	 *
	 * @param c Color
	 * @param x posicion x
	 * @param y posicion y
	 * @param width el ancho
	 * @param height el alto
	 */
	public void dibujarRectangulo(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
		
	}
	
	/**
	 * Dibujar ovalo relleno.
	 *
	 * @param c Color
	 * @param x posicion x
	 * @param y posicion y
	 * @param width el ancho
	 * @param height el alto
	 */
	public void dibujarOvalo(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}

	/**
	 * Dibujar contorno rectangular.
	 *
	 * @param c Color
	 * @param x posicion x
	 * @param y posicion y
	 * @param width el ancho
	 * @param height el alto
	 */
	public void dibujarContornoRectangular(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.drawRect(x, y, width, height);
		
	}

	/**
	 * Dibujar contorno circular.
	 *
	 * @param c Color
	 * @param x posicion x
	 * @param y posicion y
	 * @param width el ancho
	 * @param height el alto
	 */
	public void dibujarContornoOvalo(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.drawOval(x, y, width, height);
	}

	/**
	 * Dibujar texto.
	 *
	 * @param c Color
	 * @param x posicion x
	 * @param y posicion y
	 * @param info el texto
	 */
	public void dibujarString(Color c, int x, int y, String info) {
		g.setColor(c);
		g.drawString(info, x, y);
	}

}
