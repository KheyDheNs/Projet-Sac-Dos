/*
 * Fichier : Objet.java
 * Auteurs : DE ALMEIDA Jules et KETTANI Inès
 * Date : 19/10/2021
 */

package probleme;

public class Objet {
	
	/* Attributs */

	private String nom;
	private double poids;
	private double valeur;
	
	/* Constructeur */
	
	/* Constructeur Objet()
	 * Constructeur permettant de creer un objet avec un nom, un poids et une valeur
	 * @param String n, le nom de l objet
	 * @param double p, le poids de l objet
	 * @param double val, la valeur de l objet
	 */
	public Objet(String n, double p, double val) {
		this.nom = n;
		this.poids = p;
		this.valeur = val;
	}
	
	/* Methode */ 
	
	/* Methode toString()
	 * Methode permettant de retourner une chaine de caractere represantant un objet
	 * @return String, la chaine de carateres a afficher
	 */
	public  String toString() {
		return this.getNom() + " ; " + this.getPoids() + " ; " + this.getValeur(); // + " ; " + this.getRapport();
	}
	
	
	/* Getters */
	
	/* Methode getNom()
	 * Methode calculant le rapport entre la valeur et le poids de l objet
	 * @return double rapport
	 */
	public  double getRapport() {
		return this.getValeur()/this.getPoids();
	}
	
	/* Methode getNom()
	 * @return String nom
	 */
	private String getNom() {
		return this.nom;
	}
	
	/* Methode getPoids()
	 * @return double poids
	 */
	public double getPoids() {
		return this.poids;
	}
	
	/* Methode getValeur()
	 * @return double valeur
	 */
	public double getValeur() {
		return this.valeur;
	}
	

	

}
