package Lista;

/**
 * Elaborado por:
 * Torres Rocha Luis Brandon Emmanuel
*/

public class EscrituraListas {
    private final Lista listaG, listaId, listaP,listaCar, listaE, listaEn, listaF;   
    
    public EscrituraListas(){
        listaG = new Lista("GENERAL");
        listaG.inserta("|LEXEMA      |      CLASIFICACION     |     ATRIBUTO");
        listaId = new Lista("IDENTIFICADORES");
        listaId.inserta("|LEXEMA      ||     ATRIBUTO");
        listaP = new Lista("PALABRAS RESERVADAS");
        listaP.inserta("|LEXEMA      ||     ATRIBUTO");
        listaCar = new Lista("CARACTERES");
        listaCar.inserta("|LEXEMA  ||  ATRIBUTO");
        listaE = new Lista("ERRORES");
        listaE.inserta("|\tLEXEMA\t\t||\t\tATRIBUTO\t");
        listaEn = new Lista("ENTEROS");
        listaEn.inserta("|LEXEMA      ||     ATRIBUTO");
        listaF = new Lista("FLOTANTES");
        listaF.inserta("|LEXEMA      ||     ATRIBUTO");
    }
    
    private String clasificacion(int atributo){
        if(atributo == 450) return "Identificadores";
        else if(atributo == '(' || atributo == ')' || atributo == '+' || 
                atributo == ',' || atributo == '-' || atributo == '/' || 
                atributo  == ';' || atributo  == '=') 
                 return "Caracter especial";
        else if(atributo == 400) return "Enteros";
        else if(atributo == 405) return "Flotantes";
        else if(atributo >= 420 && atributo <= 428) return "Palabra Reservada";
        return "";
    }
    
    public void escribirTokenG(String token, int atributo){
        if(atributo != 444){
            listaG.inserta("| " + token+"   |   "+clasificacion(atributo)+"   |   "+atributo); 
            if(atributo == 450) 
                listaId.inserta("| " + token+"\t||\t"+atributo);
            else if(atributo == '(' || atributo == ')' || atributo == '+' || 
                    atributo == ',' || atributo == '-' || atributo == '/' || 
                    atributo  == ';' || atributo  == '=') 
                listaCar.inserta("| " + token+"\t||\t"+atributo);
            else if(atributo == 400) 
                listaEn.inserta("| " + token +"\t||\t"+atributo);
            else if(atributo == 405) 
                listaF.inserta("| " + token+"\t||\t"+atributo);
            else if(atributo >= 420 && atributo <= 428) 
                listaP.inserta("| " + token+"\t||\t"+atributo);
        }
        else
            listaE.inserta("| " + token + "\t||\t" + atributo);
    }    
     
    //Tokens Generales
    public void consultarListaG(){ System.out.println(listaG.toString()); }
    
    //Tokens Identificadores
    public void consultarListaId(){ System.out.println(listaId.toString()); }
    
    //Tokens Palabras Reservadas
    public void consultarListaP(){ System.out.println(listaP.toString()); }
    
    //Tokens Caracteres
    public void consultarListaCar(){ System.out.println(listaCar.toString()); }
    
    //Tokens Enteros
    public void consultarListaEn(){ System.out.println(listaEn.toString()); }
    
    //Tokens Flotantes
    public void consultarListaF(){ System.out.println(listaF.toString()); }
    
    //Tokens Errores
    public void consultarListaE(){ System.out.println(listaE.toString()); }
}
