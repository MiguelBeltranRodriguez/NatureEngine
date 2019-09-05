package vista;

import java.awt.Color;

import controlador.Renderizador2D;
import modelo.Dibujable;

public class PopUpInfo implements Dibujable {
	private Dibujable dibujable;

	public PopUpInfo(Dibujable dibujable) {
		this.dibujable = dibujable;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		r.dibujarRectangulo(Color.WHITE, dibujable.getX(), dibujable.getY(), 80,50);
		r.dibujarContornoRectangular(Color.BLACK, dibujable.getX()-1, dibujable.getY()-1, 81,51);
		dibujable.Resaltar();
	}

	@Override
	public int getX() {
		return dibujable.getX();
	}

	@Override
	public int getY() {
		return dibujable.getY();
	}

	@Override
	public void init() {
	}

	@Override
	public void Resaltar() {
	}

	@Override
	public void DesResaltar() {
		dibujable.DesResaltar();
	}
	
	
	
}
