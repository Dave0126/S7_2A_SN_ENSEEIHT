open Util
open Mem

(*  get_assoc: cette fonction retourne la valeur associée à la clef e dans la liste l, ou la valeur fournie def si la clef n’existe pas.
    signature: get_assoc: int -> (int * char) list -> char -> char 
    parametre:  e: <int> key words 
                l: <(int * char) list> list 
                def: <char> new definiton if key word e do not existe
    resultat:   <char> la valeur associée à la clef e dans la liste l, ou la valeur fournie def si la clef n’existe pas
 *)
let rec get_assoc e l def = match l with
    | [] -> def
    | (add,data) :: tl -> if e = add then data 
                        else get_assoc e tl def


(* Tests unitaires :  *)
let%test _ =  get_assoc 0 [(0,'D');(1,'G');(2,'H')] 'P' = 'D'
let%test _ =  get_assoc 4 [(0,'D');(1,'G');(2,'H')] 'P' = 'P'

(*  set_assoc : cette fonction remplace la valeur associée à la clef e dans la liste l par x, ou ajoute le couple (e, x) si la clef n’existe pas déjà.
    signature: set_assoc: int -> (int * char) list -> char -> (int * char) list 
    parametre:  e: <int>    key words 
                l: <(int * char) list>  list 
                x: <char>   new definiton if key word e do not existe
    resultat:   <(int * char) list>     la valeur associée à la clef e dans la liste l par x, ou ajoute le couple (e, x) si la clef n’existe pas déjà.
 *)
let rec set_assoc e l x = match l with
    | [] -> (e,x)::l
    | (add,data) :: tl -> if e = add then (add,x)::l 
                    else l @ (set_assoc e tl x)

(* Tests unitaires :  *)
let%test _ = set_assoc 0 [(0,'D');(1,'G');(2,'H')] 'P' = [(0, 'P'); (0, 'D'); (1, 'G'); (2, 'H')]
let%test _ = set_assoc 3 [(0,'D');(1,'G');(2,'H')] 'P' = [(0, 'D'); (1, 'G'); (2, 'H'); (1, 'G'); (2, 'H'); (2, 'H'); (3, 'P')]

module AssocMemory : Memory =
struct
    (* Type = liste qui associe des adresses (entiers) à des valeurs (caractères) *)
    type mem_type = (int * char) list

    (* Un type qui contient la mémoire + la taille de son bus d'adressage *)
    type mem = int * mem_type

    (* Nom de l'implémentation *)
    let name = "assoc"

    (* Taille du bus d'adressage *)
    let bussize (bs, _) = bs

    (* Taille maximale de la mémoire *)
    let size (bs, _) = pow2 bs

    (* Taille de la mémoirse en mémoire *)
    let allocsize (bs, m) = pow2 (List.length(m))

    
    (* Nombre de cases utilisées *)
    let rec busyness (bs, m) = failwith "TODO"
    (*
        (match m with
    | [] -> 0
    | (_,data)::m_tl -> if data = 0 then (busyness (bs,m_tl))
                        else 1 + (busyness (bs,m_tl))
    *)
                        

    (* Construire une mémoire vide *)
    let clear bs = failwith "TODO"

    (* Lire une valeur *)
    let read (bs, m) addr = get_assoc addr m 'E'

    (* Écrire une valeur *)
    let write (bs, m) addr x = set_assoc addr m x
end
