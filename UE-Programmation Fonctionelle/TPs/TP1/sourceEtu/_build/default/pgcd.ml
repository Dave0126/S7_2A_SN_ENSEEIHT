(*  Exercice à rendre **)
(*  pgcd : int -> int -> int = <fun>
Fonction qui calcule alcul du Plus Grand Commun Diviseur
Paramètre "a" et "b": deux entiers positives à calculer
Précondition : a >= 0 , b >= 0
Résultat : un entier positive de la résultat de PGCD 
*)
let rec pgcd a b = 
if a = 0 then b else
if b = 0 then a else
if a >= b then pgcd (a mod b) b
else pgcd a (b mod a)

(*  TO DO : tests unitaires *)

let%test _ = pgcd 0 1 = 1
let%test _ = pgcd 1 0 = 1
let%test _ = pgcd 1 1 = 1
let%test _ = pgcd 2 4 = 2
let%test _ = pgcd 45 24 = 3
