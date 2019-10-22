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

	public Float RandomFloatGaussianoDePorcentaje(Float valorInicial, Float variabilidad) {
		return RandomGaussianoLimitadoFloat(valorInicial, variabilidad, 0f, 1f);
	}
	

	public Float RandomFloatGaussianoBasico(Float promedio, Float desvest) {
		Float gaussianrandom = (float) new Random().nextGaussian();
		gaussianrandom = (gaussianrandom * desvest) + promedio;
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
		Float valorRandom = RandomFloatGaussianoBasico(variabilidad, valorInicial);
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
