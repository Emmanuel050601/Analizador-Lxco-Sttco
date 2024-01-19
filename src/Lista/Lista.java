/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
 * 
 * Clase de lista generica
 */

package Lista;

public class Lista <T> {
    Nodo <T> primero;
    Nodo <T> ultimo;
    String cabecera;
    
    /**
     * Constructor de la clase
     */
    public Lista(){
        primero = ultimo = null;
        cabecera = "";
    }
    
    /**
     * Constructor de la clase que recibe como parametro un String
     * @param cabecera 
     */
    public Lista(String cabecera){
        this.cabecera = cabecera;
        primero = ultimo = null;
    }
    
    /**
     * Metodo para insertar un valor en la lista
     * @param elemento 
     */
    public void inserta(T elemento){
        Nodo <T> nuevo = new Nodo (elemento);
        if(esVacia()){
            primero = nuevo;
            ultimo = nuevo;
        }else{
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }
    
    /**
     * Metodo que nos devuelve si la lista es vacia o no
     * @return 
     */
    public boolean esVacia(){ return primero == null; }
    
    /**
     * Metodo para vaciar la lista
     */
    public void vaciar(){ primero = ultimo = null; }
    
    /**
     * Metodo para devolvernos la informacion de la lista
     * @return 
     */
    @Override
    public String toString(){
        String texto = cabecera+"\n";
        Nodo auxiliar = primero;
        while(auxiliar.siguiente != null){
            texto += auxiliar.contenido.toString()+"\n";
            auxiliar = auxiliar.siguiente;
        }
        return texto + auxiliar.contenido+"\n";
    }
}
