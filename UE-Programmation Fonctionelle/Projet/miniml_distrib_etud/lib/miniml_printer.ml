open Miniml_types

(* affichage d'une constante        *)
(* on utilise Format.std_formatter  *)
(* comme premier paramètre          *)
(* pour la sortie standard          *) 
let print_constant fmt c =
  match c with
  | CBooleen b   -> Format.fprintf fmt "%b" b
  | CEntier   i  -> Format.fprintf fmt "%d" i
  | CNil         -> Format.fprintf fmt "[]"
  | CUnit        -> Format.fprintf fmt "()"

(* affichage d'un opérateur binaire *)
(* on utilise Format.std_formatter  *)
(* comme premier paramètre          *)
(* pour la sortie standard          *) 
let print_binop fmt token =
  match token with
  | VIRG   -> Format.fprintf fmt ","
  | OR     -> Format.fprintf fmt "||"
  | AND    -> Format.fprintf fmt "&&"
  | EQU    -> Format.fprintf fmt "="
  | INFEQ  -> Format.fprintf fmt "<="
  | SUPEQ  -> Format.fprintf fmt ">="
  | INF    -> Format.fprintf fmt "<"
  | SUP    -> Format.fprintf fmt ">"
  | NOTEQ  -> Format.fprintf fmt "<>"
  | PLUS   -> Format.fprintf fmt "+"
  | MOINS  -> Format.fprintf fmt "-"
  | DIV    -> Format.fprintf fmt "/"
  | MULT   -> Format.fprintf fmt "*"
  | FPLUS  -> Format.fprintf fmt "+."
  | FMOINS -> Format.fprintf fmt "-."
  | FDIV   -> Format.fprintf fmt "/."
  | FMULT  -> Format.fprintf fmt "*."
  | _      -> assert false

(* affichage d'une expression       *)
(* on utilise Format.std_formatter  *)
(* comme premier paramètre          *)
(* pour la sortie standard          *) 
let rec print_expr fmt expr =
  match expr with
  | EConstant c           -> Format.fprintf fmt "%a" print_constant c
  (* les opérateurs ',' et '::'     *)
  (* sont des constructeurs ocaml   *)
  (* et ne peuvent être banalisés   *)
  (* en fonctions                   *)
  | EBinop VIRG           -> Format.fprintf fmt "(fun x -> (fun y -> (x,y)))"
  | EBinop CONS           -> Format.fprintf fmt "(fun x -> (fun y -> (x::y)))"
  (* les autres opérateurs binaires *)
  (* sont banalisés en fonctions    *)
  | EBinop b              -> Format.fprintf fmt "( %a )" print_binop b
  | EIdent id             -> Format.fprintf fmt "%s" id
  | EProd (e1, e2)        -> Format.fprintf fmt "(%a, %a)" print_expr e1 print_expr e2
  | ECons (e1, e2)        -> Format.fprintf fmt "(%a::%a)" print_expr e1 print_expr e2                    
  | EFun (id, e)          -> Format.fprintf fmt "(fun %s -> %a)" id print_expr e
  | EIf (i, t, e)         -> Format.fprintf fmt "if %a then %a else %a" print_expr i print_expr t print_expr e
  | EApply (f, a)         -> Format.fprintf fmt "(%a %a)" print_expr f print_expr a
  | ELet (id, e1, e2)     -> Format.fprintf fmt "let %s = %a in %a" id print_expr e1 print_expr e2
  | ELetrec (id, e1, e2)  -> Format.fprintf fmt "let rec %s = %a in %a" id print_expr e1 print_expr e2

(* affichage d'un type              *)
(* paramétré par l'affichage des    *)
(* variables de type 'print_var'    *)
(* on utilise Format.std_formatter  *)
(* comme premier paramètre          *)
(* pour la sortie standard          *) 
let rec print_typ print_var fmt typ =
  match typ with
  | TUnit          -> Format.fprintf fmt "unit"
  | TBool          -> Format.fprintf fmt "bool"
  | TInt           -> Format.fprintf fmt "int"
  | TVar  v        -> Format.fprintf fmt "%a" print_var v
  | TProd (t1, t2) -> Format.fprintf fmt "(%a * %a)" (print_typ print_var) t1 (print_typ print_var) t2          
  | TFun (t1, t2)  -> Format.fprintf fmt "(%a -> %a)" (print_typ print_var) t1 (print_typ print_var) t2
  | TList t1       -> Format.fprintf fmt "%a list" (print_typ print_var) t1

