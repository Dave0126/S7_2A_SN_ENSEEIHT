Moniteurs en Java (rappel) :
--------------------

 - Créer un objet de type java.util.concurrent.locks.Lock
   (à partir de java.util.concurrent.locks.ReentrantLock) :     
      `    mon_moniteur = new java.util.concurrent.locks.ReentrantLock();`       
   Cet objet réalise le moniteur en fournissant, par son utilisation
   explicite, l'exclusion mutuelle et permet de créer des
   variables-conditions associées au verrou.  
   `    `  
   *Remarque* :  Il est possible de créer un ReentrantLock équitable (mais pas nécessairement FIFO) :  
      `    mon_moniteur = new java.util.concurrent.locks.ReentrantLock(true);`
      

 - Créer une ou des variables-conditions (java.util.concurrent.locks.Condition)
   à partir du "lock" précédemment créé :
   
       `ma_var_condition = mon_moniteur.newCondition();`

 - Les opérations doivent explicitement manipuler le verrou pour obtenir
   l'exclusion mutuelle :
   
    `mon_opération() {`   
    `  mon_moniteur.lock();`     
       
    `     ...           `
    
    `  mon_moniteur.unlock(); `         
    `  }`

 - Les variables-conditions s'utilisent avec
 
       `    ma_var_condition.await();`     
   et  
       `    ma_var_condition.signal();`      
       `    ma_var_condition.signalAll();`
   
 - La sémantique est priorité au signaleur, sans file des signalés : 
	* en outre, l'attente sur les conditions n'est **pas FIFO**, et
	* le signaleur conserve l'accès exclusif
	* le signalé est réveillé, mais doit réacquérir l'accès exclusif, et il n'est pas
 	   prioritaire par rapport aux entrants en attente (que le verrou soit équitable ou non) ;
 	   il doit donc nécessairement retester la condition attendue, car elle a pu être utilisée/
 	   invalidée par un entrant avant qu'il obtienne l'accès exclusif.  
   Il faut donc penser à utiliser des boucles "while" pour déterminer si les conditions 
   attendues sont effectivement vérifiées au moment du réveil.