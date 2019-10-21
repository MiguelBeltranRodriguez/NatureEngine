package NatureEngine.Utils;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * Clase de variables utilizadas por todos los paquetes.
 */
public class VarGlobalGame {

	/** FPS */
	public final static int FPS =30; 
	
	/** Delta de los FPS */
	public static double DELTA = 0;
	
	public static int FRECUENCIA_TICKS_CONSUMO = 100;
	
	/** Ticks por segundo */
	public static int TICKS_S = 0;
	
	public static int DELAY = 10;
	
	public static int MIU_DE_FRICCION = 100;
	
	public final static int UNIDAD_TIEMPO_VELOCIDAD = DELAY * FRECUENCIA_TICKS_CONSUMO;
	
	/** Ruta del mapa por defecto. */
	public final static String RUTA_MAPA = "./assets/mapa1.csv";
	
	/** Puerto del NatureEngineController */
	public final static int PORT_CONTROLLER = 6005;
	
	/** Colores para dibujar las casillas con humedad del 0-100 */
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
			new Color(0, 2, 71), //100
	};
	
	/** Color de las plantas */
	public final static Color colorPlantas = new Color(0,220,0);
	
	/** Coheficiente de atributos Agua */
	public final static int POTENCIA_HUMEDAD = 2;	 	
	public final static int COHEFICIENTE_VELOCIDAD_ACTUAL = 2;
	
	/** Operaciones de calculo */
	public final static int EXPONENTE = 0;
	public final static int INVERSO = 1;
	public final static int MULTIPLICACION = 2;
	public final static int DIVISION = 3;
	
	/** Coheficiente de atributos Energia */
	public final static int COHEFICIENTE_ENERGIA_MAXIMA = 1000;
	public final static int COHEFICIENTE_POTENCIA_ACTUAL = 2;	
	public final static int COHEFICIENTE_AGUA_MAXIMA = 1000;
	public final static int COHEFICIENTE_PERCEPCION = 1;
	public final static int COHEFICIENTE_TAMAÑO_ACTUAL = 2;
	public final static int COHEFICIENTE_TOLERANCIA_HUMEDAD = 1;
	public final static float COHEFICIENTE_GRADO_CRECIMIENTO = (float) 0.5;
	public final static int COHEFICIENTE_GRADO_ENVEJECIMIENTO = 10;

	/** Coheficiente en los Desires */
	public final static float UMBRAL_HAMBRE = (float) 0.5;
}