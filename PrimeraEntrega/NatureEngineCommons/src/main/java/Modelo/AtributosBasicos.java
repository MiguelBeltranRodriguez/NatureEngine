package Modelo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Representa los atributos heradables basicos de un agente, tal como son tomados del 
 * archivo Atributos.xml.    
 * @author Andrea Gutierrez
 *
 * Fecha	Autor				Descripcion
 * 20191006	Andrea Gutierrez	Version inicial
 */
public class AtributosBasicos {

	//Nombres Atributos
	//Si nuevos atributos son agregados, su nombre debe ser incluido aqui
	public static final String ENERGIA_MAXIMA_ = "Energia maxima";
	public static final String AGUA_MAXIMA_ = "Agua maxima";
	public static final String VELOCIDAD_MAXIMA_= "Velocidad maxima";
	public static final String TAMANO_MAXIMO_ = "Tamano maximo";
	public static final String PERCEPCION_ = "Percepcion";
	public static final String SEXO_ = "Sexo";
	public static final String CAPACIDAD_REPRODUCTIVA_ = "Capacidad reproductiva";
	public static final String ANSIEDAD_ = "Ansiedad";
	public static final String HUMEDAD_IDEAL_ = "Humedad ideal";
	public static final String TOLERANCIA_HUMEDAD_ = "Tolerancia humedad";
	public static final String DIGESTION_VEGETAL_ = "Digestion vegetal";
	public static final String AGRESIVIDAD_ = "Agresividad";
	public static final String LONGEVIDAD_ = "Longevidad";
	
	//Tipos Atributos
	public static final String TIPO_FLOAT_ = "java.lang.Float";
	public static final String TIPO_INTEGER_ = "java.lang.Integer";
	public static final String TIPO_BOOLEAN_ = "java.lang.Boolean";
		
	public static final String PATH_ATRIBUTOS_ = "resources/Atributos.xml";
	public static final String TAG_ATRIBUTO_ = "atributo";
	public static final String TAG_ID_ = "id";
	public static final String TAG_TIPO_ = "tipo";
	public static final String TAG_NOMBRE_ = "nombre";
	public static final String TAG_VALOR_MAXIMO = "valorMaximo";
	public static final String TAG_VALOR_MINIMO = "valorMinimo";
	
	//Contiene los atributos basicos por nombre
	protected static HashMap<String, CaracteristicaHeredablePrototype> atributosByName = new HashMap<>();
	//Contiene los atributos basicos por id (posicion en el arreglo de atributos)
	protected static HashMap<Integer, CaracteristicaHeredablePrototype> atributosById = new HashMap<>();
	
	static {
		try {
			loadAtributosXML();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga la configuracion general de los atributos desde el archivo Atributos.xml
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	protected static void loadAtributosXML() throws ParserConfigurationException, SAXException, IOException {
		File xmlFile = new File(PATH_ATRIBUTOS_);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();	
		Document doc = builder.parse(xmlFile);	
		
		NodeList nList = doc.getElementsByTagName(TAG_ATRIBUTO_);

		CaracteristicaHeredablePrototype caracteristica;		
		Element eElement = null;

		for (int index = 0; index < nList.getLength(); index++) {

			Node nNode = nList.item(index);
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				eElement = (Element) nNode;
				
				nNode.getUserData(TAG_VALOR_MAXIMO);

				caracteristica = new CaracteristicaHeredablePrototype(
						eElement.getElementsByTagName(TAG_ID_).item(0).getTextContent(),
						eElement.getElementsByTagName(TAG_TIPO_).item(0).getTextContent(), 
						eElement.getElementsByTagName(TAG_NOMBRE_).item(0).getTextContent(), 
						eElement.getElementsByTagName(TAG_VALOR_MAXIMO).item(0) == null ? 
								null : eElement.getElementsByTagName(TAG_VALOR_MAXIMO).item(0).getTextContent(), 
						eElement.getElementsByTagName(TAG_VALOR_MINIMO).item(0) == null ? 
								null : eElement.getElementsByTagName(TAG_VALOR_MINIMO).item(0).getTextContent());

				atributosByName.put(caracteristica.getNombreCaracteristica(), caracteristica);
				atributosById.put(caracteristica.getId(), caracteristica);
			}
		}

	}
	
	public static HashMap<String, CaracteristicaHeredablePrototype> getAtributosBasicosByName() {
		return atributosByName;
	}

	public static HashMap<Integer, CaracteristicaHeredablePrototype> getAtributosBasicosById() {
		return atributosById;
	}

}
