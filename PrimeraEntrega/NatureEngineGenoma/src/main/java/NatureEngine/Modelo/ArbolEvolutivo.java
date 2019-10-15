package NatureEngine.Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArbolEvolutivo<String> implements Iterable<ArbolEvolutivo<String>> {

	String data;
    ArbolEvolutivo<String> parent;
    List<ArbolEvolutivo<String>> children;

    public ArbolEvolutivo(String data) {
        this.data = data;
        this.children = new ArrayList<ArbolEvolutivo<String>>();
    }

    public ArbolEvolutivo<String> addChild(String child) {
    	ArbolEvolutivo<String> childNode = new ArbolEvolutivo<String>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

	@Override
	public Iterator<ArbolEvolutivo<String>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

    // other features ...

}