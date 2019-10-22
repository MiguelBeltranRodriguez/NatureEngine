package NatureEngine.Modelo.Desires;

import java.util.Random;
import java.util.Stack;

import NatureEngine.Modelo.Intentions.Intention;
import NatureEngine.Modelo.Intentions.MoverseA;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.Utils.VarGlobalVista;

public class DesireMovimientoAleatorio extends Desire {
	int posX;
	int posY;
	
	public DesireMovimientoAleatorio(Agente agente) {
		this.agente = agente;
		this.intenciones = new Stack<Intention>();
	}
	
	@Override
	public boolean tengoCapacidad() {
		aleatorio();
		return true;
	}

	private void aleatorio() {
		Random randX = new Random();
		Random randY = new Random();
		
		int minX = agente.getX()-agente.getDistanciaPercepcion();
		int maxX = agente.getX()+agente.getDistanciaPercepcion();
		
		int minY = agente.getY()-agente.getDistanciaPercepcion();
		int maxY = agente.getY()+agente.getDistanciaPercepcion();
		
		
		posX = randX.nextInt((maxX - minX)+1)+minX;
		posY = randY.nextInt((maxY - minY)+1)+minY;
		
		if(posX<0) {
			posX = 0;
		}
		else if(posX >= VarGlobalVista.WIDHT_PANTALLA) {
			posX = VarGlobalVista.WIDHT_PANTALLA-1;
		}
		if(posY<0) {
			posY = 0;
		}
		else if(posY >= VarGlobalVista.HEIGTH_PANTALLA) {
			posY = VarGlobalVista.HEIGTH_PANTALLA-1;
		}
	}

	@Override
	public void init(Desire desireAnterior) {
		llenarPila();
	}

	@Override
	public void llenarPila() {
		Intention intencionMoverse = new MoverseA(this.agente, posX, posY);
		if(!intencionMoverse.isFinalizado()){
			this.intenciones.push(intencionMoverse);
		}
	}

}
