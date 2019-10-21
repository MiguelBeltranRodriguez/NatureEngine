package NatureEngine.NatureEngineController;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


import NatureEngine.Mensajeria.Mensaje;
import NatureEngine.Modelo.Alelo;
import NatureEngine.Modelo.AtributosBasicos;
import NatureEngine.Modelo.GenAtributo;
import NatureEngine.Modelo.Mundo;
import NatureEngine.NatureEngineAgente.Agente;
import NatureEngine.NatureEngineCommons.ObjetoDistribuido;
import NatureEngine.NatureEngineGUI.Menu;
import NatureEngine.NatureEngineGUI.Pantalla;
import NatureEngine.NatureEngineGUI.Renderizador2D;
import NatureEngine.NatureEngineGenoma.main.GenomaHandler;
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
		
		try {
			AtributosBasicos.loadAtributosXML();
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
	public synchronized void conectarAlServidorAgentes(int port) throws RemoteException {
		try {
			ServiciosAdministradorAgentes serviciosAgentes = (ServiciosAdministradorAgentes) Naming.lookup("rmi://localhost:"+port+"/controller");
			servidoresAgentes.add(serviciosAgentes);
			System.out.println("Controller conectado al servidor agentes: "+port);
			crearAgentesIniciales(serviciosAgentes);
		} catch (MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		} 

	}

	@Override
	public synchronized void  moverAgente(int x2, int y2, ObjetoDistribuido agente) throws RemoteException {
		mundo.moverAgente(x2, y2, agente);
	}

	public void addAgente(ObjetoDistribuido ag) throws RemoteException {
		mundo.addAgente(ag);
	}
	

	public Map<String, GenAtributo> crearAtributosAgentePrimitivo(boolean Hembra) {		
		HashMap<String, Object> fenotipo = new HashMap<String, Object>();
		fenotipo.put(AtributosBasicos.ENERGIA_MAXIMA_, 6000.0f);
		fenotipo.put(AtributosBasicos.AGUA_MAXIMA_, 3000.0f);
		fenotipo.put(AtributosBasicos.POTENCIA_MAXIMA_, 20.0f);
		fenotipo.put(AtributosBasicos.TAMANO_MAXIMO_, 10);
		fenotipo.put(AtributosBasicos.PERCEPCION_, 50);
		fenotipo.put(AtributosBasicos.SEXO_, Hembra);
		fenotipo.put(AtributosBasicos.CAPACIDAD_REPRODUCTIVA_, 1);
		fenotipo.put(AtributosBasicos.ANSIEDAD_, 50);
		fenotipo.put(AtributosBasicos.HUMEDAD_IDEAL_, 0.5f);
		fenotipo.put(AtributosBasicos.TOLERANCIA_HUMEDAD_, 0.5f);
		fenotipo.put(AtributosBasicos.DIGESTION_VEGETAL_, 1);
		fenotipo.put(AtributosBasicos.AGRESIVIDAD_, 0.5f);
		fenotipo.put(AtributosBasicos.LONGEVIDAD_, 100);
		fenotipo.put(AtributosBasicos.MADUREZ_REPRODUCTIVA, 10);
		GenomaHandler genomahandler = GenomaHandler.Singleton();
		Integer numeroIndividuos = 1;
		HashMap<String, GenAtributo> individuodePrueba=null;
		try {
			List<HashMap<String, GenAtributo>> listadeIndividuos = genomahandler.NuevaEspecie(numeroIndividuos, fenotipo);	
			individuodePrueba = listadeIndividuos.get(0);
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("[Error en GenomaHandler] "+ex.getMessage());
		}
		if(individuodePrueba == null) {
			System.out.println("Error");
		}
		return individuodePrueba;
	}
	
	
	public void crearAgentesIniciales(ServiciosAdministradorAgentes serviciosAgentes) throws RemoteException {
		// TODO: Agregar agentes en un csv o xml etc
		Agente agenteNuevo = new Agente(Mundo.ID_ACTUAL++,Color.ORANGE, 150+(6), 150+(6), (ServiciosController)this, serviciosAgentes, this.crearAtributosAgentePrimitivo(true));
		Agente agenteNuevoHembra = new Agente(Mundo.ID_ACTUAL++,Color.ORANGE, 150+(15), 150+(15), (ServiciosController)this, serviciosAgentes, this.crearAtributosAgentePrimitivo(false));
		addAgente(agenteNuevo);
		addAgente(agenteNuevoHembra);
		
		serviciosAgentes.agregarAgente((ObjetoDistribuido)agenteNuevo);
		serviciosAgentes.agregarAgente((ObjetoDistribuido)agenteNuevoHembra);
	}

	@Override
	public List<ObjetoDistribuido> percibir(Long idAgente, int x, int y) throws RemoteException {
		return mundo.percibir(idAgente, x, y);
	}

	@Override
	public void actualizarAgente(ObjetoDistribuido agente) throws RemoteException {
		mundo.actualizarAgente((Agente)agente);
		
	}

	@Override
	public ObjetoDistribuido getCasilla(int x, int y) throws RemoteException {
		return mundo.getCasilla(x, y);
	}

	@Override
	public void morir(ObjetoDistribuido agente) throws RemoteException {
		mundo.matarAgente(agente);
	}

	@Override
	public float consumirPlata(ObjetoDistribuido agente, ObjetoDistribuido planta) throws RemoteException {
		return mundo.consumirPlata(agente, planta);
	}

	public Mensaje enviarMensaje(Mensaje mensaje) throws RemoteException {
		Agente agenteReceptor  = (Agente) mensaje.getReceptor();
		for (ServiciosAdministradorAgentes serviciosAdministradorAgentes : servidoresAgentes) {
			String ID = serviciosAdministradorAgentes.getID();
			if(agenteReceptor.getServiciosAgente().getID().equals(ID)) {
				return serviciosAdministradorAgentes.enviarMensaje(mensaje);
			}
		}
		throw new RemoteException("Error");
	}
}
