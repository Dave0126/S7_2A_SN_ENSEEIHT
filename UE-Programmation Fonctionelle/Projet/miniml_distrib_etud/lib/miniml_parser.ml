open Miniml_types
open Miniml_lexer
open Lazyflux

(* Fonction de lecture d'un fichier.    *)
(* Produit le flux des lexèmes reconnus *)
let read_miniml_tokens_from_file filename : token Flux.t =
  try
    let chan = open_in filename in
    let buf = Lexing.from_channel chan in
    line_g := 1;
    let next_token () =
      try
        let next = token buf in
        if next = EOF
        then
          begin
            close_in chan;
            None
          end
        else
          Some (next, ())
   with
   | ErreurLex msg ->
      begin
        close_in chan;
        raise (ErreurLecture (Format.sprintf "ERREUR : ligne %d, lexème '%s' : %s" !line_g (Lexing.lexeme buf) msg))
      end in
    Flux.unfold next_token ()
 with
    | Sys_error _ -> raise (ErreurLecture (Format.sprintf "ERREUR : Impossible d'ouvrir le fichier '%s' !" filename))
;;

(* Fonction de lecture d'un buffer.   *)
(* Similaire à la fonction précédente *)
let read_miniml_tokens_from_lexbuf buf : token Flux.t =
  line_g := 1;
  let next_token () =
    try
      let next = token buf in
      if next = EOF
      then
        begin
          None
        end
      else
        Some (next, ())
    with
    | ErreurLex msg ->
       begin
         raise (ErreurLecture (Format.sprintf "ERREUR : ligne %d, lexème '%s' : %s" !line_g (Lexing.lexeme buf) msg))
       end in
  Flux.unfold next_token ()
;;

(* Fonction de lecture d'une chaîne.  *)
(* Similaire à la fonction précédente *)
let read_miniml_tokens_from_string chaine : token Flux.t =
  read_miniml_tokens_from_lexbuf (Lexing.from_string chaine)
;;

(* Fonctions auxiliaires de traitement des lexèmes *)
(* contenant une information: IDENT, BOOL et INT   *)
let isident =
  function IDENT _     -> true
         | _           -> false
let isbool =
  function BOOL _      -> true
         | _           -> false
let isint =
  function INT _       -> true
         | _           -> false

let unident =
  function
  | IDENT id    -> id
  | _           -> assert false
let unbool =
  function
  | BOOL b      -> b
  | _           -> assert false   
let unint =
  function
  | INT i       -> i
  | _           -> assert false


(* Fonctions de parsing de MiniML *)
(* ******** à compléter ********* *)

(* les deux types de flux utilisés: le flux à parser et le flux des solutions *)
module Flux = Lazyflux.Flux ;;
module Solution = Lazyflux.Flux ;;

(* types des parsers généraux *)
type ('a, 'b) result = ('b * 'a Flux.t) Solution.t;;
type ('a, 'b) parser = 'a Flux.t -> ('a, 'b) result;;

module type Parsing =
  sig
    val map : ('b -> 'c) -> ('a, 'b) parser -> ('a, 'c) parser

    val return : 'b -> ('a, 'b) parser

    val ( >>= ) : ('a, 'b) parser -> ('b -> ('a, 'c) parser) -> ('a, 'c) parser

    val zero : ('a, 'b) parser

    val ( ++ ) : ('a, 'b) parser -> ('a, 'b) parser -> ('a, 'b) parser

    val run : ('a, 'b) parser -> 'a Flux.t -> 'b Solution.t

    val pvide : ('a, unit) parser

    val ptest : ('a -> bool) -> ('a, 'a) parser

    val ( *> ) : ('a, 'b) parser -> ('a, 'c) parser -> ('a, 'b * 'c) parser
  end

 module Parser : Parsing =
  struct
    let map fmap parse f = Solution.map (fun (b, f') -> (fmap b, f')) (parse f);; 

    let return b f = Solution.return (b, f);;

    let (>>=) parse dep_parse = fun f -> Solution.(parse f >>= fun (b, f') -> dep_parse b f');;

    let zero f = Solution.zero;;

    let (++) parse1 parse2 = fun f -> Solution.(parse1 f ++ parse2 f);;

    let run parse f = Solution.(map fst (filter (fun (b, f') -> Flux.uncons f' = None) (parse f)));;

    let pvide f =
      match Flux.uncons f with
      | None   -> Solution.return ((), f)
      | Some _ -> Solution.zero;;

    let ptest p f =
      match Flux.uncons f with
      | None        -> Solution.zero
      | Some (t, q) -> if p t then Solution.return (t, q) else Solution.zero;;

    let ( *> ) parse1 parse2 = fun f ->
      Solution.(parse1 f >>= fun (b, f') -> parse2 f' >>= fun (c, f'') -> return ((b, c), f''));;
  end
  
type liaison = ident * expr
and bbinop = | BArithop of arithop | BBoolop of boolop | BRelop of relop | CONCAT | CONS
and arithop = | PLUS | MOINS | MULT | DIV
and boolop = | AND | OR
and relop = | EQU | NOTEQ | INFEQ | SUPEQ | INF | SUP 

open Parser


let parse_ident = ptest isident >>= fun (t) -> return (unident t)
let parse_bool = ptest isbool >>= fun (t) -> return (unbool t)
let parse_int = ptest isint >>= fun (t) -> return (unint t)
let isToken : token -> token -> bool = fun token t -> token = t
let parse_token token = ptest (isToken token) >>= fun (t) -> return ()

let p_eof = parse_token EOF

let rec parse_expr : (token, expr) parser = fun flux ->
    ( 
      (parse_token LET >>= fun () -> parse_liaison >>= fun (ident1, expr1) -> parse_token IN >>= fun () -> parse_expr >>= fun (expr2) -> return (ELet (ident1, expr1, expr2))) 
    ++
      (parse_token LET >>= fun () -> parse_token REC >>= fun () -> parse_liaison >>= fun (ident1, expr1) -> parse_token IN >>= fun () -> parse_expr >>= fun (expr2) -> return (ELetrec (ident1, expr1, expr2)))   
    ++
      (parse_token PARO >>= fun () -> parse_expr >>= fun (expr1) -> parse_binop >>= fun (binop) -> parse_expr >>= fun (expr2) -> parse_token PARF >>= fun () -> return (EApply (EApply (binop, expr1), expr2)))
    ++
      (parse_token PARO >>= fun () -> parse_expr >>= fun (expr1) -> parse_token PARF >>= fun () -> return (expr1))
    ++
      (parse_token PARO >>= fun () -> parse_expr >>= fun (expr1) -> parse_expr >>= fun (expr2) -> parse_token PARF >>= fun () -> return (EApply (expr1, expr2)))
    ++
      (parse_token IF >>= fun () -> parse_expr >>= fun (expr1) -> parse_token THEN >>= fun () -> parse_expr >>= fun (expr2) -> parse_token ELSE >>= fun () -> parse_expr >>= fun (expr3) -> return (EIf (expr1, expr2, expr3)))
    ++
      (parse_token FUN >>= fun () -> parse_ident >>= fun (ident1) -> parse_token TO >>= fun () -> parse_expr >>= fun (expr1) -> return (EFun(ident1, expr1)))
    ++
      (parse_ident >>= fun (ident1) -> return (EIdent ident1))
    ++
      (parse_constant >>= fun (constant1) -> return (EConstant constant1))   
     ) flux

and parse_liaison : (token, (ident*expr)) parser = fun flux ->
    (
      parse_ident >>= fun (ident1) -> parse_token EQU >>= fun () -> parse_expr >>= fun (expr1) -> return ((ident1, expr1))
    ) flux

and parse_binop : (token, expr) parser = fun flux ->
    (
      (parse_token CONCAT >>= fun () -> return (EBinop CONCAT))
    ++
      (parse_token CONS >>= fun () -> return (EBinop CONS))
    ++
      (parse_token PLUS >>= fun () -> return (EBinop PLUS))
    ++
      (parse_token MOINS >>= fun () -> return (EBinop MOINS))
    ++
      (parse_token MULT >>= fun () -> return (EBinop MULT))
    ++
      (parse_token DIV >>= fun () -> return (EBinop DIV))
    ++
      (parse_token AND >>= fun () -> return (EBinop AND))
    ++
      (parse_token OR >>= fun () -> return (EBinop OR))
    ++
      (parse_token EQU >>= fun () -> return (EBinop EQU))
    ++
      (parse_token NOTEQ >>= fun () -> return (EBinop NOTEQ))
    ++
      (parse_token INFEQ >>= fun () -> return (EBinop INFEQ))
    ++
      (parse_token INF >>= fun () -> return (EBinop INF))
    ++
      (parse_token SUPEQ >>= fun () -> return (EBinop SUPEQ))
    ++
      (parse_token SUP >>= fun () -> return (EBinop SUP)) 
    ) flux

and parse_constant : (token, constant) parser = fun flux ->
    (
      (parse_int >>= fun (int1) -> return (CEntier int1))
    ++ 
      (parse_bool >>= fun (bool1) -> return (CBooleen bool1))
    ++ 
      (parse_token CROO >>= fun () -> parse_token CROF >>= fun () -> return (CNil))
    ++ 
      (parse_token PARO >>= fun () -> parse_token PARF >>= fun () -> return (CUnit))
    ) flux



  
    
