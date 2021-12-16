# Examen. Session 2. PF 2019 - 2020
## Structure de données
### Exercice 1
```ocaml
type ensnat = Node of bool * ensnat option * ensnat option

let node b l r =Some(Node(b, l, r))
let leaf = node true None None
let exemple=
    node true 
    None
    (node true
        (node false
            (node false 
                leaf
                None) 
            leaf )
        leaf )

(* --------------------------------------------- *)

(*  la fonction cardinal qui calcule le cardinal d’un ensemble d’entiers 
    cardinal : ensnat −> int *)
let rec cardinal ensnat = match ensnat with
    | None -> 0
    | Some (Node(bool,left, right)) -> if bool == true then match left with 
        | None -> 1+2*((cardinal right))
        | Some (Node(bool,left_l, right_l)) -> 1+2*(cardinal left)
                                        else match right with 
        | None -> 0+2*((cardinal left))
        | Some (Node(bool,left_r, right_r)) -> 0+2*(cardinal right)

(*  the finction int_to_intList can transform int to list of int by bit, for example: 123 -> [1;2;3]
    int_to_intList : int -> int list *)
let rec int_to_intList acc i =
  		if i=0 then acc
  		else int_to_intList (i land 1::acc) (i lsr 1)

(*  the finction intList_to_boolList can transform the list of int to list of boolean by 1 -> true | 0 -> false, for example: 101 -> [true; false; true]
    int_to_intList : int list -> bool list *)
let rec intList_to_boolList list = match list with
        | [] -> []
        | hd :: tl -> if hd == 1 then true::(intList_to_boolList tl)
                        else false::(intList_to_boolList tl)

(* the fonction binaire can translate the integer to a bool list. 101 -> [true; false; true]  
    binaire : int -> bool list *)
let binaire n =
    intList_to_boolList (int_to_bitList [] n)

```

### Exercice 2
```ocaml
let appartient i ensnat = 0 (* TODO *)

let ajout i ensnat = if (appartient i ensnat) then ensnat
                    else 
```



## Itérateurs monadique
### Exercice 4
```ocaml
type 'a iter = Next of unit -> ('a * 'a iter ) option

let rec map : (’a −>’b) −>’a iter −>’b iter = fun f iter = match iter with
    | None -> None
    | Some (a, next_iter) -> Some (f a, map f next_iter)

let return : 'a −> 'a iter = fun a =
    Some (a, None)

let bind : 'a iter −>('a −>'b iter) −>'b iter = fun iter f = match iter with 
    |


let zero = Some (0,None)
let plus iter1 iter2 : ’a iter −>’a iter −>’a iter =



```

## Typage avançé
### Exercice 6
1. 我们可以用`type _ t`来表示q0到q4状态。我们可以用以下形式表式
```ocaml
module type PIN =
    struct
        type _ t = Q0: q0 t | Q1: q1 t | Q2: q2 t | Q3: q3 t | Q4: q4 t

```
