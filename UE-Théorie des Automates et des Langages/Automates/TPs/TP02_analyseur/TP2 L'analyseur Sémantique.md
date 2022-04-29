### TP2: L'analyseur Sémantique

#### `parserJava.mly` 

<img src="pics/2022-04-28 18.16.26.png" alt="2022-04-28 18.16.26" style="zoom:50%;" />

```ocaml
typeStruct : typeBase declTab { (print_endline "typeStruct : typeBase declTab") }

typeBase : INT { (print_endline "typeBase : INT") }
         | FLOAT { (print_endline "typeBase : FLOAT") }
         | BOOL { (print_endline "typeBase : BOOL") }
         | CHAR { (print_endline "typeBase : CHAR") }
         | STRING { (print_endline "typeBase : STRING") }
         | TYPEIDENT { (print_endline "typeBase : TYPEIDENT") }

declTab : /* Lambda, mot vide */ { (print_endline "declTab : /* Lambda, mot vide */") }
        | CROOUV CROFER { (print_endline "declTab : CROOUV CROFER") }
```



<img src="pics/2022-04-28 18.20.02.png" alt="2022-04-28 18.20.02" style="zoom:60%;" />

```ocaml
entete : 
		typeStruct IDENT PAROUV parsFormels PARFER 
			{ (print_endline "entete : typeStruct IDENT PAROUV parsFormels PARFER") }
	| VOID IDENT PAROUV parsFormels PARFER 
			{ (print_endline "entete : VOID IDENT PAROUV parsFormels PARFER") }

parsFormels : 
		/* Lambda, mot vide */ 
			{ (print_endline "parsFormels : /* Lambda, mot vide */") }
		| typeStruct IDENT suiteParsFormels 
			{ (print_endline "parsFormels : typeStruct IDENT suiteParsFormels") }

suiteParsFormels : 
		/* Lambda, mot vide */
			{ (print_endline "suiteParsFormels : /* Lambda, mot vide */") }
		| VIRG typeStruct IDENT suiteParsFormels 
			{ (print_endline "suiteParsFormels : VIRG typeStruct IDENT suiteParsFormels") }

```



<img src="pics/截屏2022-04-28 18.24.48.png" alt="截屏2022-04-28 18.24.48" style="zoom:60%;" />

```ocaml
bloc : 
		ACCOUV variables instructions ACCFER
			{ (print_endline "bloc : ACCOUV variables instructions ACCFER"); 
				(print_string "Nombre de variables = "); 
				(print_int $2); /* variables */
				(print_newline ()) 
			}

variables : 
		/* Lambda, mot vide */ 
			{ (print_endline "variables : /* Lambda, mot vide */"); 
				0 
			}
		| variable /* $1 */	variables /* $2 */
			{ (print_endline "variables : variable variables"); 
				($2 + 1) 
			}

instructions : 
		/* Lambda, mot vide */ 
			{ (print_endline "instructions : /* Lambda, mot vide */") }
		| instruction instructions 
			{ (print_endline "instructions : instruction instructions") }
```



<img src="pics/截屏2022-04-28 18.28.49.png" alt="截屏2022-04-28 18.28.49" style="zoom:60%;" />

```ocaml
variable : 
		typeStruct IDENT PTVIRG 
			{ (print_endline "variable : typeStruct IDENT PTVIRG") }
/* 我自己添加的：例如 int i = 1; 上一行不能有 "=1" 这种赋值情况存在
	 只能写成如下形式：
	 		int i;
	 		i = 1;
*/
	| typeStruct IDENT binaire expression PTVIRG
			{ (print_endline "variable : typeStruct IDENT binaire expression PTVIRG") }
```





<img src="pics/截屏2022-04-28 18.31.14.png" alt="截屏2022-04-28 18.31.14" style="zoom:60%;" />

```ocaml
instruction :
		expression PTVIRG 
			{ (print_endline "instruction : expression PTVIRG") }
	| SI PAROUV expression PARFER bloc 
			{ (print_endline "instruction : SI PAROUV expression PARFER bloc") }
	| SI PAROUV expression PARFER bloc SINON bloc
			{ (print_endline "instruction : SI PAROUV expression PARFER bloc SINON bloc") }
	| TANTQUE PAROUV expression PARFER bloc 
			{ (print_endline "instruction : TANTQUE PAROUV expression PARFER bloc") }
	| RETOUR expression PTVIRG  
			{ (print_endline "instruction : RETURN expression PTVIRG") }
```





