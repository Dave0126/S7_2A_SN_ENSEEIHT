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
  {(print_endline "sub_package : sub_package1") }

%%

