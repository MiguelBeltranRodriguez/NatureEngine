package controlador;

import Utils.VarGlobalVista;
import vista.Pantalla;

/*
 * Clase principal del simulador, inicial el loop principal, la creación y carga de los componentes
 */
public class NatureEngine{
	
	private Pantalla pantalla;
	private Loop loop;
	
	public NatureEngine() {
		crearPantalla();
		loop = new Loop();
		loop.start();
	}


	private void crearPantalla() {
		Pantalla pantalla = Pantalla.getPantalla();
		pantalla.setTitle(VarGlobalVista.titlePantalla);
		pantalla.setSize(VarGlobalVista.widhtPantalla,VarGlobalVista.heightPantalla);
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
