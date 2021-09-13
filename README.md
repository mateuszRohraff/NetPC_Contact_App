Sposób Kompilacji:

Apliakcje uruchamiamy poprzez uruchomienie głównej klasy Spring Boot
"NetPcContactManagerApplication".

Aplikacja domyślnie uruchamia się na porcie 8080. 
Główny endpoint: /home

Przejście na jakikolwiek inny endpoint spowoduje przekierowanie na endpoint /login.

Aplikacja korzysta z bazy danych H2 w konfiguracji "inMemory", co oznacza, że po restarcie 
dane apliakcji zostaną utracone.

Apliakcja przy uruchomieniu nie tworzy przykładowego użytkownika. W celu przetestownia 
pełnej funkcjonalności należy stworzyć nowe konto.

