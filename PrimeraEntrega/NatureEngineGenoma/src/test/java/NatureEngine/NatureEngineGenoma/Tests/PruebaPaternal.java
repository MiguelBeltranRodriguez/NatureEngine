package NatureEngine.NatureEngineGenoma.Tests;

import NatureEngine.NatureEngineGenoma.Simulator.Simulador;


import org.junit.jupiter.api.Test;

class PruebaPaternal {

	private Simulador toTest = new Simulador();

    @Test
    public void Simuladordegeneraciones() {
		try {
			Integer numeroGeneraciones=1;
			Integer numeroinicialindividuos=10;
			Integer numeromaximoinidividuos=2;
			Boolean MostrarLogDetallado=true;
			Integer MostrarPadresEHijosDeGeneracionNumero = 1;
			toTest.SimularNuevaEspecieYGeneraciones(numeroGeneraciones,numeroinicialindividuos,numeromaximoinidividuos,MostrarLogDetallado,MostrarPadresEHijosDeGeneracionNumero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
