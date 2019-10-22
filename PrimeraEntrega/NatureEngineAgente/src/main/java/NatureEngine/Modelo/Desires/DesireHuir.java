package NatureEngine.Modelo.Desires;

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
import NatureEngine.Utils.VarGlobalVista;

public class DesireHuir extends Desire {
	private Agente objetivoHostil;
	
	public DesireHuir(Agente agente) {
		super();
		this.agente = agente;
		this.intenciones = new Stack<Intention>();
	}


	@Override
	public boolean tengoCapacidad() {
		this.buscarDepredador();
		
		return (objetivoHostil != null);
	}

	private boolean esDepredador(Agente depredador) {
		boolean distintaEspecie = true; // TODO: poner funcion del genoma
		boolean esCarnivoro = depredador.isEsCarnivoro();
		boolean esGrande = (this.agente.getTamañoActual() < depredador.getTamañoActual());
		
		return (distintaEspecie && esCarnivoro && esGrande);
	}
	
	private void buscarDepredador() {
		List<ObjetoDistribuido> percepciones = agente.getPercepciones();

		for (ObjetoDistribuido percepcion : percepciones) {
			Casilla casilla = (Casilla)percepcion;
			Agente depredador = (Agente)casilla.getElementoTipo(DiccionarioDePalabras.AGENTE);
			if(depredador!=null && this.esDepredador(depredador)) {				
				if (this.objetivoHostil == null) {
					this.objetivoHostil = depredador;
				}
				else if (Desire.calcularDistancia(depredador, this.agente) < Desire.calcularDistancia(this.objetivoHostil, this.agente)) {
					this.objetivoHostil = depredador;
				}
			}
		}
	}

	@Override
	public void init(Desire desireAnterior) {
		this.llenarPila();
	}
	
	private void llenarPila() {		
		int objetivoX = this.agente.getX();
		int objetivoY = this.agente.getY();
		
		if(objetivoX >= 0 || objetivoX <= VarGlobalVista.WIDHT_PANTALLA) {
			objetivoX += this.objetivoHostil.getDireccionX();
		}
		
		if(objetivoY >= 0 || objetivoX <= VarGlobalVista.HEIGTH_PANTALLA) {
			objetivoY += this.objetivoHostil.getDireccionY();
		}

		this.intenciones.push(new MoverseA(this.agente, objetivoX, objetivoY));
	}

}
