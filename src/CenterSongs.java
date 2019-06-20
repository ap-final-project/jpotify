import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
public class CenterSongs extends Panel implements addGUIToCenter{
    Label title=new Label(2);
    Label artist=new Label(2);
    Label album=new Label(2);
    Label year=new Label(2);
    Label time=new Label(2);
    Panel p=new Panel(3);
    public CenterSongs()  throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
        this.setVisible(true);
        title.setText("Title");
        artist.setText("Artist");
        album.setText("Album");
        year.setText("Year");
        time.setText("Time");
        p.setLayout(new GridLayout(1,5));
        this.setPreferredSize(new Dimension());
        p.add(title);
        p.add(artist);
        p.add(album);
        p.add(year);
        p.add(time);
        this.add(p);
    }
    @Override
    public void addGui(SongGUI songGUI){
        this.add(songGUI);
        revalidate();
    }
}
