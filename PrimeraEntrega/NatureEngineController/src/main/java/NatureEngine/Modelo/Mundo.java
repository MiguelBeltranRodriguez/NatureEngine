package NatureEngine.Modelo;


import java.io.BufferedReader;
import java.io.IOException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.PopUpInfo;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.Utils.ManejadorArchivos;
import NatureEngine.Utils.VarGlobalGame;
import NatureEngine.Utils.VarGlobalVista;



public class Mundo implements Dibujable{


	private Dibujable [][] dibujablesDelMundo;
	private Casilla [][] objetosDelMundo;
	private List<PopUpInfo> popUpsInfo;
	private int contadorAgentes;

	public Mundo() {
		objetosDelMundo = new Casilla[VarGlobalVista.WIDHT_PANTALLA_GAME][VarGlobalVista.HEIGTH_PANTALLA_GAME];
		dibujablesDelMundo = new Dibujable[VarGlobalVista.WIDHT_PANTALLA_GAME][VarGlobalVista.HEIGTH_PANTALLA_GAME];
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA_GAME; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PANTALLA_GAME; y++) {
				dibujablesDelMundo[x][y] = null;
			}
		}
		popUpsInfo = new ArrayList<PopUpInfo>();
		setContadorAgentes(0);
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
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA_GAME/VarGlobalVista.TAMANO_TEXTURA; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PANTALLA_GAME/VarGlobalVista.TAMANO_TEXTURA; y++) {
				objetosDelMundo[x*8][y*8].dibujarCasillas(r);
			}
		}
		for(int x = 0; x < VarGlobalVista.WIDHT_PANTALLA_GAME; x++) {
			for(int y = 0; y < VarGlobalVista.HEIGTH_PANTALLA_GAME; y++) {
				if(dibujablesDelMundo[x][y] != null) {
					dibujablesDelMundo[x][y].dibujar(r);
				}

			}
		}
		for (PopUpInfo popUp : popUpsInfo) {
			popUp.dibujar(r);
		}
	}
	public  void addAgente(ObjetoDistribuido agente) {
		Agente ag = (Agente) agente;
		dibujablesDelMundo[ag.getX()][ag.getY()] = (Dibujable) ag;
		setContadorAgentes(getContadorAgentes() + 1);
	}



	@Override
	public int getX() {
		return 0;
	}



	@Override
	public int getY() {
		return 0;
	}




	@Override
	public void init() {


	}



	public boolean celdaVacia(int newX, int newY) {
		if(dibujablesDelMundo[newX][newY]==null) {
			return true;
		}else {
			return false;
		}
	}



	public synchronized boolean addPopUp(int x, int y) {
		int d0x = x - 15;
		int d1x = x + 15;
		int d0y = y - 15;
		int d1y = y + 15;

		if(d0x<0) {
			d0x = 0;
		}
		else if(d1x>=VarGlobalVista.WIDHT_PANTALLA_GAME) {
			d1x = VarGlobalVista.WIDHT_PANTALLA_GAME-1;
		}
		if(d0y<0) {
			d0y = 0;
		}
		else if(d1y>=VarGlobalVista.HEIGTH_PANTALLA_GAME) {
			d1y = VarGlobalVista.HEIGTH_PANTALLA_GAME-1;
		}
		for(int i = d0x; i < d1x; i++) {
			for(int j = d0y; j < d1y; j++) {
				if(dibujablesDelMundo[i][j]!=null) {
					Dibujable dibu = dibujablesDelMundo[i][j];
					popUpsInfo.add(new PopUpInfo(dibu));
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



	@Override
	public String info() {
		return "N�mero de agentes en el mundo: "+contadorAgentes+
				"#Tama�o del mundo: "+VarGlobalVista.WIDHT_PANTALLA_GAME+"x"+VarGlobalVista.HEIGTH_PANTALLA_GAME;
	}



	public int getContadorAgentes() {
		return contadorAgentes;
	}



	public void setContadorAgentes(int contadorAgentes) {
		this.contadorAgentes = contadorAgentes;
	}



	public boolean moverAgente(int x2, int y2, ObjetoDistribuido agente) throws RemoteException {

		Agente ag = (Agente) agente;	
		Agente ag2 = (Agente) dibujablesDelMundo[ag.getX()][ag.getY()];


		synchronized (dibujablesDelMundo) {
			synchronized (dibujablesDelMundo) {
				if(celdaVacia(x2, y2)&&dibujablesDelMundo[ag.getX()][ag.getY()] != null) {
					dibujablesDelMundo[ag.getX()][ag.getY()] = null;
					dibujablesDelMundo[x2][y2] = ag2;
					ag2.setX(x2);
					ag2.setY(y2);
					return true;
				}
				return false;
			}
		}






	}









}
