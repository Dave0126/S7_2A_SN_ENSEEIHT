
let _ =
  let input = open_in "123.txt" in
  let lexbuf = input_line(input) in
  let out = open_out ("out") in
  let outbuf = Format.formatter_of_out_channel out in
  Format.fprintf outbuf "@[%s@]" lexbuf;