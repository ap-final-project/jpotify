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
    CenterPanelOrderable centerPanelOrderable;
    static Playlist currentPlayList;
    static Album currentAlbum;
    JPanel songs=new JPanel();
    public CenterSongs(CenterPanelOrderable centerPanelOrderable) {
        super(2);
        this.centerPanelOrderable=centerPanelOrderable;
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
        centerPanelOrderable.makeUI(MusicPlayer.currentPlaylist.guis);
        songs=centerPanelOrderable.panel;
        Label temp=new Label(2);
        temp.setPreferredSize(new Dimension(24,24));
//        temp.setIcon(new ImageIcon("menu1.png"));
//        temp.setVisible(false);
//        p.add(temp);
        p.setLayout(new GridLayout(1, 5));
        p.add(title);
        p.add(artist);
        p.add(album);
        p.add(year);
        p.add(time);
//        p.add(test);
        p.setPreferredSize(new Dimension(800,20));
        this.add(equalizer);
        this.add(p);
        songs.add(Box.createVerticalGlue());
        this.add(songs);
    }

    @Override
    public void addGui(SongGUI songGUI) {
        addToPanel(songGUI);
        songs.add(Box.createVerticalGlue());
        revalidate();
    }

    public void addToPanel(SongGUI songGUI){
        centerPanelOrderable.add(songGUI);
    }
    @Override
    public void sendValues(short[] V) {
        equalizer.setValues(V);
    }

    public void showPlayList(Playlist playlist) {
        currentPlayList = playlist;
        songs=centerPanelOrderable.changePlayList(playlist.guis);
    }

    public void showAlbum(Album album) {
        currentAlbum = album;
        currentPlayList=album.getAlbumPL();
        songs=centerPanelOrderable.changePlayList(album.getGuis());
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

