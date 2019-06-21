import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.BorderFactory.createLineBorder;

public class MainPage extends JFrame {
    private final String TITLE = "Kian & Pariya Jpotify";
    private final int HEIGHT = 500, WIDTh = 500;
    LeftBar leftBar = new LeftBar();
    Panel main = new Panel(2);
    Center center=new Center();
    MusicBar musicBar = new MusicBar();
    CenterSongs centerSongs =new CenterSongs();
    public MainPage() throws IOException, JavaLayerException{
        this.setTitle(TITLE);
        Color bright =new Color(194,194,194);
        Color dark=new Color(24,24,24);
        this.setBackground(bright);
        this.setForeground(dark);
        this.setSize(HEIGHT, WIDTh);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(new BorderLayout());
        main.add(leftBar, BorderLayout.WEST);
        main.add(musicBar, BorderLayout.PAGE_END);
        leftBar.setListener(centerSongs);
        main.add(center,BorderLayout.CENTER);
        this.setVisible(true);
        this.add(main);
    }
}
