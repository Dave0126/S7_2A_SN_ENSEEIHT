(*  Module qui permet la décomposition et la recomposition de données **)
(*  Passage du type t1 vers une liste d'éléments de type t2 (décompose) **)
(*  et inversement (recopose).**)
open Be.Chaines
module type DecomposeRecompose =
sig
  (*  Type de la donnée **)
  type mot
  (*  Type des symboles de l'alphabet de t1 **)
  type symbole

  val decompose : mot -> symbole list
  val recompose : symbole list -> mot
end

module DRString:DecomposeRecompose with type mot = string and type symbole = char =
struct
  (*  Type de la donnée **)
  type mot = string
  (*  Type des symboles de l'alphabet de t1 **)
  type symbole = char

  let decompose mot = decompose_chaine mot
  let recompose symboles = recompose_chaine symboles
end

module DRNat:DecomposeRecompose with type mot = int and type symbole = int =
struct
  (*  Type de la donnée **)
  type mot = int
  (*  Type des symboles de l'alphabet de t1 **)
  type symbole = int

  let decompose mot = decompose_int  mot
  let recompose symboles = recompose_int symboles
end


(******************************************************************************)
(*                                                                            *)
(*      fonction de décomposition pour de nombre en chiffre                   *)
(*                                                                            *)
(*   signature : decompose_int : int -> int list = <fun>                      *)
(*                                                                            *)
(*   paramètre(s) : un nombre                                                 *)
(*   résultat     : la liste des chiffres composant le nombre en paramètre    *)
(*                                                                            *)
(******************************************************************************)
let decompose_int number = List.map int_of_char (decompose_chaine (string_of_int number))

let%test _ = decompose_int 0 = [0]
let%test _ = decompose_int 1 = [1]
let%test _ = decompose_int 26 = [2;6] 
let%test _ = decompose_int 546 = [5;4;6] 

(******************************************************************************)
(*                                                                            *)
(*      fonction de recomposition chiffres en nombre                   *)
(*                                                                            *)
(*   signature : decompose_int : int -> int list = <fun>                      *)
(*                                                                            *)
(*   paramètre(s) : un nombre                                                 *)
(*   résultat     : la liste des chiffres composant le nombre en paramètre    *)
(*                                                                            *)
(******************************************************************************)
let recompose_int chiffres = int_of_string (recompose_chaine (List.map char_of_int chiffres))

