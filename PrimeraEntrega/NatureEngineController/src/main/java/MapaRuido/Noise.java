package MapaRuido;

/**
 * Generador del mapa de ruido Perlin
 * Llama al agoritmo de ruido Perlin con los valores generados segun una funcion especifica
 * (puede modificarse para crear mas aleatoriedad en el mapa) y caulcula el resultado final
 * sumando los valores de las octavas, cuyo numero es definido por el usuario
 * 
 * Basado en: 
 * Procedural Landmass Generation (E02: Noise Map) -https://www.youtube.com/watch?v=WP-Bm65Q-1Y&t=207s- 
 * 
 * @author Andrea Gutierrez
 *
 * Fecha	Autor				Descripcion
 * 20191020	Andrea Gutierrez	Version inicial
 */
public class Noise {
	public static float[][] generateNoiseMap(int mapWidth, int mapHeight, float scale, int octaves){
		float[][] noiseMap = new float[mapWidth][mapHeight];
		
		float sampleX;
		float sampleY;
		float perlinValue;
		
		if (scale <= 0) {
			scale = 0.0001f;
		}
		
		for (int y = 0; y < mapHeight; y++) {
			for (int x= 0; x < mapWidth; x++) {
				noiseMap[x][y] = 0f;			
			}
		}
		
		
		for (int s = 1; s <= octaves ; s++)
		{
			for (int y = 0; y < mapHeight; y++) {
				for (int x= 0; x < mapWidth; x++) {
					sampleX = ((float)(Math.random())+x+s) / (scale*s);
					sampleY = ((float)(Math.random())+y+s) / (scale*s);

					//Usando Z=0, por ser solo dos dimensiones
					perlinValue = (float) (Math.abs((ImprovedNoise.noise(sampleX, sampleY, 0))));					
					noiseMap[x][y] += perlinValue;
				}
			}
		}
		
		//Para que los valores entregados sean entre 0 y 1
		for (int y = 0; y < mapHeight; y++) {
			for (int x= 0; x < mapWidth; x++) {
				noiseMap[x][y] = noiseMap[x][y] / octaves;			
			}
		}
		
		return noiseMap;
		
	}
}
