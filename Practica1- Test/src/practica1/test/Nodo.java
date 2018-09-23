package practica1.test;

public class Nodo {
    private int nombre;
    private Transicion[] transicion;
    private int i;

    
    public Nodo(int nombre) {
        this.nombre = nombre;
        i = 0;
    }
    
    public void agregarTransicion(Nodo desde, Nodo hacia, String valor){
        transicion[i] = new Transicion(desde, hacia, valor);
        i = i+1;
    }
    
    public Transicion mostrarTransicion(int i){
        return transicion[i];
    }
    
    public void eliminarTransición(int posicion){
        if(posicion == i){
            i = i-1;
        }else if (posicion > i){
            System.out.println("No existe transicion en esa posición");
        }else{
            int j;
            j = posicion;
            while(j<i){
                transicion[j] = transicion[j+1];
                j = j+1;
            } 
        }
    }
    
    public int numeroDeTransiciones(){
        return i;
    }
    

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
