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
  | "("							{ UL_PAROUV }
  | ")"							{ UL_PARFER }
  | "fail"          { UL_FAIL }
  | "!"             { UL_COUP }
  | "-"             { UL_NEG }
  | "."             { UL_PT }
  | ","             { UL_VIRG}
  | ":-"            { UL_DEDUCTION}
  | (minuscule)(majuscule|minuscule)* as texte { (UL_SYMBOLE texte)}
  | (majuscule)(majuscule|minuscule)* as texte { (UL_VARIABLE texte)}
  | eof							{ UL_FIN }
  | _ as texte				 		{ (print_string "Erreur lexicale : ");(print_char texte);(print_newline ()); raise LexicalError }

{

}
