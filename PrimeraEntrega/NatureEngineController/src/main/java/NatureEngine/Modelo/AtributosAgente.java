package NatureEngine.Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtributosAgente {

    //Atributos dinámicos
    private int energia = 100;
    private int agua = 100;
    private int edad = 0;
    private int velocidad = 50;
    private int tamano = 1;

    //Atributos de grupo


    //Atributos estáticos

    private Map<String, Attribute> attributes = new HashMap<String, Attribute>();

    //Atributos estáticos

    public AtributosAgente(Map<String, List<String>> genoma, Map<String, Integer> values){
        for (Map.Entry<String, List<String>> entry : genoma.entrySet()) {
            String attrname = entry.getKey();
            if(!(values.containsKey(attrname))){
             //throw
            }
            int value = values.get(attrname);
            List<String> locus= entry.getValue();
            Attribute attr = new Attribute(value,locus);
            attributes.put(attrname,attr);
        }
    }

    ///////////////////////////////////

    public Map<String, List<String>> getFullGenoma() {
        Map<String, List<String>> genoma = new HashMap<String, List<String>>(); //Por cada atributo devuelve dos cadenas
        for (Map.Entry<String, Attribute> entry : attributes.entrySet()) {
            Attribute tmpAttr = entry.getValue();
            genoma.put(entry.getKey(), tmpAttr.getLocus());
        }
        return genoma;
    }

    public int getAttrValue(String name) {
        Attribute tmpAttr = this.AttrExists(name);
        return tmpAttr.getValue();
    }

    private Attribute AttrExists(String name) {
        if (!(attributes.containsKey(name))) {
            //throw new...
        }
        return attributes.get(name);
    }

    //Atributos de dinámicos

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getAgua() {
        return agua;
    }

    public void crecer() {
        edad = edad + 1;
    }

    public void tomarAgua(int agua) {
        this.agua = agua;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setGradoVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getTamano() {
        return tamano;
    }

    //Atributos de grupos y roles

}
