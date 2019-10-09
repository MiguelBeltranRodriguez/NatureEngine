package NatureEngine.NatureEngineController;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import NatureEngine.Modelo.Mundo;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Menu;
import NatureEngine.NatureEngineGUI.Pantalla;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.RMI.ServiciosAdministradorAgentes;
import NatureEngine.RMI.ServiciosController;
import NatureEngine.Utils.VarGlobalGame;




/*
 * Loop principal
 */
public class Loop extends UnicastRemoteObject implements Runnable, ServiciosController {

	private List<ServiciosAdministradorAgentes> servidoresAgentes;
	protected Loop() throws RemoteException {
		super();
		servidoresAgentes = new ArrayList<ServiciosAdministradorAgentes>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean corriendo = false;
	private Pantalla pantalla;
	private Renderizador2D render2D;
	private Mundo mundo;
	private Menu menu;


	//Parte encargada de inicializar todo lo necesario
	private void inicio() {
		setPantalla(Pantalla.getPantalla());
		render2D = new Renderizador2D();
		mundo = new Mundo();
		corriendo = true;

		pantalla.getCanvas().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionar(e);
			}
		});

		mundo.init();
		menu = new Menu(mundo);
	}	

	protected synchronized void seleccionar(MouseEvent e) {
		if(!mundo.addPopUp(e.getX(), e.getY())) {
			mundo.limpiarPopUp();
		}
	}

	private void update() {
		mundo.update();
	}

	private void renderizar() {
		render2D.renderizar(mundo, menu);
	}

	@Override
	public void run() {
		inicio();

		double timerPerTick = 1000000000 / VarGlobalGame.FPS;

		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(corriendo) {
			now = System.nanoTime();
			VarGlobalGame.DELTA += (now -lastTime)/timerPerTick;
			timer += now - lastTime;
			lastTime = now;

			if(VarGlobalGame.DELTA>=1) {
				update();
				renderizar();
				ticks++;
				VarGlobalGame.DELTA--;
			}
			if(timer > 1000000000) {
				VarGlobalGame.TICKS_S = ticks;
				ticks = 0;	
				timer = 0;

			}

		}		
	}




	public Pantalla getPantalla() {
		return pantalla;
	}

	public void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}

	@Override
	public synchronized   void conectarAlServidorAgentes(int port) throws RemoteException {
		try {
			ServiciosAdministradorAgentes serviciosAgentes = (ServiciosAdministradorAgentes) Naming.lookup("rmi://localhost:"+port+"/controller");
			servidoresAgentes.add(serviciosAgentes);
			System.out.println("Controller conectado al servidor agentes: "+port);
			Random aleatorio = new Random(System.currentTimeMillis());
			int aux = aleatorio.nextInt(255);
			for(int i = 0; i < 1; i++) {
				Agente ag = new Agente(Mundo.ID_ACTUAL++,new Color(aleatorio.nextInt(255),  aux, aux, 255), 150+(i), 150+(i), 5+i%5, 20, 50+i%5,(ServiciosController)this);
				addAgente(ag);
				serviciosAgentes.agregarAgente((ObjetoDistribuido)ag);
			}
		} catch (MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		} 

	}

	@Override
	public synchronized void  moverAgente(int x2, int y2, ObjetoDistribuido agente) throws RemoteException {
		mundo.moverAgente(x2, y2, agente);
	}

	@Override
	public boolean esCeldaVacia(int i, int j) throws RemoteException {
		return mundo.celdaVacia(i, j);
	}

	public void addAgente(ObjetoDistribuido ag) throws RemoteException {
		mundo.addAgente(ag);

	}

	@Override
	public List<ObjetoDistribuido> percibir(Long idAgente, int x, int y) throws RemoteException {
		return mundo.percibir(idAgente, x, y);
	}
}
