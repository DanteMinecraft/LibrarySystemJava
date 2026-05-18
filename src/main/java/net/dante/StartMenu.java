package net.dante;

/*Klass som innehåller programmets huvudmeny*/

public class StartMenu {

    LibraryManager manager = new LibraryManager("http://localhost:3000");

    public void ShowMenu() {
        IO.println("""
                        -----Biblioketsystem-----
                        1. Hämta data för samtliga böcker
                        2. Hämta data för samtliga magasin
                        3. Lista bibliotekets böcker och magasin
                        4. Registrera nytt föremål i systemet
                        5. Registrera ny användare i systemet
                        6. Registrera ny avstängd användare i systemet
                        7. Avsluta programmet
                    """);

            switch (IO.readln()) {
                case "1" -> manager.fetchBooks();
                case "2" -> manager.fetchMagazines();
                case "3" -> manager.listLibraryItems();
                case "4" -> manager.addLibraryItem();
                case "5" -> manager.addUser();
                case "6" -> IO.println("Placeholder för att registrera avstängd användare");
                case "7" -> {
                    IO.println("Avslutar programmet...");
                    System.exit(0);
                }
            }
    }    
}
