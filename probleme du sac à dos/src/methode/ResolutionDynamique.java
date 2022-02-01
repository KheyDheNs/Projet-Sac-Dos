/*
 * Fichier : ResolutionDynamique.java
 * Auteurs : DE ALMEIDA Jules et KETTANI Inès
 * Date : 19/10/2021
 */
package methode;

import java.util.ArrayList;
import probleme.*;

public class ResolutionDynamique extends Resolution{
	
	/* Attribut */
	
	private static final int Multiplicateur = 10;
	
	/* Methodes */
	
	/* Methode resoudre()
	 * Methode implementant le developpement de la resolution dynamique
	 * @param SacADos sac
	 * @return double tmp, le temps d execution de la methode
	 */
	public static double resoudre(SacADos sac) {	
		ArrayList<Objet> listePossibles = sac.getObjPossibles();
		double tmpDebut = System.nanoTime();
		int pMaxInt = (int)(sac.getPoidsMax() * Multiplicateur);
		double[][] matPossibles = new double[listePossibles.size()][pMaxInt+1];
		int i, j;
		
		//Remplir la matrice
		for(j = 0; j < pMaxInt ; j++) {
			if (listePossibles.get(0).getPoids() * Multiplicateur > j)
				matPossibles[0][j] = 0;
			else
				matPossibles[0][j] = listePossibles.get(0).getValeur();
		}
		
		for(i = 1 ; i < listePossibles.size(); i++) {
			for(j = 1; j<= pMaxInt; j++)
			if(listePossibles.get(i).getPoids() * Multiplicateur > j)
				matPossibles[i][j] = matPossibles[i-1][j];
			else
				matPossibles[i][j] = Math.max(matPossibles[i-1][j], matPossibles[i-1][(int)(j - listePossibles.get(i).getPoids() * Multiplicateur)] + listePossibles.get(i).getValeur());
		}
		
		//Recuperer les objets de la matrice
		 i = listePossibles.size()-1;
		 j = pMaxInt;
		 
		while(j >= 0 && matPossibles[i][j] == matPossibles[i][j-1])
			j--;
		
		while(j > 0) {
			while(i > 0 && matPossibles[i][j] == matPossibles[i-1][j]){
				i--;
				}
			j =  (int)(j -  listePossibles.get(i).getPoids() * Multiplicateur);
			if (j >= 0)
				sac.ajouter(listePossibles.get(i));
			i--;
		}
		
		double tmpFin =  System.nanoTime();
		return ((tmpFin - tmpDebut)/1000000);
	}

}
