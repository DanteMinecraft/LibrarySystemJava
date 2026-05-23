package net.dante.client;

/*Klass som innehåller all logik i hur programmet fungerar.
Innehåller metoder som hämtar data, listar data, och lägger till data i systemet. */

import java.util.ArrayList;
import java.util.Collections;

import net.dante.items.LibraryItem;
import net.dante.items.MediaItem;
import net.dante.items.specific.BookItem;
import net.dante.items.specific.MagazineItem;
import net.dante.users.SuspendedUser;
import net.dante.users.User;

public class LibraryManager {

    private ArrayList<BookItem> books;
    private ArrayList<MagazineItem> magazines;
    private ArrayList<MediaItem> media;
    private ArrayList<LibraryItem> allItems;

    private ArrayList<User> users;
    private ArrayList<SuspendedUser> suspendedUsers;

    private ArrayList<Loans> loans;


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
        this.media = gsonHandler.fetchMedia();

        this.users = gsonHandler.fetchUsers();
        this.suspendedUsers = gsonHandler.fetchSuspendedUsers();

        // new list (polymorphism)
        this.allItems = new ArrayList<>();

        allItems.addAll(books);
        allItems.addAll(magazines);
        allItems.addAll(media);
    }

    // ================
    // LISTING METHODS
    // ================

    // print for every item in arrays (beautiful lambda expressions :D)

    public void listLibraryItems() {

        Collections.sort(allItems);

        IO.println("\nAlla föremål:");

        allItems.forEach(i -> IO.println(i));
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

    public void addLibraryItem() {
        String itemType = null;

        while (true) {
            IO.println("""
                    Vilken typ av föremål vill du lägga till i systemet?
                    1. Bok
                    2. Magasin
                    3. Film
                    4. Spel
                    5  Album
                    """);

            String userChoice = IO.readln();

            switch (userChoice) {
                case "1" -> {
                    itemType = "book";
                    IO.println("Du har valt att lägga till en bok.");
                    break;
                }
                case "2" -> {
                    itemType = "magazine";
                    IO.println("Du har valt att lägga till ett magasin.");
                    break;
                }
                case "3" -> {
                    itemType = "movie";
                    IO.println("Du har valt att lägga till en film.");
                    break;
                }
                case "4" -> {
                    itemType = "game";
                    IO.println("Du har valt att lägga till ett spel.");
                    break;
                }
                case "5" -> {
                    itemType = "album";
                    IO.println("Du har valt att lägga till ett album.");
                    break;
                }

                default -> {
                    IO.println("Ogiltigt val, försök igen.");
                }
            }
            break;
        }

        if (itemType.equals("book")) {

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
            books.add(newBook); // local book list
            allItems.add(newBook); // local list for all items
            gsonHandler.uploadBook(newBook); // upload to server

        } else if (itemType.equals("magazine")) {

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
            magazines.add(newMagazine); // local magazine list
            allItems.add(newMagazine); // local list for all items
            gsonHandler.uploadMagazine(newMagazine); // upload to server

        } else if (itemType.equals("movie")) {

            IO.println("Titel på film: ");
            String newMovieTitle = IO.readln();
            IO.println("Genre: ");
            String newMovieGenre = IO.readln();
            IO.println("Längd i minuter: ");
            int newMovieLength = Integer.parseInt(IO.readln());
            IO.println("Åldergräns på film: ");
            int newMovieAgeLimit = Integer.parseInt(IO.readln());

            MediaItem newMovie = new MediaItem(newMovieTitle, true, itemType, newMovieGenre, null, newMovieAgeLimit,
                    newMovieLength);

            media.add(newMovie);
            allItems.add(newMovie);
            gsonHandler.uploadMedia(newMovie);

        } else if (itemType.equals("game")) {

            IO.println("Namn på spel: ");
            String newGameTitle = IO.readln();
            IO.println("Genre: ");
            String newGameGenre = IO.readln();
            IO.println("Åldergräns på spelet: ");
            int newGameAgeLimit = Integer.parseInt(IO.readln());

            MediaItem newGame = new MediaItem(newGameTitle, true, itemType, newGameGenre, null, newGameAgeLimit, 0);

            media.add(newGame);
            allItems.add(newGame);
            gsonHandler.uploadMedia(newGame);

        } else if (itemType.equals("album")) {

            IO.println("Namn på album: ");
            String newAlbumTitle = IO.readln();
            IO.println("Artist: ");
            String newAlbumArtist = IO.readln();

            MediaItem newAlbum = new MediaItem(newAlbumTitle, true, itemType, null, newAlbumArtist, 0, 0);

            media.add(newAlbum);
            allItems.add(newAlbum);
            gsonHandler.uploadMedia(newAlbum);

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

        if (foundUser == null) {
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

    // items

    public void searchItem() {
        IO.println("Titel på föremålet du söker: ");
        String searchedItem = IO.readln();

        allItems.stream().filter(i -> i.getTitle().contains(searchedItem)).forEach(i -> IO.println("\n" + i)); // print
    }

    // users

    public void searchUser() {
        IO.println("Email till användaren du söker: ");
        String searchedEmail = IO.readln();

        users.stream().filter(u -> u.getUserEmail().contains(searchedEmail)).forEach(u -> {
            IO.print(u);
            suspendedUsers.stream().filter(su -> su.getUserId().equals(u.getUserId())).forEach(su -> IO.print(
                    "STATUS: Användaren får ej låna någonting just nu då hen är avstängd i systemet med orsaken: "
                            + su.getReason() + "\n\n")); // print for all found items
        });
    }

    // ===========
    // EXTERMINATE
    // ===========

    // items

    public void deleteLibraryItem() {

        String itemType = null;

        while (true) {

            IO.println("""
                    Vilken typ av föremål vill du ta bort från systemet?
                    1. Bok
                    2. Magasin
                    3. Film
                    4. Spel
                    5. Album
                    """);

            String userChoice = IO.readln();

            switch (userChoice) {

                case "1" -> {
                    itemType = "book";
                    IO.println("Du har valt att ta bort en bok.");
                    break;
                }

                case "2" -> {
                    itemType = "magazine";
                    IO.println("Du har valt att ta bort ett magasin.");
                    break;
                }

                case "3" -> {
                    itemType = "movie";
                    IO.println("Du har valt att ta bort en film.");
                    break;
                }

                case "4" -> {
                    itemType = "game";
                    IO.println("Du har valt att ta bort ett spel.");
                    break;
                }

                case "5" -> {
                    itemType = "album";
                    IO.println("Du har valt att ta bort ett album.");
                    break;
                }

                default -> {
                    IO.println("Ogiltigt val, försök igen.");
                }
            }

            break;
        }
        
        if (itemType.equals("book")) {

            IO.println("Titel på boken du vill ta bort: ");
            String searchedBook = IO.readln();

            BookItem foundBook = books.stream()
                    .filter(i -> i.getTitle().equals(searchedBook))
                    .findFirst()
                    .orElse(null);

            if (foundBook == null) {
                IO.println("Ingen bok med den titeln hittades.");
                return;
            }

            gsonHandler.deleteBook(foundBook.getId());
            books.remove(foundBook);
            allItems.remove(foundBook);

            IO.println("Boken " + foundBook.getTitle() + " togs bort.");

        } else if (itemType.equals("magazine")) {

            IO.println("Titel på magasinet du vill ta bort: ");
            String searchedMagazine = IO.readln();

            MagazineItem foundMagazine = magazines.stream()
                    .filter(i -> i.getTitle().equals(searchedMagazine))
                    .findFirst()
                    .orElse(null);

            if (foundMagazine == null) {
                IO.println("Inget magasin med den titeln hittades.");
                return;
            }

            gsonHandler.deleteMagazine(foundMagazine.getId());
            magazines.remove(foundMagazine);
            allItems.remove(foundMagazine);

            IO.println("Magasinet " + foundMagazine.getTitle() + " togs bort.");

        } else if (itemType.equals("movie")) {

            IO.println("Titel på filmen du vill ta bort: ");
            String searchedMovie = IO.readln();

            MediaItem foundMovie = media.stream()
                    .filter(i -> i.getType().equals("movie"))
                    .filter(i -> i.getTitle().equals(searchedMovie))
                    .findFirst()
                    .orElse(null);

            if (foundMovie == null) {
                IO.println("Ingen film med den titeln hittades.");
                return;
            }

            gsonHandler.deleteMedia(foundMovie.getId());
            media.remove(foundMovie);
            allItems.remove(foundMovie);

            IO.println("Filmen " + foundMovie.getTitle() + " togs bort.");

        } else if (itemType.equals("game")) {

            IO.println("Titel på spelet du vill ta bort: ");
            String searchedGame = IO.readln();

            MediaItem foundGame = media.stream()
                    .filter(i -> i.getType().equals("game"))
                    .filter(i -> i.getTitle().equals(searchedGame))
                    .findFirst()
                    .orElse(null);

            if (foundGame == null) {
                IO.println("Inget spel med den titeln hittades.");
                return;
            }

            gsonHandler.deleteMedia(foundGame.getId());

            media.remove(foundGame);
            allItems.remove(foundGame);

            IO.println("Spelet " + foundGame.getTitle() + " togs bort.");

        } else if (itemType.equals("album")) {

            IO.println("Titel på albumet du vill ta bort: ");
            String searchedAlbum = IO.readln();

            MediaItem foundAlbum = media.stream()
                    .filter(i -> i.getType().equals("album"))
                    .filter(i -> i.getTitle().equals(searchedAlbum))
                    .findFirst()
                    .orElse(null);

            if (foundAlbum == null) {
                IO.println("Inget album med den titeln hittades.");
                return;
            }

            gsonHandler.deleteMedia(foundAlbum.getId());
            media.remove(foundAlbum);
            allItems.remove(foundAlbum);

            IO.println("Albumet " + foundAlbum.getTitle() + " togs bort.");
        }
    }

    // users

    public void deleteUser() {
        IO.println("Email till användaren du vill ta bort: ");
        String searchedEmail = IO.readln();

        User foundUser = users.stream()
                .filter(u -> u.getUserEmail().equals(searchedEmail))
                .findFirst()
                .orElse(null);

        if (foundUser == null) {
            IO.println("Ingen användare med den mejladressen hittades.");
            return;
        }

        SuspendedUser foundSuspendedUser = suspendedUsers.stream()
                .filter(su -> su.getUserId().equals(foundUser.getUserId()))
                .findFirst()
                .orElse(null);

        if (foundSuspendedUser != null) {
            gsonHandler.deleteSuspendedUser(foundSuspendedUser.getSuspendedId());
        }

        gsonHandler.deleteUser(foundUser.getUserId());

        IO.println("Användaren " + foundUser.getUserName() + " togs bort.");

        users.remove(foundUser);
        suspendedUsers.remove(foundSuspendedUser);
    }
}