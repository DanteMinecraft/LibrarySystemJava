package net.dante;

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
    // private ArrayList<User> users = new ArrayList<>();
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
            List<BookItem> fetchedBooks = gson.fromJson(responseBody, new TypeToken<List<BookItem>>(){}.getType());
            books.addAll(fetchedBooks);
            IO.println("Hämtade data för böcker");
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
            List<MagazineItem> fetchedMagazines = gson.fromJson(responseBody, new TypeToken<List<MagazineItem>>(){}.getType());
            magazines.addAll(fetchedMagazines);
            IO.println("Hämtade data för magasin\n");
        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
        }
    }

    // 3. List all books & magazines
    public void listLibraryItems() {
        
        // print for every book and magazine in array
        IO.println("Böcker:\n");
        books.forEach(b -> IO.println(b));
        IO.println("\n\nMagasin:\n");
        magazines.forEach(m -> IO.println(m));
    }

    // Method for adding item to library
    public void addLibraryItem(LibraryItem libItem) {

    }

    // Method for removing item from library
    public void removeLibraryItem(LibraryItem libItem) {

    }

}
