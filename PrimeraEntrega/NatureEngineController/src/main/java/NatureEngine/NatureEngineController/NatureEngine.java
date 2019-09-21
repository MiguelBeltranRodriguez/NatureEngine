package NatureEngine.NatureEngineController;

import NatureEngine.NatureEngineGUI.Pantalla;
import NatureEngine.Utils.VarGlobalVista;


/*
 * Clase principal del simulador, inicial el loop principal, la creaci�n y carga de los componentes
 */
public class NatureEngine{
	
	private Pantalla pantalla;
	private Loop loop;
	
	public NatureEngine() {
		
		
		
		
		
		crearPantalla();
		loop = new Loop();
		System.setProperty("sun.java2d.opengl", "true");
		loop.start();
	}


	private void crearPantalla() {
		Pantalla pantalla = Pantalla.getPantalla();
		pantalla.setTitle(VarGlobalVista.TITULO_JUEGO);
		pantalla.setSize(VarGlobalVista.WIDHT_PANTALLA,VarGlobalVista.HEIGTH_PANTALLA);
		pantalla.crearPantalla();
		this.pantalla = pantalla;
	}


	public Pantalla getPantalla() {
		return pantalla;
	}


	public void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}
	
	
}
