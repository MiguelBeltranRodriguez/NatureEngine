package NatureEngine.Modelo;

public class Alelo {

	private Object Valor;
	private Integer Dominancia;
	private String nameId;
	
	public Alelo(Integer Dominancia, Object Valor, String hashId) {
		this.Dominancia=Dominancia;
		this.Valor = Valor;
		this.nameId = hashId;
	}
	
	public String getnameId() {
		return this.nameId;
	}
	
	public Integer getDominancia() {
		return this.Dominancia;
	}

	public Object getValor() {
		return this.Valor;
	}
	
}
