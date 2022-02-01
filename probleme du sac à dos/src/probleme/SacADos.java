/*
 * Fichier : SacADos.java
 * Auteurs : DE ALMEIDA Jules et KETTANI Inès
 * Date : 19/10/2021
 */

package probleme;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import methode.ResolutionDynamique;
import methode.ResolutionGloutonne;
import methode.ResolutionPSE;

public class SacADos {
	
	/* Attributs */
	
	private ArrayList<Objet> objPossibles;
	private ArrayList<Objet> objDansSac;	
	private double poidsMax;
	
	/* Constructeurs */
	
	/* Constructeur SacADos()
	 * Contructeur du sac a dos vide
	 */
	public SacADos() {
		this.objPossibles = new ArrayList<Objet>();
		this.objDansSac = new ArrayList<Objet>();
		this.poidsMax = (double) 0;
	}
	
	/* Constructeur SacADos()
	 * Contructeur d'un sac a dos avec sa liste d objet et son poids max
	 * @param String chemin, le chemin de la liste d objet
	 * @param double pMax, le poids max du sac
	 */
	public SacADos(String chemin, double pMax) throws FileNotFoundException {
		this.objPossibles = new ArrayList<Objet>();
		this.objDansSac = new ArrayList<Objet>();
		this.poidsMax = pMax;
		
		//lecture du ficher texte
		Scanner input = new Scanner(new File(chemin));
	    input.useDelimiter(" ;|\n");
	    while(input.hasNext()) {
	    	String tmpName = input.next();
	    	double tmpPoids = Double.valueOf(input.next());
	    	double tmpValeur = Double.valueOf(input.next());
	    	Objet Obj = new Objet(tmpName, tmpPoids, tmpValeur);
	    	this.objPossibles.add(Obj);
	    }
	}
	
	/* Methodes */
	
	/* Methode resoudre()
	 * Methode permettant de choisir la methode gloutonne, dynamique ou PSE selon l entree
	 * @param String methode, la methode a utiliser
	 * @return double tmp, le temps d execution
	 */
    public double resoudre(String methode) {
        switch (methode) {
            case "gloutonne":
                return ResolutionGloutonne.resoudre(this);
            case "dynamique":
            	return ResolutionDynamique.resoudre(this);
            case "pse":
            	return ResolutionPSE.resoudre(this);
        }
        return (double) 0;
    }
	
    /* Methode verifPoids()
	 * Methode verifiant que si l on ajoute l objet dans le sac, le poids maximum n est pas depasse
	 * @param double pObjet, le poids de l objet
	 * @return boolean, si vrai le poids total est inferieur au poids max du sac
	 */
	public boolean verifPoids(double pObjet) {
		if (this.getPoids() + pObjet > poidsMax) {
			return false;
		}
		else
			return true;
	}
	
	/* Methode ajouter()
	 * Methode qui permet d'ajouter un objet dans le sac, en verifiant si l'objet n est pas trop lourd
	 * @param Objet obj, l objet a ajouter
	 */
	public void ajouter(Objet obj) {
		if(verifPoids(obj.getPoids())) {
			objDansSac.add(obj);
		}
	}
	
	/* Methode toString()
	 * Methode permettant d avoir une chaine de caractere affichant le contenu et les valeurs d un sac
	 * @return String sb, la chaine de caractere a afficher
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sac à dos :\n");
		sb.append("Valeur totale : " + this.getValeur()+ "\n");
		sb.append("Poids total : " + this.getPoids()+ "\n");
		sb.append("Poids maximal : " + this.getPoidsMax()+ "\n");
		sb.append("Objets : \n");

		for(int i = 0; i< objDansSac.size() ;i++) {
			sb.append(" - " + this.objDansSac.get(i).toString() + "\n");
		}
		return sb.toString();
	}
	
	/* Getters */
	
    /* Methode getPoids()
	 * Methode qui calcule le poids de tous les objets dans le sac
	 * @return double pActuel, le poids actuel dans le sac
	 */
	private double getPoids() {
		double pActuel = (double) 0;
		for (int i = 0; i < this.objDansSac.size(); i++)
			pActuel += this.objDansSac.get(i).getPoids();
		return pActuel;
	}
	
	/* Methode getValeur()
	 * Methode qui calcule la valeur de tous les objets dans le sac
	 * @return double vActuelle, la valeur actuelle dans le sac
	 */
	private double getValeur() {
		double vActuelle = (double) 0;
		for (int i = 0; i < this.objDansSac.size(); i++)
			vActuelle += this.objDansSac.get(i).getValeur();
		return vActuelle;
	}
	
    /* Methode getPoidsMax()
	 * Methode qui retourne le poids maximum du sac
	 * @return double poidsMax, le poids maximum du le sac
	 */	
	public double getPoidsMax() {
		return this.poidsMax;
	}
	

	/* Methode getObjPossibles()
	 * @return ArrayList<Objet> objPossibles, la liste des objets possibles
	 */
	public ArrayList<Objet> getObjPossibles(){
		return this.objPossibles;
	}
}
