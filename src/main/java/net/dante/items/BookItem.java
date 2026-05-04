package net.dante.items;

/*En child-klass som extendar LibraryItem som innehåller alla gemensamma variabler, dvs detta är en subklass.
Denna klass innehåller även variabler och datan för BookItem, dvs alla böcker i systemet*/

import net.dante.LibraryItem;

public class BookItem extends LibraryItem{

    private String author;
    private String genre;
    private int pages;

    public BookItem(String id, String title, boolean isAvailable, String author, String genre, int pages) {
        super(id, title, isAvailable);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

}
