type zero = private Dummy1
type _ succ = private Dummy2
type nil = private Dummy3
type 'a list = Nil | Cons of 'a * 'a list

type ('a, 'n) nlist =   Nil : ('a, zero) nlist (** Nil : (type of return) *)
                      | Cons : 'a * ('a, 'n) nlist -> ('a, 'n succ) nlist

(** (int, zero succ succ) nlist *)

(** map : ('a −> 'b) −> ('a,'n) nlist −> ('b, 'n) nlist *)
(** n non uniforme in (type n.) *)         
let rec map_n : type n. ('a -> 'b) -> ('a, n) nlist -> ('b, n) nlist = 
  fun f nlist ->  match nlist with
    | Nil -> Nil
    | Cons (hd, tl) -> Cons (f hd, map_n f tl)      

(** snoc_n : ('a, n) nlist -> 'a -> ('a, n succ) nlist *)    
let rec snoc_n : type n. ('a, n) nlist -> 'a -> ('a, n succ) nlist =
  fun nlist e -> match nlist with
    | Nil -> Cons (e, Nil)
    | Cons (hd, tl) -> Cons (hd, snoc_n tl e) 

(** tail_n : type n. ('a, n) nlist -> 'a option *)
let rec tail_n : type n. ('a, n) nlist -> 'a option =
fun nlist -> match nlist with 
  | Nil -> None
  | Cons (hd, Nil) -> Some hd
  | Cons (_, tl) -> tail_n tl

(** rev_n : ('a ,'n) nlist −> ('a,'n) nlist *)
let rec rev_n : type n. ('a, n) nlist -> ('a, n) nlist =
fun nlist -> match nlist with 
  | Nil -> Nil
  | Cons (hd, tl) -> snoc_n(rev_n tl) hd

(** EXERCICE 2 *)
let rec insert_n : type n. ('a, n) nlist -> 'a -> ('a, n succ) nlist =
  fun nlist e -> match nlist with
    | Nil -> Cons (e, Nil)
    | Cons (hd, tl) -> if hd < e then Cons (hd, insert_n tl e)
                                  else Cons (e, nlist)

let rec sort_by_insertion : type n. ('a, n) nlist -> ('a, n) nlist =
  fun nlist -> match nlist with
    | Nil -> Nil
    | Cons (hd, tl) -> insert_n (sort_by_insertion tl) hd                             