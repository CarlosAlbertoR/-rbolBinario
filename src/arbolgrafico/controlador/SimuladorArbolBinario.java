
package arbolgrafico.controlador;

import arbolgrafico.modelo.Arbol;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ploks
 */

/**
 * 
 * @author alese
 */

//Crea la clase Arbol
public class SimuladorArbolBinario {
//Inicializa un objeto llamado miArbol el cual es de la clase arbol
    Arbol miArbol = new Arbol();

    public SimuladorArbolBinario() {
    }
//Crea un metodo insertar el cual llama el metodo agregar de la clase arbol
    public boolean insertar(Integer dato) {
        return (this.miArbol.agregar(dato));
    }
//Crea un metodo borrar de tipo String el cual llama al metodo borrar de la clase arbol
    public String borrar(Integer dato) {
        Integer x = this.miArbol.borrar(dato);
        //Si el dato a borrar no existe en el arbol retorna el mensaje "No existe el dato en el arbol"
        if (x == null) {
            return ("No existe el dato en el arbol");
        }
        //Si existe elimina el dato y retorna el mensaje "Borrado el dato
        return ("Borrado el dato: " + x.toString());
    }
//Hace un recorrido preOrden en el arbol
    public String preOrden() {
        ArrayList it = this.miArbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden"));
    }
//Hace un recorrido inOrden en el arbol
    public String inOrden() {
        ArrayList it = this.miArbol.inOrden();
        return (recorrido(it, "Recorrido InOrden"));
    }
//Hace un recorrido posOrden en el abrol
    public String posOrden() {
        ArrayList it = this.miArbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    }
//Hace un impresion del arbol por niveles del arbol
    public String imprimirPorNiveles() {
        ArrayList it = this.miArbol.impNiveles();
        return (recorrido(it, "Imprimir Por niveles"));
    }
//Imprime las hojas del arbol,las cuales no tienen hijos
    public String darHojas() {
        ArrayList it = this.miArbol.getHojas();
        return (recorrido(it, "Hojas del Arbol"));
    }
//Le doy un Nodo y me dice cual es el padre
    public String darPadre(Integer hijo) {
        if (this.miArbol.getRaiz().getDato() == (hijo)) {
            return ("La raiz no tiene padre");
        }
        Integer padre = this.miArbol.padre(hijo);
        if (padre == null) {
            return ("No existe el Dato: " + hijo.toString());
        }
        return ("El padre de: " + hijo + "\n es : " + padre.toString());
    }
//Metodo para saber si un nodo esta en el arbol
    public String esta(Integer dato) {
        boolean siEsta = this.miArbol.buscar(dato);
        String r = "El dato:" + dato.toString() + "\n";
        r += siEsta ? "Si se encuentra en el arbol" : "No se encuentra en el arbol";
        return (r);
    }
//Recorre el arbol
    private String recorrido(ArrayList it, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < it.size()) {
            r += "\t" + it.get(i).toString() + "\n";
            i++;
        }
        return (r);
    }
    //Retorna la cantidad del arbol
    public String CantidadNodos(){
        return this.miArbol.cantidadNodos();
    }
    //Retorna la cantidad de hojas
    public String CantidadHojas(){
        return this.miArbol.cantidadNodosHoja();
    }
    //Retorna la altura del arbol
    public String alturaArbol(){
        return this.miArbol.retornarAltura();
    }
    //Retorna el nodo de menor valor
    public String menorValor(){
        return this.miArbol.menorValor();
    }
    //Retorna el nodo de mayor valor
    public String mayorValor(){
        return this.miArbol.mayorValor();
    }
    //Imprime si el arbol es balancero en caso que no muestra cual es la rama que mas tiene
    public String balance(){
        return this.miArbol.imprimirBalance();
    }
    //Elimina el nodo de menor valor
    public String borrarMenor(){
        return this.miArbol.borrarMenor();
    }
    //Elimina el nodo de mayor valor
    public String borrarMayor(){
        return this.miArbol.borrarMayor();
    }
    //Imprime por niveles en orden
    public String porNivel(){
        this.miArbol.alturaArbol();
        ArrayList it = this.miArbol.imprimirNivel();
        return (recorrido(it, "Imprimir Por niveles en orden!!!"));
    }
    //Cambia los valores del arbol
    public String cambiar() {
        this.miArbol.cambiar();
        return "Valores cambiados!!!";
    }
    //Recorre el arbol para decir cual es la rama mayor
    public String ramaMayor() {
        ArrayList it = this.miArbol.ObtenerRamamayor();
        return (recorrido(it, "Rama(s) con mas valores"));
    }
    
//Elimina hojas del arbol
    public void podarArbol() {
        this.miArbol.podar();
    }
//Metodo para Dibujar el Arbol
    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
