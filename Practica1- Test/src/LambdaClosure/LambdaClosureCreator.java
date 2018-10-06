/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaClosure;

import java.util.ArrayList;
import java.util.List;
import practica1.Estructuras.Nodo;
import practica1.Thompson.NodosSingleton;

/**
 *Clase que crea el cierre lambda para cada estado
 * @author Tatiana Gómez
 */
public class LambdaClosureCreator {
    private List<Nodo> estados;
    private List<List<String>> estadosLambda;
    private int i;
    NodosSingleton nodos;
    
    public LambdaClosureCreator(NodosSingleton nodos){
        this.nodos = nodos;
        estados = nodos.getNodesList();
        i = estados.size();
        estadosLambda = new ArrayList<List<String>>();
    }
    
    public void CreateLambdaSets(){
        Nodo nodo;
        List<String> set;
        LambdaClosure build;
        for(int j=0; j<i; j++){
            nodo = estados.get(j);
            build = new LambdaClosure(nodo);
            build.lambdaSet();
            set = build.returnLambdaSet();
            estadosLambda.add(set);
        }
    }
    
    public List<List<String>> returnLambdaSets(){
        return estadosLambda;
    }
    
    public int returnSize(){
        return i;
    }
    
    public List<Nodo> returnEstados(){
        return estados;
    }
    
    
}
