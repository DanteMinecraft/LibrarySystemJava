package net.dante.items.media;

import net.dante.items.LibraryItem;

public class MovieItem extends LibraryItem{

    private String genre;
    private int minutes;

    public MovieItem(String genre, int minutes, String title, boolean isAvailable) {
        super(title, isAvailable);
        this.genre = genre;
        this.minutes = minutes;
    }
}
