package NatureEngine.Utils;

import java.awt.Color;

public class VarGlobalGame {

	public final static int FPS =30; 
	public static double DELTA = 0;
	public static int TICKS_S = 0;
	public final static long UNIDAD_DE_TIEMPO_BASE_MS = 10;
	public final static String RUTA_MAPA = "./assets/mapa1.csv";
	public final static int PORT_CONTROLLER = 6005;
	
	public final static Color[] dibujoCasillas = {
			new Color(241, 243, 25), //0-10
			new Color(223, 222, 25), //10-20
			new Color(203, 203, 25), //20-30
			new Color(182, 178, 25), //30-40
			new Color(155, 155, 25), //40-50
			new Color(134, 132, 25), //50-60
			new Color(106, 105, 25), //70-80
			new Color(0, 2, 71), //80-90
			new Color(0, 2, 71), //90-100
			new Color(0, 2, 71), //100
	};
	
}