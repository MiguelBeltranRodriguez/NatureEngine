package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controlador.Renderizador2D;
import utils.ManejadorArchivos;
import utils.VarGlobalGame;
import utils.VarGlobalVista;
import vista.PopUpInfo;

public class Mundo implements Dibujable {


	private Casilla [][] objetosDelMundo;
	private List<PopUpInfo> popUpsInfo;

	public Mundo() {

		objetosDelMundo = new Casilla[VarGlobalVista.WIDHT_PANTALLA][VarGlobalVista.HEIGTH_PATALLA];
		popUpsInfo = new ArrayList<PopUpInfo>();
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
		for (PopUpInfo popUp : popUpsInfo) {
			popUp.dibujar(r);
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



	public boolean addPopUp(int x, int y) {
		int d0x = x - 10;
		int d1x = x + 10;
		int d0y = y - 10;
		int d1y = y + 10;

		if(d0x<0) {
			d0x = 0;
		}
		else if(d1x>=VarGlobalVista.WIDHT_PANTALLA) {
			d1x = VarGlobalVista.WIDHT_PANTALLA-1;
		}
		if(d0y<0) {
			d0y = 0;
		}
		else if(d1y>=VarGlobalVista.HEIGTH_PATALLA) {
			d1y = VarGlobalVista.HEIGTH_PATALLA-1;
		}
		for(int i = d0x; i <= d1x; i++) {
			for(int j = d0y; j <= d1y; j++) {
				if(!objetosDelMundo[i][j].esVacia()) {
					List<Dibujable> listO = objetosDelMundo[i][j].getObjetosEnCasilla();
					for (Dibujable dibujable : listO) {
						popUpsInfo.add(new PopUpInfo(dibujable));
					}
					return true;
				}
			}
		}
		return false;
	}



	public void limpiarPopUp() {
		for (PopUpInfo popUps : popUpsInfo) {
			popUps.DesResaltar();
		}
		popUpsInfo.clear();
	}



	@Override
	public void Resaltar() {	
	}



	@Override
	public void DesResaltar() {
	}





}
