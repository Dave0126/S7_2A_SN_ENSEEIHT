%{

(* Partie recopiee dans le fichier CaML genere. *)
(* Ouverture de modules exploites dans les actions *)
(* Declarations de types, de constantes, de fonctions, d'exceptions exploites dans les actions *)

%}

/* Declaration des unites lexicales et de leur type si une valeur particuliere leur est associee */

%token UL_PAROUV UL_PARFER
%token UL_PT UL_VIRG
%token UL_FAIL
%token UL_COUP
%token UL_NEG
%token UL_DEDUCTION

/* Defini le type des donnees associees a l'unite lexicale */

%token <string> UL_SYMBOLE
%token <string> UL_VARIABLE

/* Unite lexicale particuliere qui represente la fin du fichier */

%token UL_FIN

/* Type renvoye pour le nom terminal document */
%type <unit> document

/* Le non terminal document est l'axiome */
%start document

%% /* Regles de productions */
document :
  programme UL_FIN { (print_endline "programme : programme UL_FIN ") }

programme : 
  /* Empty */ { (print_endline "programme : Empty ") }
  | sub_programme programme { (print_endline "programme : sub_programme programme ") }

sub_programme :
  axiome { (print_endline "sub_programme : axiome ") }
  | deduction { (print_endline "sub_programme : deduction ") }

axiome :
  predicat UL_PT { (print_endline "aximoe : predicat UL_PT ") }

deduction :
  predicat UL_DEDUCTION sub_deduction UL_PT { (print_endline "deduction : predicat UL_DEDUCTION sub_deduction UL_PT ") }

sub_deduction :
  sub_deduction1 { (print_endline "sub_deduction : sub_deduction1")}
  | sub_deduction1 UL_VIRG sub_deduction { (print_endline "sub_deduction : sub_deduction1 UL_VIRG sub_deduction")}

sub_deduction1 :
  sub_deduction1_predicate { (print_endline "sub_deduction1 : sub_deduction1_predicate")}
  | UL_FAIL { (print_endline "sub_deduction1 : UL_FAIL")}
  | UL_COUP { (print_endline "sub_deduction1 : UL_COUP")}

sub_deduction1_predicate :
  predicat { (print_endline "sub_deduction1_predicat : predicate")}
  | UL_NEG predicat { (print_endline "sub_deduction1_predicat : UL_NEG predicate")}

predicat :
  UL_SYMBOLE UL_PAROUV sub_predicat_virg UL_PARFER { (print_endline "predicat : UL_SYMBOLE UL_PAROUV sub_predicat_virg UL_PARFER")}

sub_predicat_virg :
  sub_predicat { (print_endline "sub_predicat_virg : sub_predicat")}
  | sub_predicat UL_VIRG sub_predicat_virg { (print_endline "sub_predicat_virg : sub_predicat UL_VIRG sub_predicat_virg")}

sub_predicat :
  UL_VARIABLE {(print_endline "sub_predicat : UL_VARIABLE")}
  | terme {(print_endline "sub_predicat : terme")}

terme :
  UL_SYMBOLE sub_terme {(print_endline "terme : sub_terme")}

sub_terme :
  /* Empty */ {(print_endline "sub_terme : Empty")}
  | UL_PAROUV sub_terme_par UL_PARFER {(print_endline "sub_terme : UL_PAROUV sub_terme_par UL_PARFER")}

sub_terme_par :
  sub_terme_par_virg {(print_endline "sub_terme_par : sub_terme_par_virg")}
  | sub_terme_par_virg UL_VIRG sub_terme_par {(print_endline "sub_terme_par : sub_terme_par_virg UL_VIRG sub_terme_par")}

sub_terme_par_virg :
  UL_VARIABLE {(print_endline "sub_terme_par_virg : UL_VARIABLE")}
  | terme {(print_endline "sub_terme_par_virg : terme")}

%%
