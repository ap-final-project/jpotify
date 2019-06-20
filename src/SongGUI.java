import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SongGUI extends Panel {
    Label artist;
    Label date;
    Label name;
    Label album;
    Label time;
    Song song;

    public SongGUI(Song song) {
        super(2);
        this.song = song;
        name = new Label(song.title, 2);
        artist = new Label(song.artist, 2);
        album = new Label(song.album, 2);
        date = new Label(song.year, 2);
        time = new Label(song.lenght, 2);
        this.setLayout(new GridLayout(1,5));
        this.add(name);
        this.add(artist);
        this.add(album);
        this.add(date);
        this.add(time);
    }
}