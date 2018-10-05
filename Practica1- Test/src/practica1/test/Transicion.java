/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.test;

/**
 *
 * @author Tatiana Gómez
 */
public class Transicion {
    
    private Nodo desde;
    private Nodo hacia;
    private String valor;

    
    
    public Transicion(Nodo desde, Nodo hacia, String valor) {
        this.desde = desde;
        this.hacia = hacia;
        this.valor = valor;
    }

    public Transicion() {
        desde = null;
        hacia = null;
    }
    
    
    public Nodo getDesde() {
        return desde;
    }

    public void setDesde(Nodo desde) {
        this.desde = desde;
    }

    public Nodo getHacia() {
        return hacia;
    }

    public void setHacia(Nodo hacia) {
        this.hacia = hacia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
}
