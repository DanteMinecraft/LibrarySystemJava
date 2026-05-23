package net.dante.client;

/*Klass som innehåller programmets huvudmeny*/

public class StartMenu { 

    LibraryManager manager;

    public StartMenu(LibraryManager manager) {
        this.manager = manager;
    }

    /** 
     * @param (IO.readln()
     */
    public void ShowMenu() { //TODO: Gör att man kan välja att lista endast en sorts föremål i submeny

        IO.println("""
                        -----Biblioketsystem-----
                        1. Lista alla föremål
                        2. Lista alla användare samt avstängda användare
                        3. Registrera nytt föremål
                        4. Registrera ny användare
                        5. Stäng av användare
                        6. Sök efter föremål
                        7. Sök efter användare och deras status
                        8. Ta bort föremål
                        9. Ta bort användare
                        10. Låna föremål
                        11. Lämna tillbaka föremål
                        12. Avsluta programmet
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
                case "8" -> manager.deleteLibraryItem();
                case "9" -> manager.deleteUser();
                case "10" -> manager.borrowItem();
                case "11" -> manager.returnItem();
                case "12" -> {
                    IO.println("Avslutar programmet...");
                    System.exit(0);
                }
            }
    }    
}