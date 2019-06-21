import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Center extends Panel {
    Label title;
    Scroll scrollpane;
    public Center() throws IOException, JavaLayerException{
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
        CenterSongs centerSongs=new CenterSongs();
        centerSongs.setVisible(true);
        scrollpane=new Scroll(centerSongs);
    this.add(scrollpane);
    }
}
