package practica1.ExpresionesRegulares;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RegularExpression {
    final static String SPECIALSYMBOLS = "+().|*¬";
    List<String> symbols;
    String er;
    final static String OPERATORS = ".|*+()";
    private static int [] PRIORITY = {3,4,2,2,1,1};

    public RegularExpression(String er){
        symbols = new Stack<>();
        this.er = er;
    }
    public String returnRegularExpression(){
        return er;
    }  
    public boolean verificateValidExpression(){
        String [][] transitionMatrix = {{"K.","!","K","!","!","!","!","R","0"},
                                        {"R.","N","!","C","K","K","N","!","1"},
                                        {"C.","N","!","!","K","K","N","!","1"},
                                        {"N.","!","K","C","K","K","R","!","1"}};
        if(parenthesisAnalyzer()) {
            int i = 0;
            String lastState = "K.";
            boolean isValid = false;
            while (er.charAt(i) != '¬') {
                int row = 0;
                while (row < transitionMatrix.length && !transitionMatrix[row][0].equals(lastState)) {
                    row++;
                }
                int column = SPECIALSYMBOLS.indexOf(er.charAt(i)) + 1;
                if (column == 0) {
                    column = 7;
                }

                while(column == 7 && SPECIALSYMBOLS.indexOf(er.charAt(i+1)) + 1 == 0){
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
    public boolean analizeSymbols() {
        for (int i = 0; i < er.length(); i++) {
            if (!SPECIALSYMBOLS.contains(Character.toString(er.charAt(i)))) {
                String symbolResult = Character.toString(er.charAt(i));
                if(!SPECIALSYMBOLS.contains(Character.toString(er.charAt(i+1)))){
                    return false;
                }
//                while(!SPECIALSYMBOLS.contains(Character.toString(er.charAt(i)))){
//                    symbolResult= symbolResult+er.charAt(i);
//                    i++;
//                }
               if(!symbols.contains(symbolResult)){
                    symbols.add(symbolResult);
               }

            }
        }
        return true;
    }
    public boolean parenthesisAnalyzer() {
        Stack<String> automatonStack = new Stack<String>();
        automatonStack.push("°");
        for (int i = 0; i < er.length(); i++) {
            String stackValue = automatonStack.peek();
            String symbolValue = Character.toString(er.charAt(i));
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
    public int getPrincipalOperation(){
        int parentesis = 0;
        List<Integer> opEr = new ArrayList<>();
        int mainOperand = -1;

        for (int token = 0; token< er.length();token++) {
            String element = Character.toString(er.charAt(token));

            if(element.equals(OPERATORS.substring(4,5))){
                parentesis++;
            }
            if(parentesis == 0 && OPERATORS.contains(element)){
                opEr.add(token);
                mainOperand = token;
            }
            if(element.equals(OPERATORS.substring(5,6))) {
                parentesis--;
            }
        }
        if(!opEr.isEmpty()){
            for(int i = 0; i < opEr.size();i++){
                if(returnPriority(er.charAt(opEr.get(i))) > returnPriority(er.charAt(mainOperand))){
                    System.out.println(OPERATORS.indexOf(er.charAt(opEr.get(i))));
                    System.out.println(OPERATORS.indexOf(er.charAt(mainOperand)));
                    mainOperand = opEr.get(i);
                }
            }
        }
        return mainOperand;
    }
    public int returnPriority(Character operator) {
        return PRIORITY[OPERATORS.indexOf(operator)];
    }
    public String returnSymbol(int index){
        return er.substring(index,index+1);
    }
    public String delSorroundParenthesis(String er){
        return er.substring(1,er.length()-1);
    }

}
