package NatureEngine.NatureEngineGUI;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Clase que representa el elemento visual que se mostrara a los usuarios, solo se puede instanciar uno
 */
public class Pantalla {

	/** El Jframe o ventana */
	private JFrame frame;

	/** El canvas, o zona de pintado */
	private Canvas canvas;

	/** El titulo de la aplicación */
	private String title;

	/** Las dimensiones */
	private int width, height;

	/** La pantalla (única) */
	private static Pantalla pantalla = null;

	/**
	 * Instantiates a new pantalla.
	 */
	private Pantalla() {
	}

	/**
	 * Crea una pantalla con todos sus parametros requeridos.
	 */
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

	/**
	 * Obtener la pantalla.
	 *
	 * @return La pantalla
	 */
	//Metodo singleton
	public static Pantalla getPantalla() {
		if(pantalla == null) {
			pantalla = new Pantalla();
			return pantalla;
		}else {
			return pantalla;
		}
	}
	/**
	 * Obtiene el titulo.
	 *
	 * @return El titulo
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * Cambia el titulo.
	 *
	 * @param title El nuevo titulo
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * Retorna el ancho
	 *
	 * @return el ancho
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * Cambia el ancho.
	 *
	 * @param width el nuevo ancho
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * Obtiene la altura.
	 *
	 * @return la altura
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * Cambia la altura
	 *
	 * @param height la nueva altura
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Cambia las dimensiones
	 *
	 * @param width el nuevo ancho
	 * @param height la nueva altura
	 */
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Obtiene el canvas
	 *
	 * @return el canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Cambia el canvas
	 *
	 * @param canvas El nuevo canvas
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

}
