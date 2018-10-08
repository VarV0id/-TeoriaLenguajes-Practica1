package practica1.Estructuras;

import practica1.ExpresionesRegulares.RegularExpression;
import practica1.LambdaV2.LambdaBuilder;
import practica1.Thompson.Thompson;

import java.util.*;

public class FiniteAutomaton{

    List<Nodo> nodosList;
    List<TreeSet<String>> conjuntos;
    Thompson th;
    RegularExpression expression;
    int cantConjuntos;

    public FiniteAutomaton(Thompson th, RegularExpression expression) {
        conjuntos = new LinkedList<>();
        nodosList = new ArrayList<>();
        this.th = th;
        this.expression = expression;
        cantConjuntos = 0;
    }

    public void crearAutomata(){
        LambdaBuilder generator = new LambdaBuilder(th);
        Nodo initial = generator.searchInitialState();
        TreeSet<String> primero = new TreeSet<>();
        conjuntos.add(generator.buildLambdaClose(initial,primero));
        Nodo first = new Nodo();
        nodosList.add(first);
        cantConjuntos++;

        List<String> symbols = expression.returnSymbols();
        int i = 0;
        while(i < cantConjuntos){
            for(int j = 0; j < symbols.size(); j++){
                TreeSet<String> conjuntosTemp = new TreeSet<>();
                conjuntosTemp = generator.setSymbolLambdaClosure(symbols.get(j),conjuntos.get(i));
                Nodo x = new Nodo();
                if(conjuntosTemp.contains(initial.getIdentifier())){
                    x.setAcceptation(true);
                }
                if(!conjuntos.contains(conjuntosTemp)){
                    conjuntos.add(conjuntosTemp);
                    cantConjuntos++;
                    nodosList.add(x);
                }
                nodosList.get(i).agregarTransicion(x,symbols.get(j));
            }
            i++;
        }

    }
   /* public void addEntrySymbol(String symbol){
        List<String> colSymbol = new ArrayList<>();
        colSymbol.add(symbol);
        List<String> colEmpty = new ArrayList<>();
        colEmpty.add("");
        if(columns == 1){
            transitionsTable.add(colSymbol);
            transitionsTable.add(colEmpty);
            columns=columns+2;
        }else{
            transitionsTable.remove(columns-1);
            transitionsTable.add(colSymbol);
            transitionsTable.add(colEmpty);
            columns++;
        }
    }
    public void addStates(String state, String isAcceptated){
        if(columns >= 3){
            for(int i = 0; i <columns; i++) {
                if (i == 0) {
                    transitionsTable.get(i).add(state);
                }else {
                    transitionsTable.get(i).add("");
                }
            }
            rows++;
            transitionsTable.get(columns-1).set(rows-1, isAcceptated);
        }
    }
    public void setTransition(String symbol, String state, String transition){
        int column = 0;
        for(int i = 0; i < columns; i++){
            if(transitionsTable.get(i).get(0).equals(symbol)){
                column = i;
            }
        }
        int indexState = transitionsTable.get(0).indexOf(state);
        transitionsTable.get(column).set(indexState,transition);
    }
    public String getTransition(String symbol, String state){
        int column = 0;
        for(int i = 0; i < columns; i++){
            if(transitionsTable.get(i).get(0).equals(symbol)){
                column = i;
            }
        }
        int indexState = transitionsTable.get(0).indexOf(state);
        return transitionsTable.get(column).get(indexState);
    }
    public List<List<String>> getFiniteAutomaton() {
        return transitionsTable;
    }*/





// public String inTopos(String expr){
//        Stack pp = new Stack();
//        String x;
//        x = nextToken(expr);
//        while(!"~".equals(x)){
//            switch (x=)
//        } 
//    }    
/*

    private String nextToken(String e){
        int j;
        String c;
        j = e.length();
        i = i+1;
        if(i > j){
            i = 0;
            c = "~"; //Fin de expresión
        }else{
            c = Character.toString(e.charAt(i));
        }
        return c; 
    }
*/



}
