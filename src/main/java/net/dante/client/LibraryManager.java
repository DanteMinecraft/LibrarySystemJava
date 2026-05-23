package net.dante.client;

/*Klass som innehåller all logik i hur programmet fungerar.
Innehåller metoder som hämtar data, listar data, och lägger till data i systemet. */

import java.util.ArrayList;
import java.util.Collections;

import net.dante.items.BookItem;
import net.dante.items.LibraryItem;
import net.dante.items.MagazineItem;
import net.dante.users.SuspendedUser;
import net.dante.users.User;

public class LibraryManager {

    private ArrayList<BookItem> books;
    private ArrayList<MagazineItem> magazines;
    private ArrayList<User> users;
    private ArrayList<SuspendedUser> suspendedUsers;

    GsonHandler gsonHandler;

    public LibraryManager(GsonHandler gsonHandler) {
        this.gsonHandler = gsonHandler;
        refreshData();
    }

    // ========
    // REFRESH
    // ========

    public void refreshData() {
        this.books = gsonHandler.fetchBooks();
        this.magazines = gsonHandler.fetchMagazines();
        this.users = gsonHandler.fetchUsers();
        this.suspendedUsers = gsonHandler.fetchSuspendedUsers();
    }

    // ================
    // LISTING METHODS
    // ================

     // print for every item in arrays (beautiful lambda expressions :D)

    public void listLibraryItems() {

        Collections.sort(books);
        Collections.sort(magazines);

        IO.println("\nBöcker:");
        books.forEach(b -> IO.println(b));

        IO.println("\nMagasin:");
        magazines.forEach(m -> IO.println(m));
    }

    public void listAllUsers() {

        Collections.sort(users);

        IO.println("\nAnvändare:");
        users.forEach(u -> IO.println(u));

        IO.println("\nAvstängda användare:");
        suspendedUsers.forEach(su -> IO.println(su));
    }

    // ============
    // ADD METHODS
    // ============

    // TODO: Add checks so program doesn't crash when invalid inputs
    public void addLibraryItem() {

        // Boolean for type of item. if true = book, if false = magazine.
        boolean itemType;

        while (true) {

            IO.println("""
                        Vilken typ av föremål vill du lägga till i systemet?
                        1. Bok
                        2. Magasin
                    """);

            String userChoice = IO.readln();

            // Check if book or magazine, if not valid input ask again
            if (userChoice.equals("1")) {
                itemType = true;
                IO.println("Du har valt att lägga till en bok i systemet. Vänligen ange följande information:");
                break;

            } else if (userChoice.equals("2")) {
                itemType = false;
                IO.println("Du har valt att lägga till ett magasin i systemet. Vänligen ange följande information:");
                break;

            } else {
                IO.println("Ogiltigt val, försök igen.");
            }
        }

        if (itemType == true) {

            // Logic for adding book

            IO.println("Titel på bok: ");
            String newBookTitle = IO.readln();

            IO.println("Författare: ");
            String newBookAuthor = IO.readln();

            IO.println("Genre: ");
            String newBookGenre = IO.readln();

            IO.println("Antal sidor: ");
            int newBookPages = Integer.parseInt(IO.readln());

            BookItem newBook = new BookItem(newBookTitle, true, newBookAuthor, newBookGenre,
                    newBookPages);
            books.add(newBook); //local
            gsonHandler.uploadBook(newBook); // upload to server

        } else if (itemType == false) {

            // Logic for adding magazine

            IO.println("Titel på magasin: ");
            String newMagazineTitle = IO.readln();

            IO.println("Utgåva: ");
            int newMagazineIssueNumber = Integer.parseInt(IO.readln());

            IO.println("Publiceringsår: ");
            int newMagazinePublicationYear = Integer.parseInt(IO.readln());

            IO.println("Kategori: ");
            String newMagazineCategory = IO.readln();

            MagazineItem newMagazine = new MagazineItem(newMagazineTitle, true, newMagazineIssueNumber,
                    newMagazinePublicationYear, newMagazineCategory);
            magazines.add(newMagazine); // local
            gsonHandler.uploadMagazine(newMagazine); // upload to server

        }
    }

    public void addUser() {

        IO.println("Användarnamn: ");
        String newUserName = IO.readln();

        IO.println("Användarens mejladress: ");
        String newUserEmail = IO.readln();

        User newUser = new User(newUserName, newUserEmail);
        users.add(newUser); // local
        gsonHandler.uploadUser(newUser); // upload to server
    }

