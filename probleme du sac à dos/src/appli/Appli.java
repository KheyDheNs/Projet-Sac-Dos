/*
 * Fichier : Appli.java
 * Auteurs : DE ALMEIDA Jules et KETTANI Inès
 * Date : 19/10/2021
 */

package appli;

import java.io.FileNotFoundException;
import java.util.Scanner;
import probleme.SacADos;

public class Appli {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		String chemin;
		double poidsMaximal;
		String methode;
		
		System.out.println("Le probleme du sac a dos : ");
		do {
			System.out.println("Veuillez saisir le chemin d'acces de la liste d'objets : ");
			chemin = in.nextLine();
		} while(!chemin.contains(".txt"));
		do {
			System.out.println("\nVeuillez saisir le poids maximal du sac a dos : ");
			poidsMaximal = in.nextDouble();
		} while(poidsMaximal <= 0);
		SacADos sac = new SacADos(chemin, poidsMaximal);
		System.out.println("\nChoissisez votre methode de resolution : Gloutonne, Dynamique ou PSE ");

		do {
			methode = in.nextLine();
			methode = methode.toLowerCase();
		} while(!(methode.equals(("gloutonne")) || methode.equals(("dynamique")) || methode.equals(("pse"))));
			
		double temps = sac.resoudre(methode);
		System.out.println(sac.toString());
		System.out.println("Duree d'execution : " + temps + " ms");

	}

}
