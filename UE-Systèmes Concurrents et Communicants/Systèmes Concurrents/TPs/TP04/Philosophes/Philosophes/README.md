Problème des philosophes
========================

Énoncé
------

N philosophes sont autour d'une table. Il y a une assiette par philosophe,
et *une* fourchette entre chaque assiette. Pour manger, un philosophe
doit utiliser les deux fourchettes adjacentes à son assiette (et celles-là
seulement).

Un philosophe peut être dans l'état :

- penseur : il n'utilise pas de fourchettes ;
- mangeur : il utilise les deux fourchettes adjacentes ; aucun de ses
  voisins ne peut manger ;
- demandeur : il souhaite manger mais ne dispose pas des deux fourchettes.

Visuellement les états mangeur/demandeur/penseur sont représentés par un
rond noir (ou rouge en cas de possible problème)/un rond blanc/rien.

         
Le bouton d'aide de la fenêtre affichée par l'application en présente les fonctionnalités.


Code fourni
-----------
Le code fourni a pour seul but de fournir un exemple de protocole d'usage des moniteurs
 Java. La classe `PhiloMon.java` fournit cette illustration et constitue donc la seule 
 classe à regarder dans ce paquetage.
 
- `StrategiePhilo.java` : interface de la synchronisation entre philosophes.
- `PhiloMon.java` : une implantation de cette interface.
- `ProcessusPhilosophe.java` : code d'un philosophe.
- `Main.java` : programme principal.
  Définit aussi les `PhiloDroite(i)`, `PhiloGauche(i)`, `FourchetteGauche(i)`,
  `FourchetteDroite(i)`.
- `EtatFourchette.java` : définition des constantes pour fourchette placée
  sur la table, l'assiette gauche, l'assiette droite.
- `EtatPhilosophe.java` : définition des constantes pour philosophe penseur,
  demandeur ou mangeur.
- `IHM*.java` : interface utilisateur.
- `Synchro/Simulateur.java` : le simulateur de temps.

- Compilation:  
        `javac *.java`

- Exécution:  
        `java Main`  
        `java Main PhiloSem 10`  
         (classe implantant l'interface StrategiePhilo) (nb de philosophes)
