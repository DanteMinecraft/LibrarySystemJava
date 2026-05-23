package net.dante.items.specific;

import net.dante.items.LibraryItem;

public class BookItem extends LibraryItem {

    private String author;
    private String genre;
    private int pages;

    public BookItem(String title, boolean isAvailable, String author, String genre, int pages) {
        super(title, isAvailable);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Författare: " + author + "\nGenre: " + genre + "\nAntal sidor: " + pages + "\nID i system: " + getId()
                + "\nTitel: " + getTitle() + "\nFinns tillgänglig: " + isAvailable() + "\n";
    }
}