trim <- function (x) gsub("^\\s+|\\s+$", "", x)
pliki= list.files()
for(plik in pliki)
{
  a=readLines(file(plik, open="r"))
  a=a[grep(" T=L-TERM", a)]
  if(!(identical(a, character(0))))
  {
    a=strsplit(a, " ")
    a=unlist(lapply(a, "[[", 5))
    a=trim(unlist(lapply(strsplit(a, "\\|"), function(x) paste(x, collapse=" "))))
    writeLines(a, file(paste(c(plik, ".clean.txt"), collapse = "")))
  }
}