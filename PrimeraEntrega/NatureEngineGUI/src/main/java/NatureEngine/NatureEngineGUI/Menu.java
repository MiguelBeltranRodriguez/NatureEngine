package NatureEngine.NatureEngineGUI;

import java.awt.Color;



import NatureEngine.Utils.VarGlobalGame;
import NatureEngine.Utils.VarGlobalVista;


public class Menu implements Dibujable {
	
	private Dibujable mundo;
	private int indiceString = 0;
	
	public Menu(Dibujable mundo) {
		this.mundo = mundo;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		indiceString = 12;
		r.dibujarString(Color.black, VarGlobalVista.widht_pantalla_map + 5 , indiceString, "Informaci�n del mundo: ");
		indiceString += 12;
		r.dibujarString(Color.black, VarGlobalVista.widht_pantalla_map + 5, indiceString, "FPS: "+VarGlobalGame.TICKS_S);
		indiceString += 12;
		String info = mundo.info();
		String [] infoS = info.split("#");
		for(int i = 1; i <= infoS.length; i++) {
			r.dibujarString(Color.black, VarGlobalVista.widht_pantalla_map + 5, indiceString, infoS[i-1]);
			indiceString +=12;
		}
	}

	@Override
	public void Resaltar() {
	}

	@Override
	public void DesResaltar() {
	}

	@Override
	public int getX() {
		return VarGlobalVista.heigth_pantalla_map+1;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public void init() {
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	
}
