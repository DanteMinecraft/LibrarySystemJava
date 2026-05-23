package net.dante.client;

/*
    HANTERAR ALLA GSON-HÄNDELSER I PROGRAMMET
*/

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import net.dante.items.MediaItem;
import net.dante.items.specific.BookItem;
import net.dante.items.specific.MagazineItem;
import net.dante.users.SuspendedUser;
import net.dante.users.User;

public class GsonHandler {

    private String serverUrl;
    private Gson gson = new Gson();

    public GsonHandler(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    // ===============
    // FETCH METHODS
    // ===============

    /**
     * @return ArrayList<Loans>
     */
    /** 
     * @return ArrayList<Loans>
     */
    public ArrayList<Loans> fetchLoans() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/loans").asString();
            String responseBody = response.getBody();

            ArrayList<Loans> fetchedLoans = gson.fromJson(responseBody, new TypeToken<ArrayList<Loans>>() {
            }.getType());

            // IO.println("Hämtade data för böcker\n");
            return fetchedLoans; // adds all the loans from the fetch

        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return new ArrayList<>();
        }
    }

    /**
     * @return ArrayList<BookItem>
     */
    /** 
     * @return ArrayList<BookItem>
     */
    public ArrayList<BookItem> fetchBooks() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/books").asString();
            String responseBody = response.getBody();

            ArrayList<BookItem> fetchedBooks = gson.fromJson(responseBody, new TypeToken<ArrayList<BookItem>>() {
            }.getType());

            // IO.println("Hämtade data för böcker\n");
            return fetchedBooks; // adds all the books from the fetch

        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return new ArrayList<>();
        }
    }

    /**
     * @return ArrayList<MagazineItem>
     */
    /** 
     * @return ArrayList<MagazineItem>
     */
    public ArrayList<MagazineItem> fetchMagazines() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/magazines").asString();
            String responseBody = response.getBody();

            ArrayList<MagazineItem> fetchedMagazines = gson.fromJson(responseBody,
                    new TypeToken<ArrayList<MagazineItem>>() {
                    }.getType());

            // IO.println("Hämtade data för magasin\n"); // adds all magazines from fetch
            return fetchedMagazines;

        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return new ArrayList<>();
        }
    }

    /** 
     * @return ArrayList<MediaItem>
     */
    public ArrayList<MediaItem> fetchMedia() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/media").asString();
            String responseBody = response.getBody();

            ArrayList<MediaItem> fetchedMedia = gson.fromJson(responseBody, new TypeToken<ArrayList<MediaItem>>() {
            }.getType());

            return fetchedMedia;

        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return new ArrayList<>();
        }
    }

    /**
     * @return ArrayList<User>
     */
    /** 
     * @return ArrayList<User>
     */
    public ArrayList<User> fetchUsers() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/users").asString();
            String responseBody = response.getBody();

            ArrayList<User> fetchedUsers = gson.fromJson(responseBody, new TypeToken<ArrayList<User>>() {
            }.getType());

            // IO.println("Hämtade data för users\n");
            return fetchedUsers;

        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return new ArrayList<>();
        }
    }

    /**
     * @return ArrayList<SuspendedUser>
     */
    /** 
     * @return ArrayList<SuspendedUser>
     */
    public ArrayList<SuspendedUser> fetchSuspendedUsers() {
        HttpResponse<String> response;
        try {
            response = Unirest.get(serverUrl + "/suspended").asString();
            String responseBody = response.getBody();

            ArrayList<SuspendedUser> fetchedSuspendedUsers = gson.fromJson(responseBody,
                    new TypeToken<ArrayList<SuspendedUser>>() {
                    }.getType());

            // IO.println("Hämtade data för suspended\n");
            return fetchedSuspendedUsers;

        } catch (UnirestException e) {
            IO.println("Ett fel uppstod vid hämtning av data: " + e.getLocalizedMessage() + "\n");
            return new ArrayList<>();
        }
    }

    // ===============
    // UPLOAD METHODS
    // ===============

    // helper for uploading stuff
    /**
     * @param endpoint
     * @param data
     */
    /** 
     * @param endpoint
     * @param data
     */
    private void toJson(String endpoint, Object data) {
        try {
            String json = gson.toJson(data);
            Unirest.post(serverUrl + endpoint)
                    .header("Content-type", "application/json")
                    .body(json)
                    .asString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // individual uploders

    /**
     * @param loans
     */
    /** 
     * @param loan
     */
    public void uploadLoan(Loans loan) {
        toJson("/loans", loan);
    }

    /**
     * @param book
     */
    /** 
     * @param book
     */
    public void uploadBook(BookItem book) {
        toJson("/books", book);
    }

    /**
     * @param magazine
     */
    /** 
     * @param magazine
     */
    public void uploadMagazine(MagazineItem magazine) {
        toJson("/magazines", magazine);
    }

    /**
     * @param media
     */
    /** 
     * @param media
     */
    public void uploadMedia(MediaItem media) {
        toJson("/media", media);
    }

    /**
     * @param user
     */
    /** 
     * @param user
     */
    public void uploadUser(User user) {
        toJson("/users", user);
    }

    /**
     * @param suspendedUser
     */
    /** 
     * @param suspendedUser
     */
    public void uploadSuspendedUser(SuspendedUser suspendedUser) {
        toJson("/suspended", suspendedUser);
    }

    // ===============
    // UPDATE METHODS
    // ===============

    /** 
     * @param book
     */
    public void updateBook(BookItem book) {
        try {

            String json = gson.toJson(book);
            Unirest.put(serverUrl + "/books/" + book.getId())
                    .header("Content-type", "application/json")
                    .body(json)
                    .asString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
     * @param magazine
     */
    public void updateMagazine(MagazineItem magazine) {
        try {

            String json = gson.toJson(magazine);
            Unirest.put(serverUrl + "/magazines/" + magazine.getId())
                    .header("Content-type", "application/json")
                    .body(json)
                    .asString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
     * @param media
     */
    public void updateMedia(MediaItem media) {
        try {

            String json = gson.toJson(media);
            Unirest.put(serverUrl + "/media/" + media.getId())
                    .header("Content-type", "application/json")
                    .body(json)
                    .asString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===============
    // DELETE METHODS
    // ===============

    // for loan

    /**
     * @param id
     */
    /** 
     * @param id
     */
    public void deleteLoan(String id) {
        try {
            Unirest.delete(serverUrl + "/loans/" + id).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // items

    /**
     * @param id
     */
    /** 
     * @param id
     */
    public void deleteBook(String id) {
        try {
            Unirest.delete(serverUrl + "/books/" + id).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     */
    /** 
     * @param id
     */
    public void deleteMagazine(String id) {
        try {
            Unirest.delete(serverUrl + "/magazines/" + id).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     */
    /** 
     * @param id
     */
    public void deleteMedia(String id) {
        try {
            Unirest.delete(serverUrl + "/media/" + id).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // users

    /**
     * @param userId
     */
    /** 
     * @param userId
     */
    public void deleteUser(String userId) {
        try {

            Unirest.delete(serverUrl + "/users/" + userId).asString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param suspendedId
     */
    /** 
     * @param suspendedId
     */
    public void deleteSuspendedUser(String suspendedId) {
        try {
            Unirest.delete(serverUrl + "/suspended/" + suspendedId).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}