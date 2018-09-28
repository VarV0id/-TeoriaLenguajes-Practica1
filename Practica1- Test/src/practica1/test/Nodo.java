package practica1.test;

import java.util.LinkedList;
import java.util.List;

public class Nodo {
    private String nombre;
    List<Transicion> transicion;
    private int i;
    

    
    public Nodo(String nombre) {
        transicion = new LinkedList<>();
        this.nombre = nombre;
        i = 0;
    }
    
    public void agregarTransicion(Nodo desde, Nodo hacia, String valor){
        transicion.add(new Transicion(desde, hacia, valor));
        i++;
    }
    
    public Transicion mostrarTransicion(int i){
        return transicion.get(i);
    }
    
    public void eliminarTransición(int posicion){
        if (posicion > i){
            System.out.println("No existe transicion en esa posición");
        }else{
            transicion.remove(posicion);
            i--;
        }
    }
    
    public int numeroDeTransiciones(){
        return i;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
