package NatureEngine.Modelo;

import java.io.Serializable;

public class Alelo implements Serializable {

	private Object Valor;
	private Integer Dominancia;
	
	public Alelo(Integer Dominancia, Object Valor) {
		this.Dominancia=Dominancia;
		this.Valor = Valor;
	}
	
	public Integer getDominancia() {
		return this.Dominancia;
	}

	public Object getValor() {
		return this.Valor;
	}
	
}
