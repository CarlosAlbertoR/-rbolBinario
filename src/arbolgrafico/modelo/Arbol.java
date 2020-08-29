
package arbolgrafico.modelo;

import arbolgrafico.vista.ArbolExpresionGrafico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author alese
 */
//Crea la clase arbol
public class Arbol {
//Declara la raiz ,Declara la cantidad de nodos,Declara la altura del arbol
    private Nodo raiz;
    int cant;
    int altura;

   //Metodos Get y Set

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void setRaiz(Nodo r) {
        this.raiz = r;
    }
//Constructor
    public Arbol() {
        this.raiz = null;
    }
//Metodo para agregar un nuevo nodo el cual llama al metodo insertar
    public boolean agregar(int dato) {
        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, raiz);
        return true;
    }
//Metodo para insertar un nuevo nodo al arbol
    public void insertar(Nodo nuevo, Nodo pivote) {
        if (this.raiz == null) {
            raiz = nuevo;
        } else {
            if (nuevo.getDato() <= pivote.getDato()) {
                if (pivote.getIzq() == null) {
                    pivote.setIzq(nuevo);
                } else {
                    insertar(nuevo, pivote.getIzq());
                }
            } else {
                if (pivote.getDer() == null) {
                    pivote.setDer(nuevo);
                } else {
                    insertar(nuevo, pivote.getDer());
                }
            }
        }

    }
    //Obtiene la cantidad de nodos del arbol
    public String cantidadNodos() {
        cant = 0;
        cantidadNodos(raiz);
        return ""+cant;
    }
    //Este metodo es llamado por cantidadNodos
    private void cantidadNodos(Nodo reco) {
        if (reco != null) {
            cant++;
            cantidadNodos(reco.getIzq());
            cantidadNodos(reco.getDer());
        }
    }
    
    
    public String cantidadNodosHoja() {
        cant = 0;//Inicializa el contador en 0
        cantidadNodosHoja(raiz);//Llama el metodo cantidadNodosHoja
        return ""+cant;//Imprime la cantidad de hojas
    }
      private void cantidadNodosHoja(Nodo reco) {
        if (reco != null) {//Mira si la raiz es null o si no entra a recorer
            if (reco.getIzq() == null && reco.getDer() == null) {
                cant++;
            }//Mira si los nodos de la izq y der son null lo cual quiere decir que es una hoja e incrementa la cantidad en 1
            //El metodo se llama a si mismo tanto con el nodo de la izq como el de la der
            cantidadNodosHoja(reco.getIzq());
            cantidadNodosHoja(reco.getDer());
        }
    }

      //altura del arbol
      public String retornarAltura() {
        altura = 0;//Inicializa el contador en 0
        retornarAltura(raiz, 1);//llama al metodo retornar retornarAltura luego retorna en un string la altura del arbol
        return ""+altura;
    }

    private void retornarAltura(Nodo reco, int nivel) {
        if (reco != null) {//si es diferente a null llama al metodo de la izq y der 
            retornarAltura(reco.getIzq(), nivel + 1);
            if (nivel > altura) {//Si el nivel del nodo es mayor al contador de altura incrementa el contador de altura en 1
                altura = nivel;
            }
            retornarAltura(reco.getDer(), nivel + 1);
        }
    }
    
    //Busca el nodo con menorValor y lo recorre por la izq
     public String menorValor() {
         Nodo reco = raiz;
        if (raiz != null) {
            
            while (reco.getIzq() != null) {
                reco = reco.getIzq();
            }
        }
        return ("" + reco.getDato());
    }
     //Busca el nodo con mayorValor y lo recorre por la der
    public String mayorValor() {
        Nodo reco = raiz;
        if (raiz != null) {
            while (reco.getDer() != null) {
                reco = reco.getDer();
            }
        }
        return ("" + reco.getDato());
    }
    
    //inicializa los contadores de la izq y der en 0
    int subizq = 0;
    int subder = 0;

    public String imprimirBalance() {
         subizq = 0;
         subder = 0;
         //Llama al metodo balance 
        Balance(this.raiz, true, 0);
        //System.out.println("lado Izquierdo " + subizq + " Lado Derecho " + subder);
        //Si la resta del conteo de niveles es igual a 0 entonces el arbol es balanceado
        //Si la resta del conteo de niveles es igual a -1 entonces el arbol es un nivel mas alto a la derecha
        //si la resta del conteo de niveles es igual a 1 entonces el arbol es un nivel mas alto a la izquierda
        if (subizq - subder == 0) {
            return ("El balance es: 0 ");
        } else if (subizq - subder == -1) {
            return("El balance es -1, derecha");
        } else if (subizq - subder == 1) {
            return("El balance 1, izquierda");
//Imprime si el arbol no es balanceado verificando porque lado es mas alto
        } else {
            return("No es balanceado.."
                    + "porque es mas grande el lado "
                    + ((subizq > subder) ? "Izquierdo" : "Derecho"));
        }

    }

    public void Balance(Nodo reco, boolean lado, int i) {
        //Verifica que el nodo actual sea diferente de null
        if (reco != null) {
         //Verifica si el nodo de la izq y nodo der sean iguales a null
            if (reco.getDer() == null && reco.getIzq() == null) {
                if (lado) {
                    subder = (i > subder) ? i : subder;
                } else {
                    subizq = (i > subizq) ? i : subizq;
                }
            }

            Balance(reco.getDer(), lado, i + 1);
            if (i == 0) {
                lado = false;
            }
            Balance(reco.getIzq(), lado, i + 1);
        }

    }
    
    public String borrarMenor() {
         //Crea un objeto que llama reco y lo inicializa en la raiz.izq
        Nodo reco=raiz.getIzq();
        if (raiz != null) {//si la raiz es diferente de null
            if (raiz.getIzq() == null) {//si la raiz .izq es null la nueva raiz sera la dereha
                raiz = raiz.getDer();
            } else {//Crea un nuevo objeto llamado anterior y lo inicializa en la raiz
                Nodo anterior = raiz;
                reco = raiz.getIzq();//Cambia el valor de reco por raiz.izq
                while (reco.getIzq() != null) {//ejecuta un while que mientras reco.izq sea diferente de null
                    anterior = reco;//anterior sera igual a reco
                    reco = reco.getIzq();//Reco sera igual a reco.izq
                }
                
                anterior.setIzq(reco.getDer());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }

    //borrar mayor
    public String borrarMayor() {
        Nodo reco=raiz.getIzq();//Crea un objeto que llama reco y lo inicializa en la raiz.izq
        //si la raiz es diferente a null entonces si raiz.der es igual a null la raiz es igual a .izq
        //si no ejecuta el while hasta encontrar el elemento el mayor y lo elimina
        if (raiz != null) {
            if (raiz.getDer() == null) {
                raiz = raiz.getIzq();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getDer();
                while (reco.getDer() != null) {
                    anterior = reco;
                    reco = reco.getDer();
                }
                
                anterior.setDer(reco.getIzq());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    //imprimir ordenado con niveles
    //inicializa un arreglo para los diferentes niveles del arbol
    String[] niveles;

    public int alturaArbol() {
        altura = 0;
        alturaArbol(raiz, 0);
        return altura;
    }
//Cuenta la altura del arbol
    private void alturaArbol(Nodo pivote, int nivel) {
        if (pivote != null) {
            alturaArbol(pivote.getIzq(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            alturaArbol(pivote.getDer(), nivel + 1);
        }
    }
//Imprime un arreglo por cada nivel del arbol
    public ArrayList imprimirNivel() {
        niveles = new String[altura + 1];
        ArrayList l=new ArrayList();
        imprimirNivel(raiz, 0);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            //System.out.println(niveles[i] + " ");
        }
        return l;
    }
    //Imprime cada nodo con su respectivo nivel
      public void imprimirNivel(Nodo pivote, int nivel2) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDer(), nivel2 + 1);
            imprimirNivel(pivote.getIzq(), nivel2 + 1);
        }
    }
      
      //Cambia todos los datos del arbol
      public boolean cambiar() {
            cambiar(raiz, 1);
            //System.out.println();
            return true;
    }
//Incrementa cada dato del arbol multiplicando por 3
    private void cambiar(Nodo reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato() * 3);
            cambiar(reco.getIzq(), nivel + 1);
            //System.out.print(reco.getDato() + " Nivel: (" + nivel + ") ,");
            cambiar(reco.getDer(), nivel + 1);
        }
    }
    //Obtiene la rama mayor del arbol
    int numeroRamas = 0;
    public ArrayList<String>ObtenerRamamayor(){
        obtenernumeroRamas(this.raiz, 0);
        return ObtenerRamamayor(this.raiz, 0, "", new ArrayList<String>());
    }
    //Obtiene el numero de ramas del arbol 
    public void obtenernumeroRamas(Nodo pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzq(), contador);
            obtenernumeroRamas(pivote.getDer(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }
//Obtiene la rama mayor pero con una lista de sus nodos
     public ArrayList<String> ObtenerRamamayor(Nodo pivote, int contador, String dato, ArrayList lista){
        if (pivote != null ) {
            dato+=pivote.getDato()+",";
            contador ++;
            lista=ObtenerRamamayor(pivote.getIzq(), contador, dato, lista);
            lista=ObtenerRamamayor(pivote.getDer(), contador, dato, lista);
            
            if (contador == this.numeroRamas) {
                lista.add(dato);
            }
        }
        return lista;
    }
    
    //Borrar un elemento dado de la lista
    public int borrar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }

        Nodo z = borrar(this.raiz, x);
        this.setRaiz(z);
        return x;

    }

    private Nodo borrar(Nodo r, int x) {
        if (r == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            r.setIzq(borrar(r.getIzq(), x));
        } else if (compara < 0) {
            r.setDer(borrar(r.getDer(), x));
        } else {
            System.out.println("Encontro el dato:" + x);
            if (r.getIzq() != null && r.getDer() != null) {
                /*
                 *	Buscar el menor de los derechos y lo intercambia por el dato
                 *	que desea borrar. La idea del algoritmo es que el dato a borrar 
                 *	se coloque en una hoja o en un nodo que no tenga una de sus ramas.
                 **/
                Nodo cambiar = buscarMin(r.getDer());
                int aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDer(borrar(r.getDer(), x));
                System.out.println("2 ramas");
            } else {
                r = (r.getIzq() != null) ? r.getIzq() : r.getDer();
                System.out.println("Entro cuando le faltan ramas ");
            }
        }
        return r;
    }

    //buscar un elemento dentro del arbol
    public boolean buscar(int x) {
        return (buscar(this.raiz, x));


    }
    //Hace el trabajo de busqueda en el arbol si lo encutra retorna a true
    private boolean buscar(Nodo r, int x) {
        if (r == null) {
            return (false);
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(r.getIzq(), x));
        } else if (compara < 0) {
            return (buscar(r.getDer(), x));
        } else {
            return (true);
        }
    }

    //buscar el valor minimo dentro del arbol
    private Nodo buscarMin(Nodo r) {
        for (; r.getIzq() != null; r = r.getIzq());
        return (r);
    }
       //imprime en preorden inicializando en la raiz hacia la izquierda cuando termna lo recorre a la derecha
    public ArrayList preOrden() {
        ArrayList l=new ArrayList();
        preOrden(raiz,l);
        return l;
    }

    private void preOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            l.add(reco.getDato() + " ");
            preOrden(reco.getIzq(),l);
            preOrden(reco.getDer(),l);
        }
    }
    //imprimir en inOrden Recorre hacia el ultimo de la izquierda vuelve al padre y leugo recorre a la derecha
    public ArrayList inOrden() {
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }

    private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzq(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDer(),l);
        }
    }

