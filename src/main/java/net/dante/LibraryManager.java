package net.dante;

import java.util.ArrayList;

import com.google.gson.Gson;

import net.dante.items.BookItem;
import net.dante.items.MagazineItem;

public class LibraryManager {
    // Variabels
    
    private ArrayList<BookItem> bookItems;
    private ArrayList<MagazineItem> magazineItems;
    //private ArrayList<User> users;
    //private ArrayList<SuspendedUser> suspendedUser;

    private Gson gson = new Gson();


    // Server URL handling
    private String serverUrl;

    public LibraryManager(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    // Method for fetching magazines
    public void fetchBooks() {

    }

    // Method for fetching magazines
    public void fetchMagazines() {

    }

    // Method for adding item to library
    public void addLibraryItem(LibraryItem libItem) {

    }
    
}
