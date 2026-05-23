package net.dante;

import net.dante.client.GsonHandler;
import net.dante.client.LibraryManager;
import net.dante.client.StartMenu;

/*Main-klassen för projektet. Den här klassen gör inte mycket i sig självt, utan är endast kopplad
till andra klasser som hanterar programmets logik */

public class Main {
    void main() {

        GsonHandler gsonHandler = new GsonHandler("http://localhost:3000");
        LibraryManager manager = new LibraryManager(gsonHandler);
        StartMenu menu = new StartMenu(manager);

        while (true) {
            menu.ShowMenu();
        }
    }
}