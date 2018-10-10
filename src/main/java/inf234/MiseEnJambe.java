package inf234;

import java.io.*;

/**
 * Classe mise en jambe dont le but est d'illustrer la définition de jeux de tests en JUnit.
 */
public class MiseEnJambe {
    public String texte;

    public MiseEnJambe(String texte) {
        this.texte = texte;
    }

    public MiseEnJambe(File f) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        this.texte = fileData.toString();
    }

    /**
     * Vrai ssi texte contient la sous chaîne s.
     * @param s la sous chaîne à rechercher.
     * @return
     */
    private static boolean stringContient(String contenant, String s){
        boolean result = false; // variable resultat qui sera return
        int i = 0; // compteur pour le while
        while ((i<contenant.length()) && (result == false)) {// shema de recherche de texte
            if (s.charAt(0) = contenant.charAt(i)){ // test si un caractère de texte est égal au premier de s
                result = true; // initialisation de la variable resultat
                for(int j=1;j<s.length();j++){ // parcours de s
                    result = result && (s.charAt(j) == contenant.charAt(i+j)); // comparaison des tout les elements de s avec la sequence correspondante de texte
                }
            }
            i++;
        }
        return result;
    }

    /**
     * Vrai ssi texte contient la sous chaîne s.
     * @param s la sous chaîne à rechercher.
     * @return le resultat boolean si la sous chaine s est contenu dans texte
     */
    public boolean contient(String s) {
        return stringContient(this.texte,s); // appel a la methode private static
    }

    /**
     * Vrai ssi texte contient la balise de nom b.
     * Rappel, on devra prendre en compte les cas simples (ex :"<b>") et les cas complexes
     * (ex: "<b attr="...">
     * @param b le nom de la balise à rechercher.
     * @return 
     */
    public boolean contientBalise(String b) {
    int i = 0;                                       //conteur parcourant chaque caractère du texte 
    String s = "";                                   // chaîne de caractère vide que l'on va concaténer avec chaque caractère de la sous 
						     // chaîne de caractère obtenue entre '<' et '>'
    boolean resultat = false;
	
	while( (i < (this.texte).length())&&(!resultat) ){   //shéma de recherche du premier caractère '<'
	     	
		if((this.texte).charAt(i) == '<'){
		i++;                                         //on se place sur le caractère qui suit '<'
		
			while( (this.texte).charAt(i) != '>'){  //shéma de recherche du cractère '>' qui referme '<'
	             
				s = s + charAt(i);
				i++;
		     	    }
				resultat = stringContient(s,b);
	         
		   }else{ i++; 
                       }

             }

	return resultat;
    }

    /**
     * Vrai ssi texte contient le contenu c (en ignorant les éléments contenus à l'intérieur d'une balise).
     * (ex: texte = "<a>b</a>" contient le contenu b, mais pas le contenu a).
     * @param c le contenu à rechercher.
     * @return contenance de c dans le texte
     */
    public boolean contientContenu(String c) {
        String container = ""; //Variable de stockage avec remise a zero a chaque passage sur balise
        boolean isIn = false; //Varbiable de verification d'appartennance
        for(int i=0; i < this.texte.length(); i++){ //Schema de parcours du texte
            if(this.texte.charAt(i) != '<'){  //Supression des balises et reset de la variable container
                while(this.texte.charAt(i) != '>'){
                    container = "";
                    i++;
                }
            }

            container += this.texte.charAt(i);

            if (stringContient(container,c)){ //Verification de l'apartenance du contenu c au container
                isIn = true; 
                i = this.texte.length();
            }

            i++;
        }
        return isIn;
    }
    
    /**
     * Renvoie une version sans commentaire XML de texte.
     * @return le texte sans les commentaires
     */
    public String sansCommentaire() {
        	String text = this.texte; // recuperation du string texte de l'objet pour simplifier le texte
			int tlength = texte.length(); //longueur en nombre de caracteres du  
		  	String textemodif= ""; // String d'enregistrement du texte modifie ( sans les commentaires)
		  
			for( int i=0; i<tlength;i++){ // boucle de Parcour pour l'enregistrement du texte modife
				if((text.charAt(i) == '<')
				&& (text.charAt(i+1) == '!')  
				&& (text.charAt(i+2) == '-') 
				&& (text.charAt(i+3) == '-')){
					i+=4;

					//Boucle de parcour secondaire pour ne pas enregister les commentaires
					while ((!(text.charAt(i) == '-')) && (!(text.charAt(i+1) == '-')) && (!(text.charAt(i+2) == '>'))) { 
						i++;
					}
					i+=3;
				} else {
					textemodif = textemodif + texte.charAt(i);
				}		
			}
			return textemodif;
	}

    /**
     * Renvoie une version de texte où toutes les données contenues à l'intérieur d'une balise b on été supprimées.
     * ex: texte = "<a>x<b></b>z</a>" : filtere("b") renvoie "<a>xz</a>"
     * @return
     */
    public String filtrer(String b) {
        return null;
    }

}
