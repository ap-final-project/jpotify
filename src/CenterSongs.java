import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class CenterSongs extends Panel implements AddPlaylistListener{
    JList songJList;
    ArrayList<Song> songs=new ArrayList<>();
    DefaultListModel<String> songListModel;

    public CenterSongs() throws IOException, JavaLayerException {
        super(2);
        GridLayout gridLayout=new GridLayout(0,4);
        this.setAlignmentX(10);
        this.setAlignmentY(10);
        this.setLayout(gridLayout);
        songListModel = new DefaultListModel<>();
        this.setVisible(true);
    }

    @Override
    public void addToPlayList(String path) {
    //
    }
}
