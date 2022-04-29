{

  open TokenJava
(*  open String *)
(*  open Str *)
  exception LexicalError

}

(* Macro-definitions *)
let minuscule = ['a'-'z']
let majuscule = ['A'-'Z']
let chiffre = ['0'-'9']
let alphabet = minuscule | majuscule
let alphanum = alphabet | chiffre | '_'
let commentaireBloc = (* A COMPLETER *) "/*" _* "*/" 
let commentaireLigne = "//" [^'\n']* '\n'

let hexadecimal = (['0'-'9'] | ['a'-'f'] | ['A'-'F'])
let octal = ['0'-'7']

let binary = '0' | '1'
let digit = chiffre (('_' chiffre) | chiffre)*

(* Analyseur lexical : expression reguliere { action CaML } *)
rule lexer = parse

(* Espace, tabulation, passage a ligne, etc : consommes par l'analyse lexicale *)
  | ['\n' '\t' ' ']+    { lexer lexbuf }

(* Commentaires consommes par l'analyse lexicale *)
  | commentaireBloc  	{ lexer lexbuf }
  | commentaireLigne	{ lexer lexbuf }

(* Structures de blocs *)
  | "("                 { PAROUV }
  | ")"                 { PARFER }
  | "["                 { CROOUV }
  | "]"                 { CROFER }
  | "{"                 { ACCOUV }
  | "}"                 { ACCFER }

(* Separateurs *)
  | ","                 { VIRG }
  | ";"                 { PTVIRG }

(* Operateurs booleens *)
  | "||"                { OPOU }
  | "&&"                { OPET }
  | "!"                 { OPNON }

(* Operateurs comparaisons *)
  | "=="                { OPEG }
  | "!="                { OPNONEG }
  | "<="                { OPSUPEG }
  | "<"                 { OPSUP }
  | ">="                { OPINFEG }
  | ">"                 { OPINF }

(* Operateurs arithmetiques *)
  | "+"                 { OPPLUS }
  | "-"                 { OPMOINS }
  | "*"                 { OPMULT }
  | "/"                 { OPDIV }
  | "%"                 { OPMOD }
  | "."                 { OPPT }
  | "="                 { ASSIGN }
  | "new"               { NOUVEAU }

(* Mots cles : types *)
  | "bool"              { BOOL }
  | "char"              { CHAR }
  | "float"             { FLOAT }
  | "int"               { INT }
  | "String"            { STRING }
  | "void"              { VOID }
  
(* Mots cles : instructions *)
  | "while"		{ TANTQUE }
  | "if"		{ SI }
  | "else"		{ SINON }
  | "return"		{ RETOUR }

(* Mots cles : constantes *)
  | "true"		{ (BOOLEEN true) }
  | "false"		{ (BOOLEEN false) }
  | "null"		{ VIDE }

(* Nombres entiers : A COMPLETER *)
  | ('0' |  ('0' ['1' - '9'] chiffre* ('_' chiffre+)*) | (['1' - '9'] (('_' chiffre) | chiffre)* ))  as texte { (ENTIER (int_of_string texte)) }

(* Nombres binary : A COMPLETER *)
  | (("0b" | "0B") binary (('_' binary) | binary)*) as texte   { (BINARY (int_of_string texte)) }

(* Nombres hexadecimal : A COMPLETER *)
  | ('0' ('x' | 'X') hexadecimal (('_' hexadecimal) | hexadecimal)*) as texte   { (HEXADECIMAL (int_of_string texte)) }

(* Nombres octal : A COMPLETER *)
  | ('0' ?'_' octal (('_' octal) | octal)*)   as texte   { (OCTAL (int_of_string texte)) }

(* Nombres flottants : A COMPLETER *)
  | ((digit) ".")  as texte { (FLOTTANT (float_of_string texte)) }
  | ((digit) "." digit)  as texte { (FLOTTANT (float_of_string texte)) }
  | ((digit) "." ('e' | 'E'))  as texte { (EXPONENT (texte)) }
  | ((digit) "." digit ('e' | 'E'))  as texte { (EXPONENT (texte)) }
  | ("." (digit))  as texte { (FLOTTANT (float_of_string texte)) }
  | ("." (digit) ('e' | 'E' | "e+" | "E+" | "e-" | "E-") (digit))  as texte { (FLOTTANT (float_of_string texte)) }
  | (digit ('e' | 'E') digit) as texte { (FLOTTANT (float_of_string texte)) }
  | (digit ('f' | 'F' | 'd' | 'D')) as texte { (FLOTTANT (float_of_string texte)) }

(* Caracteres : A COMPLETER *)
  | ''' [^'\\'] ''' as texte                   { CARACTERE texte.[1] }
  | ''' ("\\b" | "\\t" | "\\n" | "\\f" | "\\r" | "\\\"" | "\\\'" | "\\\\") ''' as texte { CARACTERESPECIAL (texte.[2]) }

(* Chaines de caracteres : A COMPLETER *)
  | '"' _* '"' as texte                  { CHAINE texte }

(* Identificateurs *)
  | majuscule alphanum* as texte              { TYPEIDENT texte }
  | minuscule alphanum* as texte              { IDENT texte }
  | eof                                       { FIN }
  | _                                         { raise LexicalError }

{

}