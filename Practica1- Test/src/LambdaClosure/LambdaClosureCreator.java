/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaClosure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import practica1.Estructuras.FiniteAutomaton;
import practica1.Estructuras.Nodo;
import practica1.ExpresionesRegulares.RegularExpression;
import practica1.Thompson.NodosSingleton;

/**
 *Clase que crea el cierre lambda para cada estado
 * @author Tatiana Gómez
 */
public class LambdaClosureCreator {
    private List<Nodo> estados;
    private List<HashSet<String>> estadosLambda, newStates;
    private NodosSingleton nodos;
    private RegularExpression re;
    private int i, estadosA;
    
    public LambdaClosureCreator(NodosSingleton nodos, RegularExpression re){
        this.nodos = nodos;
        this.re = re;
        re.analizeSymbols();
        estados = nodos.getNodesList();
        i = estados.size();
        estadosLambda = new ArrayList<HashSet<String>>();
        newStates = new ArrayList<HashSet<String>>();
        estadosA = 0;
    }
    
    public void createLambdaSets(){
        Nodo nodo;
        HashSet<String> set;
        LambdaClosure build;
        for(int j=0; j<i; j++){
            nodo = estados.get(j);
            build = new LambdaClosure(nodo);
            build.lambdaSet();
            set = build.returnLambdaSet();
            estadosLambda.add(set);
        }
    }
    
    public FiniteAutomaton generarAutomata(){
       FiniteAutomaton automata; 
       createLambdaSets();
       automata = construccionAF();
       return automata;
    }
    
    private FiniteAutomaton construccionAF(){
        HashSet<String> statesAux;
        FiniteAutomaton au;
        HashSet<String> set, co;
        List<String> simbols;
        String s, as;
        int j, k, begin, n, estado;
        
        as = acceptState().getIdentifier();
        au = new FiniteAutomaton();
        simbols = re.returnSymbols();
        begin = postbegin();
        set = estadosLambda.get(begin);
        
        estado = 1;
        
        /**
         * El estado inicial en el Automata es el estado 1
         */
        if(set.contains(as)){
            au.addStates(Integer.toString(estado), "1");
        }else{
            au.addStates(Integer.toString(estado), "0");
        }
        /**
         * Se agrega el simbolo del error
         */
        au.addEntrySymbol("!");
        
        /**Se agrega los estados que se van creando al Automata y se agrega los
         * simbolos de la expresión regular. Se añade las transiciones que tenga
         * el estado de inicio al Automata.
        */
        for(String si: simbols){
            //Se ingrese el simbolo al AUTOMATA
            au.addEntrySymbol(si);
            //Estado con la transición de la variable si
            co = analizarEstado(si, set);
            au = agg(au, si, set, co);            
        }
        
        /**
         * Se buscan y se agregan las transiociones de los estados de newStates
         * teniendo en cuenta que en el Automata el estado esta nombrado por
         * j+2(int en String)
         */
        k = newStates.size();
        for(j=0; j<k; j++){
            statesAux = newStates.get(j);
            for(String sim: simbols){
                co = analizarEstado(sim, statesAux);
                au = agg(au, sim, statesAux, co);
            }
        }
        return au;
    }
   
    public HashSet<String> analizarEstado(String simbol, HashSet<String> set){
        HashSet<String> conjunto, c1, c2; 
        Nodo nodo, n;
        String valorT, estado, va;
        int j, k;
        conjunto = set;
        c2 = new HashSet<>();
        for(String e:conjunto){
            va = analizarEstado(simbol, e);
            if(!va.equals("-1")){
                c1 = estadosLambda.get(Integer.parseInt(va));
                c2.addAll(c1);
            }
        }
        return c2;
    }
    
    public String analizarEstado(String simbol, String estado){
        Nodo nodo, aux;
        String valorT, pos;
        int e, t, j, p;
        e = Integer.parseInt(estado);
        nodo = estados.get(e);
        t = nodo.numeroDeTransiciones();
        for(j=0; j<t; j++){
            valorT = nodo.mostrarTransicion(j).getValue();
            if(valorT.equals(simbol)){
                aux = nodo.mostrarTransicion(j).getEnd();
                pos = aux.getIdentifier();
                return pos;
            }
        } 
        pos = "-1";
        return pos;
    }
    
    private int posNewStates(HashSet<String> estado){
        int j, k;
        HashSet<String> aux;
        k=0;
        j = newStates.size()+1;
        while(k<j){
            aux = newStates.get(k);
            if(aux.equals(estado)){
                return k;
            }
            k = k+1;
        }
        return -1;
    }
    
    private FiniteAutomaton agg(FiniteAutomaton automata, String simbolo, 
            HashSet<String> estado, HashSet<String> transicion){
        
        FiniteAutomaton au;
        HashSet<String> set;
        String as;
        int k, begin, j;
        begin = postbegin();
        set = estadosLambda.get(begin);
        
        as = acceptState().getIdentifier();
        au = automata;
        
        if(set.equals(estado)){
          k = -1;  
        }else{
          k = posNewStates(estado);  
        }
        if(!transicion.isEmpty()){
                if(!newStates.contains(transicion) && !transicion.equals(estado)){
                    newStates.add(transicion);
                    if(transicion.contains(as)){
                        estadosA = estadosA + 1;
                        au.addStates(Integer.toString(estadosA), "1");
                    }else{
                        estadosA = estadosA + 1;
                        au.addStates(Integer.toString(estadosA), "0");
                    }
                    au.setTransition(simbolo,Integer.toString(k+2), Integer.toString(estadosA));
                }else if(transicion.equals(estado)){
                    au.setTransition(simbolo,Integer.toString(k+2), Integer.toString(k+2));
                }else{
                    j = posNewStates(transicion);
                    au.setTransition(simbolo,Integer.toString(k+2), Integer.toString(j+2));
                }                
                
        }else{
            au.setTransition(simbolo,Integer.toString(k+2), "!");
        }
        return au;
    }
    
    public int postbegin(){
      Nodo nodo;
        int j;
        for(j=0; j<i; j++){
            nodo = estados.get(j);
            if(nodo.isBegin()){
                return j;
            }
        }
        return -1;  
    }
    
    public Nodo acceptState(){
        Nodo nodo;
        int j;
        for(j=0; j<i; j++){
            nodo = estados.get(j);
            if(nodo.isAcceptation()){
                return nodo;
            }
        }
        return null;
    }
    
    public List<HashSet<String>> returnLambdaSets(){
        return estadosLambda;
    }
    
    public int returnSize(){
        return i;
    }
    
    public List<Nodo> returnEstados(){
        return estados;
    }  
    
}
