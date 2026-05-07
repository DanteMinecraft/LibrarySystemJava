package net.dante;

public class Main {
    void main() {

        LibraryManager Manager = new  LibraryManager();

        while (true) { 

            IO.println("""
            -----Biblioketsystem-----
            1. Hämta data för samtliga böcker
            2. Hämta data för samtliga magasin
            3. Lista bibliotekets böcker och magasin
            4. Registrera ny bok i systemet
            5. Registrera nytt magasin i systemet
            6. Avsluta programmet
        """);

        switch (IO.readln()) {
            case 1 -> 
            }
            
        }
    }
}