    public void suspendUser() {

        IO.println("Email på användaren som ska stängas av: ");
        String emailForSuspended = IO.readln();

        User foundUser = users.stream()
            .filter(u -> u.getUserEmail().equals(emailForSuspended))
            .findFirst()
            .orElse(null);

        if(foundUser == null) {
            IO.println("Ingen användare med den mejladressen hittades.");
            return;
        }

        IO.println("Anledning: ");
        String newSuspendedUserReason = IO.readln();

        SuspendedUser newSuspendedUser = new SuspendedUser(foundUser.getUserId(), newSuspendedUserReason);
        suspendedUsers.add(newSuspendedUser);
        gsonHandler.uploadSuspendedUser(newSuspendedUser);
    }

    // ==============
    // SEARCH METHODS
    // ==============

    //items
    
    public void searchItem() {
        IO.println("Titel på föremålet du söker: ");
        String searchedItem = IO.readln();

        ArrayList<LibraryItem> items = new ArrayList<>();
        items.addAll(books);
        items.addAll(magazines);

        items.stream().filter(i -> i.getTitle().contains(searchedItem)).forEach(i -> IO.println("\n" + i)); //print for all found items
    }

    //users

    public void searchUser() {
        IO.println("Email till användaren du söker: ");
        String searchedEmail = IO.readln();

        users.stream().filter(u -> u.getUserEmail().contains(searchedEmail)).forEach(u -> 
            {
                IO.print(u);
                suspendedUsers.stream().filter(su -> su.getUserId().equals(u.getUserId())).forEach(su -> IO.print("STATUS: Användaren får ej låna någonting just nu då hen är avstängd i systemet med orsaken: " + su.getReason() + "\n\n")); //print for all found items
            }
        );
    }

    // ===========
    // EXTERMINATE
    // ===========

    //items

    public void deleteLibraryItem() {

        // Boolean for type of item. if true = book, if false = magazine.
        boolean itemType;

        while (true) {

            IO.println("""
                        Vilken typ av föremål vill du ta bort från systemet?
                        1. Bok
                        2. Magasin
                    """);

            String userChoice = IO.readln();

            // Check if book or magazine, if not valid input ask again
            if (userChoice.equals("1")) {
                itemType = true;
                IO.println("Du har valt att ta bort en bok i systemet. Vänligen ange följande information:");
                break;

            } else if (userChoice.equals("2")) {
                itemType = false;
                IO.println("Du har valt att ta bort ett magasin i systemet. Vänligen ange följande information:");
                break;

            } else {
                IO.println("Ogiltigt val, försök igen.");
            }
        }

        if (itemType == true) {

            // Logic for removing book

            IO.println("Titel på boken du vill ta bort: ");
            String searchedBook = IO.readln();

            BookItem foundBook = books.stream()
            .filter(i -> i.getTitle().equals(searchedBook))
            .findFirst()
            .orElse(null);

            if(foundBook == null) {
                IO.println("Ingen bok med den titeln hittades.");
                return;
            }

            gsonHandler.deleteBook(foundBook.getId());
        
            IO.println("Boken " + foundBook.getTitle() + " togs bort.");

            books.remove(foundBook);

        }
        
        else if (itemType == false) {

            // Logic for removing magazine

            IO.println("Titel på magasinet du vill ta bort: ");
            String searchedMagazine = IO.readln();

            MagazineItem foundMagazine = magazines.stream()
            .filter(i -> i.getTitle().equals(searchedMagazine))
            .findFirst()
            .orElse(null);

            if(foundMagazine == null) {
                IO.println("Inget magasin med den titeln hittades.");
                return;
            }

            gsonHandler.deleteMagazine(foundMagazine.getId());
        
            IO.println("Magasinet " + foundMagazine.getTitle() + " togs bort.");

            magazines.remove(foundMagazine);

        }
    }

    //users

    public void deleteUser() {
        IO.println("Email till användaren du vill ta bort: ");
        String searchedEmail = IO.readln();

        User foundUser = users.stream()
            .filter(u -> u.getUserEmail().equals(searchedEmail))
            .findFirst()
            .orElse(null);

        if(foundUser == null) {
            IO.println("Ingen användare med den mejladressen hittades.");
            return;
        }

        SuspendedUser foundSuspendedUser = suspendedUsers.stream()
            .filter(su -> su.getUserId().equals(foundUser.getUserId()))
            .findFirst()
            .orElse(null);

        if(foundSuspendedUser != null) {
            gsonHandler.deleteSuspendedUser(foundSuspendedUser.getSuspendedId());
        }

        gsonHandler.deleteUser(foundUser.getUserId());
        
        IO.println("Användaren " + foundUser.getUserName() + " togs bort.");

        users.remove(foundUser);
        suspendedUsers.remove(foundSuspendedUser);
    }
}