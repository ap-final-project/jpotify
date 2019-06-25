import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CenterSongs extends Panel implements addGUIToCenter, InformEqualizer ,Scrollable{
    Label title = new Label(2);
    Label artist = new Label(2);
    Label album = new Label(2);
    Label year = new Label(2);
    Label time = new Label(2);
    Panel p = new Panel(2);
    Equalizer equalizer;
    static Playlist currentPlayList;
    static Album currentAlbum;
    Panel songs=new Panel(2);
    public CenterSongs() {
        super(2);
        equalizer = new Equalizer();
        this.setLayout(new WrapLayout(WrapLayout.CENTER));
        title.setText("Title");
        artist.setText("Artist");
        album.setText("Album");
        year.setText("Year");
        time.setText("Time");
        artist.setFont(new Font("Cambria", Font.BOLD, 14));
        album.setFont(new Font("Cambria", Font.BOLD, 14));
        time.setFont(new Font("Cambria", Font.BOLD, 14));
        year.setFont(new Font("Cambria", Font.BOLD, 14));
        title.setFont(new Font("Cambria", Font.BOLD, 14));
        songs.setLayout(new BoxLayout(songs,1));
        songs.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        p.setLayout(new GridLayout(1, 5));
        p.add(title);
        p.add(artist);
        p.add(album);
        p.add(year);
        p.add(time);
//        p.add(test);
        p.setPreferredSize(new Dimension(800,20));
        this.add(equalizer);
        songs.add(p);
        songs.add(Box.createVerticalGlue());
        this.add(songs);
    }

    @Override
    public void addGui(SongGUI songGUI) {
        songs.add(songGUI);
        songs.add(Box.createVerticalGlue());
        revalidate();
    }


    @Override
    public void sendValues(short[] V) {
        equalizer.setValues(V);
    }

    public void showPlayList(Playlist playlist) {
        currentPlayList = playlist;

        int comps = songs.getComponents().length;
        for (int i = comps - 1; i > 1; i--) {
            songs.remove(songs.getComponents().length - 1);
        }
        for (SongGUI songGUI : currentPlayList.guis) {
            addGui(songGUI);
        }
    }

    public void showAlbum(Album album) {
        currentAlbum = album;
        int comps = songs.getComponents().length;
        for (int i = comps - 1; i > 1; i--) {
            songs.remove(songs.getComponents().length - 1);
        }
        for (SongGUI songGUI : currentAlbum.getGuis()) {
            addGui(songGUI);
        }
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}

