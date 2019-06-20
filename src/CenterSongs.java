import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
public class CenterSongs extends Panel implements addGUIToCenter{
    DefaultListModel<String> songListModel;
    Label title=new Label(2);
    Label artist=new Label(2);
    Label album=new Label(2);
    Label year=new Label(2);
    Label time=new Label(2);
    Panel p=new Panel(3);

    public CenterSongs()  throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
//        this.setAlignmentX(10);
//        this.setAlignmentY(10);
        this.setLayout(new GridLayout(60,0));
//        GridBagConstraints c=new GridBagConstraints();
//        c.weighty=9;
//        c.weightx=1;
        songListModel = new DefaultListModel<>();
        this.setVisible(true);
        title.setText("Title");
        artist.setText("Artist");
        album.setText("Album");
        year.setText("Year");
        time.setText("Time");
        p.setLayout(new GridLayout(1,5));
        this.setPreferredSize(new Dimension());
//        this.setMaximumSize(new Dimension(200,200));
        p.add(title);
        p.add(artist);
        p.add(album);
        p.add(year);
        p.add(time);
        this.add(p);
//        this.setBounds(10,10,10,10);
//        p.setLocation(200,200);

    }


    @Override
    public void addGui(SongGUI songGUI){
        this.add(songGUI);
        System.out.println(songGUI.song.title);
        revalidate();
    }
}
