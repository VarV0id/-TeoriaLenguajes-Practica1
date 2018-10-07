package practica1.Estructuras;

import java.util.LinkedList;
import java.util.List;

public class Nodo {
    private String identifier;
    List<Transition> transition;
    private int i;
    private boolean isAcceptation;
    private boolean isBegin;
    

    
    public Nodo() {
        transition = new LinkedList<>();
        isAcceptation = false;
        isBegin = false;
        i = 0;
    }
    
    public void agregarTransicion(Nodo destino, String valor){
        transition.add(new Transition(this , destino, valor));
        i++;
    }
    
    public Transition mostrarTransicion(int i){
        return transition.get(i);
    }
    
    public void eliminarTransición(int posicion){
        if (posicion > i){
            System.out.println("No existe transicion en esa posición");
        }else{
            transition.remove(posicion);
            i--;
        }
    }
    
    public int numeroDeTransiciones(){
        return i;
    }
    

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isAcceptation() {
        return isAcceptation;
    }

    public void setAcceptation(boolean acceptation) {
        isAcceptation = acceptation;
    }
    
    public boolean isBegin(){
        return isBegin;
    }
    
    public void setBegin(boolean begin){
        isBegin = begin;
    }
}
