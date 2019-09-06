package vista;

import java.awt.Color;

import controlador.Renderizador2D;
import modelo.Dibujable;

public class PopUpInfo implements Dibujable {
	private Dibujable dibujable;
	private int height;
	private int width;
	

	public PopUpInfo(Dibujable dibujable) {
		this.dibujable = dibujable;
		width = 80;
		height = 90;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		r.dibujarRectangulo(Color.WHITE, dibujable.getX(), dibujable.getY(), width,height);
		r.dibujarContornoRectangular(Color.BLACK, dibujable.getX()-1, dibujable.getY()-1, width+1,height+1);
		String info = dibujable.info();
		String [] infoS = info.split("#");
		for(int i = 1; i <= infoS.length; i++) {
			r.dibujarString(Color.black, dibujable.getX() + 4, dibujable.getY()+(10*i), infoS[i-1]);
		}
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

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
