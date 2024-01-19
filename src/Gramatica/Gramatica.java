package Gramatica;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
*/

import Archivo.Archivo;
import java.util.ArrayList;

public class Gramatica {

    private Archivo arch = new Archivo();
    private ArrayList<String> gramatica, ladoDer, noTerminales, terminales, palabras;
    private ArrayList<ArrayList> produc;
    private String [] der, izq;
    private String var;
    private int bandera;
    private String palabra;
    private boolean continuar;
    private int tope, fin, ini;
    private int [][] matriz = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,3,0,0,0,0,3,0,0,3,0,0,0,3,3,0,0,0},
        {0,0,4,0,5,0,0,4,0,0,4,0,0,0,4,4,0,0,0},
        {0,0,7,0,0,0,0,8,0,0,9,0,0,0,6,6,0,0,0},
        {0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,12,0,0,0,12,0,11,0,0,0,0,0,0,0},
        {0,0,13,0,0,0,0,0,0,0,0,0,13,13,0,0,0,0,0},
        {0,0,0,0,0,15,0,0,0,15,0,0,0,0,0,0,14,14,14},
        {0,0,17,0,0,0,0,0,16,0,0,0,18,19,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,20,21,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,23,24},
    };
    
    public Gramatica() {
        gramatica = new ArrayList();
        ladoDer = new ArrayList();
        terminales = new ArrayList();
        noTerminales = new ArrayList();
        palabras = new ArrayList();
        produc = new ArrayList();
    }

    public void leerGramatica() { gramatica = arch.getTexto(); }

    public int [][] getMatriz(){ return matriz; }
    
    public ArrayList getGramatica() { return gramatica; }

    public ArrayList getLadoDer() {
        ladoDer.add("!");
        for (int i = 0; i < gramatica.size(); i++) {
            der = gramatica.get(i).split(">");
            var = der[1];
            var = var.substring(1);
            ladoDer.add(var);                         
        }
        return ladoDer;
    }
    
    public ArrayList getNoTerminales(){
        noTerminales.add("!");
         for (int i = 0; i < gramatica.size(); i++) {
            izq = gramatica.get(i).split(">");
            var = izq[0];
            var = var.substring(0,var.length()-1);
            if(existeNoTerm(var)==false)
                noTerminales.add(var);                        
        }
         return noTerminales;
    }
    
    private boolean existeNoTerm(String nterm){
        bandera = 0;
        for (int i = 0; i < noTerminales.size(); i++) {
            if(!nterm.equals(noTerminales.get(i))) bandera = 0;
            else bandera = 1;  
        }
        if (bandera != 1) return false;        
        else return  true;
    }
    
    //
    private void buscaTerminales(String texto){
        continuar = true;
        palabra = "";
        tope = texto.length();
        while(continuar){
            if(!texto.isEmpty()){
               if(fin != tope){
                   if(otro(texto.charAt(ini))){ini++; fin++;}
                   else if(Character.isLetter(texto.charAt(fin))){
                       palabra += texto.charAt(fin);
                       fin++;
                   }
                   else if(otro(texto.charAt(fin))){//SI HAY ESPACIO
                       if(!palabra.isEmpty()){
                           if(esTerm(palabra))
                               if(existeTerm(palabra)){terminales.add(palabra);}
                           palabra = "";
                       }
                       ini = fin+1;
                       fin = ini;
                   }
                   else if(!otro(texto.charAt(fin)) && !Character.isLetter(texto.charAt(fin))){
                       if(!palabra.isEmpty()){
                           if(esTerm(palabra))
                               if(existeTerm(palabra)){terminales.add(palabra);}
                           palabra = "";
                       }
                       else{
                            palabra = texto.charAt(fin)+"";
                            if(!palabra.isEmpty()){
                                if(esTerm(palabra))
                                    if(existeTerm(palabra)){terminales.add(palabra);}
                                palabra = "";
                                fin++;
                                ini = fin;
                            }
                       }
                   }
               }else{ //FIN LLEGO AL TOPE DE LA LINEA
                   if(!palabra.isEmpty()){
                        if(esTerm(palabra))
                            if(existeTerm(palabra)){terminales.add(palabra);}
                        palabra = "";
                    }
                   ini = 0;
                   fin = 0;
                   continuar = false;
               }
            }
        }
    }
    
    
    /**
     * Metodo que separa cada produccion del lado derecho de la gramatica
     * @param texto 
     */
    private void producciones(String texto){
        continuar = true;
        palabra = "";
        tope = texto.length();
        palabras = new ArrayList();
        while(continuar){
            if(!texto.isEmpty()){
               if(fin != tope){
                   if(otro(texto.charAt(ini))){ini++; fin++;}
                   else if(Character.isLetter(texto.charAt(fin))){
                       palabra += texto.charAt(fin);
                       fin++;
                   }
                   else if(otro(texto.charAt(fin))){//SI HAY ESPACIO
                       if(!palabra.isEmpty()){
                           palabras.add(palabra);
                           palabra = "";
                       }
                       ini = fin+1;
                       fin = ini;
                   }
                   else if(!otro(texto.charAt(fin)) && !Character.isLetter(texto.charAt(fin))){
                       if(!palabra.isEmpty()){
                           palabras.add(palabra);
                           palabra = "";
                       }
                       else{
                            palabra = texto.charAt(fin)+"";
                            if(!palabra.isEmpty()){
                                palabras.add(palabra);
                                palabra = "";
                                fin++;
                                ini = fin;
                            }
                       }
                   }
               }else{ //FIN LLEGO AL TOPE DE LA LINEA
                   if(!palabra.isEmpty()){
                        palabras.add(palabra);
                        palabra = "";
                   }
                   produc.add(palabras);
                   palabras = new ArrayList();
                   ini = 0;
                   fin = 0;
                   continuar = false;
               }
            }
        }
    }
    
    /**
     * Metodo que nos devuelve un arraylist bidimensional, en donde se almacena 
     * cada produccion del lado derecho
     * @return 
     */
    public ArrayList<ArrayList> getProducciones(){ 
        palabras.add("!");
        produc.add(palabras);
        for (int i = 0; i < ladoDer.size(); i++) { 
            if(!ladoDer.get(i).isEmpty() && !ladoDer.get(i).equals("!")){
                 producciones(ladoDer.get(i));
            }
            else if(ladoDer.get(i).isEmpty()){
                palabras.add("");
                produc.add(palabras);
            }
                
        }
        return produc; 
    }
    
    /**
     * Metodo que nos ayuda a comprobar si el siguiente caracter es espacio
     * o salto de linea
     * @param car
     * @return 
     */
    private boolean otro(char car){
        if(Character.isWhitespace(car)) return true;
        else if(car == '\n') return true;
        else return false;
    }
    
    /**
     * Metodo que nos devuelve los terminales de la gramatica
     * @return 
     */
    public ArrayList getTerminales(){
        terminales.add("!");
        for (int i = 0; i < ladoDer.size(); i++) { 
            if(!ladoDer.get(i).isEmpty() && !ladoDer.get(i).equals("!")){
                buscaTerminales(ladoDer.get(i));
            }
        }
        return terminales;
    }
     
    /**
     * Metodo para comprobar si una palabra es terminal o no
     * @param palabra
     * @return 
     */
    private boolean esTerm(String palabra){
        bandera = 0;
        for (int i = 0; i < noTerminales.size(); i++) {
            if(noTerminales.get(i).equals(palabra)) {
                bandera = 0 ;
                break;
            }
            else bandera = 1;  
        }
        if (bandera == 0) return false;        
        else return  true;
    }
    
    /**
     * Metodo para comprobar si ya existe un terminal en la lista
     * @param term
     * @return 
     */
    private boolean existeTerm(String term){
        bandera = 0;
        for (int i = 0; i < terminales.size(); i++) {
            if(term.equals(terminales.get(i))) { 
                bandera = 0;
                break;
            }
            else bandera = 1;  
        }
        if (bandera != 1) return false;        
        else return  true;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    public boolean esTerminal(String valor){
        boolean bol = false;
        for (int i = 0; i < terminales.size(); i++) {
            if(valor.equals(terminales.get(i))){
                bol = true;
                break;
            }
            else{ 
                bol = false;
           
            }
        }
        return bol;
    }
    
    public int numeroTerm(String token){
        int num = 0;
        for (int i = 0; i < terminales.size(); i++) {
            if(token.equals(terminales.get(i))){
                num = i;
            }
        }
        return num;
    }
    
    public int numeroNTerm(String vPila){
        int num = 0;
        for (int i = 0; i < noTerminales.size(); i++) {
            if(vPila.equals(noTerminales.get(i))){
                num = i;
            }
        }
        return num;
    }
    
    public void generaEstructuras(){
        this.getGramatica();
        this.getLadoDer();
        this.getNoTerminales();
        this.getTerminales();
    }

    public String simboloInicial(){
        String inicial = "!";
        int ban = 0;
        
        for (int i = 1; i < noTerminales.size(); i++) {
            for (int j = 1; j < ladoDer.size(); j++) {
                if (ladoDer.get(j).contains(noTerminales.get(i)))
                {ban = 0;}
                else  {
                   inicial = noTerminales.get(i);
                   ban = 1;
                   break;
                }
            }
            if (ban == 1){
            break;
            }
        }     
        return inicial;
    }

}
