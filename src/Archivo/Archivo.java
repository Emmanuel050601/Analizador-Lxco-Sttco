package Archivo;

/**
 * Elaborado por: 
 * Torres Rocha Luis Brandon Emmanuel
 * 
 * Clase encargada del uso del archivo (Busqueda y Lectura).
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Archivo {
    private final JFileChooser fileChooser;
    private final FileNameExtensionFilter filtro;
    private Scanner entrada;
    private String ruta;
    private final ArrayList <String> texto;
    private File f;

    /**
     * Constructor de la clase Archivo
     */
    public Archivo() {
        fileChooser = new JFileChooser();
        texto = new ArrayList();
        filtro = new FileNameExtensionFilter("Archivos de texto (.txt)", "txt");
    }

    /**
     * Metodo que busca el archivo, para esto utiliza filechooser
     * para seleccionar el archivo de cualquier ruta, en este metodo
     * llamamos al metodo encargado de leer el archivo
     */
    private void buscaArchivo() {
        fileChooser.setFileFilter(filtro);
        fileChooser.showOpenDialog(fileChooser);
     
        try {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();
            leer(ruta);
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
    
    /**
     * Metodo encargado de leer el contenido del archivo y escribir 
     * la informacion dentro de arrayList.
     * @param ruta 
     */
    private void leer(String ruta) {
        try {
            f = new File(ruta);
            entrada = new Scanner(f);
            while (entrada.hasNext()) { texto.add(entrada.nextLine()); }
        } 
        catch (FileNotFoundException e) { System.out.println(e.getMessage());}
        catch (NullPointerException e) { 
            System.out.println("No se ha seleccionado ningún fichero");
        } 
        catch (Exception e) { System.out.println(e.getMessage()); } 
        finally { if (entrada != null) {  entrada.close(); } }
    }
    
    /**
     * Metodo que devuelve el arraylist que contiene el contenido del archivo
     * @return 
     */
    public ArrayList getTexto(){
        buscaArchivo();
        return texto;
    }
}
