package NatureEngine.NatureEngineGenoma.Tests;

import NatureEngine.NatureEngineGenoma.Simulator.Simulador;


import org.junit.jupiter.api.Test;

class SimuladorTest {

	private Simulador toTest = new Simulador();

    @Test
    public void Simuladordegeneraciones() {
		try {
			toTest.SimularNuevaEspecieYGeneraciones(100,20,100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
