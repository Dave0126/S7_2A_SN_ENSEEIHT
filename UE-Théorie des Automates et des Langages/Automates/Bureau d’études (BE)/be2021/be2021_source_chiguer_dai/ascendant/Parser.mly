%{

(* Partie recopiee dans le fichier CaML genere. *)
(* Ouverture de modules exploites dans les actions *)
(* Declarations de types, de constantes, de fonctions, d'exceptions exploites dans les actions *)

%}

/* Declaration des unites lexicales et de leur type si une valeur particuliere leur est associee */

%token UL_MODEL
%token UL_BLOCK
%token UL_SYSTEM
%token UL_FLOW
%token UL_ACCOUV UL_ACCFER UL_PAROUV UL_PARFER UL_CROOUV UL_CROFER
%token UL_IN UL_OUT UL_FROM UL_TO
%token UL_INT UL_FLOAT UL_BOOL 

/* Defini le type des donnees associees a l'unite lexicale */

%token <string> UL_IDENT
%token <string> UL_UPIDENT
%token <int> UL_ENTIER
%token <string> UL_IDENT
%token UL_PTVIRG UL_VIRG UL_DPT
%token
%token
%token
%token
%token
%token
%token
%token

/* Unite lexicale particuliere qui represente la fin du fichier */

%token UL_FIN

/* Type renvoye pour le nom terminal document */
%type <unit> modele

/* Le non terminal document est l'axiome */
%start modele

%% /* Regles de productions */

modele : UL_MODEL UL_IDENT UL_ACCOUV UL_ACCFER /* TODO */ UL_FIN { (print_endline "modele : UL_MODEL IDENT { ... } UL_FIN ") }


%%
