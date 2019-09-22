package NatureEngine.Modelo;


import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.Utils.VarGlobalGame;
import NatureEngine.Utils.VarGlobalVista;




public class Casilla implements Dibujable {

	protected int x;
	protected int y;
	protected float humedadBase;
	protected float humedadActual;
	protected float humedadAnterior;
	public Casilla(int x, int y, float humedad) {
		this.x = x;
		this.y = y;
		this.humedadBase = humedad;
		humedadActual = humedad;
		humedadAnterior = humedadActual;
		
	}

	public void dibujarCasillas(Renderizador2D r) {
		int texturaHumedad = (int) (humedadActual/10);
		r.dibujarRectangulo(VarGlobalGame.dibujoCasillas[texturaHumedad], x, y, VarGlobalVista.TAMANO_TEXTURA, VarGlobalVista.TAMANO_TEXTURA);
		
		
	}

	
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}


	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	

	public float getHumedad() {
		return humedadBase;
	}

	public void setHumedad(float humedad) {
		this.humedadBase = humedad;
	}

	

	public float getHumedadActual() {
		return humedadActual;
	}

	public void setHumedadActual(float humedadActual) {
		this.humedadActual = humedadActual;
	}

	@Override
	public void dibujar(Renderizador2D r) {
		// TODO Auto-generated method stub
		
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
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String info() {
		
		return "Sin info";
	}

	@Override
	public void update() {
	}

	public void cambiarHumedad(float estacion) {
		humedadAnterior = humedadActual;
		humedadActual = humedadBase * estacion;
		if(humedadActual>100f) {
			humedadActual = 100;
		}
		if(humedadActual<0f) {
			humedadActual = 0;
		}
	}

	public float getHumedadBase() {
		return humedadBase;
	}

	public void setHumedadBase(float humedadBase) {
		this.humedadBase = humedadBase;
	}

	public float getHumedadAnterior() {
		return humedadAnterior;
	}

	public void setHumedadAnterior(float humedadAnterior) {
		this.humedadAnterior = humedadAnterior;
	}
	
}
