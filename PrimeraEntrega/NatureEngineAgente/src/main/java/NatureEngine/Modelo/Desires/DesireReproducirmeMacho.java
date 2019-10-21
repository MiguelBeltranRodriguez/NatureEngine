package NatureEngine.Modelo.Desires;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Stack;

import NatureEngine.Mensajeria.Mensaje;
import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.Casilla;
import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.Modelo.Intentions.MoverseA;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.Utils.DiccionarioDePalabras;

public class DesireReproducirmeMacho extends Desire {
	private Agente agenteHembra;
	
	
	public DesireReproducirmeMacho(Agente agente) {
		this.agente = agente;
		this.intenciones = new Stack<Intention>();
	}
	
	
	@Override
	public boolean tengoHabilidad() {
		
		List<ObjetoDistribuido> percepciones = agente.getPercepciones();
		
		hembraObjetivo(percepciones);
		if(agenteHembra != null && this.agente.getEdadActual()>=(int)this.agente.getCaracteristicaHeredable(AtributosBasicos.MADUREZ_REPRODUCTIVA)) {
			try {
				Mensaje respuesta = agenteHembra.enviarCortejo(new Mensaje(agente.getID(), 5, DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION, this.agente, agenteHembra));
				
				if(respuesta.getTipo().equals(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCIRME_ACK)) {
					return true;
				}else {
					return false;
				}
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		return false;
	}

	@Override
	public void init(Desire desireAnterior) {
		/*
		if(desireAnterior instanceof DesireReproducirmeMacho) {
			DesireReproducirmeMacho desireAlim = (DesireReproducirmeMacho) desireAnterior;
			if(desireAlim.agenteHembra.getID().equals(agenteHembra.getID())) {
				this.agenteHembra = desireAlim.agenteHembra;
				this.intenciones = desireAnterior.getIntenciones();
			}else {
				this.llenarPila();
			}
		}else {
			this.llenarPila();
		}*/
		this.llenarPila();
	}
	private void llenarPila() {
		this.intenciones.push(new MoverseA(agente, agenteHembra.getX(), agenteHembra.getY()));
	}
	private void hembraObjetivo(List<ObjetoDistribuido> percepciones) {
		for (ObjetoDistribuido c : percepciones) {
			Casilla casilla = (Casilla)c;
			// TODO agregar esta palabra al diccionario en commons	
			Agente agente = (Agente)casilla.getElementoTipo("agente");
			if(agente!=null && (boolean)agente.getCaracteristicaHeredable(AtributosBasicos.SEXO_)) {
				if (this.agenteHembra==null) {
					this.agenteHembra = agente;
				} else if (calcularDistancia(agenteHembra, this.agente) < calcularDistancia(this.agenteHembra, this.agente)) {
					this.agenteHembra = agente;
				}
			}
			// TODO: heuristica quien me da mas energia incluyendo la distancia
		}
	}
}
