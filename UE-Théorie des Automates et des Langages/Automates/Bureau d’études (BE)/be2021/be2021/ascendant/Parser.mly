%{

(* Partie recopiee dans le fichier CaML genere. *)
(* Ouverture de modules exploites dans les actions *)
(* Declarations de types, de constantes, de fonctions, d'exceptions exploites dans les actions *)

%}

/* Declaration des unites lexicales et de leur type si une valeur particuliere leur est associee */

%token UL_MODEL
%token UL_ACCOUV UL_ACCFER

%token UL_SYSTEM
%token UL_BLOCK
%token UL_FLOW
%token UL_FROM
%token UL_TO
%token UL_IN
%token UL_OUT
%token UL_INT
%token UL_FLOAT
%token UL_BOOLEAN
%token UL_PAROUV
%token UL_PARFEM
%token UL_CROOUV
%token UL_CROFEM
%token UL_PTVIRG
%token UL_VIRG
%token UL_TWOPT
%token UL_PT

/* Defini le type des donnees associees a l'unite lexicale */

%token <string> UL_IDENT
%token <string> UL_MINUIDENT
%token <int> ENTIER

/* Unite lexicale particuliere qui represente la fin du fichier */

%token UL_FIN

/* Type renvoye pour le nom terminal document */
%type <unit> modele

/* Le non terminal document est l'axiome */
%start modele

%% /* Regles de productions */

modele : UL_MODEL UL_IDENT UL_ACCOUV sub_modele UL_ACCFER UL_FIN { (print_endline "modele : UL_MODEL IDENT { sub_modele } UL_FIN ") }

sub_modele : 
  /* Empty */ { (print_endline "sub_modele : Empty ") }
  | sub_modele_1 sub_modele { (print_endline "sub_modele : sub_modele_1 sub_modele ") }

sub_modele_1 :
  bloc { (print_endline "sub_modele_1 : Bloc ") }
  | systeme  {(print_endline "sub_modele_1 : Systeme ") }
  | flot  {(print_endline "sub_modele_1 : Flot ") }

bloc :
  UL_BLOCK UL_IDENT parametres UL_PTVIRG { (print_endline "Bloc : UL_BLOCK UL_IDENT parametres UL_PTVIRG ") }

systeme :
  UL_SYSTEM UL_IDENT parametres UL_ACCOUV sub_systeme UL_ACCFER {(print_endline "Systeme : UL_SYSTEM UL_IDENT parametres UL_ACCOUV sub_systeme UL_ACCFER ") }

sub_systeme : 
  /* Empty */ { (print_endline "sub_systeme : Empty "); }
  | sub_systeme_1 sub_systeme { (print_endline "sub_systeme : sub_systeme_1 sub_systeme ");}

sub_systeme_1 :
  bloc { (print_endline "sub_systeme_1 : Bloc ") }
  | systeme  {(print_endline "sub_systeme_1 : Systeme ") }
  | flot  {(print_endline "sub_systeme_1 : Flot ") }

parametres :
  UL_PAROUV sub_parametres UL_PARFEM {( print_endline "Parametres : UL_PAROUV sub_parametres UL_PARFEM")}

sub_parametres :
  port { (print_endline "sub_parametres : Port ") }
  | UL_VIRG sub_parametres { (print_endline "sub_parametres : UL_VIRG sub_parametres ") }

port :
 UL_MINUIDENT UL_TWOPT UL_IN types { (print_endline "Port : UL_MINUIDENT UL_TWOPT UL_IN type ") }
 | UL_MINUIDENT UL_TWOPT UL_OUT types { (print_endline "Port : UL_MINUIDENT UL_TWOPT UL_OUT type ") }

types :
 sub_types sub_types_num { (print_endline "Type : sub_types sub_types_num") }

sub_types :
  UL_INT { (print_endline "sub_types : UL_INT ") }
  | UL_FLOAT { (print_endline "sub_types : UL_FLOAT ") }
  | UL_BOOLEAN { (print_endline "sub_types : UL_BOOLEAN ") }

sub_types_num :
  /* Empty */ { (print_endline "sub_types_num : Empty ") }
  | UL_CROOUV sub_types_num_loop UL_CROFEM { (print_endline "sub_types_num : UL_CROOUV sub_types_num_loop UL_CROFEM ") }

sub_types_num_loop :
  ENTIER {(print_endline "sub_types_num_loop : ENTIER")}
  | UL_VIRG sub_types_num_loop {(print_endline "sub_types_num_loop : UL_VIRG sub_types_num_loop")}

flot :
{(print_endline "Flot : Empty")}




%%
