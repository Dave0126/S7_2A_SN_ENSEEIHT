(* Exercice 1 *)

(** a. on veut lire 2 carateres

        -open-> #zero# -read-> #un# -read-> #deux# -close-> #

      open:   string -> zero fichier
      close : deux fichier -> unit
      read:   zero fcher -> un fichier
              un ficher -> deux fichier

      on va generaliser le probleme et encoder 1 "compteur" entier returnal

      type zero
      type _ succ

      open : string -> zero fichier                  | open :string -> zero succ succ fichier
      close : zero succ succ fichier -> unit         | close : zero fichier -> unit
      read : 'a fichier -> char * 'a succ fichier    | read : 'a succ ficjier -> char * 'a fichier
*)

(** b. on veut lire 1 nb pair de caracteres 
        -open-> ### -read-> ###
                ### <-read- ###
                |
              close
                | 
              #end#
      
    (zero,un) -succ-> (un,zero) -succ-> (zero,un) -> ...

    open : string -> (zero * un) fichier
    close : (zero * un ) fichier -> unit
    read : ('a * 'b) fichier -> char * ('b * 'a) fichier
*)

(* Exercice 2 *)

(** modifier RSA *)
module type RSA = 
sig 
  type key
  type secret
  val creat_key_pair : unit -> key * key
  val encrypt : bytes -> key -> secret
  val decrypt : secret -> key -> bytes
end



(* Exercice 3 & 4 *)

type 'a perfect_tree =
  | Empty
  | Node of 'a * ('a * 'a ) perfect_tree

(** Definition: 
    split : ('a *'a) perfect_tree -> 'a perfect_tree * 'a perfect_tree *)

let rec spilt perfect_tree = match perfect_tree with
  | Empty -> Empty, Empty
  | Node ((leftChild , rightChild), subTree) -> let (subLeftChild,subRightChild) = spilt subTree in
                                                Node (leftChild,subLeftChild), Node(rightChild,subRightChild)

let rec split : type a. (a * a) perfect_tree -> a perfect_tree * a perfect_tree
    = fun perfect_tree -> match perfect_tree with 

type ('p , 'a) perfect_tree = 
  | Empty : (zero, 'a) perfect_tree
  | Node : 'a * ('p, 'a * 'a) perfect_tree -> ('p succ, 'a) perfect_tree

let rec spilt : type p a. (p, a * a) perfect_tree -> (p, a) perfect_tree * (p, a) perfect_tree
(** 
  = MEME CODE 
  (le parametre p est fautome) *)

let rec merge : type p a. (p,a) perfect_tree * (p, a) perfect_tree -> (p, a * a) perfect_tree 
  = fun (subLeftChild, subRightChild) -> match (subLeftChild, subRightChild) with
  | Empty, Empty -> Empty
  | Node (leftChild,subLeftChild'), Node(rightChild,subRightChild') -> Node ((leftChild, rightChild), merge subLeftChild' subRightChild')


(** Exercice 6 *)
type _ repr =
  | Int : int repr
  | Add : (int -> int -> int) repr
(*| Apply : (int -> int -> int) repr * int repr * int repr -> int repr *)
  | Apply : ('a -> 'b) repr -> 'a repr -> 'b repr
  | Equ : ('a -> 'a -> bool) repr
  | Val : 'a -> 'a repr


  let eval : type a. a repr -> a =
    funtion 
    | Int i -> i
    | Add -> ((fun a b) -> a + b)
  (*| Apply(f, a, b) -> (eval f)(** int -> int -> int *) (eval a)(** int *) (eval b)(** int *) *)
    | Appy (f, a) -> (eval f)(** int -> int -> int *) (eval a)(** int *)
    | Equ -> (fun x y -> x = y)

