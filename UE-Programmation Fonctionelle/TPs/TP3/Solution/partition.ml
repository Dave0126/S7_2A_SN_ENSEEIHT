
(*** Partition d'un entier ***)

(* partition int -> int list
Fonction qui calcule toutes les partitions possibles d'un entier n
Paramètre n : un entier dont on veut calculer les partitions
Préconditions : n >0
Retour : les partitions de n
*)

let partition n =
  let rec partition_aux n t =
    if      t > n then []
    else if t = n then [[t]]
    else (* t < n *)   (List.map (fun l -> t::l) (partition_aux (n-t) t)) @ (partition_aux n (t+1))
  in partition_aux n 1


(* TEST *)
let%test _ = partition 1 = [[1]]
let%test _ = partition 2 = [[1;1];[2]]
let%test _ = partition 3 = [[1; 1; 1]; [1; 2]; [3]]
let%test _ = partition 4 = [[1; 1; 1; 1]; [1; 1; 2]; [1; 3]; [2; 2]; [4]]