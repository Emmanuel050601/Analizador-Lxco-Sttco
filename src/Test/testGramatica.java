package Test;

import Gramatica.Gramatica;
import java.util.ArrayList;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
*/

public class testGramatica {
    public static void main(String[] args) {
        Gramatica g = new Gramatica();
        ArrayList<String> ar = new ArrayList();
        ArrayList<String> ar2 = new ArrayList();
        ArrayList<String> ar3 = new ArrayList();
        g.leerGramatica();
        //MUESTRA LA GRAMATICA
        for (int i = 0; i < g.getGramatica().size(); i++) {
            System.out.println(g.getGramatica().get(i));
        }
        
        System.out.println("--------------DER-------------");
        ar = g.getLadoDer();//Arreglo que almacena el lado derecho
        for (int i = 0; i < ar.size(); i++) {
            System.out.println(ar.get(i));
        }
        System.out.println("---------------NO TERMINALES-----------------");
        ar2 = g.getNoTerminales();//Arreglo que almacena los no terminales sin repetir
        for (int i = 0; i < ar2.size(); i++) {
            System.out.println(ar2.get(i));
        }
        System.out.println("----------------TERMINALES---------------------");
        ar3 = g.getTerminales();//Arreglo que almacena los no terminales sin repetir
        for (int i = 0; i < ar3.size(); i++) {
            System.out.println(ar3.get(i));
        }  
        System.out.println("------------------MATRIZ------------------------");
        int [][] matriz = g.getMatriz();
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print("[ " + matriz[x][y] + " ], ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------------------------");
        ArrayList <ArrayList> ar4 = g.getProducciones();
        for (int i = 0; i < ar4.size(); i++) {
            System.out.print(i + "-");
            for (int j = ar4.get(i).size()-1; j >= 0; j--) {
                System.out.print(ar4.get(i).get(j) + " ");
            }
            System.out.println("");
        }
        System.out.println("El simbolo inicial es: ");
        System.out.println(g.simboloInicial());
    }
}
