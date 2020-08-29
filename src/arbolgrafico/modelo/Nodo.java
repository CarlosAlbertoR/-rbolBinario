/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolgrafico.modelo;

/**
 *
 * @author ploks
 */
//Crea la clase Nodo
public class Nodo {
    //Declara la variable dato y declara la izq y der
    private int dato;
    private Nodo izq, der;
    //Contructor
    public Nodo(int dato, Nodo izq, Nodo der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }
    //Metodos Get y Set de todos (De la izq y la der)
    public int getDato() {
        return dato;
    }
    
    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    } 

}
