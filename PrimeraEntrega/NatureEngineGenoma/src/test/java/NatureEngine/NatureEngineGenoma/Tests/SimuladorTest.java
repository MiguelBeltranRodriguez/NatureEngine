package NatureEngine.NatureEngineGenoma.Tests;

import NatureEngine.NatureEngineGenoma.Simulator.Simulador;


import org.junit.jupiter.api.Test;

class SimuladorTest {

	private Simulador toTest = new Simulador();

    @Test
    public void Simuladordegeneraciones() {
		try {
			Integer numeroGeneraciones=100;
			Integer numeroinicialindividuos=40;
			Integer numeromaximoinidividuos=1000;
			toTest.SimularNuevaEspecieYGeneraciones(numeroGeneraciones,numeroinicialindividuos,numeromaximoinidividuos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
