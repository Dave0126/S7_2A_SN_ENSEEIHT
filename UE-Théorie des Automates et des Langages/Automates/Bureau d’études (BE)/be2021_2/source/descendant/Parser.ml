open Tokens


(* Type du résultat d'une analyse syntaxique *)
type parseResult =
  | Success of inputStream
  | Failure
;;

(* accept : token -> inputStream -> parseResult *)
(* Vérifie que le premier token du flux d'entrée est bien le token attendu *)
(* et avance dans l'analyse si c'est le cas *)
let accept expected stream =
  match (peekAtFirstToken stream) with
    | token when (token = expected) ->
      (Success (advanceInStream stream))
    | _ -> Failure
;;

(* accept : token -> inputStream -> parseResult *)
(* Vérifie que le premier token du flux d'entrée est bien le token attendu *)
(* et avance dans l'analyse si c'est le cas *)
let acceptPackageIdent stream =
  match (peekAtFirstToken stream) with
    | UL_IDENT_PACKAGE _ ->
      (Success (advanceInStream stream))
    | _ -> Failure
;;

let acceptInterfaceIdent stream =
  match (peekAtFirstToken stream) with
    | UL_IDENT_INTERFACE _ ->
      (Success (advanceInStream stream))
    | _ -> Failure
;;

(* Définition de la monade  qui est composée de : *)
(* - le type de donnée monadique : parseResult  *)
(* - la fonction : inject qui construit ce type à partir d'une liste de terminaux *)
(* - la fonction : bind (opérateur >>=) qui combine les fonctions d'analyse. *)

(* inject inputStream -> parseResult *)
(* Construit le type de la monade à partir d'une liste de terminaux *)
let inject s = Success s;;

(* bind : 'a m -> ('a -> 'b m) -> 'b m *)
(* bind (opérateur >>=) qui combine les fonctions d'analyse. *)
(* ici on utilise une version spécialisée de bind :
   'b  ->  inputStream
   'a  ->  inputStream
    m  ->  parseResult
*)
(* >>= : parseResult -> (inputStream -> parseResult) -> parseResult *)
let (>>=) result f =
  match result with
    | Success next -> f next
    | Failure -> Failure
;;


(* parseMachine : inputStream -> parseResult *)
(* Analyse du non terminal Programme *)
let (* rec *) parsePackage stream =
  (print_string "Package -> ");
  (match (peekAtFirstToken stream) with
   | UL_PACKAGE ->
      (print_endline "package UL_IDENT_PACKAGE { ... }");
      ((inject stream) >>=
        (accept UL_PACKAGE) >>=
        acceptPackageIdent >>=
        (accept UL_LEFT_BRACE) >>=
        (accept UL_RIGHT_BRACE))
   | _ -> Failure)

let rec parseSE stream = 
  (print_string "SE -> ");
  (match (peekAtFirstToken stream) with
  | UL_RIGHT_BRACE -> 
      ((print_endline "}");
      (inject stream))
  | UL_PACKAGE -> 
      ((inject stream) >>=
      (accept UL_PACKAGE) >>=
      parseE >>=
      parseSE)
  | (UL_INTERFACE) -> 
      ((inject stream) >>=
      (accept UL_INTERFACE) >>=
      parseE >>=
      parseSE)
  | _ -> Failure)

let parseE stream =
  (print_string "E -> ");
  (match (peekAtFirstToken stream) with 
  | UL_PACKAGE ->
    ((inject stream) >>=
    (accept UL_PACKAGE) >>=
    parseP)
  | UL_INTERFACE ->
    ((inject stream) >>= 
    (accept UL_INTERFACE) >>=
    parseI)
  | _ -> Failure)

let parseI stream =
  (print_string "I -> ");
  (match (peekAtFirstToken stream) with 
  | UL_INTERFACE ->
    ((inject stream) >>= 
    (accept UL_INTERFACE) >>=
    acceptInterfaceIdent >>=
    parseX >>=
    (accept UL_LEFT_BRACE) >>=
    parseSM >>=
    (accept UL_RIGHT_BRACE))
  | _ -> Failure)

let parseX stream =
  (print_string "X -> ");
  (match (peekAtFirstToken stream) with 
  | UL_LEFT_BRACE ->
    ((print_endline "}");
    (inject stream))
  | UL_EXTENDS -> 
    ((inject stream) >>=
    (accept UL_EXTENDS) >>= 
    parseQ >>=
    acceptInterfaceIdent >>= 
    parseSQ)
  | _ -> Failure)

let rec parseQ stream =
  (print_string "Q -> ");
  (match (peekAtFirstToken stream) with 
  | UL_IDENT_INTERFACE ->
    (inject stream)
  | UL_IDENT_PACKAGE ->
    ((inject stream) >>=
    acceptPackageIdent >>=
    (accept UL_DOT) >>=
    parseQ)
  | _ -> Failure)

  let rec parseSQ stream =
    (print_string "SQ -> ");
    (match (peekAtFirstToken stream) with 
    | UL_LEFT_BRACE ->
      (inject stream)
    | UL_COMMA ->
      ((inject stream) >>=
      (accept UL_COMMA) >>=
      parseQ >>=
      acceptInterfaceIdent >>=
      parseSQ)
    | _ -> Failure)

let rec parseSM stream =
  (print_string "SM -> ");
  (match (peekAtFirstToken stream) with 
  | UL_RIGHT_BRACE ->
    (inject stream)
  | (UL_BOOLEAN|UL_INT|UL_VOID|UL_IDENT_PACKAGE|UL_IDENT_INTERFACE) ->
    ((inject stream) >>=
    parseM >>=
    parseSM)
  | _ -> Failure)

let rec parseM stream =
  (print_string "M -> ");
  (match (peekAtFirstToken stream) with 
  | (UL_BOOLEAN|UL_INT|UL_VOID|UL_IDENT_PACKAGE|UL_IDENT_INTERFACE) ->
    ((inject stream) >>=
    parseT >>=
    acceptPackageIdent >>= 
    (accept UL_LEFT_PAREN) >>=
    parseO >>=
    (accept UL_RIGHT_PAREN) >>=
    (accept UL_SEMICOLON))
  | _ -> Failure)



(* To be continue... *)


;;
