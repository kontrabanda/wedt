# wedt

Oprogramowanie służące do porównia algorytmów wykrywanie encji złożony
na stronach domowych konferecji z algorytmem heurystycznym.

## Opis instalacji

Aby zainstalować aplikację należy:
* ściągnąć kod źródłowy,
* umieścić dodatkową bibliotekę eduentitymine-proc_2.11-0.1-SNAPSHOT-one-jar.jar w folderze lib,
* umieścić dane konferencji w folderze data/pagestorage_anno

## Opis budowania

Do zbudowania projektu niezbędne jest narzędzie ant.
Z wykorzystaniem narzędzia można wykonać następujące komendy:
* ant compile (bądź sam ant) - kompilacja i zbudowanie folderu target,
* ant run - uruchomienie prgramu,
* ant clean - czyszczenie projektu.

## Opis działania

Na razie zaimplementowana została prosta obsługa algorytmu heurystycznego.
Wyniki działania algorytmu umieszczane są w folderze data/featurestorage (tworzonego w trakcie działania programu).