//imprimir post orden Recorre hacia la izquierda hasta null luego mira si en la derecha tambien es null
    //lo marca y luego se devuelve al padre lo marca y asi sucesibamente
    public ArrayList postOrden() {
        ArrayList l=new ArrayList();
        postOrden(raiz,l);
        return l;
    }

    private void postOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            postOrden(reco.getIzq(),l);
            postOrden(reco.getDer(),l);
            l.add(reco.getDato() + " ");
        }
    }
    
    //Imprime por niveles del arbol
       public ArrayList impNiveles() {
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }

    private void impNiveles(Nodo reco, int nivel,ArrayList l) {
        if (reco != null) {
            impNiveles(reco.getIzq(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDer(), nivel + 1, l);
        }
    }
    
    //Imprime un arreglo con todas las hojas del arbol
    public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }

    private void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzq(), l);
            getHojas(r.getDer(), l);
        }

    }
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzq() == null && x.getDer() == null);
    }
    
    
    public int padre(int info) {
        if (info == 0 || this.raiz == null) {
            return 0;
        }
        Nodo x = padre(this.raiz, info);
        if (x == null) {
            return 0;
        }
        return (x.getDato());
    }

    private Nodo padre(Nodo x, int info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzq() != null && x.getIzq().getDato()==(info)) || (x.getDer() != null && x.getDer().getDato()==(info))) {
            return (x);
        }
        Nodo y = padre(x.getIzq(), info);
        if (y == null) {
            return (padre(x.getDer(), info));
        } else {
            return (y);
        }
    }
    
    //Elimina las hojas del arbol Cuando verifica si .izq y .der son null
     public void podar() {
        podar(this.raiz);
    }

    private void podar(Nodo x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzq())) {
            x.setIzq(null);
        }
        if (this.esHoja(x.getDer())) {
            x.setDer(null);
        }
        podar(x.getIzq());
        podar(x.getDer());
    }
   
    
    
    
    //dibuja el arbol
     public JPanel getdibujo() {
        return new ArbolExpresionGrafico(this);
    }
}
