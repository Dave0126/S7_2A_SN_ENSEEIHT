(* Lexèmes de Ocaml.             *)
(* Seule une partie est utilisée *)
(* pour MiniML.                  *)
(* consulter miniml_lexer.mll    *)
(* pour savoir à quoi correspond *)
(* chacun des lexèmes.           *)
type token =
  | EOF
  | LET
  | IN
  | REC
  | MATCH
  | WITH
  | BAR
  | FUN
  | TO
  | IF
  | THEN
  | ELSE
  | DOUBLEPTVIRG
  | SEP
  | CONS
  | CONCAT
  | PARO
  | PARF
  | CROO
  | CROF
  | PTVIRG
  | VIRG
  | PLUS
  | MOINS
  | UMOINS
  | MULT
  | DIV
  | MOD
  | FPLUS
  | FMOINS
  | FUMOINS
  | FMULT
  | FDIV
  | EQU
  | INFEQ
  | SUPEQ
  | INF
  | SUP
  | NOTEQ
  | AND
  | OR
  | BOOL of bool
  | INT of int
  | FLOAT of float
  | IDENT of string
  | CONSTRUCT of string
  | TYPE
  | QUOTE

(* Type des noms de variables    *)
type ident = string

(* Type des constantes de MiniML *)
type constant =
  (* constante booléenne         *)
  | CBooleen  of bool
  (* constante entière           *)
  | CEntier   of int
  (* constante []                *)
  | CNil
  (* constante ()                *)
  | CUnit

(* Type des expressions MiniML   *)
(* en cas de doute, consulter    *)
(* miniml_printer.ml pour savoir *)
(* comment les différents        *)
(* constructeurs sont affichés   *)
(* et donc à quels éléments de   *)
(* la syntaxe de MiniML ils      *)
(* correspondent.                *)
type expr =
  (* constante                   *)
  | EConstant of constant
  (* variable                    *)
  | EIdent of ident
  (* paire (e1, e2)              *)
  | EProd of expr * expr
  (* liste (e1::e2)              *)
  | ECons of expr * expr
  (* fonction anonyme            *)
  | EFun of ident * expr
  (* conditionnelle              *)
  | EIf of expr * expr * expr
  (* application (e1 e2)         *)
  | EApply of expr * expr
  (* opérateur binaire prédéfini *)
  | EBinop of token
  (* définition locale           *)
  | ELet of ident * expr * expr
  (* définition récursive locale *)
  | ELetrec of ident * expr * expr

(* exception en cas d'erreur     *)
(* de lecture des lexèmes        *)
exception ErreurLecture of string

(* type des types MiniML         *)
(* paramétré par le type des     *)
(* variables (de type)           *)
type 'a typ =
  (* type unit                   *)
  | TUnit
  (* type bool                   *)
  | TBool
  (* type int                    *)
  | TInt
  (* variable de type            *)
  | TVar of 'a
  (* type produit: (t1 * t2)     *)
  | TProd of 'a typ * 'a typ
  (* type fonction: (t1 -> t2)   *)
  | TFun  of 'a typ * 'a typ
  (* type liste: t1 list         *)
  | TList of 'a typ

