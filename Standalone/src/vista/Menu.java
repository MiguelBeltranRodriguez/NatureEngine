package vista;

import java.awt.Color;

import controlador.Renderizador2D;
import modelo.Dibujable;
import modelo.Mundo;
import utils.VarGlobalGame;
import utils.VarGlobalVista;

public class Menu implements Dibujable {
	
	private Mundo mundo;
	private int indiceString = 0;
	
	public Menu(Mundo mundo) {
		this.mundo = mundo;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		indiceString = 12;
		r.dibujarString(Color.black, VarGlobalVista.WIDHT_PANTALLA_GAME + 5 , indiceString, "Información del mundo: ");
		indiceString += 12;
		r.dibujarString(Color.black, VarGlobalVista.WIDHT_PANTALLA_GAME + 5, indiceString, "FPS: "+VarGlobalGame.TICKS_S);
		indiceString += 12;
		String info = mundo.info();
		String [] infoS = info.split("#");
		for(int i = 1; i <= infoS.length; i++) {
			r.dibujarString(Color.black, VarGlobalVista.WIDHT_PANTALLA_GAME + 5, indiceString, infoS[i-1]);
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
		return VarGlobalVista.HEIGTH_PANTALLA_GAME+1;
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

	
	
}
