package NatureEngine.Modelo;

public class Alelo {

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
