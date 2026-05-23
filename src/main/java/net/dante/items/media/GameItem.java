package net.dante.items.media;

import net.dante.items.LibraryItem;

public class GameItem extends LibraryItem {

    private String genre;
    private int age;

    public GameItem(int age, String genre, String title, boolean isAvailable) {
        super(title, isAvailable);
        this.age = age;
        this.genre = genre;
    }
}