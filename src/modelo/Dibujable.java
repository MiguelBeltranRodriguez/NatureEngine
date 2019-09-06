package modelo;

import controlador.Renderizador2D;

public interface Dibujable {
	public void dibujar(Renderizador2D r);
	public void Resaltar();
	public void DesResaltar();
	public int getX();
	public int getY();
	public void  init();
	public String info();
}
