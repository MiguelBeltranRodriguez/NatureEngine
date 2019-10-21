package MapaRuido;

/**
 * Generador del mapa
 * Clase a la que se le asignan los parametros de usuario y que llama a la clase que
 * genera el mapa de ruido
 * 
 * Basado en: 
 * Procedural Landmass Generation (E02: Noise Map) -https://www.youtube.com/watch?v=WP-Bm65Q-1Y&t=207s- 
 * 
 * @author Andrea Gutierrez
 *
 * Fecha	Autor				Descripcion
 * 20191020	Andrea Gutierrez	Version inicial
 */
public class MapGenerator {
	public int mapWidth;
	public int mapHeight;
	public float noiseScale;
	
	public int octaves;
	
	public float[][]  GenerateMap(){
		float[][] noiseMap = Noise.generateNoiseMap(mapWidth, mapHeight, noiseScale, octaves);

		return noiseMap;
	}
}
