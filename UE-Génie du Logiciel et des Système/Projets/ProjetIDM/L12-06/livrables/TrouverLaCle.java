package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Prototype en mode texte pour un jeu consistant à ce déplacer
 * de lieu en lieu, jusqu'a trouver une connaissance signifiant la fin de
 * la partie.
 * Cet exemple permet de mettre en avant : 
 * - l'utilisation des chemins non obligatoires,
 * - la présence d'objets et de connaissances sur un lieu
 * - l'utilisation d'une condition sur une connaissance pour acceder à la fin du jeu.
 * 
 * @author jcolin2
 */
public class TrouverLaCle {
	
	/** Connaissances du joueur. */
	private static boolean fin; 
	
	/** 
	 * Lieux indentifiés par un nom, associés à d'autres lieux où l'on peut acceder
	 * depuis ce lieu.
	 */
	private static Map<String, List<String>> lieux = new HashMap<>();
	
	public static void main(String... args) {
		// initialisation des lieux
		lieux.put("Départ", Arrays.asList("lieu A", "lieu B"));
		lieux.put("lieu A", Arrays.asList("lieu B", "lieu C"));
		lieux.put("lieu B", Arrays.asList("lieu D"));
		lieux.put("lieu C", Arrays.asList("lieu F", "lieu B"));
		lieux.put("lieu D", Arrays.asList("lieu C", "lieu F"));
		
		String lieuCourant = "Départ";
		do {
			// Choix du prochain lieu. Tous les chemins sont visibles et non obligatoires
			Map<String, String> options = new HashMap<>();
			int i = 0;
			for (String destination : lieux.get(lieuCourant)) {
				options.put(destination, Integer.toString(i));
				i++;
			}
			String reponse = displayMenu("Vous vous trouvez dans le lieu " + lieuCourant 
										 + ". Dans quel lieu souhaitez vous vous dirigez ?", options);
			// déplacement vers le prochain lieu
			lieuCourant = lieux.get(lieuCourant).get(Integer.parseInt(reponse));
			
			if (lieuCourant.equals("lieu F")) {
				// on donne la connaissance fin
				fin = true;
			}
			
		} while (!fin);
		
		// la connaissance "fin" rend visible et obligatoire un chemin qui va du lieu F vers un lieu "Sortie".
		System.out.println("Vous avez trouver la sortie");
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