<img src="pics/截屏2022-04-28 18.34.46.png" alt="截屏2022-04-28 18.34.46" style="zoom:60%;" />

```ocaml
expression : 
		unaires part_expression 
			{ (print_endline "expression : unaires part_expression") }
	| unaires part_expression binaire expression 
			{ (print_endline "expression : unaires part_expression binaire expression") }
			
unaires : 
	/* Lambda, mot vide */ 
			{ (print_endline "unaires : /* Lambda, mot vide */") }
	| unaire unaires 
			{ (print_endline "expressionvirgule : VIRG expression expressionvirgule") }
			
part_expression : 
		ENTIER { (print_endline "part_expression : ENTIER") }
	| FLOTTANT { (print_endline "part_expression : FLOTTANT") }
	| CARACTERE { (print_endline "part_expression : CARACTERE") }
	| BOOLEEN { (print_endline "part_expression : BOOLEEN") }
	| VIDE { (print_endline "part_expression : VIDE") }
	| NOUVEAU IDENT PAROUV PARFER 
			{ (print_endline "part_expression : NOUVEAU IDENT PAROUV PARFER") }
	| NOUVEAU IDENT CROOUV expression CROFER 
			{ (print_endline "part_expression : NOUVEAU IDENT CROOUV expression CROFER") }
	| IDENT suffixes { (print_endline "part_expression : IDENT suffixes") }
	| PAROUV expression PARFER suffixes 
			{ (print_endline "part_expression : PAROUV expression PARFER suffixes") }
			
suffixes : 
	/* Lambda, mot vide */ { (print_endline "suffixes : /* Lambda, mot vide */") }
	| suffixe suffixes { (print_endline "suffixes : suffixe suffixes") }
```





<img src="pics/截屏2022-04-28 18.41.13.png" alt="截屏2022-04-28 18.41.13" style="zoom:60%;" />

```ocaml
binaire :    
			ASSIGN { (print_endline "binaire : ASSIGN") }
	   | OPINF { (print_endline "binaire : OPINF") }
	   | OPSUP { (print_endline "binaire : OPSUP") }
	   | OPINFEG { (print_endline "binaire : OPINFEG") }
	   | OPSUPEG { (print_endline "binaire : OPSUPEG") }
	   | OPEG { (print_endline "binaire : OPEG") }
	   | OPNONEG { (print_endline "binaire : OPNONEG") }
	   | OPPLUS { (print_endline "binaire : OPPLUS") }
	   | OPMOINS { (print_endline "binaire : OPMOINS") }
	   | OPOU { (print_endline "binaire : OPOU") }
	   | OPMULT { (print_endline "binaire : OPMULT") }
	   | OPMOD { (print_endline "binaire : OPMOD") }
	   | OPDIV { (print_endline "binaire : OPDIV") }
	   | OPET { (print_endline "binaire : OPET") }
	   | OPNON { (print_endline "binaire : OPNON") }
	   | OPPT { (print_endline "binaire : OPPT") }
```





<img src="pics/截屏2022-04-28 18.43.25.png" alt="截屏2022-04-28 18.43.25" style="zoom:60%;" />

```ocaml
unaire : 
		PAROUV typeBase PARFER { (print_endline "unaire : PAROUV typeBase PARFER") }
	| OPPLUS { (print_endline "unaire : OPPLUS") }
	| OPMOINS { (print_endline "unaire : OPMOINS") }
	| OPNON { (print_endline "unaire : OPNON") }
```





<img src="pics/截屏2022-04-28 18.49.07.png" alt="截屏2022-04-28 18.49.07" style="zoom:60%;" />

```ocaml
suffixe :
		PAROUV PARFER { (print_endline "suffixe : PAROUV PARFER") }
	| PAROUV expression expressionvirgule PARFER
			{ (print_endline "suffixe : PAROUV expression expressionvirgule PARFER") }
	| CROOUV expression CROFER { (print_endline "suffixe : CROOUV expression CROFER") }

expressionvirgule : 
	/* Lambda, mot vide */ 
			{ (print_endline "expressionvirgule : /* Lambda, mot vide */") }
	| VIRG expression expressionvirgule 
			{ (print_endline "expressionvirgule : VIRG expression expressionvirgule") }
```

