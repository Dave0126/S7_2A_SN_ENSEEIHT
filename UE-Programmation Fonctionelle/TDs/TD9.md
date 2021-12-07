# TD9 : Les Contiuations Natires

On utilise la librainrue Definiee
```ocaml
(* type des marquteuse de pile *)
type 'a prompt 
val neur_prompt: unit -> 'reset prompt
(** push_prompt: reset *)
val push_prompt: 'reset prompt -> (unit -> 'reset) -> 'reset
val shift: (('shift -> 'reset) -> 'reset) -> 'shift
(**         |-------k-------|    |-expr-| *)
```
on utilise pour 'reset 
```ocaml
(* le type suivant: *)
  type ('shift, 'reset)res =
    (* calcule normeux*)
    | Done of 'reset
    (* calcule aretes par 1 shift *)
    | Request of ('shift -> 'reset)
    (*                   ｜contiuation k | *)
  ```

## Exo 1: 
```ocaml
let rec prod_int_list l =
  match l with 
  | [] -> 1
  | hd :: tl -> hd * (prod_int_list tl)
```

on peut "optimiser" le programme pour ne realiser auncune muiltiplication si un 0 et dan le liste (arc 1 seul parcours) 
```ocaml
  let rec prod_int_list l =
    match l with
    | [] -> 1
    | 0 :: tl -> 0
    | hd :: tl -> hd * (prod_int_list tl)
```

cette solution erite la mulitplication des elements apres le premier 0, mais pas ceux avant

on peut eriter les calcule avec des exceptions

```ocaml
exception Zero;;
let prod_int_list l =
  match l with
  | [] -> 1
  | 0 :: tl -> raise Zero
  | hd :: tl -> hd * loop tl
      in try loop l with
      | Zero -> 0
```

on peut retransive cette solution avec des contiuations

```ocaml
let p = neur_prompt ()

let prod_int_list l =
  let rec loop l =
    match l with
    | [] -> 1
    | 0 :: tl -> shift (func k -> 0)
    | hd :: tl -> hd * loop tl
  in push_prompt p (func () -> loop l )
  ```

## Exo 3: definir les primitives yield et foreach 
略


## Exo 4&5 : 
```ocaml
(** 这tmd都是些什么东西啊 *)
  let ping ()=
    for i = 1 to 10 do
      print_endline "ping!";
      shift(func k -> Request)
    done
  let pong ()=
    for i = 1 to 10 do
      print_endline "pong!"
    done

(** reset prompt *)
let p = neur_prompt()

(** 
    ping              scheduler             pong
    |                     |                   |
    () --shift(push...)-> ()                  |
    |                     ()                  |
    |                     | ----------------> ()
    |                     | <---------------- () 
    |                     ()
    ()<--------k()--------()
    |                     |
    ...                   ...
    ()---------Done------>()
*)

(** on dirt distinguer l'interruption de l'arret difinitif *)
type res =
  | Done 
  | Request of (unit -> res)

(** func insercer 1 marquer de pile une foi ... *)
let run (proc: unit -> res ) () =
  push_prompt p proc
let scheduler () =
  let rec loop file =
    match file with
    | [] -> ()
    | proc :: tl -> match proc with
                    | Done -> loop tl
                    | Request k -> loop (tl @ [k])
                    in loop [run ping; run pong]
```