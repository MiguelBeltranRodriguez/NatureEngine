package NatureEngine.Modelo.Desires;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import NatureEngine.Modelo.Casilla;
import NatureEngine.Modelo.Planta;
import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.Modelo.Intentions.MoverseA;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;

public class DesireAlimentarme extends Desire {
	private Dibujable objetivo;

	public DesireAlimentarme(Agente agente) {
		super();
		this.agente = agente;
		this.intenciones = new Stack<Intention>();
		
	}
	
	@Override
	public void init(Desire desire) {
		if(desire instanceof DesireAlimentarme) {
			DesireAlimentarme desireAlim = (DesireAlimentarme) desire;
			if(desireAlim.objetivo.equals(this.objetivo)) {
				this.intenciones = desire.getIntenciones();
			}else {
				this.llenarPila();
			}
		}else {
			this.llenarPila();
		}
	}
	
	private void llenarPila() {
		this.intenciones.push(new MoverseA(agente, objetivo.getX(), objetivo.getY()));
	}

	

	@Override
	public boolean tengoHabilidad() {
		List<ObjetoDistribuido> percepciones = agente.getPercepciones();

		boolean esHerviboro = true; // Depende de las caracteristicas heredables

		boolean esCarnivoro = false; // Depende de las caracteristicas heredables

		boolean tengoHambre = true; // Eso va en believes
		
		if (tengoHambre) {
			if (esHerviboro) {
				this.plantaObjetivo(percepciones);
				if(this.objetivo == null) {
					return false;
				} else {
					return true;
				}
			} else if (esCarnivoro) {
				// TODO: logica
			} else {
				// TODO: logica cuando es obniboro depende de que tanta habre tiene
			}
		} else {
			return false;
		}
		return false;
	}

	private void plantaObjetivo(List<ObjetoDistribuido> percepciones) {
		for (ObjetoDistribuido c : percepciones) {
			Casilla casilla = (Casilla)c;
			// TODO agregar esta palabra al diccionario en commons	
			Planta planta = (Planta)casilla.getElementoTipo("planta");
			if(planta!=null) {
				if (this.objetivo==null) {
					this.objetivo = planta;
				} else if (Desire.calcularDistancia(planta, this.agente) < (Desire.calcularDistancia(this.objetivo, this.agente))) {
					this.objetivo = planta;
				}
			}
			// TODO: heuristica quien me da mas energia incluyendo la distancia
		}
	}

	

}
