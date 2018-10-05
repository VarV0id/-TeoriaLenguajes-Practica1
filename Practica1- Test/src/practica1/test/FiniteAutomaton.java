package practica1.test;

import javafx.scene.chart.Chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FiniteAutomaton {
    List<String> symbols;
    String specialSimbols = "+().|*¬";
    int i;

    public FiniteAutomaton() {
        symbols = new ArrayList<String>();
        i = 0;
    }

    public void generateAutomaton(String input) {
        if(verificateValidExpression(input)){

        }


    }

    public void analizeSymbols(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!specialSimbols.contains(Character.toString(input.charAt(i)))) {
                String symbolResult = "";
                while(!specialSimbols.contains(Character.toString(input.charAt(i)))){
                    symbolResult= symbolResult+input.charAt(i);
                    i++;
                }
                if(!symbols.contains(symbolResult)){
                    symbols.add(symbolResult);
                }
            }
        }
    }
    public boolean verificateValidExpression(String input){
        String [][] transitionMatrix = {{"K.","!","K","!","!","!","!","R","0"},
                                        {"R.","N","!","C","K","K","N","!","1"},
                                        {"C.","N","!","!","K","K","N","!","1"},
                                        {"N.","!","K","C","K","K","R","!","1"}};
        if(parenthesisAnalyzer(input)) {
            int i = 0;
            String lastState = "K.";
            boolean isValid = false;
            while (input.charAt(i) != '¬') {
                int row = 0;
                while (row < transitionMatrix.length && !transitionMatrix[row][0].equals(lastState)) {
                    row++;
                }
                int column = specialSimbols.indexOf(input.charAt(i)) + 1;
                if (column == 0) {
                    column = 7;
                }

                while(column == 7 && specialSimbols.indexOf(input.charAt(i+1)) + 1 == 0){
                    i++;
                }

                if (transitionMatrix[row][column].equals("!")) {
                    return false;
                } else {
                    lastState = transitionMatrix[row][column] + ".";
                }
                i++;
            }
            int rowLastState = 0;
            while (rowLastState < transitionMatrix.length && !transitionMatrix[rowLastState][0].equals(lastState)) {
                rowLastState++;
            }
            if (transitionMatrix[rowLastState][8] == "1") {
                return true;
            }
        }
        return false;
    }
    
//    public String inTopos(String expr){
//        Stack pp = new Stack();
//        String x;
//        x = nextToken(expr);
//        while(!"~".equals(x)){
//            switch (x=)
//        } 
//    }    
    
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

    public boolean parenthesisAnalyzer(String input) {
        Stack<String> automatonStack = new Stack<String>();
        automatonStack.push("°");
        for (int i = 0; i < input.length(); i++) {
            String stackValue = automatonStack.peek();
            String symbolValue = Character.toString(input.charAt(i));
            switch (stackValue) {
                case "(":
                    switch (symbolValue) {
                        case "(":
                            automatonStack.push("(");
                            break;
                        case ")":
                            automatonStack.pop();
                            break;
                        case "¬":
                            return false;
                    }
                    break;
                case "°":
                    switch (symbolValue) {
                        case "(":
                            automatonStack.push("(");
                            break;
                        case ")":
                            return false;
                        case "¬":
                            return true;
                    }
                    break;
            }
        }
        return false;

    }

    public List<String> returnSymbols() {
        return symbols;
    }
}
