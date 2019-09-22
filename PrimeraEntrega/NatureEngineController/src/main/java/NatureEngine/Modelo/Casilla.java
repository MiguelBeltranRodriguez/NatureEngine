package NatureEngine.Modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.Utils.VarGlobalVista;




public abstract class Casilla {

	protected int x;
	protected int y;
	protected int humedad;
	protected Color color;

	public Casilla(int x, int y, int humedad) {
		this.x = x;
		this.y = y;
		this.humedad = humedad;
		
	}

	public void dibujarCasillas(Renderizador2D r) {
		r.dibujarRectangulo(color, x, y, VarGlobalVista.TAMANO_TEXTURA, VarGlobalVista.TAMANO_TEXTURA);
	}

	
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}


	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	

	public int getHumedad() {
		return humedad;
	}

	public void setHumedad(int humedad) {
		this.humedad = humedad;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
