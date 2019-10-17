package NatureEngine.Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArbolEvolutivo<string> implements Iterable<ArbolEvolutivo<string>> {

	string data;
    ArbolEvolutivo<string> parent;
    List<ArbolEvolutivo<string>> children;

    public ArbolEvolutivo(string child) {
        this.data = child;
        this.children = new ArrayList<ArbolEvolutivo<string>>();
    }

    public ArbolEvolutivo<string> addChild(string child) {
    	ArbolEvolutivo<string> childNode = new ArbolEvolutivo<string>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

	@Override
	public Iterator<ArbolEvolutivo<string>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

    // other features ...

}