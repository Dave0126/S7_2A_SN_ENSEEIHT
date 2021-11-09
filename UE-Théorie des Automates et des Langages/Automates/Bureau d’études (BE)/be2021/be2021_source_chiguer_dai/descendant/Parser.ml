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
let (* rec *) parseR stream =
  (print_string "R -> ...");
  (match (peekAtFirstToken stream) with
    | UL_MODEL -> inject stream >>= accept (UL_UPIDENT _) >>= accept UL_ACCOUV >>= parseSE >>= accept UL_ACCFER 
    | _ -> Failure)

;;

let (* rec *) parseSE stream =
  (print_string "SE -> ...");
  (match (peekAtFirstToken stream) with
    | UL_MODEL -> inject stream >>= parseE >>= parseSE
    | UL_ACCFER -> inject stream
    | _ -> Failure)

;;

let (* rec *) parseE stream =
  (print_string "E -> ...");
  (match (peekAtFirstToken stream) with
    | UL_BLOCK -> inject stream >>= accept (UL_UPIDENT _) >>= parseP >>= accept UL_PTVIRG
    | UL_SYSTEM -> inject stream >>= accept (UL_UPIDENT _) >>= parseP >>= accept UL_ACCOUV >>= parseSE >>= accept UL_ACCFER
    | UL_FLOW -> inject stream >>= accept (UL_IDENT _) >>= accept UL_FROM >>= parseNQ >>= accept UL_TO >>= parseLN
    | _ -> Failure)

;;

let (* rec *) parseNQ stream =
  (print_string "NQ -> ...");
  (match (peekAtFirstToken stream) with
    | (UL_IDENT _) -> inject stream
    | (UL_UPIDENT _) -> inject stream >>= accept UL_OPPT >>= accept (UL_IDENT _)
    | _ -> Failure)

;;

let (* rec *) parseLN stream =
  (print_string "LN -> ...");
  (match (peekAtFirstToken stream) with
    | ((UL_IDENT _) | (UL_UPIDENT _)) -> inject stream >>= parseNQ >>= parseSN
    | UL_PTVIRG -> inject stream
    | _ -> Failure)
;;

let (* rec *) parseSN stream =
  (print_string "SN -> ...");
  (match (peekAtFirstToken stream) with
    | UL_VIRG -> inject stream >>= parseNQ >>= parseSN
    | UL_PTVIRG -> inject stream
    | _ -> Failure)
;;

let (* rec *) parseP stream =
  (print_string "P -> ...");
  (match (peekAtFirstToken stream) with
    | UL_PAROUV -> inject stream >>= parseLP >>= accept UL_PARFER
    | _ -> Failure)
;;

let (* rec *) parseLP stream =
  (print_string "LP -> ...");
  (match (peekAtFirstToken stream) with
    | (UL_IDENT _) -> inject stream >>= parseDP >>= parseSP
    | _ -> Failure)
;;

let (* rec *) parseSP stream =
  (print_string "SP -> ...");
  (match (peekAtFirstToken stream) with
    | UL_VIRG -> inject stream >>= parseDP >>= parseSP
    | UL_PARFER -> inject stream
    | _ -> Failure)
;;

let (* rec *) parseDP stream =
  (print_string "DP -> ...");
  (match (peekAtFirstToken stream) with
    | (UL_IDENT _) -> inject stream >>= accept UL_DPT >>= parseM >>= parseT >>= parseOT
    | _ -> Failure)
;;

let (* rec *) parseM stream =
  (print_string "M -> ...");
  (match (peekAtFirstToken stream) with
    | (UL_IN | UL_OUT) -> inject stream 
    | _ -> Failure)
;;

let (* rec *) parseT stream =
  (print_string "T -> ...");
  (match (peekAtFirstToken stream) with
    | (UL_INT | UL_FLOAT | UL_BOOL) -> inject stream 
    | _ -> Failure)
;;

let (* rec *) parseOT stream =
  (print_string "OT -> ...");
  (match (peekAtFirstToken stream) with
    | UL_CROOUV -> inject stream >>= accept (UL_ENTIER _) >>= parseSV >>= accept UL_CROFER
    | (UL_VIRG | UL_PARFER ) -> inject stream
    | _ -> Failure)
;;

let (* rec *) parseSV stream =
  (print_string "SV -> ...");
  (match (peekAtFirstToken stream) with
    | UL_VIRG -> inject stream >>= accept (UL_ENTIER _) >>= parseSV
    | UL_CROFER -> inject stream
    | _ -> Failure)
;;