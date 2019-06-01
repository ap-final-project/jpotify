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
    private final String TITLE="Kian Jpotify";
    private final int HEIGHT = 500,WIDTh=500;
    LeftBar leftBar=new LeftBar();
    MusicBar musicBar=new MusicBar();
    JPanel main=new JPanel();
    public MainPage(){
        this.setTitle(TITLE);
        this.setSize(HEIGHT,WIDTh);
        this.setLocation(500,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leftBar.setBorder(createLineBorder(Color.white,2));
        main.setLayout(new BorderLayout());
        main.add(leftBar,BorderLayout.WEST);
        main.add(musicBar,BorderLayout.PAGE_END);
        this.add(main);
        this.setVisible(true);
//        this.pack();
    }
}
