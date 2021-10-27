(*** Tri plus efficace en rendant fusionne récursif terminal ***)

(* CONTRAT
Fonction qui décompose une liste en deux listes de tailles égales à
plus ou moins un élément
Paramètre : l, la liste à couper en deux
Retour : deux liste *)
let scinde_rec_t l =
  let rec aux_rec_t l n accu =
    if n = 0 then l, accu else
      match l with
      | [] -> [], accu
      | t::q -> aux_rec_t q (n - 1) (t::accu) in
  aux_rec_t l (List.length l / 2) []

(* TESTS *)
let%test _ = scinde_rec_t [1; 2; 3; 4; 5; 6; 7; 8; 9] = ([5; 6; 7; 8; 9],[4; 3; 2; 1])
let%test _ = scinde_rec_t [1; 2; 3; 4] = ([3; 4], [2; 1])
let%test _ = scinde_rec_t [1; 2; 3] = ([2; 3], [1])
let%test _ = scinde_rec_t [1] = ([1], [])
let%test _ = scinde_rec_t [] = ([], [])

(* CONTRAT
Fusionne deux listes triées pour en faire une seule triée dans l'ordre inverse
Paramètre : ordre  ('a -> 'a -> bool), un ordre sur les élements de la liste
Paramètre : l1 et l2, les deux listes triées selon l'ordre
Résultat : une liste triée avec les elements de l1 et l2 *)
let fusionne_rec_t ordre l1 l2 =
  let rec aux_rec_t l1 l2 accu =
    match l1, l2 with
    | [], _ -> List.rev_append l2 accu
    | _, [] -> List.rev_append l1 accu
    | a::q1, b::q2 ->
       if ordre a b then aux_rec_t q1 l2 (a::accu)
       else aux_rec_t l1 q2 (b::accu) in
  aux_rec_t l1 l2 []

(*TESTS*)
let%test _ = fusionne_rec_t (fun x y -> x<y) [1; 2; 4; 5; 6] [3; 4] = (List.rev [1; 2; 3; 4; 4; 5; 6])
let%test _ = fusionne_rec_t (fun x y -> x<y) [1; 2; 4] [3; 4] = (List.rev [1; 2; 3; 4; 4])
let%test _ = fusionne_rec_t (fun x y -> x<y) [1; 2; 4] [3; 4; 8; 9; 10] = (List.rev [1; 2; 3; 4; 4; 8; 9; 10])
let%test _ = fusionne_rec_t (fun x y -> x<y) [] [] = (List.rev [])
let%test _ = fusionne_rec_t (fun x y -> x<y) [1] [] = (List.rev [1])
let%test _ = fusionne_rec_t (fun x y -> x<y) [] [1] = (List.rev [1])
let%test _ = fusionne_rec_t (fun x y -> x<y) [1] [2] = (List.rev [1; 2])
let%test _ = fusionne_rec_t (fun x y -> x>y) [1] [2] = (List.rev [2; 1])

(* CONTRAT
Fonction qui trie une liste, selon un ordre donné
Type : ('a -> 'a -> bool) -> 'a list -> 'a list
Paramètre : ordre  ('a -> 'a -> bool), un ordre sur les élements de la liste
Paramètre : l, la liste á trier
Résultat : une liste triée avec les elements de l *)
let tri_fusion_rec_t ordre l =
  (* tri selon la négation de l'ordre *)
  let rec aux_neg l =
    match l with
    | [] -> []
    | [a]-> [a]
    | _ ->
       let (l1,l2) = scinde_rec_t l in
       (* Les sous-listes sont triée selon l'ordre.
          Pour valider la pré-condition de fusion, il faut bien ordre
          en paramètre de fusionne.
          La post condition de fusion nous donne bien la liste triée
          selon la négation de l'ordre. *)
       fusionne_rec_t ordre (aux_pos l1) (aux_pos l2)
  and aux_pos l =
    match l with
    | [] -> []
    | [a]-> [a]
    | _ ->
       let (l1,l2) = scinde_rec_t l in
       (* Les sous-listes sont triée selon la négation de l'ordre
          Pour valider la pré-condition de fusion, il faut bien la
          négation de l'ordre en paramètre de fusionne
          La post condition de fusion nous donne bien la liste triée
          selon la négation de la négation de l'ordre et donc triée
          selon l'ordre *)
       fusionne_rec_t (fun x y -> not (ordre x y)) (aux_neg l1) (aux_neg l2) in
  aux_pos l

(* TESTS *)
let%test _ = tri_fusion_rec_t (fun x y -> x<y) [] =[]
let%test _ = tri_fusion_rec_t (fun x y -> x<y) [4; 2; 4; 3; 1] =[1; 2; 3; 4; 4]
let%test _ = tri_fusion_rec_t (fun x y -> x > y) [4; 7; 2; 4; 1; 2; 2; 7]=[7; 7; 4; 4; 2; 2; 2; 1]

let%test_unit _ = let _ = tri_fusion_rec_t (fun (s1, p1, a1, nb1) (s2, p2, a2, nb2) -> nb1 > nb2) listStat in ()
(* Le test passe!!! *)

(* On notera que les fonctions auxiliaires dans scinde_rec_t et
   fusionne_rec_t sont récursives terminales mais celles dans
   tri_fusion_rec_t ne le sont pas. tri_fusion_rec_t permet tout de
   même de trier listStat sans débordement de pile car la profondeur
   de pile atteinte dans tri_fusion est logarithmique en la taille de
   la liste triée alors qu'elle était linéaire dans scinde et
   fusonne. *)
