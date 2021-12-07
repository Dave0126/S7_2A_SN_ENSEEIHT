
module GreenThreads =
  struct
    (* à compléter/modifier *)
    type res = 
      | Yield of (unit -> res)
      | Fork of (unit -> res) * (unit -> res)
      | Done

      let p = new_prompt()

    let scheduler proc_init = assert false;;

    let yield () = Delimcc.shift p (fun k -> Yield (k))
    let fork proc = Delimcc.shift p (fun k -> Fork (k, p))
    let exit () = Delimcc.shift p (fun k -> Done)
  end

module type Channel =
  sig
    val create : unit -> ('a -> unit) * (unit -> 'a)
  end

module GTChannel : Channel =
  struct
    (* à compléter/modifier *)
    let create () = assert false;;
  end
    
let sieve () =
  let rec filter reader =
    GreenThreads.(
      let v0 = reader () in
      if v0 = -1 then exit () else
      Format.printf "%d@." v0;
      yield ();
      let (writer', reader') = GTChannel.create () in
      fork (fun () -> filter reader');
      while true
      do
        let v = reader () in
        yield ();
        if v mod v0 <> 0 then writer' v;
        if v = -1 then exit ()
      done
    ) in
  let main () =
    GreenThreads.(
      let (writer, reader) = GTChannel.create () in
      fork (fun () -> filter reader);
      for i = 2 to 1000
      do
        writer i;
        yield ()
      done;
      writer (-1);
      exit ()
    ) in
  GreenThreads.scheduler main;;
