package NatureEngine.NatureEngineGenoma.Simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineGenoma.main.GenomaHandler;

public class Simulador extends SimuladorSupport {

	public List<HashMap<String, GenAtributo>> SimularNuevaEspecieYGeneraciones(Integer numeroGeneraciones,Integer numeroinicialindividuos,Integer numeromaximoinidividuos) throws Exception {
		System.out.println("Comenzando simulador");
		List<HashMap<String, GenAtributo>> poblacioninicial =  SimularNuevaEspecie(numeroinicialindividuos);
		List<HashMap<String, GenAtributo>> poblacionfinal = SimularGeneraciones(numeromaximoinidividuos, numeroGeneraciones, poblacioninicial);
		return poblacionfinal;
	}

	public List<HashMap<String, GenAtributo>> SimularNuevaEspecie(Integer numeroIndividuos) throws Exception {
		GenomaHandler genomahandler = GenomaHandler.Singleton();
		System.out.println("Generando valores de atributos iniciales al azar");
		HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie = SimulacionCrearMapaInicialDeAtributos();
		List<HashMap<String, GenAtributo>> listaDeAtributosDeIndividuosDeNuevaEspecie = genomahandler
				.NuevaEspecie(numeroIndividuos, listaDeValoresDeAtributosDeLaEspecie);
		System.out.println("Lista de GenAtributos a partir de los valores de los atributos:");
		
		SimuladorLogger.ReproductionResultsLogTest(0, listaDeAtributosDeIndividuosDeNuevaEspecie);
		return listaDeAtributosDeIndividuosDeNuevaEspecie;
	}
	
	public void SimulacionCreacionAtributos(Integer numeroRepeticiones) throws Exception {
		while(numeroRepeticiones>0) {
			System.out.println("-----------------------");
			System.out.println("**Nuevo loop**");
			SimulacionCrearMapaInicialDeAtributos();
			numeroRepeticiones--;
		}
	}
	

	public List<HashMap<String, GenAtributo>> SimularGeneraciones(Integer numeromaximoinidividuos, Integer numeroGeneraciones,
			List<HashMap<String, GenAtributo>> poblacioninicial) {
		GenomaHandler genomahandler = GenomaHandler.Singleton();
		List<HashMap<String, GenAtributo>> generacionactual = poblacioninicial;
		System.out.println("Simulando generaciones...");
		int conteo=1;
		while (numeroGeneraciones > 0) {
			try {
				generacionactual = GeneracionSimulada(numeromaximoinidividuos, genomahandler, generacionactual,conteo);	
			}catch(Exception ex) {
				System.out.println("=ERROR");
				System.out.println("*****************************");
				System.out.println("*****************************");
				System.out.println("Datos antes del error");
				System.out.println("*****************************");
				System.out.println("*****************************");
				SimuladorLogger.ReproductionResultsLogTest(conteo,generacionactual);
				throw ex;
			}
			numeroGeneraciones--;
			conteo++;
		}
		System.out.println("*****************************");
		System.out.println("Finalizado!");
		System.out.println("*****************************");
		SimuladorLogger.ReproductionResultsLogTest(conteo-1,generacionactual);
		
		return generacionactual;
	}

	private List<HashMap<String, GenAtributo>> GeneracionSimulada(Integer numeromaximoinidividuos,
			GenomaHandler genomahandler, List<HashMap<String, GenAtributo>> generacionAnterior, Integer numerogeneracionactual) {
		System.out.print("G"+numerogeneracionactual+"= ");
		List<HashMap<String, GenAtributo>> nuevaGeneracion = new ArrayList<HashMap<String, GenAtributo>>();
		Collections.shuffle(generacionAnterior);
		int parejas=0;
		for (int i = 1; i < generacionAnterior.size(); i += 2) {
			if (nuevaGeneracion.size() > numeromaximoinidividuos) {
				continue;
			}
			if ((new Random()).nextFloat() > 0.8) {
				continue;
			}
			parejas++;
			HashMap<String, GenAtributo> genomamadre = generacionAnterior.get(0);
			HashMap<String, GenAtributo> genomapadre = generacionAnterior.get(1);
			List<HashMap<String, GenAtributo>> nuevosHijos = genomahandler.Reproducirse(7, genomamadre, genomapadre);
			for (int j = 0; j < nuevosHijos.size(); j ++) {
				if ((new Random()).nextFloat() > 0.38) {
					continue;
				}
				HashMap<String, GenAtributo> hijo = nuevosHijos.get(j);
				nuevaGeneracion.add(hijo);	
			}
		}
		System.out.println("Parejas: "+parejas+" => Individuos nacidos: "+nuevaGeneracion.size());
		
		return nuevaGeneracion;
	}

}
