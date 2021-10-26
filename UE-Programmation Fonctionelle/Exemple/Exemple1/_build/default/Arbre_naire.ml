module type Arbre_naire_interface =
sig
   type 'a arbre_naire
   val cons : 'a -> 'a arbre_naire list -> 'a arbre_naire
   val racine : 'a arbre_naire -> 'a
   val fils : 'a arbre_naire -> 'a arbre_naire list
   val fold : ('a -> 'b list -> 'b) -> 'a arbre_naire -> 'b
   val map : ('a -> 'b) -> 'a arbre_naire -> 'b arbre_naire
end

module Arbre_naire : Arbre_naire_interface =
struct

  type 'a arbre_naire = 
    | Node of 'a * 'a arbre_naire list
  
  let cons racine fils = 
    Node(racine,fils)

  let racine (Node(racine,_)) = racine

  let fils (Node(_,fils)) = fils

  let rec fold fNode (Node(racine,fils)) = 
    fNode racine (List.map(fold fNode) fils)

  let rec map f (Node(racine,fils)) = 
    Node (f racine, List.map(map f)fils)
    
end
