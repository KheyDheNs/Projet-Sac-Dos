/*
 * Fichier : ResolutionPSE.java
 * Auteurs : DE ALMEIDA Jules et KETTANI Inès
 * Date : 19/10/2021
 */
package methode;

import java.util.ArrayList;
import probleme.*;

public class ResolutionPSE extends Resolution {
	
	/* Attribut */

	static Noeud meilleurResultat = new Noeud();
	
	/* Methodes */

	/* Methode resoudre()
	 * Methode implementant le developpement de la resolution PSE
	 * @param SacADos sac
	 * @return double tmp, le temps d execution de la methode
	 */
	public static double resoudre(SacADos sac) {
		ArrayList<Objet> listePossibles = sac.getObjPossibles();
		double tmpDebut = System.nanoTime();
		Noeud racine = new Noeud();
		
		construire(racine, listePossibles, sac);
		
		for (int i = 0; i < meilleurResultat.getListeActuelle().size(); i++)
			sac.ajouter(meilleurResultat.getListeActuelle().get(i));
		double tmpFin = System.nanoTime();
		return ((tmpFin - tmpDebut) / 1000000);
	}

	/* Methode construire()
	 * Methode permettant de construire de maniere recursive l ensemble des noeuds acceptable de l'arbre
	 * @param Noeud noeud, le noeud dont on va creer les fils
	 * @param ArrayList<Objet> listeP, la liste des objets possibles
	 * @param SacADos sac
	 */
	private static void construire(Noeud noeud, ArrayList<Objet> listeP, SacADos sac) {
		if (noeud.getProfondeur() == listeP.size())
			return;

		Noeud filsG = new Noeud(noeud, listeP);
		if ((noeud.poidsActuel() + listeP.get(filsG.getProfondeur() - 1).getPoids()) <= sac.getPoidsMax()) {
			filsG.getListeActuelle().add(listeP.get(noeud.getProfondeur()));
			filsG.trouverBorneInferieure();
			filsG.trouverBorneSuperieure(listeP);
			if (noeudAcceptable(filsG, listeP)) {
				noeud.setFilsGauche(filsG);
				construire(noeud.getFilsG(), listeP, sac);
				if (noeud.getFilsG().valeurActuelle() > meilleurResultat.valeurActuelle())
					meilleurResultat = noeud.getFilsG();
			}
		}
		
		Noeud filsD = new Noeud(noeud, listeP);
		filsD.trouverBorneInferieure();
		filsD.trouverBorneSuperieure(listeP);
		if (noeudAcceptable(filsG, listeP)) {
			noeud.setFilsDroit(filsD);
			construire(noeud.getFilsD(), listeP, sac);
		}

	}

	/* Methode noeudAcceptable()
	 * Methode permettant de determiner si un noeud est acceptable a l aide de ses bornes
	 * @param Noeud noeud
	 * @param ArrayList<Objet> listeP, la liste des objets possible
	 * @return boolean, si vrai le noeud est acceptable
	 */
	private static boolean noeudAcceptable(Noeud noeud, ArrayList<Objet> listeP) {	
		if (noeud.getBorneInf() > noeud.getBorneSup())
			return false;
		return true;
	}
}
