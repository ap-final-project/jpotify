import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SongGUI extends Panel{
    Label artist;
    Label date;
    Label name;
    Song song;
    public SongGUI(Song song) {
        super(2);
        this.song=song;
        artist=new Label(song.artist,2);
        name=new Label(song.title,2);
        date=new Label(song.year,2);
        this.setLayout(new FlowLayout());
        this.add(artist);
        this.add(name);
        this.add(date);
    }
}
