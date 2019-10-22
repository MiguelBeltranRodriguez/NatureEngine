package NatureEngine.Modelo.Desires;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.Casilla;
import NatureEngine.Modelo.Planta;
import NatureEngine.Modelo.Intentions.AlimentarsePlanta;
import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.Modelo.Intentions.MoverseA;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Dibujable;
import NatureEngine.Utils.DiccionarioDePalabras;
import NatureEngine.Utils.VarGlobalGame;

public class DesireAlimentarme extends Desire {
	private Dibujable objetivo;

	public DesireAlimentarme(Agente agente) {
		super();
		this.agente = agente;
		this.intenciones = new Stack<Intention>();
	}

	@Override
	public void init(Desire desire) {
		this.llenarPila();
	}

	private void llenarPila() {
		if (this.agente.isEsHerbivoro()) {
			this.intenciones.push(new AlimentarsePlanta(this.agente, this.objetivo));
		}
		
		Intention intencionMoverse = new MoverseA(this.agente, this.objetivo.getX(), this.objetivo.getY());
		if(!intencionMoverse.isFinalizado()){
			this.intenciones.push(intencionMoverse);
		}
	}

	@Override
	public boolean tengoCapacidad() {
		List<ObjetoDistribuido> percepciones = agente.getPercepciones();
		
		if (this.tengoHambre()) {
			if (this.agente.isEsHerbivoro()) {
				this.plantaObjetivo(percepciones);
			} else if (this.agente.isEsCarnivoro()) {				
				this.presaObjetivo(percepciones);
			} else {
				// TODO: Cuando se es oniboro
			}
		}

		return (this.objetivo != null);
	}

	private boolean tengoHambre() {
		float porcetajeEnergia = VarGlobalGame.UMBRAL_HAMBRE * (float) this.agente.getCaracteristicaHeredable(AtributosBasicos.ENERGIA_MAXIMA_);		
		
		return (this.agente.getEnergiaActual() < porcetajeEnergia);
	}

	private void plantaObjetivo(List<ObjetoDistribuido> percepciones) {
		for (ObjetoDistribuido percepcion : percepciones) {
			Casilla casilla = (Casilla)percepcion;
			Planta planta = (Planta)casilla.getElementoTipo(DiccionarioDePalabras.PLANTA);

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
	
	private boolean esPresa(Agente presa) {
		boolean distintaEspecie = true; // TODO: poner funcion del genoma
		boolean esPequeño = true;// (this.agente.getTamañoActual() > presa.getTamañoActual());
		
		return (distintaEspecie && esPequeño);
	}
	
	private void presaObjetivo(List<ObjetoDistribuido> percepciones) {
		for (ObjetoDistribuido percepcion : percepciones) {
			Casilla casilla = (Casilla)percepcion;
			Agente presa = (Agente)casilla.getElementoTipo(DiccionarioDePalabras.AGENTE);

			if(presa!=null) {
				if(this.esPresa(presa)) {
					if (this.objetivo == null) {
						this.objetivo = presa;
					} else if (Desire.calcularDistancia(presa, this.agente) < Desire.calcularDistancia(this.objetivo, this.agente)) {
						this.objetivo = presa;		
					}
				}				
			}
			// TODO: heuristica quien me da mas energia incluyendo la distancia
		}
	}

}
