open Parser
(* Fonction d'affichage des unités lexicales et des données qu'elles contiennent *)
 let printToken t =
  (print_endline
     (match t with
       | UL_LEFT_BRACE -> "{"
       | UL_RIGHT_BRACE -> "}"
       | UL_LEFT_PAREN -> "("
       | UL_RIGHT_PAREN -> ")"
       | UL_DOT -> "."
       | UL_COMMA -> ","
       | UL_SEMICOLON -> ";"
       | UL_PACKAGE -> "package"
       | UL_INTERFACE -> "interface"
       | UL_EXTENDS -> "extends"
       | UL_BOOLEAN -> "boolean"
       | UL_INT -> "int"
       | UL_VOID -> "void"
       | UL_IDENT_PACKAGE n -> n
       | UL_IDENT_INTERFACE n -> n
       | UL_FIN -> "EOF"
));; 

let main () =
if (Array.length Sys.argv > 1)
then
  let lexbuf = (Lexing.from_channel (open_in Sys.argv.(1))) in
  try
    (Parser.package Lexer.lexer lexbuf);(print_endline "OK")
  with
  | Lexer.LexicalError ->
    print_endline "KO"
  | Parser.Error ->
     print_endline "KO"
else
  (print_endline "MainPackage fichier");;

main();;
