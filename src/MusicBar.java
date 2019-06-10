import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TextUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.FileNotFoundException;

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

    {
        try {
            song = new Song("music4.mp3");
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Label songName=new Label(song.title,3);
    Label artistName=new Label(song.artist,3);
    Label album=new Label(song.album,3);
    Label year=new Label(song.year,3);
    Label icon=new Label(3);
    progress progressBar;
    public MusicBar() {
        super(3);
        this.setLayout(new BorderLayout());
        Panel info=new Panel(3);
        info.setLayout(new GridLayout(4,1));
        info.add(songName);
        info.add(artistName);
        info.add(album);
        info.add(year);
        Panel up=new Panel(3);
//        up.setLayout(new GridLayout(1,5));
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
        stop.setIcon(new ImageIcon("img\\stop.png"));
        previous.setIcon(new ImageIcon("img\\back.png"));
        next.setIcon(new ImageIcon("img\\next.png"));
        shuffle.setIcon(new ImageIcon("img\\shuffle.png"));
        repeat.setIcon(new ImageIcon("img\\repeat.png"));
        like.setIcon(new ImageIcon("img\\emptyHeart.png"));
        next.setSize(10,10);
        FlowLayout flowLayout=new FlowLayout();
        flowLayout.setHgap(10);
        up.setLayout(new FlowLayout());

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
    }
}
