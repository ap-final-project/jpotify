import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Center extends Panel implements addGUIToCenter{
    Label title;
    Scroll scrollpane;
    CenterSongs centerSongs=new CenterSongs();

    public Center() throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        super(2);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
//        UIManager.put("",);
        this.setLayout(new BorderLayout());
        title=new Label("your songs",2);
        this.add(title,BorderLayout.PAGE_START);
        scrollpane=new Scroll(centerSongs);
        this.add(scrollpane);
        centerSongs.setVisible(true);
//        repaint();
    }

    @Override
    public void addGui(SongGUI songGUI){
           centerSongs.add(songGUI);
            System.out.println(songGUI.song.title);
//            repaint();
            revalidate();
    }
    }

