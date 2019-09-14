package controlador.Genoma;

import modelo.AtributosAgente;
import modelo.BaseAttributes;

import java.util.List;
import java.util.Map;

public class GenomeHandler extends CombinationHandler {

    public GenomeHandler(BaseAttributes baseattributes) {
        atributesAndKeys = baseattributes.getAttributesAndKeys();
    }

    ////////////////////////////////////////////
    ///La clase CombinationHandler es la única desde la que se realizarán las mutaciones
    ///////////////////////////////////////////

    public AtributosAgente GenerarAtributosHijo(AtributosAgente AttrPadre, AtributosAgente AttrMadre) {
        Map<String, List<String>> genomaPadre = AttrPadre.getFullGenoma(); //Dos cadenas por cada argumento
        Map<String, List<String>> genomaMadre = AttrMadre.getFullGenoma();

        ///// En la Meiosis Uno, se mezclan ambas cadas de cada padre entre sí
        genomaMadre = this.MeiosisUno(genomaMadre);
        genomaPadre = this.MeiosisUno(genomaPadre);

        ///// En la Meiosis Dos, se escoge uno solo de los dos cromosomas
        Map<String, String> halfgenomaMadre = this.MeiosisDos(genomaMadre);
        Map<String, String> halfgenomaPadre = this.MeiosisDos(genomaPadre);

        ///// En la fecundación se juntan las dos mitades, de padre y madre, y se forma el nuevo cromosoma doble del hijo
        Map<String, List<String>> genomaHijo = this.Fecundacion(halfgenomaPadre, halfgenomaMadre);
        return this.GenerarAtributos(genomaHijo);
    }

    public AtributosAgente GenerarAtributosAgenteSinPadres() {
        Map<String, List<String>> genomaNuevo = this.createNewGenoma();
        return this.GenerarAtributos(genomaNuevo);
    }

    private AtributosAgente GenerarAtributos(Map<String, List<String>> genoma) {
        Map<String, Integer> atributos = this.CalcularAtributos(genoma);
        return new AtributosAgente(genoma, atributos);
    }
}