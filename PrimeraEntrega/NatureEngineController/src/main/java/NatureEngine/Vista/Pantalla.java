package NatureEngine.Vista;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
/*
 * Clase que representa el elemento visual que se mostrara a los usuarios, solo se puede instanciar uno
 */

public class Pantalla {
	
	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width, height;
	
	private static Pantalla pantalla = null;
	
	private Pantalla() {
	}
	
	public void crearPantalla() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
	}
	//Metodo singleton
	public static Pantalla getPantalla() {
		if(pantalla == null) {
			pantalla = new Pantalla();
			return pantalla;
		}else {
			return pantalla;
		}
	}
/*
*  gets and setters
*/

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
}
