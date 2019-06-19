import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
public class CenterSongs extends Panel implements addGUIToCenter{
    JList songJList;
    ArrayList<Song> songs=new ArrayList<>();
    DefaultListModel<String> songListModel;

    public CenterSongs()  throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
        GridLayout gridLayout=new GridLayout(0,1);
        this.setAlignmentX(10);
        this.setAlignmentY(10);
        this.setLayout(gridLayout);
        songListModel = new DefaultListModel<>();
        this.setVisible(true);
    }


    @Override
    public void addGui(SongGUI songGUI){
        this.add(songGUI);
        System.out.println(songGUI.song.title);
        revalidate();
    }
}
