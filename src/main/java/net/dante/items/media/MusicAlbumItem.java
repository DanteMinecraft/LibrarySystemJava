package net.dante.items.media;

import net.dante.items.LibraryItem;

public class MusicAlbumItem extends LibraryItem{

    private String artist;

    public MusicAlbumItem(String artist, String title, boolean isAvailable) {
        super(title, isAvailable);
        this.artist = artist;
    }
}
