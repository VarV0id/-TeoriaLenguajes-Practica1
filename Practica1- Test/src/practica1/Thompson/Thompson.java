/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.Thompson;

import practica1.Estructuras.Nodo;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jairo
 */
public class Thompson {
    private Nodo begin;
    private Nodo end;

    public Thompson(String er) {
        begin = new Nodo();
        end = new Nodo();
        begin.agregarTransicion(end, er);
        NodosSingleton.getInstance().addNodes(begin);
        NodosSingleton.getInstance().addNodes(end);
    }

    public Nodo getBegin() {
        return begin;
    }

    public void setBegin(Nodo begin) {
        this.begin = begin;
    }

    public Nodo getEnd() {
        return end;
    }

    public void setEnd(Nodo end) {
        this.end = end;
    }

}
