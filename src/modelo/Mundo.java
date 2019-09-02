package modelo;

import java.io.BufferedReader;
import java.io.IOException;

import controlador.Renderizador2D;
import utils.ManejadorArchivos;
import utils.VarGlobalGame;
import utils.VarGlobalVista;

public class Mundo implements Dibujable {
	
	
	private Casilla [][] objetosDelMundo;
	
	public Mundo() {
		
		objetosDelMundo = new Casilla[VarGlobalVista.WIDHT_PANTALLA][VarGlobalVista.HEIGTH_PATALLA];
		cargarMapa();
		
	}
	
	
	
	private void cargarMapa() {
		BufferedReader br = ManejadorArchivos.getManejadorArchivos().cargarArchivo(VarGlobalGame.RUTA_MAPA);
		
		try {
			String line = br.readLine();
			String sTamano[] = line.split(ManejadorArchivos.SEPARADOR);
			int widht = Integer.parseInt(sTamano[1]);
			int heigth = Integer.parseInt(sTamano[2]);
			line = br.readLine();
			for(int i = 1; i <= (heigth/VarGlobalVista.TAMANO_TEXTURA); i++) {
				line = br.readLine();		
				String sLinea[] = line.split(ManejadorArchivos.SEPARADOR);
				for(int j = 1; j <= (widht/VarGlobalVista.TAMANO_TEXTURA); j++) {
					Float humedad = Float.parseFloat(sLinea[j-1]);
					int humedadI = (int) (humedad*100);
					for(int x = (j*VarGlobalVista.TAMANO_TEXTURA)-VarGlobalVista.TAMANO_TEXTURA; x<(j*VarGlobalVista.TAMANO_TEXTURA);x++ ) {
						for(int y = (i*VarGlobalVista.TAMANO_TEXTURA)-VarGlobalVista.TAMANO_TEXTURA; y<(i*VarGlobalVista.TAMANO_TEXTURA);y++ ) {
							if(humedadI>=100) {
								objetosDelMundo[x][y] = new CasillaAgua(x, y, 100);
							}else {
								objetosDelMundo[x][y] = new CasillaTierra(x, y, humedadI);
							}
							
						}
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("Salio");
		
		
		ManejadorArchivos.cerrarArchivo(br);
		
	}



	@Override
	public synchronized  void dibujar(Renderizador2D r) {
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA/VarGlobalVista.TAMANO_TEXTURA; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PATALLA/VarGlobalVista.TAMANO_TEXTURA; y++) {
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
