import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Arrays;

import static javax.swing.BorderFactory.createLineBorder;

public class MainPage extends JFrame {
    private final String TITLE = "Kian & Pariya Jpotify";
    private final int HEIGHT = 500, WIDTh = 500;
    LeftBar leftBar = new LeftBar();
    Panel main = new Panel();
    MusicBar musicBar = new MusicBar();

    public MainPage() {
        this.setTitle(TITLE);
        Color bright =new Color(194,194,194);
        Color dark=new Color(24,24,24);
        this.setBackground(bright);
        this.setForeground(dark);
        this.setSize(HEIGHT, WIDTh);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        leftBar.setBorder(createLineBorder(Color.white, 2));
        main.setLayout(new BorderLayout());
        main.add(leftBar, BorderLayout.WEST);
        main.add(musicBar, BorderLayout.PAGE_END);
        this.add(main);
        this.setVisible(true);
//        this.pack();
    }
}
