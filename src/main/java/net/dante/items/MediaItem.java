package net.dante.items;

public class MediaItem extends LibraryItem {

    private String type;

    private String genre;
    private String artist;
    private int age;
    private int minutes;

    public MediaItem(String title, boolean isAvailable, String type, String genre, String artist, int age,
            int minutes) {
        super(title, isAvailable);
        this.age = age;
        this.artist = artist;
        this.genre = genre;
        this.minutes = minutes;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Typ: " + type + "\nTitel: " + getTitle() + "\nGenre: " + genre + "\nArtist: " + artist
                + "\nÅldersgräns: " + age + "\nLängd: " + minutes + " minuter" + "\nFinns tillgänglig: " + isAvailable()
                + "\n";
    }
}