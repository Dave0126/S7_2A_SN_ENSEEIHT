open Char

(* Type déclarent les différentes unités lexicales possibles *)

type token =
(* Identificateurs *)
  | IDENT of string
  | TYPEIDENT of string
(* Mots cles : types *)
  | INT
  | FLOAT
  | BOOL
  | CHAR
  | VOID
  | STRING
(* Structure de blocs *)
  | ACCOUV
  | ACCFER
  | PAROUV
  | PARFER
  | CROOUV
  | CROFER
(* Separateurs *)
  | PTVIRG
  | VIRG
(* Mots cles : Instructions *)
  | SI
  | SINON
  | TANTQUE
  | RETOUR
(* Valeurs *)
  | EXPONENT of string
  | ENTIER of int
  | FLOTTANT of float
  | BOOLEEN of bool
  | CARACTERE of char
  | CARACTERESPECIAL of char
  | CHAINE of string
  | OCTAL of int
  | HEXADECIMAL of int
  | BINARY of int
  | VIDE
(* Operateurs *)
  | NOUVEAU
  | ASSIGN
  | OPPT
(* Comparaison *)
  | OPNONEG
  | OPEG
  | OPSUPEG
  | OPINFEG
  | OPSUP
  | OPINF
(* Booleen *)
  | OPOU
  | OPET
  | OPNON
(* Arithmetique *)
  | OPPLUS
  | OPMOINS
  | OPMULT
  | OPDIV
  | OPMOD
(* Fin de texte *)
  | FIN ;;

let printToken t =
  (print_endline
     (match t with
(* Structure de blocs *)
       | PAROUV -> "bloc : ("
       | PARFER -> "bloc : )"
       | CROOUV -> "bloc : ["
       | CROFER -> "bloc : ]"
       | ACCOUV -> "bloc : {"
       | ACCFER -> "bloc : }"
(* Separateurs *)
       | VIRG -> "separateur : ,"
       | PTVIRG -> "separateur : ;"
(* Operateurs *)
       | OPEG -> "operateur : =="
       | OPNONEG -> "operateur : !="
       | OPINFEG -> "operateur : >="
       | OPINF -> "operateur : >"
       | OPSUPEG -> "operateur : <="
       | OPSUP -> "operateur : <"
       | OPET -> "operateur : &&"
       | OPOU -> "operateur : ||"
       | OPNON -> "operateur : !"
       | OPPLUS -> "operateur : +"
       | OPMOINS -> "operateur : -"
       | OPMULT -> "operateur : *"
       | OPDIV -> "operateur : /"
       | OPMOD -> "operateur : %"
       | ASSIGN -> "operateur : ="
       | OPPT -> "operateur : ."
(* Mots cles : Instructions *)
       | TANTQUE -> "mot-cle : while"
       | SI -> "mot-cle : if"
       | SINON -> "mot-cle : else"
       | RETOUR -> "mot-cle : return"
(* Mots cles : Types *)
       | BOOL -> "type : boolean"
       | CHAR -> "type : char"
       | FLOAT -> "type : float"
       | INT -> "type : int"
       | STRING -> "type : String"
       | VOID -> "type : void"
(* Valeurs *)
       | BOOLEEN b -> "booleen : " ^ (if b then "true" else "false")
       | CARACTERE c -> "caractere : '" ^ (escaped c) ^ "'"
       | CARACTERESPECIAL c -> "caractere special : '\\" ^ (escaped c) ^ "'"
       | CHAINE s -> "chaine : " ^ s
       | ENTIER n -> "entier : " ^ string_of_int n
       | FLOTTANT f -> "flottant : " ^ string_of_float f
       | HEXADECIMAL n -> "hexadecimal : " ^ string_of_int n
       | OCTAL n -> "octal : " ^ string_of_int n
       | BINARY n -> "binaire : " ^ string_of_int n
       | EXPONENT f -> "ExponentPart : " ^ f
(* Identificateurs *)
       | IDENT n -> "identificateur : " ^ n
       | TYPEIDENT t -> "identificateur de type : " ^ t
       | _ -> "TBC"));;