import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.Border;
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
        icon.setIcon(new ImageIcon("plus.png"));
        Panel image=new Panel(3);
        image.add(icon);
        Panel up=new Panel(3);
        up.setLayout(new GridLayout(1,5));
        progressBar=new progress();
//        progressBar.setValue(54);
//        progressBar.setStringPainted(false);
//        progressBar.setPreferredSize(new Dimension(300,10));

        this.add(info,BorderLayout.WEST);
        play = new Button(3);
        pause = new Button(3);
        stop = new Button(3);
        next = new Button(3);
        previous = new Button(3);
//        play.setBorder(BorderFactory.createEmptyBorder());
//        pause.setBorder(BorderFactory.createEmptyBorder());
//        previous.setBorder(BorderFactory.createEmptyBorder());
//        next.setBorder(BorderFactory.createEmptyBorder());
//        stop.setBorder(BorderFactory.createEmptyBorder());
        play.setIcon(new ImageIcon("img\\play.png"));
        pause.setIcon(new ImageIcon("img\\pause.png"));
        stop.setIcon(new ImageIcon("img\\stop.png"));
        previous.setIcon(new ImageIcon("back.png"));
        next.setIcon(new ImageIcon("img\\next.png"));
        next.setSize(10,10);

        up.add(previous);
//        up.add(pause);
        up.add(play);
//        up.add(stop);
        up.add(next);
        Panel keke=new Panel(3);
        keke.setLayout(new BorderLayout());
        keke.add(up,BorderLayout.PAGE_START);
        keke.add(progressBar, BorderLayout.PAGE_END);
        up.setBackground(dark3);
//        this.add(image,BorderLayout.CENTER);
        this.add(keke,BorderLayout.CENTER);
        this.add(info,BorderLayout.WEST);
    }
}
