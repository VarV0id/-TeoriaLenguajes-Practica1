package practica1.test;

public class Node {
    Object symbol;
    Object state;
    boolean isHead;

    public Node(Object symbol, Object state, boolean isHead) {
        this.symbol = symbol;
        this.state = state;
        this.isHead = isHead;
    }

    public Object getSymbol() {
        return symbol;
    }

    public void setSymbol(Object symbol) {
        this.symbol = symbol;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }
}
