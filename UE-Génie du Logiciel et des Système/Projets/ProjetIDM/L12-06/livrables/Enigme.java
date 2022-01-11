package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import fr.n7.jV.BOOLEAN;
import fr.n7.jV.Game;
import fr.n7.jV.GameElement;
import fr.n7.jV.Places;
import fr.n7.jV.JVPackage;

/**
 * Prototype en mode texte pour le jeu de l'enigme
 * @author jcolin2
 *
 */
public class Enigme {
	
	/** Connaissances du joueur */
	private static boolean reussite;
	
	/** Objets du joueur */
	private static int tentatives = 3;
	
	public static void main(String... args) {
		
		do {
			// intéraction avec la personne du lieu
			Map<String, String> options = new HashMap<>();
			options.put("réponse 1", "a");
			options.put("réponse 2", "b");
			options.put("réponse 3", "c");
			String reponse = displayMenu("il vous reste " + tentatives 
										 + " tentatives, quelle est la bonne réponse ? :", options);
			switch (reponse) {
				case "a" :
					reussite = true;
					break;
				case "b" :
					tentatives--;
					break;
				case "c" :
					tentatives--;
					break;
				default:
					continue;
			}
			
		} while (!reussite && tentatives > 0); // conditions vers les chemins obligatoires
		
		if (reussite) {
			// déplacement vers le lieu Succes, fin du jeu
			System.out.println("Succes !");
		} else {
			// déplacement vers le lieu Echec, fin du jeu
			System.out.println("Echec !");
		}
	}
		
	public static String displayMenu(String message, Map<String, String> options) {
		Scanner scan = new Scanner(System.in);
		
		// affichage des options
		System.out.println(message);
		for (Entry<String, String> entry : options.entrySet()) {
			System.out.println(entry.getKey() + " --> " + entry.getValue());
		}
		System.out.println();
		
		return scan.next();
	}
}
