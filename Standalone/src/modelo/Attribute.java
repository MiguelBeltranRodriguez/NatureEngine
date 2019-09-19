package modelo;

import java.util.List;

class Attribute {

    private List<String> locus; //Cada locus tiene 2 cadenas. Una del padre y otra de la madre
    private int value;

    Attribute(int value, List<String> locus) {
        this.value = value;
    	this.locus = locus;
    }

	int getValue() {
    	return value;
	}

	List<String> getLocus() {
    	return locus;
	}

}