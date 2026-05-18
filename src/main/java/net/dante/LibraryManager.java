package net.dante;

/*Klass som innehåller all logik i hur programmet fungerar.
Innehåller metoder som hämtar data, listar data, och lägger till data i systemet. */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import net.dante.items.BookItem;
import net.dante.items.MagazineItem;

public class LibraryManager {
    // Variabels

    private ArrayList<BookItem> books = new ArrayList<>();
    private ArrayList<MagazineItem> magazines = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    // private ArrayList<SuspendedUser> suspendedUser = new ArrayList<>();

    private Gson gson = new Gson();

    // Server URL handling
    private String serverUrl;

    public LibraryManager(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    // 1. Method for fetching books
    public void fetchBooks() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/books").asString();
            String responseBody = response.getBody();
            List<BookItem> fetchedBooks = gson.fromJson(responseBody, new TypeToken<List<BookItem>>() {
            }.getType());
            books.addAll(fetchedBooks); // adds all the books from the fetch
            IO.println("Hämtade data för böcker\n");
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
        }
    }

    // 2. Method for fetching magazines
    public void fetchMagazines() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/magazines").asString();
            String responseBody = response.getBody();
            List<MagazineItem> fetchedMagazines = gson.fromJson(responseBody, new TypeToken<List<MagazineItem>>() {
            }.getType());
            magazines.addAll(fetchedMagazines);
            IO.println("Hämtade data för magasin\n");
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
        }
    }

    // 3. List all books & magazines
    public void listLibraryItems() {

        // print for every book and magazine in array (beautiful lambda expression :D)
        IO.println("\nBöcker:");
        books.forEach(b -> IO.println(b));
        IO.println("\nMagasin:");
        magazines.forEach(m -> IO.println(m));
    }

    // 4. Method for adding item to library
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

            // String for user choice
            String userChoice = IO.readln();

            // Check if book or magazine, if not valid input ask again
            if (userChoice.equals("1")) {
                itemType = true;
                IO.println("Du valde att lägga till en bok i systemet. Vänligen ange följande information:");
                break;

            } else if (userChoice.equals("2")) {
                itemType = false;
                IO.println("Du valde att lägga till ett magasin i systemet. Vänligen ange följande information:");
                break;

            } else {
                IO.println("Ogiltigt val, försök igen.");
            }
        }

        // TODO: Move add logic to own class

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

            String newBookId = String.valueOf(books.size() + 1); // TODO: check if fetched prior to creating id

            BookItem newBook = new BookItem(newBookId, newBookTitle, true, newBookAuthor, newBookGenre,
                    newBookPages);
            books.add(newBook);

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

            String newMagazineId = String.valueOf(magazines.size() + 1); // TODO: check if fetched prior to creating id

            MagazineItem newMagazine = new MagazineItem(newMagazineId, newMagazineTitle, true, newMagazineIssueNumber,
                    newMagazinePublicationYear, newMagazineCategory);
            magazines.add(newMagazine);

        }
    }

    // 5. Method for adding user to system
    public void addUser() {

        IO.println("Användarnamn: ");
        String newUserName = IO.readln();

        IO.println("Användarens mejladress: ");
        String newUserEmail = IO.readln();

        String newUserId = String.valueOf(users.size() + 1); // TODO: check if fetched prior to creating id

        User newUser = new User(newUserId, newUserName, newUserEmail);
        users.add(newUser);
    }

    // Method for removing item from library
    public void removeLibraryItem() {

    }

}
