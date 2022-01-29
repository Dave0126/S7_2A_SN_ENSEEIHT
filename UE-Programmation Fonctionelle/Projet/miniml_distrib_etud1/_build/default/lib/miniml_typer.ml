open Miniml_types
open Miniml_printer

(* signature minimale pour définir des variables *)
module type VariableSpec =
  sig
    (* type abstrait des variables      *)
    type t

    (* création d'une variable fraîche  *)
    val fraiche : unit -> t

    (* fonctions de comparaison         *)
    (* permet de définir des conteneurs *)
    (* (hash-table, etc) de variables   *)
    val compare : t -> t -> int
    val equal : t -> t -> bool
    val hash : t -> int

    (* fonction d'affichage             *)
    (* on utilise Format.std_formatter  *)
    (* comme premier paramètre          *)
    (* pour la sortie standard          *) 
    val fprintf : Format.formatter -> t -> unit
  end

(* implantation de la spécification     *)
module TypeVariable : VariableSpec =
  struct
    type t = int

    let fraiche =
      let cpt = ref 0 in
      (fun () -> incr cpt; !cpt)

    let compare a b = a - b
    let equal a b = a = b
    let hash a = Hashtbl.hash a

    let fprintf fmt a = Format.fprintf fmt "t{%d}" a
  end


(* ******** à compléter ********* *)
(*    
let rec string_of_expr tree =
  match tree with
  | (EConstant constant) -> ("(" ^ 
                                   (match constant with
                                   | CBooleen bool -> string_of_bool bool
                                   | CEntier int -> string_of_int int
                                   | CNil -> "[]"
                                   | CUnit -> "()")
                                    ^ ")")
  | (EIdent ident) -> ident
  | (EProd (expr1,expr2)) -> ("(" ^ (string_of_expr expr1) ^ "," ^ (string_of_expr expr2) ^ ")")
  | (ECons (expr1,expr2)) -> ("(" ^ (string_of_expr expr1) ^ "::" ^ (string_of_expr expr2) ^ ")")
  | (EFun (ident, expr)) -> ("(fun " ^ ident ^ " -> " ^ (string_of_expr expr) ^ ")")
  | (EIf (econd,ethen,eelse)) -> ("(if " ^ (string_of_expr econd)
                                  ^ " then " ^ (string_of_expr ethen)
                                  ^ " else " ^ (string_of_expr eelse) ^ ")")
  | (EApply (expr1,expr2)) -> ("(" ^ (string_of_expr expr1) ^ " " ^ (string_of_expr expr2) ^ ")")
  | (EBinop token) -> (match token with
                        | MOINS -> "<"
                        | UMOINS -> ">="
                        | )
*)                        

(* ===============================================*)
(* Définition du type des erreurs *)
type errorType =
  | UnknownIdentError of string
  | UnknownReferenceError of string
  | TypeMismatchError
  | RuntimeError
  | UndefinedExpressionError
;;

(* ===============================================*)
(* Définition du type des valeurs renvoyées par l'interprète *)
type valueType =
  | FrozenValue of (expr * environment)
  | IntegerValue of int
  | FloatValue of float
  | BooleanValue of bool
  | ReferenceValue of string
  | NilValue
  | NullValue
  | ErrorValue of errorType
and 
  environment = (string * valueType) list
and 
  memory = (string * valueType) list
;;

let string_of_names names =
	List.fold_right (fun t tq -> t ^ " " ^ tq ) names "";;

  (* print_value : environment -> string *)
  (* Convertit un environnement en une chaine de caractères en vue de son affichage *)
  let rec print_env fmt env =
    match env with
    | [] -> Format.fprintf fmt ""
    | (key,value)::q -> Format.fprintf fmt "%s,%a;%a" key print_value value print_env q
    (* (key ^ "," ^ (print_value fmt value)) ^ ";" ^ (print_env fmt q) *)
  (* string_of_mem : memory -> string *)
  (* Convertit une mémoire en une chaine de caractères en vue de son affichage *)
  and print_memory fmt mem =
    match mem with
    | [] -> Format.fprintf fmt ""
    | (key,value)::q -> Format.fprintf fmt "%s,%a;%a" key print_value value print_memory q
    (* (key ^ "," ^ (print_value value)) ^ ";" ^ (string_of_mem q) *)
  (* print_value : valueType -> string *)
  (* Convertit une valueType en une chaine de caractères en vue de son affichage *)
  and print_value fmt value =
    match value with
      | (FrozenValue (expr,env)) -> Format.fprintf fmt "%a%a" print_expr expr print_env env
      (* ((print_expr fmt expr) ^ (string_of_env env)) *)
      | (IntegerValue value) -> Format.fprintf fmt "%d" value
      (*(string_of_int value)*)
      | (FloatValue value) -> Format.fprintf fmt "%f" value
      | (BooleanValue value) -> if (value) then Format.fprintf fmt "true"
                                else Format.fprintf fmt "false"
      (* (if (value) then "true" else "false") *)
      | (ReferenceValue value) -> Format.fprintf fmt "ref %s" value
      (* ("ref " ^ value) *)
      | NilValue -> Format.fprintf fmt "[]"
      | NullValue -> Format.fprintf fmt "()"
      | (ErrorValue error) -> (print_error fmt error)
  (* string_of_value_and_mem : (valueType * memory) -> string *)
  (* Convertit une valueType en une chaine de caractères en vue de son affichage *)
  and print_value_and_memory fmt vm = Format.fprintf fmt " Value = %a \n Memory = %a" print_value (fst vm) print_memory (snd vm)
  (* string_of_error : errorType -> string *)
  (* Convertit une erreur en une chaine de caractères en vue de son affichage *)
  and print_error fmt error =
    match error with
      | (UnknownIdentError name) -> Format.fprintf fmt "Unknown ident : %s" name
      | (UnknownReferenceError name) -> Format.fprintf fmt "Unknown ident : %s" name
      | RuntimeError -> Format.fprintf fmt "Runtime error"
      | TypeMismatchError -> Format.fprintf fmt "Type mismatch"
      | UndefinedExpressionError -> Format.fprintf fmt "Undefined Expression"
  
  ;;

  (* ===============================================*)
type 'a searchResult = 
  | NotFound 
  | Found of 'a;;

(* lookfor : string -> environment -> valueType searchResult *)
(* Cherche un identifiant dans un environnement et renvoie la valeur associée à cet identifiant ou une erreur le cas échéant *)
let rec lookforEnv name env =
  match env with
    | [] -> NotFound
    | (key,value) :: others ->
      (if (key = name) then (Found value) else (lookforEnv name others));;  

(* lookfor : string -> memory -> valueType searchResult *)
(* Cherche une adresse dans une mémoire et renvoie la valeur associée à cette adresse ou une erreur le cas échéant *)
let rec lookforMem name env =
  match env with
    | [] -> NotFound
    | (key,value) :: others ->
      (if (key = name) then (Found value) else (lookforMem name others));;  

(* ........................................................................*)
(*   newReference : string                                                 *)
(*     alloue une adresse dans la memoire de la forme "ref@i"              *)
(* ........................................................................*)
let referenceCounter = ref 0;;

let newReference () =
  (referenceCounter := (! referenceCounter) + 1);
  ("ref@" ^ (string_of_int (! referenceCounter)));;

  let rec value_of_expr (expr,mem) env =
    match expr with
      | (EConstant constant) -> ruleConstant constant mem
      | (EIdent ident) -> ruleIdent ident mem env
      | (EProd (expr1,expr2)) -> ruleProd expr1 expr2 mem env
      | (EFun (_,_)) -> ruleFunction expr mem env
      | (EApply (fexpr,pexpr)) -> ruleApply fexpr pexpr mem env    
      | (EIf (i_cond,i_then,i_else)) -> ruleIf i_cond i_then i_else mem env 
      | (ELet (ident,l_value,l_in)) -> ruleLet ident l_value l_in mem env
      | (ELetrec (ident,lc_value,lc_in)) -> ruleLetrec ident lc_value lc_in  mem env
      | (EBinop (e1,op,e2)) -> ruleBinary e1 op e2 mem env
  
  and 
  (* .............................................................................*)
  (*   ruleConstant : constant -> memory -> environment                           *)
  (*      -> (ValueType * memory)                                                 *)
  (* .............................................................................*)
  
  ruleConstant constant mem = 
    match constant with
      | (CBooleen boolean) -> if boolean then ((BooleanValue true), mem)
                                else ((BooleanValue false), mem)
      | (CEntier integer) -> ((IntegerValue integer), mem)
      | CNil -> (NilValue, mem)
      | CUnit -> (NullValue, mem)


  and 
  (* .............................................................................*)
  (*   ruleIdent : String -> memory -> environment                               *)
  (*      -> (ValueType * memory)                                                 *)
  (* .............................................................................*)
  
  ruleIdent i_ident mem env = 
    (match (lookforEnv i_ident env) with
    | NotFound                            -> ((ErrorValue (UnknownIdentError i_ident)),mem)
    | (Found (FrozenValue (fexpr,fenv)))  -> (value_of_expr (fexpr,mem) fenv)
    | (Found value)                       -> (value,mem))
  
  and
    (* .............................................................................*)
    (*   ruleProd : Miniml_types.expr -> Miniml_types.expr -> memory -> environment *)
    (*      -> (ValueType * memory)                                                 *)
    (* .............................................................................*)

  ruleProd expr1 expr2 mem env =
    let(left, pl_mem) = value_of_expr (expr1, mem) env in
    match left with
    | (ErrorValue _) as result -> (result, mem)
    | NullValue -> (let(right,pr_mem) = value_of_expr (expr2, pl_mem) env in
                    match right with
                    | (ErrorValue _) as result -> (result, mem)
                    | _ -> (right, pr_mem))
    | _ -> ((ErrorValue TypeMismatchError), mem)
    
  
  and 
    (* .............................................................................*)
    (*   ruleFunction : Miniml_types.expr -> memory -> environment                  *)
    (*      -> (ValueType * memory)                                                 *)
    (* .............................................................................*)
    
  ruleFunction f_expr mem env = 
      ( (FrozenValue (f_expr,env)), mem)

  and
  (* .....................................................................................................*)
  (*   ruleIf : Miniml_types.expr -> Miniml_types.expr -> Miniml_types.expr -> memory -> environment      *)
  (*      -> (ValueType * memory)                                                                         *)
  (* .....................................................................................................*)
  
  ruleIf i_cond i_then i_else mem env = 
    (let (cval,cmem) = 
       (value_of_expr (i_cond,mem) env) 
     in
       (match cval with
       | (BooleanValue rcond) ->
         (if (rcond) then 
            (value_of_expr (i_then,cmem) env)
         else 
            (value_of_expr (i_else,cmem) env))
       | (ErrorValue _) as result -> (result,cmem)
       | _ -> ((ErrorValue TypeMismatchError),cmem)))
  
       
  and 
  (* .............................................................................................*)
  (*  ruleLet : String -> Miniml_types.expr -> Miniml_types.expr -> memory -> environment         *)
  (*      -> (ValueType * memory)                                                                 *)
  (* .............................................................................................*)
  
  ruleLet ident l_value l_in mem env = 
    (let (vval,vmem) = 
       (value_of_expr (l_value,mem) env) 
     in
       (match vval with
       | (ErrorValue _) as result -> (result,vmem)
       | _ -> (value_of_expr (l_in,vmem) ((ident,vval)::env))))
  and
  (* .............................................................................*)
  (*  ruleLetrec : String -> Ast.ast -> Ast.ast -> memory -> environment          *)
  (*      -> (ValueType * memory)                                                 *)
  (* .............................................................................*)
  
  ruleLetrec ident lc_value lc_in mem env = 
    (value_of_expr (lc_in,mem) 
        ((ident,(FrozenValue ((ELetrec (ident,lc_value,lc_value)),env)))::env))

  and
  (* .............................................................................*)
  (*  ruleApply : Ast.ast -> Ast.ast -> memory -> environment               *)
  (*      -> (ValueType * memory)                                                 *)
  (* .............................................................................*)
  
  (* Appel par valeur *)
  ruleApply fexpr pexpr mem env = 
    let (pval,pmem) = 
       value_of_expr (pexpr,mem) env
     in
       match pval with
       | (ErrorValue _) as result -> (result,pmem)
       | _ -> 
    let (fval,fmem) = 
            value_of_expr (fexpr,pmem) env
          in 
           (match fval with
           | (FrozenValue ((EFun (fpar,fbody)),fenv)) ->
               (value_of_expr (fbody,fmem) ((fpar,pval)::fenv))
           | (ErrorValue _) as result -> (result,fmem)
           | _ -> ((ErrorValue TypeMismatchError),fmem))
  
  and 
  (* .............................................................................*)
  (*   ruleBinary : binary -> Ast.ast -> Ast.Ast -> memory -> environment         *)
  (*      -> (ValueType * memory)                                                 *)
  (* .............................................................................*)
  
  ruleBinary e1 op e2 mem env = 
    let (r_expr, rightmem) = 
      (value_of_expr (e2,mem) env) 
    in
      (match r_expr with
      | (ErrorValue _) as result -> (result, rightmem)
      | _ ->
        (let (l_expr, leftmem) = (value_of_expr (e1,rightmem) env) in
           (match l_expr with
           | (ErrorValue _) as result -> (result, leftmem)
           | _ ->
             (match (l_expr,r_expr) with 
              | ((IntegerValue l_expr), (IntegerValue r_expr)) -> (match op with
                | PLUS -> ((IntegerValue (l_expr + r_expr)), leftmem)
                | MOINS -> ((IntegerValue (l_expr - r_expr)), leftmem)
                | MULT -> ((IntegerValue (l_expr * r_expr)), leftmem)
                | DIV -> (if (r_expr = 0) then ((ErrorValue RuntimeError),leftmem)
                           else ((IntegerValue (l_expr / r_expr)), leftmem))
                | EQU -> ((BooleanValue (l_expr = r_expr)), leftmem)
                | NOTEQ -> ((BooleanValue (l_expr <> r_expr)), leftmem)
                | INF -> ((BooleanValue (l_expr < r_expr)), leftmem)
                | INFEQ -> ((BooleanValue (l_expr <= r_expr)), leftmem)
                | SUP -> ((BooleanValue (l_expr > r_expr)), leftmem)
                | SUPEQ -> ((BooleanValue (l_expr >= r_expr)), leftmem)
                | _ -> ((ErrorValue TypeMismatchError), leftmem))
              | ((FloatValue l_expr), (FloatValue r_expr)) -> (match op with
                | FPLUS -> ((FloatValue (l_expr +. r_expr)), leftmem)
                | FMOINS -> ((FloatValue (l_expr -. r_expr)), leftmem)
                | FMULT -> ((FloatValue (l_expr *. r_expr)), leftmem)
                | FDIV -> (if (r_expr = 0.0) then ((ErrorValue RuntimeError),leftmem)
                           else ((FloatValue (l_expr /. r_expr)), leftmem)))
              | ((BooleanValue l_expr), (BooleanValue r_expr)) -> (match op with
                | OR -> ((BooleanValue (l_expr || r_expr)),leftmem)
                | AND -> ((BooleanValue (l_expr && r_expr)) ,leftmem)
                | _ -> ((ErrorValue TypeMismatchError), leftmem))
            | _ -> ((ErrorValue TypeMismatchError), leftmem)))))

  ;