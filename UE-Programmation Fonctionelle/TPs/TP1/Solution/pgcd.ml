(** pgcd : int -> int -> int
fonction qui calcule le pgcd de deux nombres entiers non nuls
Paramètres a et b : les deux entiers dont on calcule le pgcd
Résultat : le pgcd
Erreur : diverge sur un des paramètres est à 0
*)
let pgcd a b = 
  let rec pgcd_positif a b = 
    if (a=b)
    then a
    else if (a > b)
         then pgcd_positif (a-b) (b)
         else pgcd_positif (a) (b-a)
  and valeur_absolue a = if (a >=0) then a else -a
in pgcd_positif (valeur_absolue a) (valeur_absolue b)

let%test _ = pgcd 4 6 = 2 
let%test _ = pgcd (-4) 6 = 2
let%test _ = pgcd (-4) (-6) = 2
let%test _ = pgcd 4 (-6) = 2
let%test _ = pgcd 3 7 = 1
let%test _ = pgcd 10 10 = 10
