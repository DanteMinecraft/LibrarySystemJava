package net.dante.items;

public class MediaItem extends LibraryItem {

    private String type;

    private String genre;
    private String artist;
    private int age;
    private int minutes;

    public MediaItem(int age, String artist, String genre, int minutes, String type, String title, boolean isAvailable) {
        super(title, isAvailable);
        this.age = age;
        this.artist = artist;
        this.genre = genre;
        this.minutes = minutes;
        this.type = type;
    }
}