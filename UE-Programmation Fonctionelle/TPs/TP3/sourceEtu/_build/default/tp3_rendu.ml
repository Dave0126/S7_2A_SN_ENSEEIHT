(*** Combinaisons d'une liste ***)

(* CONTRAT
Fonction qui peut engendrer toutes les combinaisons de k éléments choisis dans une liste l . Une combinaison
sera représentée par une liste de ses éléments. Chaque combinaison devra respecter l'ordre des éléments
tels qu'ils apparaissent dans l .
Type: 'a list -> int -> 'a list list
Paramètre k : un entier
Paramètre l : une liste
Résultat : la liste des permutatiions de l (toutes différentes si les élements de l sont différents deux à deux 
*)
let rec combinaison l k = 
  match k with
  | 0 -> [[]]
  | _ -> if List.length l = k then [l]
          else match l with
          | [] -> [[]]
          | hd::tl ->(List.map(fun list -> hd::list) (combinaison tl (k-1))) @ (combinaison tl k)

(* TESTS *)
let%test _ = combinaison [1;2;3;4] 3 = [[1;2;3]; [1;2;4]; [1;3;4]; [2;3;4]]
let%test _ = combinaison [1;2;3] 2 = [[1;2]; [1;3]; [2;3]]
let%test _ = combinaison [1;2;3;4] 2 = [[1;2]; [1;3]; [1;4]; [2;3]; [2;4]; [3;4]]
let%test _ = combinaison [1;2;3;4;5] 3 = [[1;2;3]; [1;2;4]; [1;2;5]; [1;3;4]; [1;3;5]; [1;4;5]; [2;3;4];
 [2;3;5]; [2;4;5]; [3;4;5]]