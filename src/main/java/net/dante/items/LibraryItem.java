package net.dante.items;

/*En parent-klass för BookItem och MagazineItem, dvs superklassen.
Innehåller alla gemensamma parametrar och variabler för klasserna.
Klassen är en abstract class för att se till att inga objekt som är endast LibraryItem:s skapas,
utan ett LibraryItem måste vara någon av child-klasserna (dvs BookItem eller MagazineItem).*/

public abstract class LibraryItem implements Comparable<LibraryItem>{

    // Variabels
    private String id;
    private String title;
    private boolean isAvailable;

    // Constructor for LibraryItem
    public LibraryItem(String title, boolean isAvailable) {
        this.title = title;
        this.isAvailable = isAvailable;
    }

    /** 
     * @return String
     */
    // Getters
    public String getId() {
        return id;
    }

    /** 
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /** 
     * @return boolean
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /** 
     * @param isAvailable
     */
    // Setters

    // Planned to be linked to borrowItem() and returnItem() in the future
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /** 
     * @param other
     * @return int
     */
    @Override
    public int compareTo(LibraryItem other) {
        return this.title.compareToIgnoreCase(other.title);
    }

}