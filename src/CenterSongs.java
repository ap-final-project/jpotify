import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
public class CenterSongs extends Panel{
    JList songJList;
    ArrayList<Song> songs=new ArrayList<>();
    DefaultListModel<String> songListModel;

    public CenterSongs() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
        GridLayout gridLayout=new GridLayout(10,0);
        this.setAlignmentX(10);
        this.setAlignmentY(10);
        this.setLayout(gridLayout);
        songListModel = new DefaultListModel<>();
        Label label=new Label(1);
        Label label1=new Label(1);
        Label label2=new Label(1);
        Label label3=new Label(1);
        Panel panel=new Panel(new GridLayout(0,4),3);
        label.setText("Title");
        label1.setText("Artist");
        label2.setText("Album");
        label3.setText("Year");
        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        this.add(panel);
        this.setVisible(true);
    }

}
