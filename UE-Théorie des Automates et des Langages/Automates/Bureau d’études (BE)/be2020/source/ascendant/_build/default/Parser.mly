%{

(* Partie recopiee dans le fichier CaML genere. *)
(* Ouverture de modules exploites dans les actions *)
(* Declarations de types, de constantes, de fonctions, d'exceptions exploites dans les actions *)

%}

/* Declaration des unites lexicales et de leur type si une valeur particuliere leur est associee */

%token UL_MACHINE
%token UL_ACCOUV UL_ACCFER
%token UL_PT

/* Defini le type des donnees associees a l'unite lexicale */

%token <string> UL_IDENT
%token UL_EVENT
%token UL_REGION
%token UL_STATE
%token UL_FROM
%token UL_TO
%token UL_ON
%token UL_STARTS
%token UL_ENDS

/* Unite lexicale particuliere qui represente la fin du fichier */

%token UL_FIN

/* Type renvoye pour le nom terminal document */
%type <unit> machine

/* Le non terminal document est l'axiome */
%start machine

%% /* Regles de productions */

machine : UL_MACHINE UL_IDENT UL_ACCOUV sub_machine UL_ACCFER /* TODO */ UL_FIN { (print_endline "machine : MACHINE IDENT { ... } FIN ") }

sub_machine : 
  /* Empty */ { (print_endline "sub_machine : Empty") }
  | UL_EVENT UL_IDENT sub_machine { (print_endline "sub_machine : UL_EVENT UL_IDENT sub_machine ") }
  | transition sub_machine { (print_endline "sub_machine : transition sub_machine")}
  | region sub_machine { (print_endline "sub_machine : region sub_machine")}

transition :
  UL_FROM nomQualife UL_TO nomQualife UL_ON UL_IDENT { (print_endline "transition : UL_FROM nomQualife UL_TO nomQualife UL_ON UL_IDENT")}

nomQualife :
  UL_IDENT {(print_endline "nomQualife : UL_IDENT")}
  | UL_IDENT UL_PT nomQualife { (print_endline "nomQualife : UL_IDENT UL_PT nomQualife")}

region :
  UL_REGION UL_IDENT UL_ACCOUV region_etat UL_ACCFER { (print_endline "region : UL_REGION UL_IDENT UL_ACCOUV region_etat UL_ACCFER")}

region_etat :
  /* Empty */ {(print_endline "region_etat : Empty")}
  | etat region_etat {(print_endline "region_etat : etat region_etat")}

etat :
  UL_STATE UL_IDENT etat_starts etat_ends etat_region {(print_endline "etat : UL_STATE UL_IDENT etat_starts etat_ends etat_region")}

etat_starts :
  /* Empty */ {(print_endline "etat_starts : Empty")}
  | UL_STARTS {(print_endline "etat_starts : UL_STARTS")}

etat_ends :
  /* Empty */ {(print_endline "etat_ends : Empty")}
  | UL_ENDS {(print_endline "etat_ends : UL_ENDS")}

etat_region :
  /* Empty */ {(print_endline "etat_region : Empty")}
  | UL_ACCOUV etat_region_sub UL_ACCFER {(print_endline "etat_region : UL_ACCOUV etat_region_sub UL_ACCFER")}

etat_region_sub :
  /* Empty */ {(print_endline "etat_region_sub : Empty")}
  | region etat_region_sub {(print_endline "etat_region_sub : region etat_region_sub")}


%%
