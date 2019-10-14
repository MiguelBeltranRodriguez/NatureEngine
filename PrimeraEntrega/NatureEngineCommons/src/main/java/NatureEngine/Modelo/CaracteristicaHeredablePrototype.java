package NatureEngine.Modelo;

/**
 * Representacion general de una caracteristica heredable de un agente. 
 * Este tipo de caracteristicas hacen parte del proceso de reproduccion de los agentes
 * @author Andrea Gutierrez
 *
 * Fecha	Autor				Descripcion
 * 20191006	Andrea Gutierrez	Version inicial
 */
public class CaracteristicaHeredablePrototype {
	protected Integer id;
	protected String nombreCaracteristica;
	protected String tipoCaracteristica;
	protected Object valorMinimo;
	protected Object valorMaximo;
	protected Object variabilidad;
	
	public CaracteristicaHeredablePrototype() {
	}


	public CaracteristicaHeredablePrototype(Integer id, String tipoCaracteristica, String nombreCaracteristica, 
			 String valorMaximo, String valorMinimo){
		this.id = id;
		this.tipoCaracteristica = tipoCaracteristica;
		this.nombreCaracteristica = nombreCaracteristica;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
	}

	public CaracteristicaHeredablePrototype(String id, String tipoCaracteristica, String nombreCaracteristica, 
			 String valorMaximo, String valorMinimo, String variabilidad){
		this.id = Integer.valueOf(id);
		this.tipoCaracteristica = tipoCaracteristica;
		this.nombreCaracteristica = nombreCaracteristica;
		this.variabilidad = variabilidad;

		
		if(valorMinimo != null && !"".equals(valorMinimo)) {
			switch (this.tipoCaracteristica){
				case(AtributosBasicos.TIPO_FLOAT_):
					this.valorMinimo = Float.parseFloat(valorMinimo);
					break;
				case(AtributosBasicos.TIPO_INTEGER_):
					this.valorMinimo = Integer.parseInt(valorMinimo);			
					break;
				case(AtributosBasicos.TIPO_BOOLEAN_):
					this.valorMinimo = Boolean.FALSE;			
					break;
			}
		}
		else{
			switch (this.tipoCaracteristica){
				case(AtributosBasicos.TIPO_FLOAT_):
					this.valorMinimo = Float.NEGATIVE_INFINITY;
					break;
				case(AtributosBasicos.TIPO_INTEGER_):
					this.valorMinimo = Integer.MIN_VALUE;			
					break;
				case(AtributosBasicos.TIPO_BOOLEAN_):
					this.valorMinimo = Boolean.FALSE;			
					break;
			}
		}
		

		if(valorMaximo != null && !"".equals(valorMaximo)) {
			switch (this.tipoCaracteristica){
				case(AtributosBasicos.TIPO_FLOAT_):
					this.valorMaximo = Float.parseFloat(valorMaximo);
					break;
				case(AtributosBasicos.TIPO_INTEGER_):
					this.valorMaximo = Integer.parseInt(valorMaximo);			
					break;
				case(AtributosBasicos.TIPO_BOOLEAN_):
					this.valorMaximo = Boolean.TRUE;			
					break;
			}
		}
		else{
			switch (this.tipoCaracteristica){
				case(AtributosBasicos.TIPO_FLOAT_):
					this.valorMaximo = Float.POSITIVE_INFINITY;
					break;
				case(AtributosBasicos.TIPO_INTEGER_):
					this.valorMaximo = Integer.MAX_VALUE;			
					break;
				case(AtributosBasicos.TIPO_BOOLEAN_):
					this.valorMaximo = Boolean.TRUE;			
					break;
			}
		}
		if(variabilidad != null && !"".equals(variabilidad)) {
			switch (this.tipoCaracteristica){
				case(AtributosBasicos.TIPO_FLOAT_):
					this.valorMaximo = Float.parseFloat(variabilidad);
					break;
				case(AtributosBasicos.TIPO_INTEGER_):
					this.valorMaximo = Integer.parseInt(variabilidad);			
					break;
				case(AtributosBasicos.TIPO_BOOLEAN_):
					this.valorMaximo = Boolean.TRUE;			
					break;
			}
		}
		else{
			switch (this.tipoCaracteristica){
				case(AtributosBasicos.TIPO_FLOAT_):
					this.valorMaximo = Float.POSITIVE_INFINITY;
					break;
				case(AtributosBasicos.TIPO_INTEGER_):
					this.valorMaximo = Integer.MAX_VALUE;			
					break;
				case(AtributosBasicos.TIPO_BOOLEAN_):
					this.valorMaximo = Boolean.TRUE;			
					break;
			}
		}
	}
	
	public Integer getId(){
		return this.id;
	}

	public String getNombreCaracteristica(){
		return this.nombreCaracteristica;
	}
	
	public String getTipoCaracteristica() {
		return this.tipoCaracteristica;
	}
	
	public Object getValorMinimo() {
		Object valor = null;		

		if(this.valorMinimo != null)
		{
			valor = new Object();
			
			try {
				Class<?> clazz = Class.forName(this.tipoCaracteristica);
				valor = clazz.cast(this.valorMinimo);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return valor;
	}

	public Object getValorMaximo() {
		Object valor = null;		

		if(this.valorMaximo != null)
		{
			valor = new Object();
			
			try {
				Class<?> clazz = Class.forName(this.tipoCaracteristica);
				valor = clazz.cast(this.valorMaximo);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return valor;
	}
	
}
