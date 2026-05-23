package net.dante;

/*Klass som innehåller programmets huvudmeny*/

public class StartMenu { 

    LibraryManager manager;

    public StartMenu(LibraryManager manager) {
        this.manager = manager;
    }

    public void ShowMenu() {

        IO.println("""
                        -----Biblioketsystem-----
                        1. Lista bibliotekets böcker och magasin
                        2. Lista alla användare samt avstängda användare
                        3. Registrera nytt föremål i systemet
                        4. Registrera ny användare i systemet
                        5. Stäng av användare i systemet
                        6. Avsluta programmet
                    """);

            switch (IO.readln()) {
                case "1" -> manager.listLibraryItems();
                case "2" -> manager.listAllUsers();
                case "3" -> manager.addLibraryItem();
                case "4" -> manager.addUser();
                case "5" -> manager.suspendUser();
                case "6" -> {
                    IO.println("Avslutar programmet...");
                    System.exit(0);
                }
            }
    }    
}
