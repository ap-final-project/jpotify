import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Center extends Panel {
    Label title;
    Scroll scrollpane;
    CenterSongs centerSongs;

    public Center(CenterSongs centerSongs) throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
        this.centerSongs=centerSongs;
        this.setLayout(new BorderLayout());
        title=new Label("your songs",2);
        this.add(title,BorderLayout.PAGE_START);
        scrollpane=new Scroll(centerSongs);
        this.add(scrollpane);
        centerSongs.setVisible(true);
    }
    }

