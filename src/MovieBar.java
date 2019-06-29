import uk.co.caprica.vlcj.player.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieBar extends Panel{
    private Button pauseButton;
    private Button rewindButton;
    private Button skipButton;
    private Button play;
    private Button stop;
    private String title;
    private Label label;
    private MovieBarListener movieBarListener;
    public  MovieBar(){
        super(2);
        this.setLayout(new FlowLayout());
        pauseButton=new Button(3);
        rewindButton=new Button(3);
        skipButton=new Button(3);
        play=new Button(3);
        stop=new Button(3);
        pauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.pause();
            }
        });
        skipButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.skip();
            }
        });
        rewindButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.rewind();
            }
        });
        stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieBarListener.close();
            }
        });
        pauseButton.setIcon(new ImageIcon("img\\pause.png"));
        skipButton.setIcon(new ImageIcon("img\\next.png"));
        rewindButton.setIcon(new ImageIcon("img\\back.png"));
        play.setIcon(new ImageIcon("img\\play.png"));
        stop.setIcon(new ImageIcon("img\\stop.png"));
        this.add(rewindButton);
        this.add(stop);
        this.add(play);
        this.add(pauseButton);
        this.add(skipButton);
    }
    public void setMovieBarListener(MovieBarListener movieBarListener) {
        this.movieBarListener = movieBarListener;
    }
}
