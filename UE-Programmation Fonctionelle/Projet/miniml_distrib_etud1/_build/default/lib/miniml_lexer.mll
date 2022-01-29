
{

open Miniml_types;;

let line_g = ref 0
;;

exception ErreurLex of string
;;

}

rule token =
 parse
  [' ''\t''\r']+           { token lexbuf }
| '\n'                     { incr line_g;
			     token lexbuf }
| "->"			   { TO }
| ";;"			   { DOUBLEPTVIRG }
| "::"			   { CONS }
| "+"			   { PLUS }
| "-"			   { MOINS }
| "*"			   { MULT }
| "/"			   { DIV }
| "+."			   { FPLUS }
| "-."			   { FMOINS }
| "*."			   { FMULT }
| "/."			   { FDIV }
| "("			   { PARO }
| "(*"			   { comment lexbuf }
| ")"			   { PARF }
| "["			   { CROO }
| "]"			   { CROF }
| "="			   { EQU }
| "<>"			   { NOTEQ }
| "<="			   { INFEQ }
| ">="			   { SUPEQ }
| "<"			   { INF }
| ">"			   { SUP }
| "@"			   { CONCAT }
| "&&"			   { AND }
| "||"			   { OR  }
| ","			   { VIRG }
| ";"                      { PTVIRG }
| "true"                   { BOOL true }
| "false"                  { BOOL false }
| "mod"			   { MOD }
| "let"			   { LET }
| "in"			   { IN }
| "rec"			   { REC }
| "fun"		           { FUN }
| "match"                  { MATCH }
| "with"                   { WITH }
| "|"                      { BAR }
| "if"			   { IF }
| "then"		   { THEN }
| "else"		   { ELSE }
| ['a'-'z''_']['a'-'z''A'-'Z''_''0'-'9''\'']*  { IDENT (Lexing.lexeme lexbuf) }
| ['A'-'Z']['a'-'z''A'-'Z''_''0'-'9''\'']*  { CONSTRUCT (Lexing.lexeme lexbuf) }
| ['0'-'9']+               { INT (int_of_string (Lexing.lexeme lexbuf)) }
| '.'['0'-'9']+            { FLOAT (float_of_string (Lexing.lexeme lexbuf)) }
| ['0'-'9']+'.'['0'-'9']*  { FLOAT (float_of_string (Lexing.lexeme lexbuf)) }
| "type"                   { TYPE }
| '\''                     { QUOTE }
| eof                      { EOF }
| _                        { raise (ErreurLex "lexème inconnu !") }
and comment =
 parse
  eof                      { raise (ErreurLex "commentaire non fermé, fin de fichier prématurée !") }
| '\n'                     { incr line_g;
			     comment lexbuf }
| "*)"			   { token lexbuf }
| _			   { comment lexbuf }



