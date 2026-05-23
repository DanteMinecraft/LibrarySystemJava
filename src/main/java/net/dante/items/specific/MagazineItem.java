package net.dante.items.specific;

import net.dante.items.LibraryItem;

public class MagazineItem extends LibraryItem{

    private int issueNumber;
    private int publishedYear;
    private String category;

    public MagazineItem(String title, boolean isAvailable, int issueNumber, int publicationYear, String category) {
        super(title, isAvailable);
        this.issueNumber = issueNumber;
        this.publishedYear = publicationYear;
        this.category = category;
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Utgåva: " + issueNumber + "\nPubliceringsår: " + publishedYear + "\nKategori: "
                + category + "\nID i system: " + getId() + "\nTitel: " + getTitle() + "\nFinns tillgänglig: " + isAvailable() + "\n";
    }
}