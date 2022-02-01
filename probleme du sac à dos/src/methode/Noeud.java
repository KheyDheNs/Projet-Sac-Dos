/*
 * Fichier : Noeud.java
 * Auteurs : DE ALMEIDA Jules et KETTANI Inès
 * Date : 19/10/2021
 */

package methode;

import java.util.ArrayList;
import probleme.Objet;

public class Noeud {
	
	/* Attributs */
	
	private int profondeur;
	private Noeud filsDroit, filsGauche;
	private ArrayList<Objet> listeActuelle = new ArrayList<Objet>();
	private static double borneInf;
	private double borneSup;
	
	/* Constructeurs */

	/* Constructeur Noeud()
	 * Constructeur vide permettant de creer un noeud vide
	 */
	public Noeud() {
	}

	/* Constructeur Noeud()
	 * Constructeur permettant d initialiser un noeud a l aide de son pere
	 * @param Noeud pere
	 * @param ArrayList<Objet> listeP, la liste des objets possibles
	 */
	public Noeud(Noeud pere, ArrayList<Objet> listeP) {
		if (pere.listeActuelle.size() > 0)
			this.listeActuelle = remplirListe(pere);
		this.borneSup = pere.borneSup;
		this.profondeur = pere.profondeur + 1;
	}
	
	/* Methodes */

	/* Methode remplirListe()
	 * Methode permettant de remplir la liste d objets du noeud 
	 * @param Noeud pere
	 * @return ArrayList<Objet>, la liste d objet du noeud fils
	 */
	private static ArrayList<Objet> remplirListe(Noeud pere) {
		ArrayList<Objet> tmpListe = new ArrayList<Objet>();
		for (int i = 0; i < pere.listeActuelle.size(); i++) {
			tmpListe.add(pere.listeActuelle.get(i));
		}
		return tmpListe;
	}
	
	/* Methode trouverBorneInferieure()
	 * Methode qui remplace la borne inferieure par la valeur actuelle si elle est plus grande
	 */
	public void trouverBorneInferieure() {
		if ( this.valeurActuelle() > Noeud.borneInf )
			Noeud.borneInf = this.valeurActuelle();
	}
	
	/* Methode trouverBorneSuperieure()
	 * Methode qui calcule la borne superieure du noeud
	 * @param ArrayList<Objet> listeP, la liste des objets possibles
	 */
	public void trouverBorneSuperieure(ArrayList<Objet> listeP) {
		double sup = (double) 0;
		sup += this.valeurActuelle();
		for (int i = this.profondeur; i < listeP.size(); ++i) {
			sup += listeP.get(i).getValeur();
		}
		this.borneSup = sup;
	}

	/* Methode poidsActuel()
	 * Methode permettant d obtenir le poids actuel du noeud
	 * @return double poids, le poids actuel du noeud
	 */
	public double poidsActuel() {
		double poids = (double) 0;
		for (int i = 0; i < this.listeActuelle.size(); i++)
			poids += this.listeActuelle.get(i).getPoids();
		return poids;
	}
	
	/* Methode valeurActuelle()
	 * Methode permettant d obtenir la valeur actuelle du noeud
	 * @return double valeur, la valeur actuelle du noeud
	 */
	public double valeurActuelle() {
		double valeur = (double) 0;
		for (int i = 0; i < this.listeActuelle.size(); i++)
			valeur += this.listeActuelle.get(i).getValeur();
		return valeur;
	}

	/*  Getters et Setters */
	
	/* Methode setFilsGauche()
	 * @param Noeud filsG
	 */
	public void setFilsGauche(Noeud filsG) {
		this.filsGauche = filsG;
	}

	/* Methode setFilsDroit()
	 * @param Noeud filsD
	 */
	public void setFilsDroit(Noeud filsD) {
		this.filsDroit = filsD;
	}

	/* Methode getFilsG()
	 * @return Noeud filsGauche
	 */
	public Noeud getFilsG() {
		return this.filsGauche;
	}

	/* Methode getFilsD()
	 * @return Noeud filsDroit
	 */
	public Noeud getFilsD() {
		return this.filsDroit;
	}

	/* Methode getProfondeur()
	 * @return int profondeur
	 */
	public int getProfondeur() {
		return this.profondeur;
	}

	/* Methode getBorneInf()
	 * @return double borneInf
	 */
	public double getBorneInf() {
		return Noeud.borneInf;
	}

	/* Methode getBorneSup()
	 * @return double borneSup
	 */
	public double getBorneSup() {
		return this.borneSup;
	}

	/* Methode getListeActuelle()
	 * @return ArrayList<Objet> listeActuelle
	 */
	public ArrayList<Objet> getListeActuelle() {
		return this.listeActuelle;
	}
}