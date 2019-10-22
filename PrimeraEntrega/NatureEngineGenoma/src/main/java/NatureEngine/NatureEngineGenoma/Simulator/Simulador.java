package NatureEngine.NatureEngineGenoma.Simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.GenAtributo;
import NatureEngine.NatureEngineCommons.ComparadorDeGenoma;
import NatureEngine.NatureEngineGenoma.main.GenomaHandler;

public class Simulador extends SimuladorSupport {

	public List<HashMap<String, GenAtributo>> SimularNuevaEspecieYGeneraciones(Integer numeroGeneraciones,
			Integer numeroinicialindividuos, Integer numeromaximoinidividuos, Boolean MostrarLogDetallado, Integer MostrarPadresEHijosDeGeneracionNumero) throws Exception {
		System.out.println("Comenzando simulador");
		List<HashMap<String, GenAtributo>> poblacioninicial = SimularNuevaEspecie(numeroinicialindividuos);
		List<HashMap<String, GenAtributo>> poblacionfinal = SimularGeneraciones(numeromaximoinidividuos,
				numeroGeneraciones, poblacioninicial,MostrarLogDetallado,MostrarPadresEHijosDeGeneracionNumero);
		return poblacionfinal;
	}

	public List<HashMap<String, GenAtributo>> SimularNuevaEspecie(Integer numeroIndividuos) throws Exception {
		GenomaHandler genomahandler = GenomaHandler.Singleton();
		System.out.println("Generando valores de atributos iniciales al azar");
		HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie = AtributosIniciales();
		List<HashMap<String, GenAtributo>> poblacioninicial = genomahandler
				.NuevaEspecie(numeroIndividuos, listaDeValoresDeAtributosDeLaEspecie);
		SimuladorLogger.ReportGeneration(poblacioninicial);
		return poblacioninicial;
	}

	public void SimulacionCreacionAtributos(Integer numeroRepeticiones) throws Exception {
		HashMap<String, List<Float>> listafinal = new HashMap<String, List<Float>>();
		while (numeroRepeticiones > 0) {
			HashMap<String, Object> listaDeValoresDeAtributosDeLaEspecie = SimulacionCrearMapaInicialDeAtributos();
			listafinal = SimuladorLogger.AddToListaFinal(listafinal,listaDeValoresDeAtributosDeLaEspecie);
			numeroRepeticiones--;
		}
		SimuladorLogger.ReportFinalData(listafinal);
	}
	

	public List<HashMap<String, GenAtributo>> SimularGeneraciones(Integer numeromaximoinidividuos,
			Integer numeroGeneraciones, List<HashMap<String, GenAtributo>> poblacioninicial, Boolean MostrarLogDetallado, Integer MostrarPadresEHijosDeGeneracionNumero) throws Exception {
		GenomaHandler genomahandler = GenomaHandler.Singleton();
		List<HashMap<String, GenAtributo>> generacionactual = poblacioninicial;
		System.out.println("Simulando generaciones...");
		int conteo = 1;
		while (numeroGeneraciones > 0) {
			generacionactual = SimularGeneracion(numeromaximoinidividuos, genomahandler, generacionactual, conteo,MostrarLogDetallado,MostrarPadresEHijosDeGeneracionNumero);
			numeroGeneraciones--;
			conteo++;
		}
		System.out.println("*****************************");
		System.out.println("Finalizado!");
		System.out.println("*****************************");
		
		if(MostrarLogDetallado==true) {
			SimuladorLogger.ReproductionResultsLogTest(conteo - 1, generacionactual);
		}else {
			SimuladorLogger.ReportGeneration(generacionactual);	
		}
		

		return generacionactual;
	}

	private List<HashMap<String, GenAtributo>> SimularGeneracion(Integer numeromaximoinidividuos,
			GenomaHandler genomahandler, List<HashMap<String, GenAtributo>> generacionAnterior,
			Integer numerogeneracionactual, Boolean MostrarLogDetallado,Integer MostrarPadresEHijosDeGeneracionNumero) throws Exception {
		System.out.print("G" + numerogeneracionactual + "= ");
		Boolean MostrarPadresEHijos=false;
		if(MostrarPadresEHijosDeGeneracionNumero!=null && MostrarPadresEHijosDeGeneracionNumero==numerogeneracionactual) {
			MostrarPadresEHijos=true;
		}
		List<HashMap<String, GenAtributo>> nuevaGeneracion = new ArrayList<HashMap<String, GenAtributo>>();
		Collections.shuffle(generacionAnterior);
		int parejas = 0;
		int interespecie = 0;
		List<HashMap<String, GenAtributo>> individuosUsados = new ArrayList<HashMap<String, GenAtributo>>();
		ComparadorDeGenoma comparadordegenoma = new ComparadorDeGenoma();
		for (int i = 1; i < generacionAnterior.size(); i++) {
			if (nuevaGeneracion.size() > numeromaximoinidividuos || parejas > numeromaximoinidividuos) {
				break;
			}
			HashMap<String, GenAtributo> genomaUno = generacionAnterior.get(i);
			if (individuosUsados.contains(genomaUno)) {
				continue;
			} else {
				individuosUsados.add(genomaUno);
			}
			Boolean sexoDosDebeSer = !((Boolean) genomaUno.get("Sexo").getFenotipo());
			for (int j = i + 1; j < generacionAnterior.size(); j++) {
				HashMap<String, GenAtributo> genomaDos = generacionAnterior.get(j);
				if (individuosUsados.contains(genomaDos)) {
					continue;
				}
				Boolean sexoDos = (Boolean) genomaDos.get("Sexo").getFenotipo();
				if (!(sexoDos == sexoDosDebeSer)) {
					continue;
				}
				individuosUsados.add(genomaDos);
				if (comparadordegenoma.SonMismaEspecieComparandoGenoma(genomaUno, genomaDos) == false) {
					interespecie++;
					continue;
				}
				if ((new Random()).nextFloat() > 0.8) {
					continue;
				}
				parejas++;
				List<HashMap<String, GenAtributo>> nuevosHijos = genomahandler.Reproducirse(7, genomaUno, genomaDos);
				for (int k = 0; k < nuevosHijos.size(); k++) {
					if ((new Random()).nextFloat() > 0.6) {
						continue;
					}
					HashMap<String, GenAtributo> hijo = nuevosHijos.get(k);
					nuevaGeneracion.add(hijo);
				}
				if(MostrarPadresEHijos==true) {
				System.out.println("**********************");
				System.out.println("**********************");
				System.out.println("Pareja #"+parejas);
				System.out.println("Parentales");
				SimuladorLogger.ReproductionResultsLogTest(null, Arrays.asList(genomaUno, genomaDos));
				System.out.println("------------------------");
				System.out.println("Hijos");
					SimuladorLogger.ReproductionResultsLogTest(null, nuevosHijos);
					System.out.println("**********************");
					System.out.println("**********************");
				}
				
				
				break;
			}
		}


		
		System.out.println("Parejas: " + parejas + " => Cruces Interespecie bloqueados: " + interespecie
				+ " => Individuos nacidos: " + nuevaGeneracion.size());

		return nuevaGeneracion;
	}

}
