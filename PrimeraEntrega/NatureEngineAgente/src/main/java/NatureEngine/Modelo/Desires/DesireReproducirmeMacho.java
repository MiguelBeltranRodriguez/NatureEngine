package NatureEngine.Modelo.Desires;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import NatureEngine.Mensajeria.Mensaje;
import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.Casilla;
import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.Modelo.Intentions.MoverseA;
import NatureEngine.Modelo.Intentions.TenerHijo;
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
	public boolean tengoCapacidad() {
		
		List<ObjetoDistribuido> percepciones = agente.getPercepciones();
		Map<String, Mensaje> mensajesEnviados = agente.getMensajesEnviadosEsperando();
		
		hembraObjetivo(percepciones);
		Mensaje mensajeAnterior = mensajesEnviados.get(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION);
		
		if(agenteHembra != null && this.agente.getEdadActual()>=(int)this.agente.getCaracteristicaHeredable(AtributosBasicos.MADUREZ_REPRODUCTIVA) && agente.getTiempoDescansoReproduccion()<=0 ) {
			if(mensajeAnterior == null) {
				try {
					Mensaje mensajeAEnviar = new Mensaje(agente.getID(), 5, DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION, this.agente, agenteHembra);
					Mensaje respuesta = agenteHembra.enviarCortejo(mensajeAEnviar);
					
					if(respuesta.getTipo().equals(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCIRME_ACK)) {
						mensajesEnviados.put(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION, mensajeAEnviar);
						return true;
					}else {
						return false;
					}
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(!mensajeAnterior.getReceptor().getID().equals(agenteHembra.getID())) {
				try {
					Mensaje mensajeAEnviar = new Mensaje(agente.getID(), 5, DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION, this.agente, agenteHembra);
					Mensaje respuesta = agenteHembra.enviarCortejo(mensajeAEnviar);
					mensajesEnviados.put(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION, mensajeAEnviar);
					if(respuesta.getTipo().equals(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCIRME_ACK)) {
						mensajesEnviados.put(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION, mensajeAEnviar);
						return true;
					}else {
						return false;
					}
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				return true;
			}
			
			
		}	
		return false;
	}

	@Override
	public void init(Desire desireAnterior) {
		this.llenarPila();
	}
	public void llenarPila() {
		
		Intention intencionMoverse = new MoverseA(agente, agenteHembra.getX(), agenteHembra.getY());
		Map<String, Mensaje> mensajesEnviados = agente.getMensajesEnviadosEsperando();
		this.intenciones.push(new TenerHijo(this.agente, this.agenteHembra));
		if(intencionMoverse.isFinalizado()) {
			mensajesEnviados.remove(DiccionarioDePalabras.TIPO_MENSAJE_REPRODUCCION);
		}else {
			this.intenciones.push(intencionMoverse);
		}
		
		
	}
	private void hembraObjetivo(List<ObjetoDistribuido> percepciones) {
		for (ObjetoDistribuido c : percepciones) {
			Casilla casilla = (Casilla)c;
			// TODO agregar esta palabra al diccionario en commons	
			Agente agente = (Agente)casilla.getElementoTipo("agente");
			if(agente!=null && (boolean)agente.getCaracteristicaHeredable(AtributosBasicos.SEXO_)) {
				if (this.agenteHembra==null) {
					this.agenteHembra = agente;
				} else if (calcularDistancia(agente, this.agente) < calcularDistancia(this.agenteHembra, this.agente)) {
					this.agenteHembra = agente;
				}
			}
			// TODO: heuristica quien me da mas energia incluyendo la distancia
		}
	}
}
