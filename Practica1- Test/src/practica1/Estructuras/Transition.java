/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.Estructuras;

import practica1.Thompson.ThompsonCreator;

/**
 *
 * @author Tatiana Gómez
 */
public class Transition {
    private Nodo start;
    private Nodo end;
    private String value;

    
    
    public Transition(Nodo start, Nodo end, String value) {
        this.start = start;
        this.end = end;
        this.value = value;
        buildInternal();
    }


    public Nodo getStart() {
        return start;
    }

    public void setStart(Nodo start) {
        this.start = start;
    }

    public Nodo getEnd() {
        return end;
    }

    public void setEnd(Nodo end) {
        this.end = end;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        buildInternal();
    }
    public void buildInternal(){
        if(value == "λ"){
            return;
        }
        if(value.length()>1){
            ThompsonCreator builder = new ThompsonCreator();
            builder.createThompson(this);
        }
    }
}
