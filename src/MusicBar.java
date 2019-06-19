import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TextUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicBar extends Panel {
    Button play;
    Button pause;
    Button stop;
    Button next;
    Button previous;
    Button shuffle;
    Button repeat;
    Button like;
    Song song;
    Label songName;
    Label artistName;
    Label album;
    Label year;
    Label icon;
    Panel info;
    progress progressBar;
    musicBarListener musicBarL;
    MusicPlayer musicPlayer=new MusicPlayer();
    public MusicBar() {
        super(3);
        this.setLayout(new BorderLayout());
        info=new Panel(3);
        info.setLayout(new GridLayout(4,1));
        Panel up=new Panel(3);
        progressBar=new progress();
        this.add(info,BorderLayout.WEST);
        play = new Button(3);
        pause = new Button(3);
        stop = new Button(3);
        next = new Button(3);
        previous = new Button(3);
        repeat=new Button(3);
        shuffle=new Button(3);
        like=new Button(3);
        play.setIcon(new ImageIcon("img\\play.png"));
        pause.setIcon(new ImageIcon("img\\pause.png"));
        previous.setIcon(new ImageIcon("img\\back.png"));
        next.setIcon(new ImageIcon("img\\next.png"));
        shuffle.setIcon(new ImageIcon("img\\shuffle.png"));
        repeat.setIcon(new ImageIcon("img\\repeat.png"));
        like.setIcon(new ImageIcon("img\\emptyHeart.png"));
        next.setSize(10,10);
        FlowLayout flowLayout=new FlowLayout();
        flowLayout.setHgap(30);
        up.setLayout(flowLayout);
        up.add(like);
        up.add(shuffle);
        up.add(previous);
        up.add(play);
        up.add(next);
        up.add(repeat);
        Panel keke=new Panel(3);
        keke.setBorder(new EmptyBorder(10,10,10,10));
        keke.setLayout(new BorderLayout());
        keke.add(up,BorderLayout.PAGE_START);
        progressBar.setBorder(new EmptyBorder(10,0,0,0));
        keke.add(progressBar, BorderLayout.PAGE_END);
        this.add(keke,BorderLayout.CENTER);
        this.add(info,BorderLayout.WEST);
    this.setListeners();
    }
    public void addInfo(){
        info.add(songName);
        info.add(artistName);
        info.add(album);
        info.add(year);
    }

    public void setMusicBarL(musicBarListener L){
        musicBarL=L;
    }
public void setListeners(){
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarL.action(0);
            }
        });
        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarL.action(1);
            }
        });
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarL.action(2);
            }
        });
        previous.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarL.action(3);
            }
        });
        like.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musicBarL.action(4);
            }
        });
    }
}
