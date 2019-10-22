package NatureEngine.NatureEngineGenoma.Tests;

import NatureEngine.NatureEngineGenoma.Simulator.Simulador;


import org.junit.jupiter.api.Test;

class SimulacionCreacionAtributosNuevaEspecie {

	private Simulador toTest = new Simulador();

    @Test
    public void Simuladordegeneraciones() {
		try {
			Integer numeroinicialindividuos=10;
			toTest.SimularNuevaEspecie(numeroinicialindividuos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
