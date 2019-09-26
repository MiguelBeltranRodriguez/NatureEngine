package NatureEngine.Modelo;


import java.io.BufferedReader;
import java.io.IOException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private Casilla [][] casillasDelMundo;
	private List<PopUpInfo> popUpsInfo;
	private List<Planta> plantasMundo;
	private int contadorAgentes;
	private float energiaMaxima;
	private float energiaActual;
	private float estacion;
	private float estacionMinima;
	private float estacionMaxima;
	private int numeroDePlantasMaxima;
	private int frecuenciaEstacion;
	private int tiempoActual;
	private int direccionCambioEstacion;
	private float velocidadCambio;
	private float energiaMaximaPorPlanta;
	private float consumoEnergiaPlanta;
	
	public Mundo() {
		casillasDelMundo = new Casilla[VarGlobalVista.widht_pantalla_map][VarGlobalVista.heigth_pantalla_map];
		dibujablesDelMundo = new Dibujable[VarGlobalVista.widht_pantalla_map][VarGlobalVista.heigth_pantalla_map];
		for(int x = 0; x < VarGlobalVista.widht_pantalla_map; x++) {
			for(int y = 0; y < VarGlobalVista.heigth_pantalla_map; y++) {
				dibujablesDelMundo[x][y] = null;
			}
		}
		popUpsInfo = new ArrayList<PopUpInfo>();
		plantasMundo = new ArrayList<Planta>();
		setContadorAgentes(0);
		energiaActual = 0;
		estacion = 1.0f;
		tiempoActual = 0;
		direccionCambioEstacion = -1;
		cargarMapa();
	}



	private void cargarMapa() {
		BufferedReader br = ManejadorArchivos.getManejadorArchivos().cargarArchivoLectura(VarGlobalGame.RUTA_MAPA);

		try {
			String line = br.readLine();
			String sTamano[] = line.split(ManejadorArchivos.SEPARADOR);
			int widht = Integer.parseInt(sTamano[1]);
			int heigth = Integer.parseInt(sTamano[2]);
			line = br.readLine();
			String sEnergiaMaxia[] = line.split(ManejadorArchivos.SEPARADOR);
			energiaMaxima = Float.parseFloat(sEnergiaMaxia[1]);
			line = br.readLine();
			String sEstacionMinima[] = line.split(ManejadorArchivos.SEPARADOR);
			estacionMinima = Float.parseFloat(sEstacionMinima[1]);
			line = br.readLine();
			String sEstacionMaxima[] = line.split(ManejadorArchivos.SEPARADOR);
			estacionMaxima = Float.parseFloat(sEstacionMaxima[1]);
			line = br.readLine();
			String sPlantasMaxima[] = line.split(ManejadorArchivos.SEPARADOR);
			numeroDePlantasMaxima = Integer.parseInt(sPlantasMaxima[1]);
			line = br.readLine();
			String sFrecuencua[] = line.split(ManejadorArchivos.SEPARADOR);
			frecuenciaEstacion = Integer.parseInt(sFrecuencua[1]);
			line = br.readLine();
			String sVelocidadCambio[] = line.split(ManejadorArchivos.SEPARADOR);
			velocidadCambio = Float.parseFloat(sVelocidadCambio[1]);
			line = br.readLine();
			String sVelocidadConsumoPlanta[] = line.split(ManejadorArchivos.SEPARADOR);
			consumoEnergiaPlanta = Float.parseFloat(sVelocidadConsumoPlanta[1]);
			line = br.readLine();
			System.out.println(line);
			for(int i = 1; i <= (heigth/VarGlobalVista.TAMANO_TEXTURA_CUADRICULA); i++) {
				line = br.readLine();		
				String sLinea[] = line.split(ManejadorArchivos.SEPARADOR);
				for(int j = 1; j <= (widht/VarGlobalVista.TAMANO_TEXTURA_CUADRICULA); j++) {
					float humedad = Float.parseFloat(sLinea[j-1]);
					float humedadI = (humedad*100);
					for(int x = (j*VarGlobalVista.TAMANO_TEXTURA_CUADRICULA)-VarGlobalVista.TAMANO_TEXTURA_CUADRICULA; x<(j*VarGlobalVista.TAMANO_TEXTURA_CUADRICULA);x++ ) {
						for(int y = (i*VarGlobalVista.TAMANO_TEXTURA_CUADRICULA)-VarGlobalVista.TAMANO_TEXTURA_CUADRICULA; y<(i*VarGlobalVista.TAMANO_TEXTURA_CUADRICULA);y++ ) {
							if(humedadI>=80) {
								casillasDelMundo[x][y] = new CasillaAgua(x, y, humedadI);
							}else {
								casillasDelMundo[x][y] = new CasillaTierra(x, y, humedadI);
							}

						}
					}
				}

			}
			energiaMaximaPorPlanta = energiaMaxima / numeroDePlantasMaxima;
			dibujablesDelMundo[20][50] = new Planta(energiaMaximaPorPlanta, 20, 50);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 

		System.out.println("Salio");


		ManejadorArchivos.cerrarArchivo(br);
		generarPlantas();
	}


	private void generarPlantas() {
		while(plantasMundo.size()<numeroDePlantasMaxima) {
			ponerPlantaAleatoria();		
		}
	}



	private void ponerPlantaAleatoria() {
		Random rX = new Random();
		Random rY = new Random();
		int x = rX.nextInt(VarGlobalVista.widht_pantalla_map);
		int y = rY.nextInt(VarGlobalVista.heigth_pantalla_map);
		if(dibujablesDelMundo[x][y]==null) {
			Casilla c = casillasDelMundo[x][y];
			Random rP = new Random();
			int probabilidad = rP.nextInt(100);
			if(c instanceof CasillaAgua) {
				if(probabilidad<c.humedadActual-90) {
					agregarPlanta(x,y);
				}
			}else {
				if(probabilidad<c.humedadActual) {
					agregarPlanta(x, y);
				}
			}
			
		}
	}



	private void agregarPlanta(int x, int y) {
		Planta planta = new Planta(energiaMaximaPorPlanta, x, y);
		dibujablesDelMundo[x][y]= planta;
		energiaActual = energiaActual + energiaMaximaPorPlanta;
		plantasMundo.add(planta);
	}



	@Override
	public void update() {
			tiempoActual++;
			
			if(tiempoActual%frecuenciaEstacion==0) {
				cambioHumedad();
			}
			reducirEnergíaPlantaAlAzar();
			if(energiaActual<=energiaMaxima-energiaMaximaPorPlanta) {
				ponerPlantaAleatoria();
			}
	}
	private void reducirEnergíaPlantaAlAzar() {
		Random r = new Random();
		int indice = r.nextInt(plantasMundo.size());
		Planta plantaAReducir = plantasMundo.get(indice);
		boolean murio = plantaAReducir.reducirEnergia(consumoEnergiaPlanta);
		energiaActual = energiaActual - consumoEnergiaPlanta;
		if(murio) {
			matarPlanta(plantaAReducir);
		}
	}



	private void matarPlanta(Planta plantaAReducir) {
		plantasMundo.remove(plantaAReducir);
		dibujablesDelMundo[plantaAReducir.getX()][plantaAReducir.getY()] = null;
	}



	private void cambioHumedad() {
		estacion = estacion+(velocidadCambio*direccionCambioEstacion);
		if(estacion >= estacionMaxima) {
			direccionCambioEstacion = -1;
		}else if(estacion <= estacionMinima){
			direccionCambioEstacion = 1;
		}
		for(int x = 0; x < VarGlobalVista.widht_pantalla_map; x++) {
			for(int y = 0; y < VarGlobalVista.heigth_pantalla_map; y++) {
				casillasDelMundo[x][y].cambiarHumedad(estacion);
				float humedadAnterior = casillasDelMundo[x][y].getHumedadAnterior();
				float humedadActual = casillasDelMundo[x][y].getHumedadActual();
				float humedadBase = casillasDelMundo[x][y].getHumedadBase();
				if(humedadAnterior<80f&&humedadActual>=80f) {
					casillasDelMundo[x][y] = new CasillaAgua(x, y, humedadBase);
					casillasDelMundo[x][y].setHumedadAnterior(humedadAnterior);
					casillasDelMundo[x][y].setHumedadActual(humedadActual);
				}else if(humedadAnterior>=80f&&humedadActual<80f) {
					casillasDelMundo[x][y] = new CasillaTierra(x, y, humedadBase);
					casillasDelMundo[x][y].setHumedadAnterior(humedadAnterior);
					casillasDelMundo[x][y].setHumedadActual(humedadActual);
				}
			}
		}
	}



	@Override
	public synchronized  void dibujar(Renderizador2D r) {
		for(int x = 0; x < VarGlobalVista.widht_pantalla_map/VarGlobalVista.TAMANO_TEXTURA_CUADRICULA; x++) {
			for(int y = 0; y < VarGlobalVista.heigth_pantalla_map/VarGlobalVista.TAMANO_TEXTURA_CUADRICULA; y++) {
				casillasDelMundo[x*8][y*8].dibujarCasillas(r);
			}
		}
		for(int x = 0; x < VarGlobalVista.widht_pantalla_map; x++) {
			for(int y = 0; y < VarGlobalVista.heigth_pantalla_map; y++) {
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
	public void init() {


	}



	public synchronized boolean celdaVacia(int newX, int newY) {
		if(dibujablesDelMundo[newX][newY]==null) {
			return true;
		}else {
			return false;
		}
	}



	public boolean addPopUp(int x, int y) {
		int d0x = x - 15;
		int d1x = x + 15;
		int d0y = y - 15;
		int d1y = y + 15;

		if(d0x<0) {
			d0x = 0;
		}
		else if(d1x>=VarGlobalVista.widht_pantalla_map) {
			d1x = VarGlobalVista.widht_pantalla_map-1;
		}
		if(d0y<0) {
			d0y = 0;
		}
		else if(d1y>=VarGlobalVista.heigth_pantalla_map) {
			d1y = VarGlobalVista.heigth_pantalla_map-1;
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




	public synchronized void moverAgente(int x2, int y2, ObjetoDistribuido agente) throws RemoteException {

		Agente ag = (Agente) agente;	
		Agente ag2 = (Agente) dibujablesDelMundo[ag.getX()][ag.getY()];

		if(celdaVacia(x2, y2)) {
			dibujablesDelMundo[ag.getX()][ag.getY()] = null;
			dibujablesDelMundo[x2][y2] = ag2;
			ag2.setX(x2);
			ag2.setY(y2);
		}
		
		
		

	}



	
	@Override
	public void Resaltar() {	
	}



	@Override
	public void DesResaltar() {
	}



	@Override
	public String info() {
		String cambioClima = "Normal";
		if(direccionCambioEstacion==1) {
			cambioClima = "Aumentando";
		}else if(direccionCambioEstacion==-1) {
			cambioClima = "Disminuyendo";
		}
		return "Número de agentes en el mundo: "+contadorAgentes+
				"#Tamaño del mundo: "+VarGlobalVista.widht_pantalla_map+"x"+VarGlobalVista.heigth_pantalla_map+
				"#Tiempo actual "+this.tiempoActual+"años"+
				"#CambioHumedad: "+cambioClima+
				"#Número de plantas: "+plantasMundo.size()+
				"#Energía actual: "+energiaActual;
	}



	public int getContadorAgentes() {
		return contadorAgentes;
	}



	public void setContadorAgentes(int contadorAgentes) {
		this.contadorAgentes = contadorAgentes;
	}





	public Dibujable[][] getDibujablesDelMundo() {
		return dibujablesDelMundo;
	}



	public void setDibujablesDelMundo(Dibujable[][] dibujablesDelMundo) {
		this.dibujablesDelMundo = dibujablesDelMundo;
	}



	public Casilla[][] getObjetosDelMundo() {
		return casillasDelMundo;
	}



	public void setObjetosDelMundo(Casilla[][] objetosDelMundo) {
		this.casillasDelMundo = objetosDelMundo;
	}



	public List<PopUpInfo> getPopUpsInfo() {
		return popUpsInfo;
	}



	public void setPopUpsInfo(List<PopUpInfo> popUpsInfo) {
		this.popUpsInfo = popUpsInfo;
	}



	public float getEnergiaMaxima() {
		return energiaMaxima;
	}



	public void setEnergiaMaxima(float energiaMaxima) {
		this.energiaMaxima = energiaMaxima;
	}



	public float getEnergiaActual() {
		return energiaActual;
	}



	public void setEnergiaActual(float energiaActual) {
		this.energiaActual = energiaActual;
	}



	public float getEstacion() {
		return estacion;
	}



	public void setEstacion(float estacion) {
		this.estacion = estacion;
	}



	public int getNumeroDePlantasMaxima() {
		return numeroDePlantasMaxima;
	}



	public void setNumeroDePlantasMaxima(int numeroDePlantasMaxima) {
		this.numeroDePlantasMaxima = numeroDePlantasMaxima;
	}




	@Override
	public int getX() {
		return 0;
	}



	@Override
	public int getY() {
		return 0;
	}



	public float getEstacionMinima() {
		return estacionMinima;
	}



	public void setEstacionMinima(float estacionMinima) {
		this.estacionMinima = estacionMinima;
	}



	public float getEstacionMaxima() {
		return estacionMaxima;
	}



	public void setEstacionMaxima(float estacionMaxima) {
		this.estacionMaxima = estacionMaxima;
	}



	public int getFrecuenciaEstacion() {
		return frecuenciaEstacion;
	}



	public void setFrecuenciaEstacion(int frecuenciaEstacion) {
		this.frecuenciaEstacion = frecuenciaEstacion;
	}



	public int getTiempoActual() {
		return tiempoActual;
	}



	public void setTiempoActual(int tiempoActual) {
		this.tiempoActual = tiempoActual;
	}



	public int getDireccionCambioEstacion() {
		return direccionCambioEstacion;
	}



	public void setDireccionCambioEstacion(int direccionCambioEstacion) {
		this.direccionCambioEstacion = direccionCambioEstacion;
	}



	public float getVelocidadCambio() {
		return velocidadCambio;
	}



	public void setVelocidadCambio(float velocidadCambio) {
		this.velocidadCambio = velocidadCambio;
	}

		
}
