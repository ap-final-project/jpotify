import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SongGUI extends Panel{
    Label artist;
    Label date;
    Label name;
    Label album;
    Label time;
//    JList jList;
//    DefaultListModel<String> songListModel;
    Song song;
    public SongGUI(Song song) {
        super(2);
//        jList=new JList(songListModel);
//        jList=new JList();
        this.song=song;

        name=new Label(song.title,2);
        artist=new Label(song.artist,2);
        album=new Label(song.album,2);
        date=new Label(song.year,2);
        time=new Label(song.lenght,2);

        this.setLayout(new GridLayout(0,5));
//        this.add(title);
        this.add(name);
        this.add(artist);
        this.add(album);
        this.add(date);
        this.add(time);
//        name.setBorder(new EmptyBorder(2,2,2,2));
//        this.setSize(200,120);
    }
}