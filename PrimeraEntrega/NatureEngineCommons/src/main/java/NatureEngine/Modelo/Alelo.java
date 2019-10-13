package NatureEngine.Modelo;

public class Alelo {

	private Float Valor;
	private Integer Dominancia;
	
	public Alelo(Integer Dominancia, Float Valor) {
		this.Dominancia=Dominancia;
		this.Valor = Valor;
	}
	
	public Integer getDominacia() {
		return this.Dominancia;
	}

	public Float getValor() {
		return this.Valor;
	}
	
}
