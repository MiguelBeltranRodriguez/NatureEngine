package NatureEngine.NatureEngineGUI;

// TODO: Auto-generated Javadoc
/**
 * Interfaz dibujable, para todos los objetos que se van a visualizar en la GUI.
 */
public interface Dibujable  {
	
	/**
	 * Metodo que todo objeto de implementar si necesita mostrarse en la pantalla
	 *
	 * @param r Renderizador del simulador
	 */
	public void dibujar(Renderizador2D r);
	
	/**
	 * Cuando el objeto es seleccionado(No todos).
	 */
	public void Resaltar();
	
	/**
	 * Cuando el objeto ya no está seleccionado(No todos).
	 */
	public void DesResaltar();
	
	/**
	 * Retorna la posición en x del objeto
	 *
	 * @return the x
	 */
	public int getX();
	
	/**
	 * Retorna la posición en y del objeto
	 *
	 * @return the y
	 */
	public int getY();
	
	/**
	 * Inicializa todo lo necesario del objeto si es necesario
	 */
	public void  init();
	
	/**
	 * Retorna la información del objeto en un string separado cada salto de linea con el caracter "#"
	 *
	 * @return the string
	 */
	public String info();
	
	/**
	 * Actualiza el objeto, para visualizar su siguiente estado
	 */
	public void update();
}
