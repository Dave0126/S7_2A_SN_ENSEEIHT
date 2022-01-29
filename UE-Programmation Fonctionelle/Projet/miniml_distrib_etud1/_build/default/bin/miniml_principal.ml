(* ouverture de la "library" definie dans lib/dune *)
open Miniml

(* ouverture de modules de la library Miniml *)
open Miniml_lexer
open Miniml_parser
open Miniml_typer
open Lazyflux

open Lexing

(* ******** à compléter ********* *)

exception EXCEPTION of string
       
let main file =
  let env = Hashtbl.create 100 in
  print_string "     Mini_ML version 1.0.0     ";
  print_endline "\n";
  try
    let inChannel = open_in file in
    let lexerBuffer = Lexing.from_channel inChannel in
    line_g := 1;
    let next_token () =
      try
        let next = token lexerBuffer in
        if next = EOF
        then
          begin
            close_in inChannel;
            None
          end
        else
          Some (next, ())
   with
   | ErreurLex msg ->
      begin
        close_in inChannel;
        raise (EXCEPTION (Format.sprintf "ERREUR : ligne %d, lexème '%s' : %s" !line_g (Lexing.lexeme lexerBuffer) msg))
      end in
    Flux.unfold next_token ()
 with
    | Sys_error _ -> raise (EXCEPTION (Format.sprintf "Error: No such file '%s' !" file))

