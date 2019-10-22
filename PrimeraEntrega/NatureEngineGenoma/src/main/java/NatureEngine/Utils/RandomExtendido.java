package NatureEngine.Utils;

import java.util.Random;

public class RandomExtendido {

	/**
	 * Randoms mejorados
	 * 
	 * @author Daniel Nioto
	 *
	 * 
	 */

	public boolean RandomBooleanoConLimite(Float probabilidadTrue) {
		Float valorRandom = new Random().nextFloat();
		Float limite = 0.5f;
		if (probabilidadTrue != null) {
			if (!(probabilidadTrue < 0 || probabilidadTrue > 1)) {
				limite = probabilidadTrue;
			}
		}
		if (valorRandom > limite) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Float ObjectAFloat(Object object) throws Exception {
		if(object==null) {
			throw new Exception ("Objeto no puede ser null");
		}
		
		Float result=null;
		if(object instanceof Boolean) {
			Boolean tmp = (Boolean)object;
			result = (float)(tmp?1:0);
		}else {
			if(object instanceof Float) {
				result = (float)object;
			}else {
				Integer tmp = (Integer)object;
				result = (float)tmp;
			}
		}

		
		return result;
	}

	public Float RandomFloatGaussianoDePorcentaje(Float valorInicial, Float variabilidad) {
		return RandomGaussianoLimitadoFloat(valorInicial, variabilidad, 0f, 1f);
	}
	

	public Float RandomFloatGaussianoBasico(Float promedio, Float desvest) {
		Float max = promedio+(desvest/2);
		Float min = promedio-(desvest/2);
		Float gaussianrandom = (float) new Random().nextGaussian();
		gaussianrandom = min + gaussianrandom * (max-min);
		return gaussianrandom;
	}


	public Float RandomGaussianoLimitadoFloat(Float valorInicial, Float variabilidad, Float limiteminimo,
			Float limitemaximo) {
		if (limiteminimo != null) {
			if (valorInicial < limiteminimo) {
				valorInicial = limiteminimo;
			}
		}
		if (limitemaximo != null) {
			if (valorInicial > limitemaximo) {
				valorInicial = limitemaximo;
			}
		}
		Float valorRandom = RandomFloatGaussianoBasico(valorInicial,variabilidad);
		if (limiteminimo != null) {
			if (valorRandom < limiteminimo) {
				valorRandom = limiteminimo;
			}
		}
		if (limitemaximo != null) {
			if (valorRandom > limitemaximo) {
				valorRandom = limitemaximo;
			}
		}
		return valorRandom;
	}

}
