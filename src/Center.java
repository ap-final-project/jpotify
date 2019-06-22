import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Center extends Panel implements MakeVisibilityTrue{
    Label title;
    Scroll scrollpane;
    CenterSongs centerSongs;
    CenterPlayLists centerPlayLists;

    public Center(CenterSongs centerSongs,CenterPlayLists centerPlayLists) throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
        this.centerPlayLists=centerPlayLists;
        this.centerSongs=centerSongs;
        centerPlayLists=new CenterPlayLists();
        this.setLayout(new BorderLayout());
        title=new Label("your songs",2);
        this.add(title,BorderLayout.PAGE_START);
        scrollpane=new Scroll(centerSongs);
        this.add(scrollpane);
        centerSongs.setVisible(true);
        this.setVisible(true);
    }

    @Override
    public void makeTrue(int i) {
        switch (i){
            case 0:
                scrollpane.setPanel(centerSongs);
                centerSongs.setVisible(true);
                break;
            case 1:
                scrollpane.setPanel(centerPlayLists);
                centerPlayLists.setVisible(true);
                break;
        }
    }
}

