/*
 * Fichier : ResolutionGloutonne.java
 * Auteurs : DE ALMEIDA Jules et KETTANI Inès
 * Date : 19/10/2021
 */

package methode;

import java.util.ArrayList;
import probleme.*;

public class ResolutionGloutonne extends Resolution {
	
	/* Methodes */
	
	/*	Methode resoudre()
	 *  Methode implementant le developpement de la resolution gloutonne
	 *  @param SacADos sac
	 *  @return double tmp, le temps d execution de la methode
	 */
	public static double resoudre(SacADos sac) {
		ArrayList<Objet> listePossibles = sac.getObjPossibles();
		double tmpDebut = System.nanoTime();
		
		triRapide(listePossibles, 0, listePossibles.size()-1);
		
		for (Objet obj : listePossibles) {
			if(sac.verifPoids(obj.getPoids())){
				sac.ajouter(obj);
			}
		}
		
		double tmpFin = System.nanoTime();
		return ((tmpFin - tmpDebut)/1000000);
	}
	
	/* Methode triRapide()
	 * Methode implementant l algorithme de tri rapide
	 * @param ArrayList<Objet> listeP, la liste des objets possibles
	 * @param int premier
	 * @param int dernier
	 */
	private static void triRapide(ArrayList<Objet> listeP, int premier, int dernier) {
		int pivot = (premier + dernier) / 2;
		int j = premier;
		
		if(premier < dernier) {
			echanger(listeP, pivot, dernier);			
			for(int i = premier; i <= dernier - 1; i++) {
				if(listeP.get(i).getRapport() > listeP.get(dernier).getRapport()){
					echanger(listeP, i, j);
					j += 1;
				}
			}
			
			echanger(listeP, dernier, j);
			pivot = j;
			triRapide(listeP, premier, pivot - 1);
			triRapide(listeP, pivot + 1, dernier);
		}
	}
	
	/* Methode echanger()
	 * Methode pour echanger deux objets dans une liste
	 * @param ArrayList<Objet> listeObj
	 * @param int obj1, l'index de l'objet 1
	 * @param int obj2, l'index de l'objet 2
	 */
	private static void echanger(ArrayList<Objet> listeObj, int obj1, int obj2) {
		Objet objTmp = listeObj.get(obj1);
		listeObj.set(obj1, listeObj.get(obj2));
		listeObj.set(obj2, objTmp);
	}
}
