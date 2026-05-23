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

    public void ShowMenu() {
        IO.println("""
                -----Biblioketsystem-----
                    1. Lista alla föremål
                    2. Hantera användare
                    3. Registrera nytt föremål
                    4. Sök efter föremål
                    5. Ta bort föremål
                    6. Utlåning
                    7. Statistik
                    8. Avsluta programmet
                """);
        manager.refreshData();
            switch (IO.readln()) {
                case "1" -> manager.listLibraryItems();
                case "2" -> ShowUserMenu();
                case "3" -> manager.addLibraryItem();
                case "4" -> manager.searchItem();
                case "5" -> manager.deleteLibraryItem();
                case "6" -> ShowLoanMenu();
                case "7" -> ShowStatisticsMenu();
                case "8" -> {
                    IO.println("Avslutar programmet...");
                    System.exit(0);
                }
            }
    }

    public void ShowUserMenu() {
        IO.println("""
                -----Användarhantering-----
                    1. Lista alla användare
                    2. Registrera ny användare
                    3. Sök efter användare
                    4. Stäng av användare
                    5. Ta bort användare
                    6. Tillbaka
                """);
        switch (IO.readln()) {
            case "1" -> manager.listAllUsers();
            case "2" -> manager.addUser();
            case "3" -> manager.searchUser();
            case "4" -> manager.suspendUser();
            case "5" -> manager.deleteUser();
            case "6" -> ShowMenu();
        }
    }

    public void ShowLoanMenu() {
        IO.println("""
                -----Utlåning-----
                    1. Låna föremål
                    2. Lämna tillbaka föremål
                    3. Tillbaka
                """);
        switch (IO.readln()) {
            case "1" -> manager.borrowItem();
            case "2" -> manager.returnItem();
            case "3" -> ShowMenu();
        }
    }
    public void ShowStatisticsMenu() {
        IO.println("""
                -----Statistik-----
                    1. Lista alla föremål
                    2. Sortera böcker efter författare
                    3. Räkna andelen böcker av specifik författare
                    4. Tillbaka
                """);
        switch (IO.readln()) {
            case "1" -> manager.listAllItems();
            case "2" -> manager.sortBooksByAuthor();
            case "3" -> manager.countBooksByAuthor();
            case "4" -> ShowMenu();
        }
    }
}