package NatureEngine.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase para entrada y salida de archivos.
 */
public class ManejadorArchivos {

	/** Singleton */
	private static ManejadorArchivos ma;
	
	/** Caracter para separar cadenas. */
	public final static String SEPARADOR = ",";
	
	/**
	 * Constructor del manejador de archivos.
	 */
	private ManejadorArchivos() {}
	
	
	/**
	 * Singleton.
	 *
	 * @return El manejador de archivos
	 */
	public static ManejadorArchivos getManejadorArchivos() {
		if(ma == null) {
			ma = new ManejadorArchivos();
		}
		return ma;
	}
	
	/**
	 * Cargar un archivo con una ruta.
	 *
	 * @param ruta la ruta del archivo
	 * @return el BufferedReader para leer el archivo
	 */
	public BufferedReader cargarArchivoLectura(String ruta) {
		BufferedReader br = null;
		 try {
			br =new BufferedReader(new FileReader(ruta));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}


	/**
	 * Cerrar un archivo abierto.
	 *
	 * @param br el BufferedReader del archivo
	 */
	public static void cerrarArchivo(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
