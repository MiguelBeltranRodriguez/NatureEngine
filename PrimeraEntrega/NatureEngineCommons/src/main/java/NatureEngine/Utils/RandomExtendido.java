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

	public Float RandomGaussianoDePorcentaje(Object valorInicial, Object variabilidad) {
		return RandomGaussianoDeObject(valorInicial, variabilidad, 0, 1);
	}
	
	
	public Integer RandomGaussianoDeEntero(Object valorInicial, Object variabilidad, Object limiteminimo,
			Object limitemaximo) {
		Float valor = RandomGaussianoDeObject(valorInicial, variabilidad, limiteminimo, limitemaximo);
		return (int) Math.round(valor);
	}

	public Float RandomGaussianoDeObject(Object valorInicial, Object variabilidad, Object limiteminimo,
			Object limitemaximo) {
		if (!(valorInicial instanceof Number)) {
			throw new ClassCastException("valorInicial no es un número");
		}
		if (!(variabilidad instanceof Number)) {
			throw new ClassCastException("variabilidad no es un número");
		}
		if (!(limiteminimo instanceof Number)) {
			throw new ClassCastException("limiteminimo no es un número");
		}
		if (!(limitemaximo instanceof Number)) {
			throw new ClassCastException("limitemaximo no es un número");
		}
		Float valorRandomGaussianoLimitadoFloat = RandomGaussianoLimitadoFloat((float) valorInicial,
				(float) variabilidad, (float) variabilidad, (float) variabilidad);
		return valorRandomGaussianoLimitadoFloat;
	}

	public Float RandomGaussianoBasico(Float promedio, Float desvest) {
		Float gaussianrandom = (float) new Random().nextGaussian();
		gaussianrandom = (gaussianrandom * desvest) + promedio;
		return gaussianrandom;
	}

	public boolean RandomBooleanoGaussiano(Object probabilidadTrue) {
		if (!(probabilidadTrue instanceof Number)) {
			throw new ClassCastException("valorInicial no es un número");
		}
		Float probTrue = (float) probabilidadTrue;
		Float valorRandom = new Random().nextFloat();
		Float limite = 0.5f;
		if (probTrue != null) {
			if (!(probTrue < 0 || probTrue > 1)) {
				limite = probTrue;
			}
		}
		if (valorRandom > limite) {
			return true;
		} else {
			return false;
		}
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
		Float valorRandom = RandomGaussianoBasico(variabilidad, valorInicial);
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
