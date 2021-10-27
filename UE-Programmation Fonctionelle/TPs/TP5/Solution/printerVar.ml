(* Module abstrayant les expressions *)
module type ExprSimple =
sig
  type t
  val const : int -> t
  val plus : t -> t -> t
  val mult : t-> t -> t
end

(* Module réalisant l'évaluation d'une expression *)
module EvalSimple : ExprSimple with type t = int =
struct
  type t = int
  let const c = c
  let plus e1 e2 = e1 + e2
  let mult e1 e2 = e1 * e2
end


(* Solution 1 pour tester *)
(* A l'aide de foncteur *)

(* Définition des expressions *)
module ExemplesSimples (E:ExprSimple) =
struct
  let exemple1  = E.(plus (const 1) (mult (const 2) (const 3)) )
  let exemple2 =  E.(mult (plus (const 5) (const 2)) (mult (const 2) (const 3)) )
end

(* Module d'évaluation des exemples *)
module EvalExemples =  ExemplesSimples (EvalSimple)

let%test _ = (EvalExemples.exemple1 = 7)
let%test _ = (EvalExemples.exemple2 = 42)



(* Module réalisant la conversion en chaîne de caractère d'une expression *)
module PrintSimple : ExprSimple with type t = string =
struct
  type t = string
  let const c = string_of_int c
  let plus e1 e2 = "("^e1^"+"^e2^")"
  let mult e1 e2 = "("^e1^"*"^e2^")"
end

(* Solution 1 pour tester *)
(* A l'aide de foncteur *)

(* Module d'affichage des exemples *)
module PrintExemples = ExemplesSimples (PrintSimple)

let%test _ = (PrintExemples.exemple1 = "(1+(2*3))")
let%test _ = (PrintExemples.exemple2 = "((5+2)*(2*3))")


(* Ajout des variables aux expressions *)

module type ExprVar =
sig
  type t
  type tname = string
  val var : tname -> t
  val def : (tname * t) -> t -> t
end

module type Expr =
sig
  include ExprSimple
  (* on définit le type t de ExprVar comme étant celui de Exprsimple *)
  include (ExprVar with type t := t)
end

(* Extension du module d'affichage *)

(* Module réalisant la conversion en chaîne de caractère d'une expression *)
module PrintVar : ExprVar with type t = string =
struct
  type t = string
  type tname = string
  let var n = n
  let def (n,i) e = "let "^n^" = "^i^" in "^e
end

module Print : Expr with type t = string =
struct
  include PrintSimple
  include (PrintVar : ExprVar with type t := t)
end


(* Solution 1 pour tester *)
(* A l'aide de foncteur *)

(* Définition des expressions *)
module ExemplesVars (E : Expr) =
struct
  include ExemplesSimples(E)
  let exemple3 = E.def ("x", E.plus (E.const 1) (E.const 2)) (E.mult (E.var "x") (E.const 3))
end

(* Module d'affichage des exemples *)
module PrintExemples2 = ExemplesVars (Print)
let%test _ = (PrintExemples2.exemple3 = "let x = (1+2) in (x*3)")
let%test _ = (PrintExemples2.exemple1 = "(1+(2*3))")
let%test _ = (PrintExemples2.exemple2 = "((5+2)*(2*3))")

