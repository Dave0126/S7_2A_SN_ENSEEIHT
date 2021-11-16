type zero = private Dummy1
type _ succ = private Dummy2
type nil = private Dummy3
type 'a list = Nil | Cons of 'a * 'a list

type ('a, 'n) nlist =   Nil : ('a, zero) nlist (** Nil : (type of return) *)
                      | Cons : 'a * ('a, 'n) nlist -> ('a, 'n succ) nlist

(** (int, zero succ succ) nlist *)

(** map:('a −> 'b) −> ('a,'n) nlist −> ('b, 'n) nlist *)
(** n non uniforme in (type n.) *)   
                  
let rec map : type n. ('a -> 'b) -> ('a, n) nlist -> ('b, n) nlist = 
  fun f nlist ->  match nlist with
    | Nil -> Nil
    | Cons (hd, tl) -> Cons (f hd, map f tl)      
    