(** TD10 : Les " parseurs" *)
(** reconnaisance de langage
    un parser consomme les elements successifs d'un flux. *)
(** si on ne slinteresse qu'a la reconnaisance (pas de construction d'AST) *)
(** Un parser est par default non-deterministe (plusieur facons de reconnaitre le flux d'entree) *)
(** E -> E + E
    E -> E * E
    E -> ident
    (x+(y*Z)): 2
    x++y: 0
    x+y: 1 *)
(** un parser va produire 1 ens. de "resultat" *)
(**     E     F
    (a+ab) .  c
    E reconnait soit a -> echec de F
                soit ab -> success de F
    E click renvoyer le flux apres consommation des elements reconnus (ad ou a) pour que F reconnaisse c. *)

type 'a result = 'a Flux.t Solution.t (** "Solution.t": une instance de la monade NDET *)
type 'a parser = 'a Flux.t -> a' result
(** on definit des parsers elementaire et des optiateur de conposilions de parser (sequence, choix, repetition, ... ) *)
let pnul flux = Solution.return flux
let perreur flux = Solution.zero
let pvide flux = match Flux.uncons flux with
  | None    −> Solution.return flux
  | Some _  −> Solution.zero
let plest p flux = match Flux.uncons flux with
  | None    -> Solution.zero
  | Some (t,q) -> if p t then Solution.returnq else Solution.zero

(***************)
(** Exercice 1 *)
(***************)
let sequence parser1 parser2 flux = 
  Solution.(parser1 flux >>= fun flux' -> parser2 flux') (** "fun flux' -> parser2 flux' " : parser2 *)

let choix parser1 parser2 flux =
  Solution.((parser1 flux) ++ (parser2 flux))
(** -------------------------------------------- *)

type 'a language =
  | Nothing
  | Empty
  | Letter of 'a
  | Sequence of 'a language * 'a language
  | Choice of 'a language * 'a language
  | Repeat of 'a language
(** 'a language conspend aux expressions reguliere sur des lettres de type 'a *)

(***************)
(** Exercice 2 *)
(***************)

(* Λ.a = a
    a.Λ.b = a.b 
    Λ | a 
*)
let rec eval lang =
  match lang with
  | Nothing                 -> perreur
  | Empty                   -> pnul
  | Letter a                -> ptest (fun token -> token = a) (** (fun token -> ( (=) a) token) *)
  | Sequence (lang1, lang2) -> sequence (eval lang1) (eval lang2)
  | Choice (lang1, lang2)   -> choice (eval lang1) (eval lang2)
  | Repeat lang1            -> eval (Choice (Empty, Sequence(lang1, lang))) (** BOUCLE INFINIE!!   (lang1)* = Λ | lang1.(lang1)*   lang1的闭包closure *)
                                                                            (** choice pnul(sequence (eval lang1) (flux flux -> eval lang flux)) *)
let rec eval lang flux =
  match lang with
  | Nothing                 -> perreur flux
  | Empty                   -> pnul flux
  | Letter a                -> ptest (fun token -> token = a) flux (** (fun token -> ( (=) a) token) *)
  | Sequence (lang1, lang2) -> sequence (eval lang1) (eval lang2) flux
  | Choice (lang1, lang2)   -> choice (eval lang1) (eval lang2) flux
  | Repeat lang1            -> choice pnul(sequence (eval lang1) (flux flux -> eval lang flux) (** BOUCLE INFINIE!!   (lang1)* = Λ | lang1.(lang1)*   lang1的闭包closure *)

let belongs lang flux = 
  let parser = sequence (eval lang) pride in 
    let solutions = parser flux in
      Solution <> Solution.zero

(** Parser more general *)
type ('a, 'b) result = (** type 'a is element du flux | type 'b is resultat produit *)
  ('b * 'a Flux.t) Solution.t
type ('a, 'b) parser = 'a Flux.t -> ('a, 'b) result

(** cela ferme une monade de additive (en fonction du parameter 'b) *)
let zero flux = Solution.zero
(** b -> (a,b) parser *)
let return b = fun flux -> Solution.return (b, flux)
(** (++) = (choice) *)
let (++) = choice
let (>>=) parser1 f parser2 = fun flux -> Solution.(parser1 flux >>= fun (b, flux') -> f parser2 b flux')
let run parser flux = 

(***************)
(** Exercice 3 *)
(***************)
let perreur = fun flux -> Solution.zero
let pnul = return ()
let ptest p flux = match Flux.uncons flux with
  | None -> Solution.zero
  | Some (t, q) -> if p t then Solution.return(t,q)
                          else Solution.zero
(** "pchoice" et "psequence" sont juste (++) et (>>=) *)
let pchoice = (++)
let psequence = (>>=)
let pride flux = match Flux.uncons flux with
  | None -> Solution.return((), flux)
  | Some _ -> Solution.zero
