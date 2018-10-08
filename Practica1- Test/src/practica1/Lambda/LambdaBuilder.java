package practica1.Lambda;

import practica1.Estructuras.Nodo;
import practica1.Estructuras.Transition;
import practica1.Thompson.NodosSingleton;
import practica1.Thompson.Thompson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LambdaBuilder {
    Thompson build;
    List<Nodo> nodes;
    List<TreeSet<String>> lamdaCloses;

    public LambdaBuilder(Thompson build) {
        this.build = build;
        nodes = NodosSingleton.getInstance().getNodesList();
        lamdaCloses = new ArrayList<TreeSet<String>>();
    }

    public Nodo searchInitialState() {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).isBegin()) {
                return nodes.get(i);
            }
        }
        return null;
    }

    public TreeSet<String> buildLambdaClose(Nodo node, TreeSet<String> set) {
        set.add(node.getIdentifier());
        for (int j = 0; j < node.numeroDeTransiciones(); j++) {
            Transition t1 = node.mostrarTransicion(j);
            if (t1.getValue() == "λ") {
                set = buildLambdaClose(t1.getEnd(), set);
            }
        }
        return set;
    }

    /* public TreeSet<String> SymbolLambdaClosure(String symbol, ){

     }*/
    public TreeSet<String> setSymbolLambdaClosure(String symbol, TreeSet<String> set) {
        TreeSet<String> result = new TreeSet<>();
        List<String> setProvisional = new ArrayList<>();
        setProvisional.addAll(set);
        for (int i = 0; i < setProvisional.size(); i++) {
            Nodo x = searchNode(setProvisional.get(i));
            for (int j = 0; j < x.numeroDeTransiciones(); j++) {
                if (x.mostrarTransicion(j).getValue().equals(symbol)) {
                    result.addAll(buildLambdaClose(x.mostrarTransicion(j).getEnd(), result));
                }
            }
        }
        return result;
    }

    public Nodo searchNode(String value) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getIdentifier() == value) {
                return nodes.get(i);
            }
        }
        return null;
    }
}
