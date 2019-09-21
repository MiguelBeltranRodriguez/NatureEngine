package NatureEngine.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ManejadorArchivos {

	private static ManejadorArchivos ma;
	public final static String SEPARADOR = ",";
	
	private ManejadorArchivos() {}
	
	
	public static ManejadorArchivos getManejadorArchivos() {
		if(ma == null) {
			ma = new ManejadorArchivos();
		}
		return ma;
	}
	public BufferedReader cargarArchivo(String ruta) {
		BufferedReader br = null;
		 try {
			br =new BufferedReader(new FileReader(ruta));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}


	public static void cerrarArchivo(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
