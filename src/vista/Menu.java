package vista;

import controlador.Renderizador2D;
import modelo.Dibujable;
import modelo.Mundo;
import utils.VarGlobalVista;

public class Menu implements Dibujable {
	
	private Mundo mundo;
	
	
	public Menu(Mundo mundo) {
		this.mundo = mundo;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Resaltar() {
	}

	@Override
	public void DesResaltar() {
	}

	@Override
	public int getX() {
		return VarGlobalVista.HEIGTH_PATALLA_GAME+1;
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
