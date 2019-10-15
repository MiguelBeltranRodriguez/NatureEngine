package NatureEngine.Modelo;

import java.util.Arrays;
import java.util.List;

public class GenAtributo {

	private Alelo aleloUno;
	private Alelo aleloDos;
	private Object valorFenotipo;

	public GenAtributo(Alelo aleloUno, Alelo aleloDos, Object valorFenotipo) {
		this.aleloUno = aleloUno;
		this.aleloDos = aleloDos;
		this.valorFenotipo = valorFenotipo;
	}

	public Object getValorAtributo() {
		return valorFenotipo;
	}
	
	public List<Alelo> getAlelos() {
		List<Alelo> alelos =  Arrays.asList(aleloUno,aleloDos);
		return alelos;
	}

}
