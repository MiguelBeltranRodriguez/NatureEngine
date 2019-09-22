package NatureEngine.NatureEngineController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import NatureEngine.Modelo.Mundo;
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
		
		System.setProperty("sun.java2d.opengl", "true");
		 try {
			 loop = new Loop();
			 LocateRegistry.createRegistry(6005);
			Naming.rebind("rmi://localhost:6005/controller", loop);
			  System.out.println("Servidor controller ON 6005");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
		loop.run();
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
