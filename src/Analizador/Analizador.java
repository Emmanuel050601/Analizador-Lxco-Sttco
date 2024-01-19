package Analizador;

import Archivo.Archivo;
import Lista.EscrituraListas;
import java.util.ArrayList;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
*/

public class Analizador{
    private final Archivo archivo;
    private int renglon, ini, fin, tope, estado, atributo;
    private boolean continuar, cont;
    private String palabra, token;
    private ArrayList <String> text;
    private final EscrituraListas lista;
    private final Alfabeto alfabeto;
    
    public Analizador() {
        archivo = new Archivo();
        palabra = "";
        continuar = true;
        alfabeto = new Alfabeto();
        text = new ArrayList();
        lista = new EscrituraListas();
        renglon =  ini =  fin = tope =  estado = 0;
    }
    
    public EscrituraListas getLista(){ return lista; }
    
    public void inicia(){ leerArchivo(); }
    
    private String palabraReservada(int caso){
        token = "";
        switch(caso){
            case 420: token = "Programa"; break;
            case 421: token = "Real"; break;
            case 422: token = "Entero"; break;
            case 423: token = "Leer"; break;
            case 424: token = "Escribir"; break;
            case 425: token = "Inicio"; break;
            case 426: token = "Fin"; break;
        }
        return token;
    }
    
    private String caracter(int caso){
        token = "";
        switch(caso){
            case '(': token = '('+""; break;
            case ')': token = ')'+""; break;
            case '+': token = '+'+""; break;
            case ',': token = ','+""; break;
            case '-': token = '-'+""; break;
            case '/': token = '/'+""; break;
            case ';': token = ';'+""; break;
            case '=': token = '='+""; break;
        }
        return token;
    }
    
    public String getToken(){
        if(renglon != text.size()){
            analiza(text.get(renglon));
            if(!palabra.isEmpty()) { 
                if(atributo == 450) return "id";
                else if(atributo == '(' || atributo == ')' || atributo == '+' || 
                atributo == ',' || atributo == '-' || atributo == '/' || 
                atributo  == ';' || atributo  == '=') return caracter(atributo);
                else if(atributo == 400) return "intliteral";
                else if(atributo == 405) return "realliteral";
                else if(atributo >= 420 && atributo <= 428) 
                     return palabraReservada(atributo);
                else return "Error";
            } //DEVUELVE LA CLASIFICACION
            else { return "1"; } //HAY SALTO DE LINEA
        }
        else{ return "0"; } //ES FIN DE ARCHIVO
    }
    
    private void leerArchivo(){ 
        text = archivo.getTexto();
        for (int i = 0; i < text.size(); i++) 
            //ELIMINAMOS LAS LINEAS VACIAS PARA EVITAR ERRORES
            if(text.get(i).isEmpty()) text.remove(i);
    }
    
    private void analiza(String texto){
        continuar = true;
        palabra = "";
        tope = texto.length();
        if(!texto.isEmpty()){
           while(continuar){
               if (fin != tope) {
                   if(otro(texto.charAt(ini))){ ini = fin+1;  fin=fin+1; }
                   else if(Character.isLetterOrDigit(texto.charAt(fin)) || 
                           texto.charAt(fin) == '_' || texto.charAt(fin) == '.'){
                            palabra += texto.charAt(fin);
                            fin++;
                   } else{
                       if(otro(texto.charAt(fin))){
                           if(!palabra.isEmpty()) {  guarda(palabra);  }
                           continuar = false;
                           ini = fin + 1;
                           fin = ini;
                       } else if(!otro(texto.charAt(fin))){
                           if(!palabra.isEmpty()) {
                               guarda(palabra);
                               continuar = false;
                           }else if(!Character.isLetterOrDigit(texto.charAt(fin))){
                                palabra = texto.charAt(fin)+"";
                                guarda(palabra);
                                continuar = false;
                                fin++;
                                ini = fin;
                            } 
                       }
                   }
               } else {  
                   if(!palabra.isEmpty()) { guarda(palabra); }
                   ini= 0; fin = 0;
                   renglon++;
                   continuar = false;
               }
           }
        }
    }
    
    private void guarda(String palabra){
        estado = 0;
        cont = true;
        while(cont){
            switch(estado){
                case 0: 
                    if(alfabeto.esIdentificador(palabra)){
                        atributo = 450;
                        lista.escribirTokenG(palabra, atributo);
                        cont = false;
                    }
                    else estado = 1;
                    break;
                case 1: 
                    if(alfabeto.esReservada(palabra)){
                        atributo = 420+alfabeto.getAtributo();
                        lista.escribirTokenG(palabra, atributo);
                        cont = false;
                    }
                    else estado = 2;
                    break;
                case 2:
                    if(alfabeto.esEntero(palabra)){
                        atributo = 400;
                        lista.escribirTokenG(palabra, atributo);
                        cont = false;
                    }
                    else estado = 3;
                    break;
                case 3: 
                    if(alfabeto.esDecimal(palabra)){
                        atributo = 405;
                        lista.escribirTokenG(palabra, atributo);
                        cont = false;
                    }
                    else estado = 4;
                    break;
                case 4: 
                    if(alfabeto.esCaracter(palabra)){
                        atributo = (int)palabra.charAt(0);
                        lista.escribirTokenG(palabra, atributo);
                        cont = false;
                    }
                    else estado = 5;
                    break;
                case 5: 
                    atributo = 444;
                    lista.escribirTokenG(palabra + " Linea - " + renglon, atributo);
                    cont = false;
                    break;
            }
        }
    }
        
    private boolean otro(char car){
        if(Character.isWhitespace(car)) return true;
        else if(car == '\n') return true;
        else return false;
    }
    
    public String regresaToken(){
            int otro;
        String st = "";
        do{
            st = this.getToken();
            if(!st.equals("1")){
                System.out.println(st);
                otro = 2;
            }
            else if (st.equals("0")) otro =2;
            else otro = 1;
        }while (otro != 2);
        return st;
    }
}

