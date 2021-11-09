{

(* Partie recopiée dans le fichier CaML généré. *)
(* Ouverture de modules exploités dans les actions *)
(* Déclarations de types, de constantes, de fonctions, d'exceptions exploités dans les actions *)

  open Parser 
  exception LexicalError

}

(* Déclaration d'expressions régulières exploitées par la suite *)
let chiffre = ['0' - '9']
let minuscule = ['a' - 'z']
let majuscule = ['A' - 'Z']
let alphabet = minuscule | majuscule
let alphanum = alphabet | chiffre | '_'
let commentaire =
  (* Commentaire fin de ligne *)
  "#" [^'\n']*

rule lexer = parse
  | ['\n' '\t' ' ']+					{ (lexer lexbuf) }
  | commentaire						{ (lexer lexbuf) }
  | "{"							{ UL_ACCOUV }
  | "}"							{ UL_ACCFER }
  | "model"						{ UL_MODEL }
  | "block"						{ UL_BLOCK }
  | "system"						{ UL_SYSTEM }
  | "flow"						{ UL_FLOW }
  | ";"						{ UL_PTVIRG }
  | "("						{ UL_PAROUV }
  | ")"						{ UL_PARFER }
  | ","						{ UL_VIRG }
  | ":"						{ UL_DPT }
  | "in"						{ UL_IN }
  | "out"						{ UL_OUT }
  | "int"						{ UL_INT }
  | "float"						{ UL_FLOAT}
  | "boolean"						{ UL_BOOL }
  | chiffre+ as texte						{ (UL_ENTIER (int_of_string texte)) }
  | "["						{ UL_CROOUV }
  | "]"						{ UL_CROFER }
  | "from"						{ UL_FROM }
  | "to"						{ UL_TO }
  | "."						{ UL_OPPT }
  | minuscule alphabet* as texte { (UL_IDENT texte) }
  | majuscule alphabet* as texte { (UL_UPIDENT texte) }
  | eof							{ UL_FIN }
  | _ as texte				 		{ (print_string "Erreur lexicale : ");(print_char texte);(print_newline ()); raise LexicalError }

{

}
