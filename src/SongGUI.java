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
    Label more;
    Song song;
    JCheckBox checkBox;
    public SongGUI(Song song) {
        super(2);
        Color bright=new Color(194,194,194);
        Color dark1=new Color(24,24,24);
        Color dark2=new Color(18,18,18);
        Color dark3=new Color(40,40,40);
        this.setPreferredSize(new Dimension(800,20));
        checkBox=new JCheckBox();
        this.song = song;
        more=new Label(2);
        more.setIcon(new ImageIcon("img\\more24.png"));
        name = new Label(song.title, 2);
        artist = new Label(song.artist, 2);
        album = new Label(song.album, 2);
        date = new Label(song.year, 2);
        time = new Label(song.lenght, 2);
        this.setLayout(new GridLayout(1,5));
        checkBox.setBackground(dark2);
//        this.add(checkBox);
        this.add(name);
        this.add(artist);
        this.add(album);
        this.add(date);
        this.add(time);
        this.add(more);
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.white));
    }
}