%{

(* Partie recopiee dans le fichier CaML genere. *)
(* Ouverture de modules exploites dans les actions *)
(* Declarations de types, de constantes, de fonctions, d'exceptions exploites dans les actions *)

%}

/* Declaration des unites lexicales et de leur type si une valeur particuliere leur est associee */

%token UL_PACKAGE
%token UL_LEFT_BRACE UL_RIGHT_BRACE

%token UL_INTERFACE
%token UL_EXTENDS
%token UL_INT
%token UL_BOOLEAN
%token UL_VOID
%token UL_LEFT_PAREN
%token UL_RIGHT_PAREN
%token UL_COMMA
%token UL_SEMICOLON 
%token UL_DOT

/* Defini le type des donnees associees a l'unite lexicale */

%token <string> UL_IDENT_PACKAGE
%token <string> UL_IDENT_INTERFACE

/* Unite lexicale particuliere qui represente la fin du fichier */

%token UL_FIN

/* Type renvoye pour le nom terminal document */
%type <unit> package

/* Le non terminal document est l'axiome */
%start package

%% /* Regles de productions */

package : internal_package UL_FIN { (print_endline "package : internal_package FIN") }

internal_package : UL_PACKAGE UL_IDENT_PACKAGE UL_LEFT_BRACE sub_package UL_RIGHT_BRACE { (print_endline "package : package IDENT_PACKAGE { }") }

sub_package :
  internal_package  {(print_endline "sub_package : internal_package ") }
  | interface { (print_endline "sub_package : interface ") }
  | internal_package sub_package {(print_endline "sub_package : internal_package sub_package") }
  | interface sub_package { (print_endline "sub_package : interface sub_package ") }

interface:
  UL_INTERFACE UL_IDENT_INTERFACE sub_interface UL_LEFT_BRACE pre_methode UL_RIGHT_BRACE {(print_endline "interface : UL_INTERFACE UL_IDENT_INTERFACE sub_interface UL_LEFT_BRACE methode UL_RIGHT_BRACE") }

sub_interface:
  /* empty */ {(print_endline "sub_interface : Empty ") }
  | UL_EXTENDS pre_nomQualifie {(print_endline "sub_interface : UL_EXTENDS pre_nomQualifie ") }

pre_nomQualifie :
  nomQualifie {(print_endline "pre_nomQualifie : nomQualifie ") }
  | nomQualifie UL_COMMA pre_nomQualifie {(print_endline "pre_nomQualifie : nomQualifie UL_COMMA pre_nomQualifie ") }

pre_methode:
  /* empty */ {(print_endline "pre_methode : empty ") }
  | methode pre_methode {(print_endline "pre_methode : methode pre_methode ") }

nomQualifie :
  sub_nomQualifie UL_IDENT_INTERFACE {(print_endline "nomQualifie : sub_nomQualifie UL_IDENT_INTERFACE ") }

sub_nomQualifie :
  /* empty */ {(print_endline "sub_nomQualifie : empty ") }
  | UL_IDENT_PACKAGE UL_DOT sub_nomQualifie  {(print_endline "sub_nomQualifie : UL_IDENT_PACKAGE UL_DOT sub_nomQualifie ") }

 methode :
  types UL_IDENT_PACKAGE UL_LEFT_PAREN sub_mothode UL_RIGHT_PAREN UL_SEMICOLON {(print_endline "methode : types UL_IDENT_PACKAGE UL_LEFT_PAREN sub_mothode UL_RIGHT_PAREN UL_SEMICOLON ") }

sub_mothode :
  /* empty */ {(print_endline "sub_mothode : empty ") }
  | pre_types {(print_endline "sub_mothode : pre_types ") }

pre_types :
  types {(print_endline "pre_types : types ") }
  | types UL_COMMA pre_types {(print_endline "pre_types : types UL_COMMA pre_types ") }

types :
  UL_BOOLEAN {(print_endline "types : BOOLEAN ") }
  | UL_INT {(print_endline "types : INT ") }
  | UL_VOID {(print_endline "types : VOID ") }
  | nomQualifie {(print_endline "types : nomQualifie ") }

%%

