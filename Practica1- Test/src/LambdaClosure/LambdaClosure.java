/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaClosure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import practica1.Estructuras.Nodo;

/**
 *Esta clase crea el cierre lambda para un estado del autómata
 * @author Tatiana Gómez
 */
public class LambdaClosure {
    
    private Nodo node;
    private List<String> set;
    
    public LambdaClosure(Nodo node){
        this.node = node;
        set = new ArrayList<String>();
        
    }
    

    public Stack searchLambda(Nodo nodo, Stack p){
        Stack pila = p;
        Stack aux;
        String valor;
        Nodo estado;
        int i, nombre;
        i = nodo.numeroDeTransiciones();
        if(i > 0){
            for(int j=0; j<i; j++){
                valor = nodo.mostrarTransicion(j).getValue();
                if("λ".equals(valor)){
                    estado = nodo.mostrarTransicion(j).getEnd();
                    pila.push(estado.getIdentifier());
                    aux = searchLambda(estado, pila);
                    if(!aux.empty()){
                        while(!aux.empty()){
                            pila.push(aux.pop());
                        }
                    }
                }
            }
        }
        return pila;
    }
    
    public void lambdaSet(){
        Stack pila, aux;
        pila = new Stack();
        aux = new Stack();
        pila.push(node.getIdentifier());
        pila = searchLambda(node, pila);
        
        while(!pila.empty()){
            aux.push(pila.pop());
        }
        while(!aux.empty()){
            set.add((String) aux.pop());
        }
    }
    
    public List<String> returnLambdaSet(){
        return set;
    }
        
}
