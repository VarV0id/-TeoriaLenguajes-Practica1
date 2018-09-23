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
public class ConstruccionesThompson {
    
    /**
     * Guarda en un vector los grafos
     */
    public Nodo[] grafo;
    /**
     * i : Estados
     */
    public int i;
    public String expr;
    
    ConstruccionesThompson(String e){
        i = 0;
        expr = e;
    }
    
    
    public void secNula(){
        
        if(i>0){
            i = i+1;
            grafo[i] = new Nodo( i);
            grafo[i-1].agregarTransicion(grafo[i-1], grafo[i], "¬");
        }else{
            i = i+1;
            grafo[i] = new Nodo(i);
            i = i+1;
            grafo[i] = new Nodo(i);
            grafo[i-1].agregarTransicion(grafo[i-1], grafo[i], "¬");
        }
    }
    
    public void reconoceString(String r){
        if(i > 0){
            grafo[i+1] = new Nodo(i+1);
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], r);
            i = i+1;
        }else{
            grafo[i+1] = new Nodo(i+1);
            grafo[i+2] = new Nodo(i+2);
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+2], r);
            i = i+2;
        }
    }
    
    public void reconoceConcatenacion(String r, String s){
        int j;
        if(i>0){
            for(j=1; j<=3; j++){
                grafo[i+j] = new Nodo(i+j);
            }
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], r);
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+2], "¬");
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+3], s);
            i = i+3;
        }else{
            for(j=1; j<=4; j++){
                grafo[i+j] = new Nodo(i+j);
            }
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+2], r);
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+3], "¬");
            grafo[i+3].agregarTransicion(grafo[i+3], grafo[i+4], s);
            i = i+4;
        }
    }
    
    public void reconoceUnion(String r, String s){
        int j;
        if(i>0){
            for(j=1; j<=6; j++){
                grafo[i+j] = new Nodo(i+j);
            }
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], "¬");
            grafo[i].agregarTransicion(grafo[i], grafo[i+2], "¬");
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+3], r);
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+4], s);
            grafo[i+3].agregarTransicion(grafo[i+3], grafo[i+5], "¬");
            grafo[i+4].agregarTransicion(grafo[i+4], grafo[i+5], "¬");
            grafo[i+5].agregarTransicion(grafo[i+5], grafo[i+6], "¬");
            i = i+6;
        }else{
            for(j=1; j<=7; j++){
                grafo[i+j] = new Nodo(i+j);
            }
            i = i+1;
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], "¬");
            grafo[i].agregarTransicion(grafo[i], grafo[i+2], "¬");
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+3], r);
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+4], s);
            grafo[i+3].agregarTransicion(grafo[i+3], grafo[i+5], "¬");
            grafo[i+4].agregarTransicion(grafo[i+4], grafo[i+5], "¬");
            grafo[i+5].agregarTransicion(grafo[i+5], grafo[i+6], "¬");
            i = i+6;
        }
    }
    
    public void reconoceClausura(String r){
        int j;
        if(i>0){
            for(j=1; j<=3; j++){
                grafo[i+j] = new Nodo(i+j);
            } 
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], "¬");
            grafo[i].agregarTransicion(grafo[i], grafo[i+3], "¬");
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+2], r);
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+1], "¬");
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+3], "¬");
            i = i+3;
        }else{
            for(j=1; j<=4; j++){
                grafo[i+j] = new Nodo(i+j);
            }
            i = i+1;
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], "¬");
            grafo[i].agregarTransicion(grafo[i], grafo[i+3], "¬");
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+2], r);
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+1], "¬");
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+3], "¬");
            i = i+3;
        }
    }
    
    public void reconoceClausuraSinSecNula(String r){
        int j;
        if(i>0){
        for(j=1; j<=3; j++){
                grafo[i+j] = new Nodo(i+j);
            } 
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], "¬");
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+2], r);
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+1], "¬");
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+3], "¬");
            i = i+3;
        }else{
            for(j=1; j<=4; j++){
                grafo[i+j] = new Nodo(i+j);
            }
            i = i+1;
            grafo[i].agregarTransicion(grafo[i], grafo[i+1], "¬");
            grafo[i+1].agregarTransicion(grafo[i+1], grafo[i+2], r);
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+1], "¬");
            grafo[i+2].agregarTransicion(grafo[i+2], grafo[i+3], "¬");
            i = i+3;
        }      
    }
    
    public int totalEstados(){
        return i;
    }
    
    public void construir(){
        int j;
    }
}
