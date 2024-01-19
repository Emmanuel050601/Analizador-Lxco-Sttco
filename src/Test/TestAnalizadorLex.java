package Test;

import Analizador.Analizador;
import Lista.EscrituraListas;
import javax.swing.JOptionPane;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
*/

public class TestAnalizadorLex {
    private static EscrituraListas lista;
    
        static Analizador analizador = new Analizador();
    public static void main(String[] args) {
        lista = analizador.getLista();
        analizador.inicia();
        /**PRUEBA DE QUE AHORA ES MANUAL CON EL METODO getToken();
         * CADA QUE SE LLAME A ESE METODO NOS DEVUELVE UN TOKEN EL CUAL 
         * SERA LA CLASIFICACION DEL TOKE.
         * SI EL TOKEN DEVUELTO ES IGUAL A 1, TIENE QUE LLAMARSE NUEVAMENTE A
         * getToken YA QUE ES LO PREVISTO PARA EL SALTO DE LINEA DESPUES DE
         * UN CARACTER
         */
        
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        darToken();
        mostrarListas();
    }
    
    
    //Este metodo es el encargado de darnos los token
    private static void darToken(){
        int otro;
        String st = "";
        do{
            st = analizador.getToken();
            if(!st.equals("1")){
                System.out.println(st);
                otro = 2;
            }
            else if (st.equals("0")) otro =2;
            else otro = 1;
        }while (otro != 2);
    }
    
    private static void mostrarListas(){
        lista.consultarListaG();
        lista.consultarListaP();
        lista.consultarListaId();
        lista.consultarListaEn();
        lista.consultarListaF();
        lista.consultarListaCar();
        lista.consultarListaE();        
    }
}
