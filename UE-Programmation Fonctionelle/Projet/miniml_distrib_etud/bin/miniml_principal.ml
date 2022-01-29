(* ouverture de la "library" definie dans lib/dune *)
open Miniml

(* ouverture de modules de la library Miniml *)
open Miniml_lexer
open Miniml_parser
open Miniml_typer
open Miniml_printer

(* ******** à compléter ********* *)

(* module principle *)
module Main = 
 struct

(* programme principale *)
(* filename: Nom du fichier MiniML *)
(* Resultat : Some type ou None au cas d'erreur ou non-existence de solutions *)
 let main = fun filename -> let helper expr = 
        (match Typer.typer expr (Env.gamma ()) with
        | Some(ty, eqs) -> ( match Normalizer.normalizer eqs ty with
            | Some(t) -> print_typ TypeVariable.fprintf Format.std_formatter t
            | None -> Format.fprintf Format.std_formatter "Erreur de type! (Equations n'admettent de solution)"
            )
        | None ->Format.fprintf Format.std_formatter "Erreur de type! (Probleme de typage)") in 
        let rec parsing tok = 
    match Solution.uncons(parse_expr tok) with
    | Some((expr,b),_) -> helper expr ; Format.fprintf Format.std_formatter "\n"; parsing b 
    | None -> Format.fprintf Format.std_formatter "***Fin***"
    in parsing (read_miniml_tokens_from_file filename)

 end

 let() =
    print_endline("\n****** MiniML ********\n");
    Main.main "testFile"
