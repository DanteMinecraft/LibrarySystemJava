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
                        3. Registrera nytt föremål
                        4. Registrera ny användare
                        5. Stäng av användare
                        6. Sök efter föremål
                        7. Sök efter användare
                        8. Avsluta programmet
                    """);
            manager.refreshData();
            switch (IO.readln()) {
                case "1" -> manager.listLibraryItems();
                case "2" -> manager.listAllUsers();
                case "3" -> manager.addLibraryItem();
                case "4" -> manager.addUser();
                case "5" -> manager.suspendUser();
                case "6" -> manager.searchItem();
                case "7" -> manager.searchUser();
                case "8" -> {
                    IO.println("Avslutar programmet...");
                    System.exit(0);
                }
            }
    }    
}
