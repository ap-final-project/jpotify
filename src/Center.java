import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Center extends JPanel implements AddPlaylistListener{
    JPanel m;
    JLabel topLabel;
    JList titleList,albumList,artistList,yearList;
    ArrayList<Song> songs=new ArrayList<>();
    DefaultListModel<String> year;
    DefaultListModel<String> title;
    DefaultListModel<String> album;
    DefaultListModel<String> artist;

    public Center() throws FileNotFoundException, JavaLayerException {
        m=new JPanel();
        m.setLayout(new GridLayout(1,4));
        topLabel=new JLabel("Your songs");
//        Song song=new Song("music4.mp3");
//        Song song1=new Song("music5.mp3");
        title = new DefaultListModel<>();
        album = new DefaultListModel<>();
        artist = new DefaultListModel<>();
        year = new DefaultListModel<>();
        title.addElement("title");
        artist.addElement("artist");
        album.addElement("album");
        year.addElement("year");
//        songs.add(song);
//        songs.add(song1);
        this.setLayout(new BorderLayout());
        titleList=new JList((ListModel) title);
        albumList=new JList((ListModel) album);
        artistList=new JList((ListModel) artist);
        yearList=new JList((ListModel) year);
        m.add(titleList);
        m.add(albumList);
        m.add(artistList);
        m.add(yearList);
        this.add(m,BorderLayout.CENTER);
        this.add(topLabel,BorderLayout.PAGE_START);
        this.setVisible(true);
    }

    @Override
    public void addToPlayList(String path) {
        try {
            Song s=new Song(path);
            songs.add(s);
                title.addElement(s.title);
                album.addElement(s.album);
                artist.addElement(s.artist);
                year.addElement(s.year);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
