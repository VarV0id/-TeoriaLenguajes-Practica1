package practica1.Estructuras;

import java.util.ArrayList;
import java.util.List;

public class FiniteAutomaton{

    List<List<String>> transitionsTable;
    int rows;
    int columns;
    int states;
    public FiniteAutomaton() {
        transitionsTable = new ArrayList<>();
        List<String> colEmpty = new ArrayList<>();
        rows= 1;
        this.columns = 1;
        colEmpty.add("");
        transitionsTable.add(colEmpty);
        states = 0;
    }
    public void addEntrySymbol(String symbol){
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
    public void addStates(String state){
        if(rows >= 3){
            
        }
    }
    public List<List<String>> getFiniteAutomaton() {
        return transitionsTable;
    }
//    public String inTopos(String expr){
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
