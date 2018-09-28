/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jairo
 */
public class Thompson {
    Stack<String>characterStack;
    Stack<Nodo> nodeStack;
    List<Nodo>nodos;
    Nodo inicio;
    Nodo fin;
    boolean state;
    int contadorNodos;
    final static String OPERADORES = ".|*+";
    public Thompson(){
        nodos = new LinkedList<>();
        characterStack = new Stack<>();
        nodeStack = new Stack<>();
        inicio = new Nodo("1");
        fin = new Nodo("2");
        inicio.agregarTransicion(inicio, fin, "λ");
        nodos.add(inicio);
        nodos.add(fin);
        state = false;
        contadorNodos = 3;
    }
    public Nodo buildThompson(String er){
        int token = 0;
        while(!Character.toString(er.charAt(token)).equals("¬")){
            switch(Character.toString(er.charAt(token))){
                case ".":
                    break;
                case "|":
                    break;
                case "*":
                    break;
                case "+":
                    break;
                default: 
                    characterStack.push(Character.toString(er.charAt(token)));
                    break;
            }
            token++;
        }
        return null;
    }
    public void kleneeStarOperation(){ 
        Nodo a = new Nodo(Integer.toString(contadorNodos+1));
        Nodo b = new Nodo(Integer.toString(contadorNodos+2));
        Nodo c = new Nodo(Integer.toString(contadorNodos+3));
        Nodo d = new Nodo(Integer.toString(contadorNodos+4));
        nodos.add(a);
        nodos.add(b);
        nodos.add(c);
        nodos.add(d);
        
        a.agregarTransicion(a, b, "λ");
        a.agregarTransicion(a, d, "λ");
        c.agregarTransicion(c, d, "λ");
        c.agregarTransicion(c, b, "λ");
        
        if(nodeStack.empty()){
            b.agregarTransicion(b, c, characterStack.pop());
        }else{
            
        }
        contadorNodos = contadorNodos+4;
    }
}
