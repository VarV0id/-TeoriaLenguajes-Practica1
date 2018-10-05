package practica1.Thompson;

import practica1.Estructuras.Nodo;

import java.util.LinkedList;
import java.util.List;

public class NodosSingleton {
    private static NodosSingleton nodes;
    private List<Nodo> nodeList;
    private NodosSingleton(){
        nodeList = new LinkedList<>();
    }
    public static NodosSingleton getInstance(){
        if(nodes == null){
            nodes = new NodosSingleton();
        }
        return nodes;
    }
    public void addNodes(Nodo x){
        nodeList.add(x);
    }
    public List<Nodo> getNodesList(){
        return nodeList;
    }
    public void setNumsToNodes(){
        for(int i=1; i<=nodeList.size();i++){
            nodeList.get(i-1).setIdentifier(Integer.toString(i));
        }
    }
}
