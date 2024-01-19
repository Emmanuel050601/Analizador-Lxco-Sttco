package Analizador;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
 * 
 *  Valor ASCII del alfabeto (Rangos)
 *  Letras mayusculas 65 - 90
 *  Letas minusculas 97 - 122
 *  Digitos 48 - 57 
*/

public class Alfabeto {
    private int puntos, atributo;
    private boolean punto;
    private char id, carac;
    private final String [] palaReservadas = {"Programa","Real","Entero", "Leer",
                                              "Escribir","Inicio", "Fin"};

    public boolean esReservada(String palabra){
        for (int i = 0; i < palaReservadas.length; i++) {
            //Si el token es igual, devuelve verdadero
            if(palaReservadas[i].equals(palabra)) {
                atributo = i;
                return true;
            } 
        }return false; //Si no, entonces devuelve falso
    }
    
    /**
     * Nos devuelve el atributo de la palabra reservada
     * @return 
     */
    public int getAtributo(){ return atributo; }
    
    public boolean esCaracter(String car){
        carac = car.charAt(0);
        //Si el caracter es diferente de uno, nos devuelve falso
        if(car.length() != 1) return false;
        //si no, entonces revisa si el caracter forma parte de los caracteres
        //permitidos y nos devuelve verdadero
        else
            return carac == '(' || carac == ')' || carac == '+' || carac == ',' ||
                   carac == '-' || carac == '/' || carac == ';' || carac == '=' ;
    }   
       
    public boolean esEntero(String num){
        //Devuelve verdadero si es solo un numero y es cero
        if(num.length() == 1 && num.equals("0")) return true;
        // Devuelve falso si empieza con 0
        if(num.charAt(0) == '0') return false;
        for (int i = 0; i < num.length(); i++) {
            if(Character.isDigit(num.charAt(i))) return true; 
        }
        return false;
    }
    
    public boolean esDecimal(String dec){
        punto = false;
        puntos = 0;
        for (int i = 0; i < dec.length(); i++) {
            if((dec.charAt(i) >= 58 || dec.charAt(i)<=47) && dec.charAt(i) != 46)
                //Devuelve falso si dec no contiene digitos o punto
                return false;
            if(dec.charAt(i) == '.'){
                //Devuelve true si encuentra el punto en intermedio
                punto = (i != 0 && i != dec.length());
                puntos++;
            }
            //Devuelve falso si dec contiene mas de un punto o punto esta al final
            else if(punto && puntos != 1 || dec.charAt(dec.length()-1) == '.') 
                return false;
        }if(punto) return true; //Devuelve true si contiene un punto
        else return false;
    } 
    
    public boolean esIdentificador(String ident){
        id = 0;
        for (int i = 0; i < ident.length(); i++) {
            id = ident.charAt(i);
            //Si incluye mayusculas, devuelve falso
            if(id >= 'A' && id <= 'Z') { return false; }
            //Si numero esta al inicio, nos devuelve false 
            else if((id >= '0' && id <= '9') && (i == 0)){ return false; }
            //Si '_' al inicio o al final, nos devuelve false 
            else if((id == '_' ) && (i == (ident.length()-1) || i == 0)){
                return false;
            }
            //Si no es letra, digito o caracter, devueve false
            else if(( id >= 58 || id <= 47) && ((id <= 96 || id >= 123) && id != 95)){
                return false;
            }
        }
        return true; //Si no entonces devuelve verdadero
    }
}
