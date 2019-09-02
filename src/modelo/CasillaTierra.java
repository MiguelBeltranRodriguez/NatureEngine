package modelo;



import java.awt.Color;


public class CasillaTierra extends Casilla {
	
	public CasillaTierra(int x, int y, int humedad) {
		super(x, y, humedad);
		color = new Color(107+humedad,97+humedad,57);
	}


}
