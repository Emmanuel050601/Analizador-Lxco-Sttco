/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
 * 
 * Clase para utilizar la pila 
 */

package Lista;

public class Pila {
    private int tope;
    ListaPila lista = new ListaPila();
    
    /**
     * Constructor de la clase
     */
    public Pila(){ tope = 0; }
    
    /**
     * Metodo para insertar valores en la pila
     * @param x 
     */
    public void push(String x){
        if(!x.isEmpty()){
            lista.insertar(x);
            tope++;
        }
    }
    
    /**
     * Metodo para sacar informacion de la pila
     * @return 
     */
    public String pop(){
        if(isEmpty() == false){
            tope--;
            return lista.eliminar();
        }
        else{
            System.out.println("No hay elementos");
            return "?";
        }
    }
    
    /**
     * Metodo que nos devuelve informacion de la pila (Si es vacia o no)
     * @return 
     */
    public boolean isEmpty(){
            if(tope == 0) return true;
            else  return false;
    }
    
       public String primeroPila(){
        String valor;
        valor = this.pop();
        this.push(valor);
        return valor;
    }
    
}
