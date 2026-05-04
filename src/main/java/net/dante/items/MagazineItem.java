package net.dante.items;

/*En child-klass som extendar LibraryItem som innehåller alla gemensamma variabler, dvs detta är en subklass.
Denna klass innehåller även variabler och datan för MagazineItem, dvs alla magasin i systemet*/

import net.dante.LibraryItem;

public class MagazineItem extends LibraryItem{

    private int issueNumber;
    private int publishedYear;
    private String category;

    public MagazineItem(String id, String title, boolean isAvailable, int issueNumber, int publicationYear, String category) {
        super(id, title, isAvailable);
        this.issueNumber = issueNumber;
        this.publishedYear = publicationYear;
        this.category = category;
    }

    
    
}
