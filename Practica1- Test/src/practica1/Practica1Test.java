/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import practica1.Estructuras.Nodo;
import practica1.ExpresionesRegulares.RegularExpression;
import practica1.Thompson.NodosSingleton;
import practica1.Thompson.ThompsonCreator;

import java.util.List;

/**
 *
 * @author Study-Development
 */
public class Practica1Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RegularExpression a = new RegularExpression("(1.2|3.5|4*.2+|G|G)*.a¬");
        System.out.println("///////////////////////////////");
        System.out.println(a.analizeSymbols());
        System.out.println("///////////////////////////////");
        System.out.println(a.parenthesisAnalyzer());
        List<String> raro = a.returnSymbols();
        for(int i = 0; i <raro.size();i++){
            System.out.print(raro.get(i)+" ");
        }
        String p = "1";
        System.out.println("\n"+a.verificateValidExpression());
        boolean esClase =  p.getClass().getName() == String.class.getName();
        System.out.println("//////////////////////////////");
        if(esClase){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        a.getPrincipalOperation();
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        NodosSingleton nodos = NodosSingleton.getInstance(); // de esta forma se saca la lista de nodos para hacer el cierre lambda
        // debe ejecutarse la siguiente linea para que el vector se llene
        ThompsonCreator th = new ThompsonCreator("(a+.b.(c|a)*)¬");
        List<Nodo>listaDeNodos = nodos.getNodesList(); // Lista de nodos luego de construccion de thompson



    }

    
}
