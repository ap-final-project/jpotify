import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CenterSongs extends Panel implements addGUIToCenter, InformEqualizer {
    Label title = new Label(2);
    Label artist = new Label(2);
    Label album = new Label(2);
    Label year = new Label(2);
    Label time = new Label(2);
    Label test = new Label("More",2);
//    Label test2 = new Label(2);
    Panel p = new Panel(3);
    SpringLayout layout;
    Equalizer equalizer;
    static Playlist currentPlayList;
    static Album currentAlbum;
    public CenterSongs() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
        equalizer = new Equalizer();
        layout = new SpringLayout();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
//        this.setVisible(true);
        this.setPreferredSize(new Dimension());
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
        p.setLayout(new GridLayout(1, 6));
//        this.setPreferredSize(new Dimension());
//        layout.putConstraint(SpringLayout.NORTH, equalizer, 40, SpringLayout.NORTH, this);
//        layout.putConstraint(SpringLayout.NORTH, p, 10, SpringLayout.SOUTH, equalizer);
//        layout.putConstraint(SpringLayout.WEST, p, 20, SprixngLayout.WEST, this);
//        layout.putConstraint(SpringLayout.EAST, p, 20, SpringLayout.EAST, this);
        p.add(title);
        p.add(artist);
        p.add(album);
        p.add(year);
        p.add(time);
        p.add(test);
//        p.add(test2);
        this.add(equalizer);
        this.add(p);
        System.out.println(this.getComponents().length);
    }

    @Override
    public void addGui(SongGUI songGUI) {
//        Component component = this.getComponent(this.getComponents().length - 1);
//        if (component instanceof SongGUI) {
//            SongGUI componentGUI = (SongGUI) component;
//            componentGUI.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(24, 24, 24)));
//        }
//        layout.putConstraint(SpringLayout.NORTH, songGUI, 10, SpringLayout.SOUTH, component);
//        layout.putConstraint(SpringLayout.WEST, songGUI, 20, SpringLayout.WEST, this);
//        layout.putConstraint(SpringLayout.EAST, songGUI, 20, SpringLayout.EAST, this);
        this.add(songGUI);

        revalidate();
    }


    @Override
    public void sendValues(short[] V) {
        equalizer.setValues(V);
    }

    public void showPlayList(Playlist playlist) {
        currentPlayList = playlist;
        int comps = this.getComponents().length;
        for (int i = comps - 1; i > 1; i--) {
            this.remove(this.getComponents().length - 1);
        }
        for (SongGUI songGUI : currentPlayList.guis) {
            addGui(songGUI);
        }
    }

    public void showAlbum(Album album) {
        currentAlbum = album;
        int comps = this.getComponents().length;
        for (int i = comps - 1; i > 1; i--) {
            this.remove(this.getComponents().length - 1);
        }
        for (SongGUI songGUI : currentAlbum.getGuis()) {
            addGui(songGUI);
        }
    }

}

