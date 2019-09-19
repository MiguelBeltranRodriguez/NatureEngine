package NatureEngine.Modelo;



import java.awt.Color;


public class CasillaTierra extends Casilla {
	
	public CasillaTierra(int x, int y, int humedad) {
		super(x, y, humedad);
		color = new Color(163-humedad,150-humedad,96);
	}


}
