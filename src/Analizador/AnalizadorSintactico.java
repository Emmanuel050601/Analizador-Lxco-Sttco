package Analizador;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
*/

import Gramatica.Gramatica;
import Lista.Pila;
import java.util.ArrayList;

public class AnalizadorSintactico {
    private final Pila pila;
    private final Gramatica gramatica;
    private String x, a;
    private final int [][] matriz;
    private final ArrayList <ArrayList> array;
    private final Analizador anali;
    
    
    public AnalizadorSintactico(){
        System.out.println("Entro a constructor");
    pila = new Pila();
    gramatica = new Gramatica();
    gramatica.leerGramatica();
    gramatica.generaEstructuras();
    x = "!";
    a = "!";
    matriz = gramatica.getMatriz();
    array = gramatica.getProducciones();
    anali = new Analizador();
    anali.inicia();
    }
    
    public void llDriver(){
        int nx;
        int na;
        pila.push(gramatica.simboloInicial());
        x = pila.primeroPila();
        System.out.println("la variable x es:" +x);
        a = anali.regresaToken();
        System.out.println("La variable a es:"+a);
        
        while(pila.isEmpty()==false){
            if(gramatica.esTerminal(x)==false){
                nx = gramatica.numeroNTerm(x);
                System.out.println("El numero nx es:"+nx);
                na = gramatica.numeroTerm(a);
                System.out.println("El numero na es:"+na);
                if(matriz[nx][na] != 0){//el uno representa la posicion en el arreglo de la matriz predictiva
                    System.out.println("se saco de la pila: "+pila.pop());
                    for (int i = array.get(matriz[nx][na]).size()-1; i >=0 ; i--)
                        pila.push((String)array.get(matriz[nx][na]).get(i));
                    System.out.println("Esta en el tope de la pila: "+ pila.primeroPila());
                    x = pila.primeroPila();
                    System.out.println("Verifica numeros");
                    System.out.println(a);
                    System.out.println(x);
                    System.out.println("");
                }
                else{
                    System.out.println("Error detectado");
                    break;
                }
            }
            else{
                if(x.equals(a)){
                    System.out.println("se saco de la pila el terminal: "+pila.pop());
                    //obtiene el siguiente token
                    a =anali.regresaToken();
                    System.out.println("El token nuevo es: "+a);
                    x = pila.primeroPila();
                    if(x.equals("?")){
                        System.out.println("El programa ha terminado de analizarse");
                        break;
                    }
                    else
                        System.out.println("El que esta en el tope de la pila Term: "+x);
                }
                else{
                    System.out.println("Error detectado");
                    break;
                }
            }        
        }       
    }
}
