package NatureEngine.NatureEngineGUI;

import java.awt.Color;



import NatureEngine.Utils.VarGlobalGame;
import NatureEngine.Utils.VarGlobalVista;


// TODO: Auto-generated Javadoc
/**
 * La clase que representa el menú del usuario
 */
public class Menu implements Dibujable {
	
	/** El mundo que se va a dibujar */
	private Dibujable mundo;
	
	/** Indica la distancia de cada salto de linea. */
	private int indiceSaltoDeLinea = 0;
	
	/**
	 * Crea un nuevo menu
	 *
	 * @param mundo El mundo al cual se le va a mostrar la información
	 */
	public Menu(Dibujable mundo) {
		this.mundo = mundo;
	}

	/**
	 * Dibujar el menú
	 *
	 * @param r El renderizador
	 */
	@Override
	public void dibujar(Renderizador2D r) {
		indiceSaltoDeLinea = 12;
		r.dibujarString(Color.black, VarGlobalVista.widht_pantalla_map + 5 , indiceSaltoDeLinea, "Informaci�n del mundo: ");
		indiceSaltoDeLinea += 12;
		r.dibujarString(Color.black, VarGlobalVista.widht_pantalla_map + 5, indiceSaltoDeLinea, "FPS: "+VarGlobalGame.TICKS_S);
		indiceSaltoDeLinea += 12;
		String info = mundo.info();
		String [] infoS = info.split("#");
		for(int i = 1; i <= infoS.length; i++) {
			r.dibujarString(Color.black, VarGlobalVista.widht_pantalla_map + 5, indiceSaltoDeLinea, infoS[i-1]);
			indiceSaltoDeLinea +=12;
		}
	}

	/**
	 * Resaltar.
	 */
	@Override
	public void Resaltar() {
	}

	/**
	 * Desresaltar.
	 */
	@Override
	public void DesResaltar() {
	}

	/**
	 * Retorna la posición en x del menú
	 *
	 * @return the x
	 */
	@Override
	public int getX() {
		return VarGlobalVista.heigth_pantalla_map+1;
	}

	/**
	 * Retorna la posición en y del menú
	 *
	 * @return the y
	 */
	@Override
	public int getY() {
		return 0;
	}

	/**
	 * Inicializar el menú
	 */
	@Override
	public void init() {
	}

	/**
	 * Retorna la información del menu, en este caso es null, porque el menu es el que dibuja la información del mundo
	 *
	 * @return La información
	 */
	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Actualiza el menu
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	
}
