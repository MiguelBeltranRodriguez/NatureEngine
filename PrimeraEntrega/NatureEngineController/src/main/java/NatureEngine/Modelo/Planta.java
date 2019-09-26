package NatureEngine.Modelo;

import java.awt.Color;

import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.Utils.VarGlobalGame;

public class Planta implements Dibujable {

	private float energiaActual;
	private int x;
	private int y;
	
	
	
	public Planta(float energiaActual, int x, int y) {
		super();
		this.energiaActual = energiaActual;
		this.x = x;
		this.y = y;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		r.dibujarRectangulo(VarGlobalGame.colorPlantas, x-2, y-10, 4, 10);
		r.dibujarContornoRectangular(Color.BLACK, x-2, y-10, 4, 10);
	}

	@Override
	public void Resaltar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void DesResaltar() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return "Planta"+
				"#Energía actual: "+energiaActual+
				"#Posición(x,y): ("+x+","+y+")";
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public float getEnergiaActual() {
		return energiaActual;
	}

	public void setEnergiaActual(float energiaActual) {
		this.energiaActual = energiaActual;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean reducirEnergia(float energiaAReducir) {
		this.energiaActual = energiaActual - energiaAReducir;
		if(energiaActual <= 0) {
			return true;
		}
		return false;
	}
	
}
