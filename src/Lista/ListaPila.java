/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
 * 
 * Clase de la lista para la pila 
 */

package Lista;

//Lista para la pila 
public class ListaPila {

    private Nodo ini, fin;//Nodos que nos serviran para hacerla con doble 
                              //enlace, para el mejor manejo de la pila
    
    /**
     * Constructor de la clase
     */
    public ListaPila() {//inicializamos los nodos en nulo
        ini = fin = null;
    }

    /**
     * Metodo para insertar informacion a la lista de la pila
     * @param i 
     */
    public void insertar(String i) {
        Nodo nuevo = new Nodo(i);
        if (ini != null) {
            fin.siguiente = nuevo;
            nuevo.anterior = fin;
            fin = nuevo;
        } else {
            ini = fin = nuevo;
        }
    }
    
    /**
     * Metodo para eliminar informacion de la lista de la pila
     * @return 
     */
    public String eliminar(){
        String valor;
        Nodo temp;
        if (ini == fin){
            valor =(String) ini.obtenerElemento();
            ini.anterior = null;
            ini.siguiente = null;
            fin.siguiente = null;
            fin.anterior = null;
        }
        else{
            temp = fin.anterior.anterior;
                valor = (String)fin.obtenerElemento();
                fin = fin.anterior;
                fin.anterior = temp;
                fin.siguiente = null;
        }
        return valor;
    }
}
