package NatureEngine.NatureEngineGUI;

import java.awt.Color;


/**
 * La clase que muestra popUps para mostrar información de objetos
 */
public class PopUpInfo implements Dibujable {
	
	/** El objeto al que se le va a mostrar la información */
	private Dibujable dibujable;
	
	/** La altura */
	private int height;
	
	/** El ancho */
	private int width;
	

	/**
	 * Instancia un nuevo PopUpInfo
	 *
	 * @param dibujable El objeto
	 */
	public PopUpInfo(Dibujable dibujable) {
		this.dibujable = dibujable;
		width = 200;
		height = 90;
	}

	/**
	 * Dibuja un popUp
	 *
	 * @param r El renderizador
	 */
	@Override
	public void dibujar(Renderizador2D r) {
		r.dibujarRectangulo(Color.WHITE, dibujable.getX(), dibujable.getY(), width,height);
		r.dibujarContornoRectangular(Color.BLACK, dibujable.getX()-1, dibujable.getY()-1, width+1,height+1);
		String info = dibujable.info();
		String [] infoS = info.split("#");
		for(int i = 1; i <= infoS.length; i++) {
			r.dibujarString(Color.black, dibujable.getX() + 4, dibujable.getY()+(12*i), infoS[i-1]);
		}
		dibujable.Resaltar();
	}

	/**
	 * Obtener la posición en x del popUp
	 *
	 * @return la posición x
	 */
	@Override
	public int getX() {
		return dibujable.getX();
	}

	/**
	 * Obtener la posición en y del popUp
	 *
	 * @return la posición y
	 */
	@Override
	public int getY() {
		return dibujable.getY();
	}

	/**
	 * Inicializa el popUp
	 */
	@Override
	public void init() {
	}

	/**
	 * Resalta el popUp
	 */
	@Override
	public void Resaltar() {
	}

	/**
	 * Desresalta el popUp
	 */
	@Override
	public void DesResaltar() {
		dibujable.DesResaltar();
	}

	/**
	 * Retorna la información del popUp, en este caso es null porque el dibuja la informacion de otro objeto
	 *
	 * @return la información
	 */
	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Actualiza el popUp
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
