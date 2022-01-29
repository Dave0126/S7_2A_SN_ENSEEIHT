open Miniml_types

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
       
(* signature pour définir un environment *)
module type Environment =
  sig

    (* création d'un environment initial  *)
    val gamma : unit -> (ident, 'a typ) Hashtbl.t

    (* fonction d'insertion         *)
    (* permet d'inserer dans l'environment *)
    val insert : ident* 'a typ -> (ident, 'a typ) Hashtbl.t -> (ident, 'a typ) Hashtbl.t

    (* fonction de recherche du type de variable *)
    val find : ident -> (ident, 'a typ) Hashtbl.t -> 'a typ option
  end

  (* implantation de la spécification d'environment *)
module Env : Environment =
  struct

    let gamma () = Hashtbl.create 100

    let insert (var, typ) env = let rst = Hashtbl.copy env in 
      match Hashtbl.add rst var typ with
      | _ -> rst

    let find var env = Hashtbl.find_opt env var

  end

  (* module typer pour l'algorithme de typage *)
module Typer =
  struct

    (* fonction appliquant les regles du typages. *)
    (* expr : expression sur laquelle on applique le typage *)
    (* env : environnemnt ou on fait le typege (il n'est pas necessaire de l'initialiser) *)
    (* car on fait le traitement des relations bin dedans *)
    (* Resultat : le typage de expr et une liste des equations de type ou None au cas d'erreur*)
    let rec typer : expr -> (ident, 'a typ ) Hashtbl.t -> ('a typ * (('a typ * 'a typ) list )) option = fun expr env -> match expr with
      | EConstant(cnst) -> Some((match cnst with
          | CBooleen(_) -> TBool
          
          | CEntier(_) -> TInt
          
          | CNil -> TList TUnit
          
          | CUnit -> TUnit
          ), [])

      | EIdent(id) -> (match (Env.find id env) with
        | Some(ty) -> Some(ty, [])
        | None -> None)

      | EBinop(tok) -> Some((match tok with
          | CONS -> let alpha = TypeVariable.fraiche () in TFun (TVar alpha , TFun (TList (TVar alpha) , TList (TVar alpha)))
          | CONCAT -> let alpha = TypeVariable.fraiche () in TFun (TList (TVar alpha), TFun (TList (TVar alpha) , TList (TVar alpha)))
          | VIRG -> let alpha1 = TypeVariable.fraiche () and alpha2 = TypeVariable.fraiche () in TFun (TVar alpha1, TFun (TVar alpha2, TProd (TVar alpha1,TVar alpha1)))
          | PLUS | MOINS | MULT | DIV -> TFun (TInt ,TFun (TInt, TInt))
          | EQU | INFEQ | SUPEQ | INF | SUP | NOTEQ -> let alpha = TypeVariable.fraiche () in TFun (TVar alpha, TFun (TVar alpha, TBool))
          | AND | OR -> TFun (TBool, TFun (TBool, TBool))
          ),[])

      | EProd(e1,e2) -> (match (typer e1 env, typer e2 env) with
        | (Some(ty1, eq1),Some(ty2,eq2)) -> Some(TProd (ty1,ty2), eq1@eq2)
        | _ -> None)

      | ECons(e1,e2) -> (match (typer e1 env, typer e2 env) with
        | (Some(ty1,eq1),Some(ty2,eq2)) -> Some(ty2, (ty2,TList ty1)::eq1@eq2)
        | _ -> None)

      | EFun(id,e) -> if id="" then (match (typer e env) with
          | Some(ty,eq) -> Some(TFun (TUnit,ty) , eq)
          | _ -> None)
        else ( let alpha = TypeVariable.fraiche () in 
          match (typer e (Env.insert (id,TVar alpha) env)) with
          | Some(ty,eq) -> Some(TFun (TVar alpha,ty), eq)
          | _ -> None)

      | EIf(e1,e2,e3) -> (match (typer e1 env, typer e2 env, typer e3 env) with
        | (Some(ty1,eq1),Some(ty2,eq2),Some(ty3,eq3)) -> Some(ty2, (ty1,TBool)::((ty2,ty3)::eq1@eq2@eq3))
        | _ -> None)

      | EApply(e1,e2) -> (let alpha = TypeVariable.fraiche () in 
        match (typer e1 env, typer e2 env) with
        | (Some(ty1,eq1),Some(ty2,eq2)) -> Some(TVar alpha, (ty1,TFun (ty2 ,TVar alpha))::eq1@eq2)
        | _ -> None)

      | ELet(id,e1,e2) -> 
       ( match (typer e1 env, typer e2 (Env.insert (id,TVar (TypeVariable.fraiche ())) env)) with
        | (Some(ty1,eq1),Some(ty2,eq2)) -> Some(ty2, eq1@eq2)
        | _ -> None)

      | ELetrec(id,e1,e2) -> (let alpha = TypeVariable.fraiche () in
        match (typer e1 (Env.insert (id,TVar alpha) env)
        , typer e2 (Env.insert (id, TVar alpha) env)) with
        | (Some(ty1,eq1),Some(ty2,eq2)) -> Some(ty2, (TVar alpha,ty1)::eq1@eq2)
        | _ -> None)      

  end

  (* module pour le normalisation des types *)
module Normalizer = 
  struct
      (* fonction qui remplace le type variable par sa definition *)
    (* t : terme type *)
    (* a : type variable a remplacer *)
    (* def : definition qui remplace le type variable *)
    (* Resultat : retourne terme maj *)
    let rec helper : 'a typ -> TypeVariable.t -> 'a typ -> 'a typ = fun e a def -> match e with

          | TVar(x) when x=a -> def
          
          | TProd(x,y) -> TProd(helper x a def, helper y a def)
          
          | TFun(x,y) -> TFun(helper x a def, helper y a def)
          
          | TList x -> TList (helper x a def)

          | x -> x

    (* fonction qui remplace les types variables par leurs definitions dans les equations *)
    (* eqs : liste des equations de type *)
    (* a : type variable a remplacer *)
    (* def : definition qui remplace le type variable *)
    (* Resultat : retourne lsite des eqations maj *)
    let rec replacer : ('a typ * 'a typ) list -> TypeVariable.t -> 'a typ -> ('a typ * 'a typ) list = fun eqs a def -> match eqs with
      | [] -> []
      | (e1,e2)::qeqs -> (helper e1 a def, helper e2 a def)::(replacer qeqs a def)

    (* fonction qui normalise les equations *)
    (* eqs : liste des equations de type *)
    (* t : type general *)
    (* Resultat: un type generale ou None au cas d'erreur *)
    let rec normalizer : ('a typ * 'a typ) list -> 'a typ -> ('a typ) option = fun eqs t -> match eqs with
      | [] -> Some(t)
      | (e1,e2)::qeqs -> 
        match (e1,e2) with
          | (TInt,TInt) | (TBool, TBool) | (TUnit, TUnit) -> normalizer qeqs t
          | (TList x, TList y) -> normalizer ((x,y)::qeqs) t
          | (TFun (x, y), TFun (a, b)) | (TProd (x, y), TProd (a, b)) -> normalizer ((x,a)::((y,b)::qeqs)) t
          | (TVar a,TVar b) when a=b -> normalizer qeqs t 
          | (TVar a, x) -> normalizer (replacer qeqs a x) (helper t a x)
          | (x, TVar a) when (match x with | TVar(_) -> false | _ -> true) -> normalizer ((TVar a, x)::qeqs) t
          | _ -> None 
  end

  (* module foncteur pour les tests unitaires de l'environment *)
module TestEnv (Env : Environment) =
  struct

    let test1 var ty = Env.find var (Env.insert (var, ty) (Env.gamma ()))

    let testGen expr = Typer.typer expr (Env.gamma ())

    let expression1 = ELet ("x" ,EConstant (CEntier 1), EApply (EApply (EBinop PLUS, EIdent "x"), EIdent "y")) (*let x=1 in x + y *)
    let expression2 = EFun ( "" ,ELet ("x" ,EConstant (CEntier 1), EApply (EApply (EBinop PLUS, EIdent "x"), EIdent "y"))) (*fun () -> let x=1 in x + y *)
    let expression3 = EFun ( "y" ,ELet ("x" ,EConstant (CEntier 1), EApply (EApply (EBinop PLUS, EIdent "x"), EIdent "y"))) (*fun y -> let x=1 in x + y *)

  end

module TestEnvironment = TestEnv (Env)

(* Les tests *)
let%test _ = (TestEnvironment.test1 "i" TInt = Some(TInt))

(* test d'echec pour un variable introuvable*)
let%test _ = (TestEnvironment.testGen (EIdent "x") = None)
(* test d'echec pour un deuxieme variable introuvable *)
let%test _ = (TestEnvironment.testGen TestEnvironment.expression1 = None)
(* test d'echec pour un deuxieme variable introuvable dedans une fonction unit -> 'a *)
let%test _ = (TestEnvironment.testGen TestEnvironment.expression1 = None)

(* test integral de normalisation et typage *)
let%test _ = (let t,l = match TestEnvironment.testGen TestEnvironment.expression3 with | Some(x,y) -> x,y in Normalizer.normalizer l t = Some(TFun(TInt,TInt)))


