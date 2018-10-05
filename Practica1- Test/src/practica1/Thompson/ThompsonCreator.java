/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.Thompson;

import practica1.Estructuras.Nodo;
import practica1.Estructuras.Transition;
import practica1.ExpresionesRegulares.RegularExpression;


/**
 *
 * @author jairo
 */
public class ThompsonCreator {

    Thompson build;


    public ThompsonCreator(String er) {
        build = new Thompson(er.substring(0,er.length()-1));
        build.getEnd().setAcceptation(true);
        NodosSingleton.getInstance().setNumsToNodes();
    }
    public ThompsonCreator(){

    }

    public void createThompson(Transition x) {
        RegularExpression expression = new RegularExpression(x.getValue());
        int principal = expression.getPrincipalOperation();
        if(principal == -1){
            String newEr = expression.delSorroundParenthesis(x.getValue());
            x.setValue(newEr);
        }else {
            switch (expression.returnSymbol(principal)) {
                case "+":
                    plusOperator(x);
                    break;
                case "*":
                    kleeneStarOperator(x);
                    break;
                case ".":
                    concatOperator(x, principal);
                    break;
                case "|":
                    disyuntiveOperator(x, principal);
                    break;
            }
        }
    }

    private void concatOperator(Transition x, int index) {
        Nodo end = x.getEnd();
        String er = x.getValue();
        String exp1 = er.substring(0, index);
        String exp2 = er.substring(index+1);
        Nodo center = new Nodo();
        center.agregarTransicion(end,exp2);
        x.setEnd(center);
        x.setValue(exp1);
        NodosSingleton.getInstance().addNodes(center);
    }

    private void kleeneStarOperator(Transition x) {
        Nodo start = x.getStart();
        Nodo end = x.getEnd();
        String er = x.getValue();
        String exp1 = er.substring(0, er.length()-1);
        Nodo izqCenter = new Nodo();
        Nodo derCenter = new Nodo();
        izqCenter.agregarTransicion(derCenter,exp1);
        derCenter.agregarTransicion(end,  "λ");
        derCenter.agregarTransicion(izqCenter, "λ");
        x.setEnd(izqCenter);
        x.setValue("λ");
        start.agregarTransicion(end, "λ");
        NodosSingleton.getInstance().addNodes(izqCenter);
        NodosSingleton.getInstance().addNodes(derCenter);
    }

    private void disyuntiveOperator(Transition x, int index) {
        Nodo start = x.getStart();
        Nodo end = x.getEnd();
        String er = x.getValue();
        String exp1 = er.substring(0, index);
        String exp2 = er.substring(index+1);
        Nodo supIzq = new Nodo();
        Nodo supDer = new Nodo();
        Nodo infIzq = new Nodo();
        Nodo infDer = new Nodo();
        supIzq.agregarTransicion(supDer, exp1);
        infIzq.agregarTransicion(infDer, exp2);
        infDer.agregarTransicion(end, "λ");
        supDer.agregarTransicion(end, "λ");
        x.setEnd(supIzq);
        x.setValue("λ");
        start.agregarTransicion(infIzq, "λ");
        NodosSingleton.getInstance().addNodes(supIzq);
        NodosSingleton.getInstance().addNodes(supDer);
        NodosSingleton.getInstance().addNodes(infIzq);
        NodosSingleton.getInstance().addNodes(infDer);
    }

    private void plusOperator(Transition x) {
        Nodo start = x.getStart();
        Nodo end = x.getEnd();
        String er = x.getValue();
        String exp1 = er.substring(0, er.length()-1);
        Nodo izqCenter = new Nodo();
        Nodo derCenter = new Nodo();
        izqCenter.agregarTransicion(derCenter,exp1);
        derCenter.agregarTransicion(end,  "λ");
        derCenter.agregarTransicion(izqCenter, "λ");
        x.setEnd(izqCenter);
        x.setValue("λ");
        NodosSingleton.getInstance().addNodes(izqCenter);
        NodosSingleton.getInstance().addNodes(derCenter);
    }
}
