package net.dante;

/*En parent-klass för BookItem och MagazineItem, dvs superklassen.
Innehåller alla gemensamma parametrar och variabler för klasserna.
Klassen är en abstract class för att se till att inga objekt som är endast LibraryItem:s skapas,
utan ett LibraryItem måste vara någon av child-klasserna (dvs BookItem eller MagazineItem).*/

public abstract class LibraryItem {
    private String id;
    private String title;
    private boolean isAvailable;

    public LibraryItem(String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }
}
