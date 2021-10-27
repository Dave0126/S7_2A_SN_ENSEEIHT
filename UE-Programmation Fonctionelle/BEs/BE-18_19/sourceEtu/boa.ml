
(********************************************************************************************************
* Exersise 1: (les contrats)
* This is an interface module of "les règles du système de réécriture"
* Type :  tid: <int> le type des identifiants de règles;
*          td: <td> le type des termes;
* Fun  :   id: <tid> doit d'etre obtrnu les identifiants de regles; 
*       appliquer: td -> td  list ; doit d’être obtenus avec une unique application de la règle
*********************************************************************************************************)
module type Regle =
sig
  type tid = int
  type td
  val id : tid
  
  val appliquer : td -> td list
end

module Regle1 : Regle =
struct
  type tid = int
  type td = char list
  let id = 1
  let appliquer cl =
    match cl with
  | [] -> [[]]
  | hd :: tl -> let rec loop_find tl = 
                match tl with
                | [] -> if hd = 'O' then [cl @ ['A']]
                        else [cl]
                | hd1 :: tl1 -> loop_find tl1
                in loop_find [cl]
                
end

module type ArbreReecriture =
sig
  
  type tid = int
  type td
  type arbre_reecriture = | Node of td * arbre_reecriture list

  val creer_noeud : td -> arbre_reecriture list -> arbre_reecriture

  val racine : arbre_reecriture -> td

  val fils : arbre_reecriture -> arbre_reecriture list

  val appartient : td -> arbre_reecriture -> bool
  
end


module ArbreReecritureBOA : ArbreReecriture =
struct
  type tid = int
  type td = char list
  type arbre_reecriture = | Node of td * arbre_reecriture list

  let creer_noeud racine fils = Node (racine,fils)

  let racine (Node(racine,_)) = racine

  let fils (Node(_,fils)) = fils

  let appartient lc (Node(racine,fils)) = match lc with
    | [] -> false
    | hd::tl -> true (* A faire*)
end