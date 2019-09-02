package modelo;

import java.util.Random;

import controlador.Renderizador2D;
import utils.VarGlobalVista;

public class Mundo implements Dibujable {
	
	
	private Casilla [][] objetosDelMundo;
	
	public Mundo() {
		objetosDelMundo = new Casilla[VarGlobalVista.WIDHT_PANTALLA][VarGlobalVista.HEIGTH_PATALLA];
		Random r = new Random();
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PATALLA; y++) {
				objetosDelMundo[x][y]= new CasillaTierra(x,y, r.nextInt(100));
			}
		}
		
	}
	
	
	
	@Override
	public synchronized  void dibujar(Renderizador2D r) {
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA/VarGlobalVista.tamanoTextura; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PATALLA/VarGlobalVista.tamanoTextura; y++) {
				objetosDelMundo[x*8][y*8].dibujarCasillas(r);
			}
		}
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PATALLA; y++) {
				objetosDelMundo[x][y].dibujar(r);
			}
		}
	}
	public void agregarAgente(Agente ag) {
		objetosDelMundo[ag.getX()][ag.getY()].agregarDibujable(ag);
	}



	@Override
	public int getX() {
		return 0;
	}



	@Override
	public int getY() {
		return 0;
	}



	public synchronized  void moverAgente(int x, int y, Agente ag) {
		objetosDelMundo[ag.getX()][ag.getY()].quitarDibujable(ag);
		objetosDelMundo[x][y].agregarDibujable(ag);
	}

	@Override
	public synchronized  void init() {
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PATALLA; y++) {
				objetosDelMundo[x][y].init();
			}
		}
		
	}



	public boolean celdaVacia(int newX, int newY) {
		return objetosDelMundo[newX][newY].esVacia();
	}



	
	
}
