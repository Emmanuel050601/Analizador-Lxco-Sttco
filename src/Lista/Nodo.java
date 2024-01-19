package Lista;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
 * @param <T>
*/

public class Nodo <T> {
    public Object contenido;  // Valor almacenado en el nodo 
    public Nodo siguiente;    // Referencia al siguiente elemento
    public Nodo anterior;     // Referencia al elemento anterior

  /**
   * Metodo para crear un nodo con elemento igual a valor y apuntando al vacio.
   * @param valor --  objeto que es el valor de nodo
   */
  Nodo(Object valor) {  this (valor, null); }

  /**
   * Metodo para crear un nodo antes del indicado, con elemento igual a valor.
   * @param valor -- objeto que es el valor de nodo
   * @param n --  nodo al que apunta el recien creado
   */
  Nodo(Object valor, Nodo n) {
      contenido = valor;
      siguiente = n;
      anterior = n;
  }

  /**
   * Metodo que devuelve el valor de un nodo
   * @return Object el valor del nodo
   */
  public Object obtenerElemento () { return contenido; }

  /**
   * Metodo que devuelve la referencia del siguiente nodo
   * @return NOdo el siguiente nodo
   */
  public Nodo obtenerSgte() { return siguiente;}
  
  /**
   * Metodo que devuelve la referencia del nodo anterior
   * @return NOdo el siguiente nodo
   */
  public Nodo obtenerAnt(){return anterior;}
  
}

