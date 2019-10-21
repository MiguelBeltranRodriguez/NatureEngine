package NatureEngine.NatureEngineGenoma.Tests;

import org.junit.jupiter.api.Test;

import NatureEngine.NatureEngineGenoma.Simulator.Simulador;

class AtributosTest {


	private Simulador toTest = new Simulador();

    @Test
    public void TestDeAtributos() {
		try {
			toTest.SimulacionCreacionAtributos(15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
